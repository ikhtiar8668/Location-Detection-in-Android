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
import com.google.firebase.auth.FirebaseAuthUserCollisionException;

public class SignUpActivity extends AppCompatActivity {
    private EditText SignUpEmail_EditText, SignUpPassword_EditText;
    private Button SignUpButton;
    private TextView SignIn_TextView;
    private ProgressBar progressBarSignUp;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        this.setTitle("Sign Up Activity");

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        SignUpEmail_EditText = (EditText) findViewById(R.id.SignUpEmail_EditText_ID);
        SignUpPassword_EditText = (EditText) findViewById(R.id.SignUpPassword_EditText_ID);
        SignUpButton = (Button) findViewById(R.id.SignUpButton_ID);
        SignIn_TextView = (TextView) findViewById(R.id.SignIn_TextView_ID);
        progressBarSignUp = findViewById(R.id.SignUpProgressBar_ID);
    }
    public void onClick(View view){
        switch (view.getId()){
            case R.id.SignUpButton_ID:
                userRegister(); 
            break;


            case R.id.SignIn_TextView_ID:
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
                break;
        }
    }

    private void userRegister() {
        String email = SignUpEmail_EditText.getText().toString().trim();
        String password = SignUpPassword_EditText.getText().toString().trim();

        //check the validity of email
        if(email.isEmpty()) {
            SignUpEmail_EditText.setError("Enter a email address");
            SignUpEmail_EditText.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            SignUpEmail_EditText.setError("Enter your valid email address");
            SignUpEmail_EditText.requestFocus();
            return;
        }
        if(password.isEmpty()) {
            SignUpPassword_EditText.setError("Enter a password");
            SignUpPassword_EditText.requestFocus();
            return;
        }
        if(password.length() < 6) {
            SignUpPassword_EditText.setError("Enter minimum 6 character of password");
            SignUpPassword_EditText.requestFocus();
            return;
        }
        progressBarSignUp.setVisibility(View.VISIBLE);
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressBarSignUp.setVisibility(View.GONE);
                if(task.isSuccessful()) {
                    Toast.makeText(getApplicationContext(),"Register is successful",Toast.LENGTH_SHORT).show();
                }
                else {
                    if (task.getException() instanceof FirebaseAuthUserCollisionException) {
                        Toast.makeText(getApplicationContext(), "Already you are registered", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(getApplicationContext(), "Error: "+task.getException(), Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}
