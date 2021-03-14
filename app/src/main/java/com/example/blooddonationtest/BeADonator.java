package com.example.blooddonationtest;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class BeADonator extends AppCompatActivity{
    EditText nameEditText,phoneEditText,countryNameEditText,districtEditText,thanaEditText;
    TextView lastDateTextView, bloodGroupTextView;
    Button saveButton;


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_be_a_donator);

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
               // donatorInfoSave();
            }
        });


    }

    public void  selectBloodGroup(){
        AlertDialog.Builder builder     =new AlertDialog.Builder(BeADonator.this);
        LayoutInflater layoutInflater   =LayoutInflater.from(BeADonator.this);
        View view                       =layoutInflater.inflate(R.layout.blood_group,null);
        builder.setView(view);
        final AlertDialog alertDialog   = builder.create();






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







    }
}