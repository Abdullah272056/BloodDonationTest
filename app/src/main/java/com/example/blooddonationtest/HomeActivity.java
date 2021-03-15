package com.example.blooddonationtest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomeActivity extends AppCompatActivity {

    Button beDonatorButton;
    String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // receive user id
        userId=getIntent().getStringExtra("userId");

        beDonatorButton=findViewById(R.id.beDonatorButtonId);
        beDonatorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Intent intent=new Intent(HomeActivity.this,BeADonator.class);
            intent.putExtra("userId",userId);
            startActivity(intent);
            }
        });

    }
}