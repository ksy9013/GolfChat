package com.example.golfchat.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.golfchat.MainActivity;
import com.example.golfchat.R;
import com.example.golfchat.RegisterActivity;
import com.example.golfchat.databinding.ActivityLoginBinding;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.material.button.MaterialButton;

import butterknife.BindView;

public class LoginActivity extends AppCompatActivity {


    @BindView(R.id.edittext_email)
    EditText mEmail;
    @BindView(R.id.edittext_password)
    EditText mPassword;
    @BindView((R.id.btn_signup))
    MaterialButton signup_btn;
    @BindView(R.id.btn_signin)
    MaterialButton login_btn;
//    @BindView((R.id.forgot_password))
//    MaterialButton forgotpassword_btn;
//    @BindView((R.id.btn_google))
//    Button google_btn;

    GoogleSignInClient mGoogleSignInClient;
    private ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setListeners();

        // Configure sign-in to request the user's ID, email address, and basic
        // profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
    }

//    public void btn_signup(View view){
//        startActivity(new Intent(getApplicationContext(), RegisterActivity.class));
//    }

    @Override
    protected void onStart() {
        super.onStart();

        // Check for existing Google Sign In account, if the user is already signed in
        // the GoogleSignInAccount will be non-null.
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        updateUI(account);
    }

    private void setListeners(){
       binding.btnSignup.setOnClickListener(v->
               startActivity(new Intent(getApplicationContext(), RegisterActivity.class)));
//       binding.btnSignin.setOnClickListener(v -> addDataToFirestore());
    }


    //Change UI according to user data.
    public void updateUI(GoogleSignInAccount account){

        if(account != null){
            Toast.makeText(this,"You Signed In successfully",Toast.LENGTH_LONG).show();
            startActivity(new Intent(this, MainActivity.class));

        }else {
            Toast.makeText(this,"You didnt signed in",Toast.LENGTH_LONG).show();
        }

    }

//    private void addDataToFirestore(){
//        FirebaseFirestore database = FirebaseFirestore.getInstance();
//        HashMap<String, Object> data = new HashMap<>();
//        data.put("first_name", "Kaylee");
//        data.put("last_name", "Kim");
//        database.collection("users")
//                .add(data)
//                .addOnSuccessListener(documentReference -> {
//                    Toast.makeText(getApplicationContext(), "Data Inserted", Toast.LENGTH_SHORT).show();
//                })
//                .addOnFailureListener(exception -> {
//                    Toast.makeText(getApplicationContext(),exception.getMessage(), Toast.LENGTH_SHORT).show();
//                });
//    }
}
