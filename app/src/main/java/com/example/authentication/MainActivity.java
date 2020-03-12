package com.example.authentication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    private EditText SignInEmail_EditText, SignInPassword_EditText;
    private Button SignInButton;
    private TextView SignUp_TextView;
    private ProgressBar progressBarSignIn;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.setTitle("Sign In Activity");

        mAuth = FirebaseAuth.getInstance();

        SignInEmail_EditText = (EditText) findViewById(R.id.SignInEmail_EditText_ID);
        SignInPassword_EditText = (EditText) findViewById(R.id.SignInPassword_EditText_ID);
        SignInButton = (Button) findViewById(R.id.SignInButton_ID);
        SignUp_TextView = (TextView) findViewById(R.id.SignUp_TextView_ID);
        progressBarSignIn = findViewById(R.id.SignInProgressBar_ID);
    }
    public void onClick(View view){
        switch (view.getId()){
            case R.id.SignInButton_ID:
                userLogin();
                break;


            case R.id.SignUp_TextView_ID:
                Intent intent = new Intent(getApplicationContext(),SignUpActivity.class);
                startActivity(intent);
                break;
        }
    }

    private void userLogin() {
        String email = SignInEmail_EditText.getText().toString().trim();
        String password = SignInPassword_EditText.getText().toString().trim();

        if(email.isEmpty()) {
            SignInEmail_EditText.setError("Enter a email address");
            SignInEmail_EditText.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            SignInEmail_EditText.setError("Enter a valid email address");
            SignInEmail_EditText.requestFocus();
            return;
        }
        if (password.isEmpty()) {
            SignInPassword_EditText.setError("Enter your correct password");
            SignInPassword_EditText.requestFocus();
            return;
        }
        if (password.length() < 6) {
            SignInPassword_EditText.setError("Enter minimum 6 character of password");
            SignInPassword_EditText.requestFocus();
            return;
        }
        progressBarSignIn.setVisibility(View.VISIBLE);
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressBarSignIn.setVisibility(View.GONE);
                if (task.isSuccessful()){
                    //Intent intent = new Intent(getApplicationContext(),ProfileActivity.class);
                    Intent intent = new Intent(getApplicationContext(),NavigationActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    finish();
                }
                else {
                    Toast.makeText(getApplicationContext(), "Login unsuccessfully", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
