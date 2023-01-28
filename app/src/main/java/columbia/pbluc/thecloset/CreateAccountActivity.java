package columbia.pbluc.thecloset;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class CreateAccountActivity extends AppCompatActivity {
  private static final String TAG = "CreateAccount";

  private FirebaseAuth mAuth;

  private EditText etFullName;
  private EditText etEmail;
  private EditText etPassword;
  private EditText etReEnterPassword;
  private Button btnSignUp;
  private TextView tvLogin;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_create_account);

    // Initialize Firebase Auth
    mAuth = FirebaseAuth.getInstance();

    etFullName = findViewById(R.id.editTextFullName);
    etEmail = findViewById(R.id.editTextEmailAddress);
    etPassword = findViewById(R.id.editTextPassword);
    etReEnterPassword = findViewById(R.id.editTextReEnterPassword);
    btnSignUp = findViewById(R.id.buttonSignUp);
    tvLogin = findViewById(R.id.textViewLogin);

    btnSignUp.setOnClickListener(v -> {
      String email = etEmail.getText().toString().trim();
      String password = etPassword.getText().toString().trim();
      String reenterPassword = etReEnterPassword.getText().toString().trim();

      if (!reenterPassword.equals(password)) {
        // Passwords do not match
        Toast.makeText(CreateAccountActivity.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
      } else {
        createAccount(email, password);
      }
    });

    tvLogin.setOnClickListener(v -> goToLoginActivity());
  }

  private void createAccount(String email, String password) {
    mAuth.createUserWithEmailAndPassword(email, password)
      .addOnCompleteListener(this, task -> {
        if (task.isSuccessful()) {
          // Sign in success, update UI with the signed-in user's information
          Log.d(TAG, "createUserWithEmail:success");
          // TODO: Take them to Closet Activity
        } else {
          // If sign in fails, display a message to the user.
          Log.d(TAG, "createUserWithEmail:failure", task.getException());
          Toast.makeText(CreateAccountActivity.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
        }
      });
  }

  @Override
  protected void onStart() {
    super.onStart();
    // Check if user is signed in (non-null) and update UI accordingly
    FirebaseUser currentUser = mAuth.getCurrentUser();
    if (currentUser != null) {
      // TODO: Take them to Closet Activity
    }
  }

  private void goToLoginActivity() {
    Intent i = new Intent(this, LoginActivity.class);
    startActivity(i);
    finish();
  }
}