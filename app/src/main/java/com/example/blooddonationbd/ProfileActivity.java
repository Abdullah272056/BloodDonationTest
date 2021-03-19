package com.example.blooddonationbd;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ProfileActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;

    TextView profileFNameTextView,profileNameTextView,profilePhoneTextView,profileEmailTextView,
            profileBloodGroupTextView,profileAddressTextView,profileLastDonateDateTextView;
    ImageView profileEditImageView;
    String userId;

    DatabaseReference singleUserDatabaseReference;
    List<UserInformation> singleUserInformationList;
    Toolbar toolbar;

    TextView toolbarTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        toolbar=findViewById (R.id.toolbarId);
        if (toolbar!=null){
            setSupportActionBar (toolbar);
        }
        // for add back Button in title bar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_24);

        toolbarTextView=findViewById (R.id.toolbarTextViewId);
        toolbarTextView.setText(" My Profile");

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        //get user id from share pref
        userId=new SharePref().loadId(ProfileActivity.this);

        // dataBase access with id
        singleUserDatabaseReference= FirebaseDatabase.getInstance().getReference("UserInformation").child(userId);
        singleUserInformationList=new ArrayList<>();



        // view finding
        profileFNameTextView=findViewById(R.id.profileFNameTextViewId);
        profileNameTextView=findViewById(R.id.profileNameTextViewId);
        profilePhoneTextView=findViewById(R.id.profilePhoneTextViewId);
        profileEmailTextView=findViewById(R.id.profileEmailTextViewId);
        profileBloodGroupTextView=findViewById(R.id.profileBloodGroupTextViewId);
        profileAddressTextView=findViewById(R.id.profileAddressTextViewId);
        profileLastDonateDateTextView=findViewById(R.id.profileLastDonateDateTextViewId);
        profileEditImageView=findViewById(R.id.profileEditImageViewId);



        profileEditImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(ProfileActivity.this,ProfileEditActivity.class);
                startActivity(intent);
                finish();

            }
        });



    }


    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState){
        super.onPostCreate(savedInstanceState);

        // get single user information
        singleUserDatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                singleUserInformationList.clear();
                for (DataSnapshot studentSnapshot:snapshot.getChildren()){
                    UserInformation userInformation=studentSnapshot.getValue(UserInformation.class);
                    singleUserInformationList.add(userInformation);

                    if (singleUserInformationList.size()>0){
//                        TextView profileFNameTextView,profileNameTextView,profilePhoneTextView,profileEmailTextView,
//                            profileBloodGroupTextView,profileAddressTextView,profileLastDonateDateTextView;

                        profileFNameTextView.setText(singleUserInformationList.get(0).getUserName());
                        profileNameTextView.setText(singleUserInformationList.get(0).getUserName());
                        profilePhoneTextView.setText(singleUserInformationList.get(0).getUserPhone());
                        profileBloodGroupTextView.setText(singleUserInformationList.get(0).getBloodGroup());

                        profileAddressTextView.setText(singleUserInformationList.get(0).getThanaName()+
                                        ", "+singleUserInformationList.get(0).getDistrictName()+
                                ", "+singleUserInformationList.get(0).getCountryName());
                        profileLastDonateDateTextView.setText(singleUserInformationList.get(0).getLastDate());

                }

                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

            String email= mAuth.getCurrentUser().getEmail();
            profileEmailTextView.setText(email);


    }




    @Override
    public void onBackPressed() {
        Intent intent =new Intent(ProfileActivity.this, HomeActivity.class);
        startActivity(intent);
        finish();
    }

    // title bar  button clicked
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


}