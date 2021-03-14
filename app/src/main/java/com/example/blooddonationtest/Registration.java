package com.example.blooddonationtest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Registration extends AppCompatActivity {

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



    }
}