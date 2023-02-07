package columbia.pbluc.thecloset;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {
  private static final String TAG = "Login";

  private FirebaseAuth mAuth;

  private EditText etEmail;
  private EditText etPassword;
  private Button btnLogin;
  private TextView tvCreateAccount;
  private TextView tvResetPassword;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_login);

    // Initialize Firebase Auth
    mAuth = FirebaseAuth.getInstance();

    etEmail = findViewById(R.id.editTextEmailAddress);
    etPassword = findViewById(R.id.editTextPassword);
    btnLogin = findViewById(R.id.buttonLogin);
    tvCreateAccount = findViewById(R.id.textViewSignUp);
    tvResetPassword = findViewById(R.id.textViewResetPassword);

    btnLogin.setOnClickListener(v -> {
      String email = etEmail.getText().toString().trim();
      String password = etPassword.getText().toString().trim();

      logIn(email, password);
    });

    tvCreateAccount.setOnClickListener(v -> goToCreateAccountActivity());
    tvResetPassword.setOnClickListener(v -> {
      String email = etEmail.getText().toString().trim();

      resetPassword(email);
    });
  }

  private void resetPassword(String email) {
    mAuth.sendPasswordResetEmail(email)
      .addOnCompleteListener(this, task -> {
        if (task.isSuccessful()) {
          Log.d(TAG, "Password reset email sent.");
          Toast.makeText(LoginActivity.this, "If account was found, reset password email was sent", Toast.LENGTH_LONG).show();
        } else {
          Log.d(TAG, "sendResetPasswordEmail:failure", task.getException());
        }
      });
  }

  private void goToCreateAccountActivity() {
    Intent i = new Intent(this, CreateAccountActivity.class);
    startActivity(i);
    finish();
  }

  private void logIn(String email, String password) {
    mAuth.signInWithEmailAndPassword(email, password)
      .addOnCompleteListener(this, task -> {
        if (task.isSuccessful()) {
          // Sign in success, update UI with the signed-in user's information
          Log.d(TAG, "logInWithEmail:success");
          // Go to Main Activity
          goToMainActivity();
        } else {
          // If sign in fails, display a message to the user
          Log.d(TAG, "logInWithEmail:failure", task.getException());
          Toast.makeText(LoginActivity.this, "Log in failed", Toast.LENGTH_SHORT).show();
        }
      });
  }

  private void goToMainActivity() {
    Intent i = new Intent(this, MainActivity.class);
    startActivity(i);
    finish();
  }

  @Override
  protected void onStart() {
    super.onStart();
    // Check if user is signed in (non-null) and update UI accordingly
    FirebaseUser currentUser = mAuth.getCurrentUser();
    if (currentUser != null) {
      // Go to Main Activity
      goToMainActivity();
    }
  }
}