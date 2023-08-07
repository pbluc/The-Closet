package columbia.pbluc.thecloset;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.ImageButton;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ImportClosetItemActivity extends AppCompatActivity {

  private static final String TAG = "ImportClosetItemActivit";

  private ArrayList<Uri> selectedImageUris;

  private FirebaseFirestore firebaseFirestore;
  private FirebaseStorage firebaseStorage;
  private FirebaseAuth firebaseAuth;

  private ImageSlider imageSlider;
  private ImageButton ibReselect;
  private ImageButton ibFinish;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_import_closet_item);

    firebaseFirestore = FirebaseFirestore.getInstance();
    firebaseStorage = FirebaseStorage.getInstance();
    firebaseAuth = FirebaseAuth.getInstance();

    imageSlider = findViewById(R.id.imageSlider);
    ibReselect = findViewById(R.id.ibReselect);
    ibFinish = findViewById(R.id.ibFinish);

    selectedImageUris = (ArrayList<Uri>) getIntent().getSerializableExtra("selectedImageUris");
    ArrayList<SlideModel> imageList = new ArrayList<>(); // Create image list

    for(Uri imageUri : selectedImageUris) {
      Log.d(TAG, imageUri.toString());
      imageList.add(new SlideModel(imageUri.toString(), ScaleTypes.CENTER_CROP));
    }

    imageSlider.setImageList(imageList);

    ibReselect.setOnClickListener(view -> goBackToCloset());
    ibFinish.setOnClickListener(view -> uploadImagesToFirestore());
  }

  private void goBackToCloset() {
    Intent i = new Intent(ImportClosetItemActivity.this, MainActivity.class);
    startActivity(i);
  }

  private void uploadImagesToFirestore() {
    FirebaseUser user = firebaseAuth.getCurrentUser();

    for (Uri imageUri: selectedImageUris) {
      Long currentTime = System.currentTimeMillis()/1000;
      String filename = currentTime + "_" + imageUri.getLastPathSegment();
      StorageReference newClosetItemRef = firebaseStorage.getReference().child(user.getEmail() + "/" + filename);

      newClosetItemRef.putFile(imageUri)
              .addOnSuccessListener(taskSnapshot -> {
                Map<String, Object> closetItem = new HashMap<>();
                closetItem.put("name", filename);
                closetItem.put("category", "");

                firebaseFirestore.collection("users").document(user.getEmail()).collection("closet")
                        .add(closetItem)
                        .addOnSuccessListener(documentReference -> {
                          Log.d(TAG, "New closet item successfully added to Firestore");
                          goBackToCloset();
                        })
                        .addOnFailureListener(e -> {
                          Log.e(TAG, "Error adding new closet item to Firestore", e);

                          // Delete the file uploaded to Storage
                          newClosetItemRef.delete()
                                  .addOnSuccessListener(unused -> {
                                    Log.d(TAG, "File successfully deleted from Storage");
                                  })
                                  .addOnFailureListener(e1 -> {
                                    Log.e(TAG, "Error deleting file from Storage", e);
                                  });
                        });
              })
              .addOnFailureListener(e -> {
                Log.e(TAG, "Error uploading new closet item to Storage", e);
              });
    }
  }

}