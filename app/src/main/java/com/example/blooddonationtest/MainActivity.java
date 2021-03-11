package com.example.blooddonationtest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    Button button;
    // Write a message to the database
    FirebaseDatabase database ;
    DatabaseReference myRef;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                database = FirebaseDatabase.getInstance();
//                myRef = database.getReference("message");
//                String key=myRef.push().getKey();
//                myRef.child(key).setValue("Hello world"+key);
//                Toast.makeText(MainActivity.this, "Success", Toast.LENGTH_SHORT).show();
//            }
//        });

    }
}