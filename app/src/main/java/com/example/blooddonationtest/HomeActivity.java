package com.example.blooddonationtest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {
    Button logInButton;
    TextView signUpTextView,forgotPasswordTextView;
    EditText logInEmailEditText,logInPasswordEditText;
    // Write a message to the database
    FirebaseDatabase database ;
    DatabaseReference myRef;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setTitle("Homepage");
        setContentView(R.layout.activity_home);

        // view finding
        logInButton =findViewById(R.id.logIbButtonID);
        signUpTextView =findViewById(R.id.signUpTextViewId);
        forgotPasswordTextView =findViewById(R.id.forgotPasswordTextViewId);
        logInEmailEditText =findViewById(R.id.logInEmailEditTextId);
        logInPasswordEditText =findViewById(R.id.logInPasswordEditTextId);

        logInButton.setOnClickListener(this);
        signUpTextView.setOnClickListener(this);
        forgotPasswordTextView.setOnClickListener(this);





//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                database = FirebaseDatabase.getInstance();
//                myRef = database.getReference("message");
//                String key=myRef.push().getKey();
//                myRef.child(key).setValue("Hello world"+key);
//                Toast.makeText(HomeActivity.this, "Success", Toast.LENGTH_SHORT).show();
//            }
//        });

    }

    @Override
    public void onClick(View v) {

    }
}