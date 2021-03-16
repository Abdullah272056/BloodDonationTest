package com.example.blooddonationtest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class ProfileActivity extends AppCompatActivity {

    TextView profileFNameTextView,profileNameTextView,profilePhoneTextView,profileEmailTextView,
            profileBloodGroupTextView,profileAddressTextView,profileLastDonateDateTextView;
    ImageView profileEditImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        // view finding
        profileFNameTextView=findViewById(R.id.profileFNameTextViewId);
        profileNameTextView=findViewById(R.id.profileNameTextViewId);
        profilePhoneTextView=findViewById(R.id.profilePhoneTextViewId);
        profileEmailTextView=findViewById(R.id.profileEmailTextViewId);
        profileBloodGroupTextView=findViewById(R.id.profileBloodGroupTextViewId);
        profileAddressTextView=findViewById(R.id.profileAddressTextViewId);
        profileLastDonateDateTextView=findViewById(R.id.profileLastDonateDateTextViewId);
        profileEditImageView=findViewById(R.id.profileEditImageViewId);




    }
}