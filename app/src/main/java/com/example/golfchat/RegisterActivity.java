package com.example.golfchat;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.golfchat.activities.LoginActivity;
import com.example.golfchat.databinding.RegistrationFormBinding;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    EditText mFname, mLname, mEmail, mPassword, mConfirm_password;
    MaterialButton submit_btn;
    ProgressBar progressBar;

    private RegistrationFormBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = RegistrationFormBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mFname = findViewById(R.id.fname);
        mLname = findViewById(R.id.lname);
        mEmail = findViewById(R.id.email);
        mPassword = findViewById(R.id.password);
        mConfirm_password = findViewById(R.id.confirm_password);
        submit_btn = findViewById(R.id.btn_submit);
        progressBar = findViewById(R.id.progressBar);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        //If the user already has an account
        if(mAuth.getCurrentUser() != null){
            startActivity(new Intent(getApplicationContext(), LoginActivity.class));
            finish();
        }

        submit_btn.setOnClickListener(v -> {
            String email = mEmail.getText().toString().trim();
            String fname = mFname.getText().toString().trim();
            String lname = mLname.getText().toString().trim();
            String password = mPassword.getText().toString().trim();
            String confirm_password = mConfirm_password.getText().toString().trim();

            // Check to make sure user put an email
            if(TextUtils.isEmpty(email)){
                mEmail.setError("Email is required");
                return;
            }

            if(TextUtils.isEmpty(password)){
                mPassword.setError("Password is required");
                return;
            }

            if(!password.equals(confirm_password)){
                mConfirm_password.setError("Password does not match");
                return;
            }

            if(password.length() < 8){
                mPassword.setError("Password must be at least 8 characters");
                return;
            }

            if(TextUtils.isEmpty(fname)){
                mFname.setError("First name is required");
                return;
            }

            if(TextUtils.isEmpty(lname)){
                mLname.setError("Last name is required");
                return;
            }

            progressBar.setVisibility(View.VISIBLE);

            //Register the user
            mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
                if(task.isSuccessful()){
                    Toast.makeText(RegisterActivity.this, "User Created", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                }else {
                    Toast.makeText(RegisterActivity.this, "Error ! " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

    }
}
