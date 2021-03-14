package com.example.blooddonationtest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Objects;

public class Registration extends AppCompatActivity implements View.OnClickListener {
    private FirebaseAuth mAuth;
    EditText signUpEmailEditText, signUpPasswordEditText,signUpConfirmPasswordEditText;
    Button signUpButton;
    TextView signInTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        // view finding
         signUpEmailEditText=findViewById(R.id.signUpEmailEditTextId);
        signUpPasswordEditText=findViewById(R.id.signUpPasswordEditTextId);
        signUpConfirmPasswordEditText=findViewById(R.id.signUpConfirmPasswordEditTextId);
        signUpButton=findViewById(R.id.signUpButtonId);
        signInTextView=findViewById(R.id.signInTextViewId);

        signUpButton.setOnClickListener(this);
        signInTextView.setOnClickListener(this);





    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.signUpButtonId:
                userRegistration();
                break;

            case R.id.signInTextViewId:

                Intent intent=new Intent(Registration.this,HomeActivity.class);
                startActivity(intent);
                finish();
                break;
        }
    }

    public  void userRegistration(){
        final String signUpEmail= signUpEmailEditText.getText().toString();
        final String signUpPassword=signUpPasswordEditText.getText().toString();
        final String signUpConfirmPassword=signUpConfirmPasswordEditText.getText().toString();

        if (TextUtils.isEmpty(signUpEmail)){
            signUpEmailEditText.setError("Enter your email");
            signUpEmailEditText.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(signUpEmail).matches()){
            signUpEmailEditText.setError("Enter a valid  email address");
            signUpEmailEditText.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(signUpPassword)){
            signUpPasswordEditText.setError("Enter password");
            signUpPasswordEditText.requestFocus();
            return;
        }
        if (signUpPassword.length()<6){
            signUpPasswordEditText.setError("password should be more than 6");
            signUpPasswordEditText.requestFocus();
            return;
        }


        if (TextUtils.isEmpty(signUpConfirmPassword)){
            signUpConfirmPasswordEditText.setError("Enter confirm password");
            signUpConfirmPasswordEditText.requestFocus();
            return;
        }
        if (!signUpPassword.equals(signUpConfirmPassword)){
            signUpConfirmPasswordEditText.setError("do not match confirm password");
            signUpConfirmPasswordEditText.requestFocus();
            return;
        }

        mAuth.createUserWithEmailAndPassword(signUpEmail, signUpPassword)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(Registration.this, "register success", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(Registration.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();

                        }
                    }
                });



                }
}