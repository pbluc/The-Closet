package columbia.pbluc.thecloset;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;

import java.util.ArrayList;

public class ImportClosetItemActivity extends AppCompatActivity {

  private ImageSlider imageSlider;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_import_closet_item);

    ArrayList<SlideModel> imageList = new ArrayList<SlideModel>(); // Create image list
    imageList.add(new SlideModel(R.drawable.placeholder, ScaleTypes.CENTER_CROP));

    imageSlider = findViewById(R.id.imageSlider);
    imageSlider.setImageList(imageList);
  }
}