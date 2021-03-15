package com.example.blooddonationtest;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
    RecyclerView recyclerView;
    Button beDonatorButton;
    String userId;
    DatabaseReference singleUserDatabaseReference, allUserDatabaseReference;

    List<UserInformation> singleUserInformationList, allUserInformationList;
    CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // receive user id
        userId=getIntent().getStringExtra("userId");

        recyclerView=findViewById(R.id.recyclerViewId);
        beDonatorButton=findViewById(R.id.beDonatorButtonId);



        // dataBase access with id
        singleUserDatabaseReference= FirebaseDatabase.getInstance().getReference("UserInformation").child(userId);
        // data base init
        allUserDatabaseReference= FirebaseDatabase.getInstance().getReference("allUserInfo");

        singleUserInformationList=new ArrayList<>();
        allUserInformationList=new ArrayList<>();
        customAdapter=new CustomAdapter(HomeActivity.this,allUserInformationList);
        recyclerView.setLayoutManager(new LinearLayoutManager(HomeActivity.this));

        beDonatorButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                int beDonateStatus=singleUserInformationList.size();
                if (beDonateStatus>0){
                    Toast.makeText(HomeActivity.this, "Already  your are donator ", Toast.LENGTH_SHORT).show();
                }else {
                    Intent intent=new Intent(HomeActivity.this,BeADonator.class);
                    intent.putExtra("userId",userId);
                    startActivity(intent);
                }


            }
        });

    }



    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState){
        super.onPostCreate(savedInstanceState);

        // get all user information
        allUserDatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                allUserInformationList.clear();
                for (DataSnapshot studentSnapshot:snapshot.getChildren()){
                    UserInformation userInformation=studentSnapshot.getValue(UserInformation.class);
                    allUserInformationList.add(userInformation);
                    Toast.makeText(HomeActivity.this, String.valueOf(allUserInformationList.size()), Toast.LENGTH_SHORT).show();

                }
                recyclerView.setAdapter(customAdapter);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        // get single user information
        singleUserDatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                singleUserInformationList.clear();
                for (DataSnapshot studentSnapshot:snapshot.getChildren()){
                    UserInformation userInformation=studentSnapshot.getValue(UserInformation.class);
                    singleUserInformationList.add(userInformation);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



    }

}