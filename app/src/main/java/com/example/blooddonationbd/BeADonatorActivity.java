package com.example.blooddonationbd;

import androidx.annotation.NonNull;
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

public class BeADonatorActivity extends AppCompatActivity{
    EditText nameEditText,phoneEditText;
    TextView lastDateTextView, bloodGroupTextView,districtTextView,divisionTextView,thanaTextView;
    Button saveButton;


    Toolbar toolbar;
    String userId;
    DatePickerDialog.OnDateSetListener mDateSetListener;
    TextView aPositiveTextView,bPositiveTextView,oPositiveTextView,abPositiveTextView,
            aNegativeTextView,bNegativeTextView,abNegativeTextView,oNegativeTextView;
    DatabaseReference singleUserDatabaseReference,allUserDatabaseReference;

    List<UserInformation> singleUserInformationList, allUserInformationList;

    TextView toolbarTextView;

    CustomProgress customProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_be_a_donator);

        toolbar=findViewById (R.id.toolbarId);
        if (toolbar!=null){
            setSupportActionBar (toolbar);
        }
        // for add back Button in title bar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarTextView=findViewById (R.id.toolbarTextViewId);
        toolbarTextView.setText("Be Donator");
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_24);

        // create a object for custom progress
        customProgress=new CustomProgress(BeADonatorActivity.this);

       // receive userId
        //userId=getIntent().getStringExtra("userId");
        userId=new SharePref().loadId(BeADonatorActivity.this);

        singleUserInformationList=new ArrayList<>();
        allUserInformationList=new ArrayList<>();

        // dataBase access with id
        singleUserDatabaseReference= FirebaseDatabase.getInstance().getReference("UserInformation").child(userId);
        // data base init
        allUserDatabaseReference= FirebaseDatabase.getInstance().getReference("allUserInfo");


        // view finding
        nameEditText=findViewById(R.id.nameEditTextId);
        phoneEditText=findViewById(R.id.phoneEditTextId);
        divisionTextView=findViewById(R.id.divisionTextViewId);
        districtTextView=findViewById(R.id.districtTextViewId);
        thanaTextView=findViewById(R.id.thanaTextViewId);
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



    // select Blood group with AlertDialog
    public void  selectBloodGroup(){
        AlertDialog.Builder builder     =new AlertDialog.Builder(BeADonatorActivity.this);
        LayoutInflater layoutInflater   =LayoutInflater.from(BeADonatorActivity.this);
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

    // select data function with DatePickerDialog
    public void selectDate(){

        Calendar calendar=Calendar.getInstance();
        int day =calendar.get(Calendar.DAY_OF_MONTH);
        int month =calendar.get(Calendar.MONTH);
        int year =calendar.get(Calendar.YEAR);

                   // DatePickerDialog create
        DatePickerDialog dialog=new DatePickerDialog(BeADonatorActivity.this,
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


    public void donatorInfoSave(){
        String name=nameEditText.getText().toString();
        String phone=phoneEditText.getText().toString();
        String divisionName=divisionTextView.getText().toString();
        String districtName=districtTextView.getText().toString();
        String thanaName=thanaTextView.getText().toString();

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
        if (TextUtils.isEmpty(divisionName)){
            divisionTextView.setError("Enter division ");
            divisionTextView.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(districtName)){
            districtTextView.setError("Enter district");
            districtTextView.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(thanaName)){
            thanaTextView.setError("Enter thana");
            thanaTextView.requestFocus();
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



        if (singleUserInformationList.size()>0){
            Toast.makeText(this, "Already  your are donator ", Toast.LENGTH_SHORT).show();
        }else {
            // call showProgress method
            customProgress.showProgress();

            String information_id=singleUserDatabaseReference.push().getKey();
            UserInformation userInformation=new UserInformation(
                    information_id,name,phone,bloodGroup,lastDate,divisionName,districtName,thanaName);
            // set data
            singleUserDatabaseReference.child(information_id).setValue(userInformation);
            allUserDatabaseReference.child(information_id).setValue(userInformation);
            // call dismissProgress method
            customProgress.dismissProgress();
            Intent intent=new Intent(BeADonatorActivity.this,HomeActivity.class);
            //intent.putExtra("userId",userId);
            startActivity(intent);
            finish();
        }



    }

    public void  selectDivision(){

    }


    @Override
    public void onBackPressed() {
        Intent intent =new Intent(BeADonatorActivity.this, HomeActivity.class);
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