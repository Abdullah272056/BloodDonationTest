package com.example.blooddonationtest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class HomeActivity extends AppCompatActivity {
    Button button;
    // Write a message to the database
    FirebaseDatabase database ;
    DatabaseReference myRef;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setTitle("Homepage");
        setContentView(R.layout.activity_home);

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
}