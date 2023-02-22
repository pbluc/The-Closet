package columbia.pbluc.thecloset;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import columbia.pbluc.thecloset.databinding.ActivityMainBinding;
import columbia.pbluc.thecloset.fragments.CalendarFragment;
import columbia.pbluc.thecloset.fragments.ClosetFragment;
import columbia.pbluc.thecloset.fragments.MeasurementsFragment;
import columbia.pbluc.thecloset.fragments.OutfitsFragment;
import columbia.pbluc.thecloset.fragments.PackingListsFragment;

public class MainActivity extends AppCompatActivity {
  private static final String TAG = "Main";

  private FirebaseAuth mAuth;

  ActivityMainBinding binding;

  private ImageButton ibLogout;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    binding = ActivityMainBinding.inflate(getLayoutInflater());
    setContentView(binding.getRoot());
    replaceFragment(new ClosetFragment());

    // Initialize Firebase Auth
    mAuth = FirebaseAuth.getInstance();

    ibLogout = findViewById(R.id.imageButtonLogout);

    ibLogout.setOnClickListener(v -> {
      logOut();
      goToLoginActivity();
    });

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

  private void goToLoginActivity() {
    Intent i = new Intent(this, LoginActivity.class);
    startActivity(i);
    finish();
  }

  private void logOut() {
    mAuth.signOut();
  }

  @Override
  protected void onStart() {
    super.onStart();
    // Check if user is signed in (non-null) and update UI accordingly
    FirebaseUser currentUser = mAuth.getCurrentUser();
    if (currentUser == null) {
      // Go to Login Activity
      goToLoginActivity();
    }
  }
}

