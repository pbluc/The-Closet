package columbia.pbluc.thecloset.fragments;

import static android.app.Activity.RESULT_OK;

import android.content.ClipData;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

import columbia.pbluc.thecloset.ImportClosetItemActivity;
import columbia.pbluc.thecloset.R;
import columbia.pbluc.thecloset.adapters.RecyclerViewAdapter;
import columbia.pbluc.thecloset.models.ClosetItem;

public class ClosetFragment extends Fragment {
  private static final String TAG = "ClosetFragment";

  private static final int SELECT_IMAGES = 1;

  private String currentCategory;

  private ArrayList<Uri> selectedImageUris;
  private ArrayList<ClosetItem> closetItems;

  private FirebaseFirestore firebaseFirestore;
  private FirebaseStorage firebaseStorage;
  private FirebaseAuth firebaseAuth;
  private FirebaseUser firebaseUser;
  private StorageReference storageRef;

  private ImageButton ibAddClosetItem;
  private ImageButton ibBackCategory;
  private ImageView ivUnselectItems;
  private ImageView ivDeleteItems;
  private Button btnTopsCategory;
  private Button btnBottomsCategory;
  private Button btnOnePiecesCategory;
  private Button btnDressesCategory;
  private Button btnShoesCategory;
  private Button btnUnderwearCategory;
  private Button btnBagsCategory;
  private Button btnHatsCategory;
  private Button btnOuterwearCategory;
  private Button btnGlovesCategory;
  private Button btnScarvesCategory;
  private Button btnTiesCategory;
  private Button btnSwimsuitsCategory;
  private Button btnBrasCategory;
  private LinearLayout linearLayoutClosetCategories;
  private LinearLayout linearLayoutTopsSubcategories;
  private LinearLayout linearLayoutBottomsSubcategories;

  private RecyclerView recyclerView;
  private RecyclerViewAdapter recyclerViewAdapter;


  public ClosetFragment() {
    // Required empty public constructor
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    return inflater.inflate(R.layout.fragment_closet, container, false);
  }

  @Override
  public void onViewCreated(View view, Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);

    firebaseFirestore = FirebaseFirestore.getInstance();
    firebaseStorage = FirebaseStorage.getInstance();
    firebaseAuth = FirebaseAuth.getInstance();
    firebaseUser = firebaseAuth.getCurrentUser();
    storageRef = firebaseStorage.getReference();

    currentCategory = getResources().getString(R.string.categories_all);;

    ibAddClosetItem = view.findViewById(R.id.imageButtonAddClosetItem);
    ibBackCategory = view.findViewById(R.id.imageButtonBackCategory);
    ivUnselectItems = view.findViewById(R.id.ivUnselectItems);
    ivDeleteItems = view.findViewById(R.id.ivDeleteItems);
    btnTopsCategory = view.findViewById(R.id.buttonTopsCategory);
    btnBottomsCategory = view.findViewById(R.id.buttonBottomsCategory);
    btnOnePiecesCategory = view.findViewById(R.id.buttonOnePiecesCategory);
    btnDressesCategory = view.findViewById(R.id.buttonDressesCategory);
    btnShoesCategory = view.findViewById(R.id.buttonShoesCategory);
    btnUnderwearCategory = view.findViewById(R.id.buttonUnderwearCategory);
    btnBagsCategory = view.findViewById(R.id.buttonBagsCategory);
    btnHatsCategory = view.findViewById(R.id.buttonHatsCategory);
    btnOuterwearCategory = view.findViewById(R.id.buttonOuterwearCategory);
    btnGlovesCategory = view.findViewById(R.id.buttonGlovesCategory);
    btnScarvesCategory = view.findViewById(R.id.buttonScarvesCategory);
    btnTiesCategory = view.findViewById(R.id.buttonTiesCategory);
    btnSwimsuitsCategory = view.findViewById(R.id.buttonSwimsuitsCategory);
    btnBrasCategory = view.findViewById(R.id.buttonBrasCategory);
    linearLayoutClosetCategories = view.findViewById(R.id.linearLayoutClosetCategories);
    linearLayoutTopsSubcategories = view.findViewById(R.id.linearLayoutTopsSubcategories);
    linearLayoutBottomsSubcategories = view.findViewById(R.id.linearLayoutBottomsSubcategories);
    recyclerView = view.findViewById(R.id.recyclerView);

