package com.example.blooddonationtest;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ProfileEditActivity extends AppCompatActivity {

    EditText nameEditText,phoneEditText,countryNameEditText,districtEditText,thanaEditText;
    TextView lastDateTextView, bloodGroupTextView;
    Button saveButton;
    DatePickerDialog.OnDateSetListener mDateSetListener;


    TextView aPositiveTextView,bPositiveTextView,oPositiveTextView,abPositiveTextView,
            aNegativeTextView,bNegativeTextView,abNegativeTextView,oNegativeTextView;



    Toolbar toolbar;
    String userId;

    DatabaseReference singleUserDatabaseReference,allUserDatabaseReference;

    List<UserInformation> singleUserInformationList, allUserInformationList;

    TextView toolbarTextView;

    String information_id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_edit);


        toolbar=findViewById (R.id.toolbarId);
        if (toolbar!=null){
            setSupportActionBar (toolbar);
        }
        toolbarTextView=findViewById (R.id.toolbarTextViewId);
        toolbarTextView.setText("Be Donator");
        // for add back Button in title bar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // receive userId
        //userId=getIntent().getStringExtra("userId");
        userId=new SharePref().loadId(ProfileEditActivity.this);

        singleUserInformationList=new ArrayList<>();
        allUserInformationList=new ArrayList<>();

        // dataBase init with id
        singleUserDatabaseReference= FirebaseDatabase.getInstance().getReference("UserInformation").child(userId);

        // data base init
        allUserDatabaseReference= FirebaseDatabase.getInstance().getReference("allUserInfo");



        // view finding
        nameEditText=findViewById(R.id.nameEditTextId);
        phoneEditText=findViewById(R.id.phoneEditTextId);
        countryNameEditText=findViewById(R.id.countryNameEditTextId);
        districtEditText=findViewById(R.id.districtEditTextId);
        thanaEditText=findViewById(R.id.thanaEditTextId);
        lastDateTextView=findViewById(R.id.lastDateTextViewId);
        bloodGroupTextView=findViewById(R.id.bloodGroupTextViewId);
        saveButton=findViewById(R.id.saveButtonId);

        saveButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                donatorInfoSave();
            }
        });
        bloodGroupTextView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                selectBloodGroup();
            }
        });
        lastDateTextView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                selectDate();
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
                        nameEditText.setText(singleUserInformationList.get(0).getUserName());
                        phoneEditText.setText(singleUserInformationList.get(0).getUserPhone());
                        bloodGroupTextView.setText(singleUserInformationList.get(0).getBloodGroup());
                        lastDateTextView.setText(singleUserInformationList.get(0).getLastDate());
                        thanaEditText.setText(singleUserInformationList.get(0).getThanaName());
                        districtEditText.setText(singleUserInformationList.get(0).getDistrictName());
                        countryNameEditText.setText(singleUserInformationList.get(0).getCountryName());
                        information_id=singleUserInformationList.get(0).getId();
                    }

                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    @Override
    public void onBackPressed() {
        Intent intent =new Intent(ProfileEditActivity.this, ProfileActivity.class);
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


    public void selectDate(){

        Calendar calendar=Calendar.getInstance();
        int day =calendar.get(Calendar.DAY_OF_MONTH);
        int month =calendar.get(Calendar.MONTH);
        int year =calendar.get(Calendar.YEAR);

        // DatePickerDialog create
        DatePickerDialog dialog=new DatePickerDialog(ProfileEditActivity.this,
                android.R.style.Theme_Holo_Light_Dialog_MinWidth,mDateSetListener,year,month,day);

        //background color set
        //dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();

        // selected date set with textView
        mDateSetListener=new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                lastDateTextView.setText(String.valueOf(dayOfMonth+"/"+month+"/"+year));
            }
        };


    }

    // select Blood group with AlertDialog
    public void  selectBloodGroup(){
        AlertDialog.Builder builder     =new AlertDialog.Builder(ProfileEditActivity.this);
        LayoutInflater layoutInflater   =LayoutInflater.from(ProfileEditActivity.this);
        View view                       =layoutInflater.inflate(R.layout.blood_group,null);
        builder.setView(view);
        final AlertDialog alertDialog   = builder.create();

        aPositiveTextView=view.findViewById(R.id.aPositiveTextViewId);
        bPositiveTextView=view.findViewById(R.id.bPositiveTextViewId);
        oPositiveTextView=view.findViewById(R.id.oPositiveTextViewId);
        abPositiveTextView=view.findViewById(R.id.abPositiveTextViewId);
        aNegativeTextView=view.findViewById(R.id.aNegativeTextViewId);
        bNegativeTextView=view.findViewById(R.id.bNegativeTextViewId);
        abNegativeTextView=view.findViewById(R.id.abNegativeTextViewId);
        oNegativeTextView=view.findViewById(R.id.oNegativeTextViewId);

        aPositiveTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                bloodGroupTextView.setText(" A+ ");
                alertDialog.dismiss();

            }
        });
        bPositiveTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bloodGroupTextView.setText(" B+ ");
                alertDialog.dismiss();
            }
        });

        oPositiveTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bloodGroupTextView.setText(" O+ ");
                alertDialog.dismiss();
            }
        });

        abPositiveTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bloodGroupTextView.setText(" AB+ ");
                alertDialog.dismiss();
            }
        });

        aNegativeTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bloodGroupTextView.setText(" A- ");
                alertDialog.dismiss();
            }
        });

        bNegativeTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bloodGroupTextView.setText(" B- ");
                alertDialog.dismiss();
            }
        });

        oNegativeTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bloodGroupTextView.setText(" O- ");
                alertDialog.dismiss();
            }
        });

        abNegativeTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bloodGroupTextView.setText(" AB- ");
                alertDialog.dismiss();
            }
        });







        alertDialog.show();
    }

    public void donatorInfoSave(){
        String name=nameEditText.getText().toString();
        String phone=phoneEditText.getText().toString();
        String countryName=countryNameEditText.getText().toString();
        String districtName=districtEditText.getText().toString();
        String thanaName=thanaEditText.getText().toString();

        String lastDate=lastDateTextView.getText().toString();
        String bloodGroup=bloodGroupTextView.getText().toString();


        if (TextUtils.isEmpty(name)){
            nameEditText.setError("Enter your name");
            nameEditText.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(phone)){
            phoneEditText.setError("Enter phone number");
            phoneEditText.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(countryName)){
            countryNameEditText.setError("Enter country name");
            countryNameEditText.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(districtName)){
            districtEditText.setError("Enter district name");
            districtEditText.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(thanaName)){
            thanaEditText.setError("Enter thana name");
            thanaEditText.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(lastDate)){
            lastDateTextView.setError("Enter last date");
            lastDateTextView.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(bloodGroup)){
            bloodGroupTextView.setError("select your blood group");
            bloodGroupTextView.requestFocus();
            return;
        }

        // call showProgress method
        new CustomProgress(ProfileEditActivity.this).showProgress();



            UserInformation userInformation=new UserInformation(
                    information_id,name,phone,bloodGroup,lastDate,countryName,districtName,thanaName);
            // set data
            singleUserDatabaseReference.child(information_id).setValue(userInformation);
            allUserDatabaseReference.child(information_id).setValue(userInformation);

            Intent intent=new Intent(ProfileEditActivity.this,HomeActivity.class);
            startActivity(intent);
            finish();


        // call dismissProgress method
        new CustomProgress(ProfileEditActivity.this).dismissProgress();

    }

}