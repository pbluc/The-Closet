package columbia.pbluc.thecloset.fragments;

import static android.app.Activity.RESULT_OK;

import android.content.ClipData;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

import columbia.pbluc.thecloset.ImportClosetItemActivity;
import columbia.pbluc.thecloset.LoginActivity;
import columbia.pbluc.thecloset.R;

public class ClosetFragment extends Fragment {
  private static final String TAG = "ClosetFragment";

  private static final int SELECT_IMAGES = 1;

  private String currentCategory;

  private ArrayList<Uri> selectedImageUris;

  private FirebaseFirestore firebaseFirestore;
  private FirebaseAuth firebaseAuth;

  private ImageButton ibAddClosetItem;
  private ImageButton ibBackCategory;
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
  private RecyclerView.LayoutManager layoutManager;


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
    firebaseAuth = FirebaseAuth.getInstance();

    currentCategory = getResources().getString(R.string.categories_all);;

    ibAddClosetItem = view.findViewById(R.id.imageButtonAddClosetItem);
    ibBackCategory = view.findViewById(R.id.imageButtonBackCategory);
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
    layoutManager = new GridLayoutManager(getActivity(), 3);
    recyclerView.setLayoutManager(layoutManager);

    ibAddClosetItem.setOnClickListener(v -> openGallery());

    ibBackCategory.setOnClickListener(v -> goToMainCloset());
    btnBottomsCategory.setOnClickListener(v -> goToBottomsCategory());
    btnTopsCategory.setOnClickListener((v -> goToTopsCategory()));

    // TODO: Show view of all closet items
    loadClosetItems();
  }

  private void loadClosetItems() {
    FirebaseUser user = firebaseAuth.getCurrentUser();

    firebaseFirestore.collection("users").document(user.getEmail()).collection("closet")
      .whereEqualTo("category", "")
      .get()
      .addOnSuccessListener(queryDocumentSnapshots -> {
        for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
          Log.d(TAG, documentSnapshot.getId() + " => " + documentSnapshot.getData());
          String category = (String) documentSnapshot.get("category");
          if (category.equals("")) {
            String imageFilename = (String) documentSnapshot.get("name");

          }
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
        selectedImageUris = new ArrayList<Uri>();
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