    closetItems = new ArrayList<>();
    recyclerViewAdapter = new RecyclerViewAdapter(closetItems);
    recyclerView.setAdapter(recyclerViewAdapter);
    recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3));

    ibAddClosetItem.setOnClickListener(v -> openGallery());
    ibBackCategory.setOnClickListener(v -> goToMainCloset());
    ivUnselectItems.setOnClickListener(v -> unselectClosetItems());
    ivDeleteItems.setOnClickListener(v -> deleteClosetItems());
    btnBottomsCategory.setOnClickListener(v -> goToBottomsCategory());
    btnTopsCategory.setOnClickListener((v -> goToTopsCategory()));

    loadClosetItems();

    // TODO: Show unselect and delete closet items buttons only when selected item count is not zero, otherwise disable
  }

  private void loadClosetItems() {
    firebaseFirestore.collection("users").document(firebaseUser.getEmail()).collection("closet")
      .orderBy("timestamp", Query.Direction.ASCENDING)
      .get()
      .addOnSuccessListener(queryDocumentSnapshots -> {
        for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
          Log.d(TAG, documentSnapshot.getId() + " => " + documentSnapshot.getData());
          String imageFilename = (String) documentSnapshot.get("name");
          storageRef.child(firebaseUser.getEmail() + "/" + imageFilename).getDownloadUrl()
                  .addOnSuccessListener(uri -> {
                    ClosetItem closetItem = new ClosetItem(documentSnapshot.getId(), imageFilename, "", uri);
                    closetItems.add(0, closetItem);
                    recyclerViewAdapter.notifyItemInserted(0);
                    // TODO: Fix ordering
                  })
                  .addOnFailureListener(e -> {
                    Log.e(TAG, "Error retrieving download URL: ", e);
                  });
        }
      })
      .addOnFailureListener(e -> {
        Log.e(TAG, "Error getting documents: ", e);
      });
  }

  private void goToTopsCategory() {
    linearLayoutClosetCategories.setVisibility(View.GONE);
    linearLayoutBottomsSubcategories.setVisibility(View.GONE);
    linearLayoutTopsSubcategories.setVisibility(View.VISIBLE);

    ibBackCategory.setVisibility(View.VISIBLE);

    currentCategory = getResources().getString(R.string.categories_tops);
  }

  private void goToBottomsCategory() {
    linearLayoutClosetCategories.setVisibility(View.GONE);
    linearLayoutTopsSubcategories.setVisibility(View.GONE);
    linearLayoutBottomsSubcategories.setVisibility(View.VISIBLE);

    ibBackCategory.setVisibility(View.VISIBLE);

    currentCategory = getResources().getString(R.string.categories_bottoms);
  }

  private void deleteClosetItems() {
    for (ClosetItem closetItem : recyclerViewAdapter.getSelected()) {
      firebaseFirestore.collection("users").document(firebaseUser.getEmail()).collection("closet").document(closetItem.getDocumentId())
              .delete()
              .addOnSuccessListener(unused -> {
                Log.d(TAG, "Closet item document successfully deleted!");

                String imageFilename = closetItem.getImageFilename();
                int removedIndex = closetItems.indexOf(closetItem);
                closetItems.remove(closetItem);
                recyclerViewAdapter.notifyItemRemoved(removedIndex);

                storageRef.child(firebaseUser.getEmail() + "/" + imageFilename)
                        .delete()
                        .addOnSuccessListener(unused1 -> Log.d(TAG, "Closet item image file successfully deleted!"))
                        .addOnFailureListener(e -> Log.e(TAG, "Error deleting closet item image file", e));
              })
              .addOnFailureListener(e -> Log.e(TAG, "Error deleting document", e));
    }
  }

  private void unselectClosetItems() {
    for (ClosetItem closetItem : recyclerViewAdapter.getSelected()) {
      closetItem.setSelected(false);
      recyclerViewAdapter.notifyItemChanged(closetItems.indexOf(closetItem));
    }
  }

  private void goToMainCloset() {
    linearLayoutTopsSubcategories.setVisibility(View.GONE);
    linearLayoutBottomsSubcategories.setVisibility(View.GONE);
    linearLayoutClosetCategories.setVisibility(View.VISIBLE);

    ibBackCategory.setVisibility(View.GONE);

    currentCategory = getResources().getString(R.string.categories_all);
  }

  private void openGallery() {
    // Create an instance of the intent of the type image
    Intent intent = new Intent();
    intent.setType("image/*");
    // Allowing multiple images to be selected
    intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
    intent.setAction(Intent.ACTION_GET_CONTENT);
    startActivityForResult(Intent.createChooser(intent, getResources().getString(R.string.select_image)), SELECT_IMAGES);
  }

  @Override
  public void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);

    if (resultCode == RESULT_OK) {
      if (requestCode == SELECT_IMAGES && data != null) {
        selectedImageUris = new ArrayList<>();
        // Multiple images were selected
        if (data.getClipData() != null) {
          ClipData clipData = data.getClipData();
          int totalImages = clipData.getItemCount();
          for (int i = 0; i < totalImages; i++) {
            Uri imageUrl = clipData.getItemAt(i).getUri();
            selectedImageUris.add(imageUrl);
          }
        } else { // One image was selected
          Uri imageUrl = data.getData();
          selectedImageUris.add(imageUrl);
        }
        Log.d(TAG, "Size of selectedImageUris: "+ selectedImageUris.size());
        Intent i = new Intent(getActivity(), ImportClosetItemActivity.class);
        i.putExtra("selectedImageUris", selectedImageUris);
        startActivity(i);
      }
    }
  }
}