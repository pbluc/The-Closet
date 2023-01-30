package columbia.pbluc.thecloset;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import columbia.pbluc.thecloset.databinding.ActivityMainBinding;
import columbia.pbluc.thecloset.fragments.CalendarFragment;
import columbia.pbluc.thecloset.fragments.ClosetFragment;
import columbia.pbluc.thecloset.fragments.MeasurementsFragment;
import columbia.pbluc.thecloset.fragments.OutfitsFragment;
import columbia.pbluc.thecloset.fragments.PackingListsFragment;

public class MainActivity extends AppCompatActivity {

  ActivityMainBinding binding;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    binding = ActivityMainBinding.inflate(getLayoutInflater());
    setContentView(binding.getRoot());
    replaceFragment(new ClosetFragment());

    binding.bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
      switch (item.getItemId()) {
        case R.id.closet:
          replaceFragment(new ClosetFragment());
          break;
        case R.id.outfits:
          replaceFragment(new OutfitsFragment());
          break;
        case R.id.calendar:
          replaceFragment(new CalendarFragment());
          break;
        case R.id.packingLists:
          replaceFragment(new PackingListsFragment());
          break;
        case R.id.measurements:
          replaceFragment(new MeasurementsFragment());
          break;
      }
      return true;
    });
  }

  private void replaceFragment(Fragment fragment) {
    FragmentManager fragmentManager = getSupportFragmentManager();
    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
    fragmentTransaction.replace(R.id.frameLayout, fragment);
    fragmentTransaction.commit();
  }
}