package com.example.blooddonationtest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Registration extends AppCompatActivity implements View.OnClickListener {

    EditText signUpEmailEditText, signUpPasswordEditText,signUpConfirmPasswordEditText;
    Button signUpButton;
    TextView signInTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
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
        final String signInEmail= signUpEmailEditText.getText().toString();
        final String signInPassword=signUpPasswordEditText.getText().toString();
        final String signInConfirmPassword=signUpConfirmPasswordEditText.getText().toString();

        if (TextUtils.isEmpty(signInEmail)){
            signUpEmailEditText.setError("Enter your email");
            signUpEmailEditText.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(signInEmail).matches()){
            signUpEmailEditText.setError("Enter a valid  email address");
            signUpEmailEditText.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(signInPassword)){
            signUpPasswordEditText.setError("Enter password");
            signUpPasswordEditText.requestFocus();
            return;
        }
        if (signInPassword.length()<6){
            signUpPasswordEditText.setError("password should be more than 6");
            signUpPasswordEditText.requestFocus();
            return;
        }


        if (TextUtils.isEmpty(signInConfirmPassword)){
            signUpConfirmPasswordEditText.setError("Enter confirm password");
            signUpConfirmPasswordEditText.requestFocus();
            return;
        }
        if (signInPassword!=signInConfirmPassword){
            signUpConfirmPasswordEditText.setError("do not match confirm password");
            signUpConfirmPasswordEditText.requestFocus();
            return;
        }





    }
}