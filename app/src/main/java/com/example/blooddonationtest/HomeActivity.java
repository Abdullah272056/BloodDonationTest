package com.example.blooddonationtest;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    Button beDonatorButton;
    String userId;
    //DatabaseReference databaseReference1,databaseReference;
    DatabaseReference databaseReference;

    List<UserInformation> userInformationList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);



        // receive user id
        userId=getIntent().getStringExtra("userId");

        //databaseReference= FirebaseDatabase.getInstance().getReference("student");
        // dataBase access with id
        databaseReference= FirebaseDatabase.getInstance().getReference("UserInformation").child(userId);

        userInformationList=new ArrayList<>();


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



    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                userInformationList.clear();
                for (DataSnapshot studentSnapshot:snapshot.getChildren()){
                    UserInformation userInformation=studentSnapshot.getValue(UserInformation.class);
                    userInformationList.add(userInformation);
                    Toast.makeText(HomeActivity.this, String.valueOf(userInformationList.get(0).getUserName()), Toast.LENGTH_SHORT).show();

                }
                //listView.setAdapter(customAdapter);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    
}