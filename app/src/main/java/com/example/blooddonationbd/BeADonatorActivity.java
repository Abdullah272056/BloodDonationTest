package com.example.blooddonationbd;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

import com.example.blooddonationbd.getDistrict.DistrictCustomAdapter;
import com.example.blooddonationbd.getDistrict.GetDistrictData;
import com.example.blooddonationbd.getDistrict.GetDistrictResponseData;
import com.example.blooddonationbd.getDivision.DivisionCustomAdapter;
import com.example.blooddonationbd.getDivision.GetDivisionData;
import com.example.blooddonationbd.getDivision.GetDivisionResponseData;
import com.example.blooddonationbd.retrofit.ApiInterface;
import com.example.blooddonationbd.retrofit.RetrofitClient;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BeADonatorActivity extends AppCompatActivity  implements DivisionCustomAdapter.OnContactClickListener1
,DistrictCustomAdapter.OnContactClickListener2{
    EditText nameEditText,phoneEditText;
    TextView lastDateTextView, bloodGroupTextView,districtTextView,divisionTextView,thanaTextView;
    Button saveButton;
    ApiInterface apiInterface;

    Toolbar toolbar;
    String userId;
    DatePickerDialog.OnDateSetListener mDateSetListener;
    TextView aPositiveTextView,bPositiveTextView,oPositiveTextView,abPositiveTextView,
            aNegativeTextView,bNegativeTextView,abNegativeTextView,oNegativeTextView;
    DatabaseReference singleUserDatabaseReference,allUserDatabaseReference;

    List<UserInformation> singleUserInformationList, allUserInformationList;

    TextView toolbarTextView;

    CustomProgress customProgress;

    List<GetDivisionData> divisionDataList;
    List<GetDistrictData> districtDataList;
    List<String> thanaDataList;

    AlertDialog alertDialog;

    RecyclerView recyclerView;

    DivisionCustomAdapter divisionCustomAdapter;
    DistrictCustomAdapter districtCustomAdapter;
//    ThanaCustomAdapter thanaCustomAdapter;
DivisionCustomAdapter.OnContactClickListener1 onContactClickListener1;
    DistrictCustomAdapter.OnContactClickListener2 onContactClickListener2;
//    ThanaCustomAdapter.OnContactClickListener3 onContactClickListener3;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_be_a_donator);


        onContactClickListener1=this;
        onContactClickListener2=this;
//        onContactClickListener3=this;

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

        apiInterface = RetrofitClient.getRetrofit("https://bdapis.herokuapp.com/").create(ApiInterface.class);


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

        divisionTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDivisionData();
            }
        });
        districtTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String division=divisionTextView.getText().toString();
                if (division.isEmpty()){
                    Toast.makeText(BeADonatorActivity.this, "Please select your division", Toast.LENGTH_SHORT).show();
                    return;
                }else {
                    getDistrict(division.toLowerCase());

                }
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

    public  void  getDivisionData(){
        apiInterface.getAllDivision().enqueue(new Callback<GetDivisionResponseData>() {
            @Override
            public void onResponse(Call<GetDivisionResponseData> call, Response<GetDivisionResponseData> response) {
                if (response.code()==200){
                    divisionDataList=new ArrayList<>();
                    assert response.body() != null;
                    divisionDataList.addAll(response.body().getDivisionDataList());
                    if (divisionDataList.size()>0){
                        Toast.makeText(BeADonatorActivity.this, "sss", Toast.LENGTH_SHORT).show();
                        showDivisionData(divisionDataList);
                    }
                }
                else {
                    Toast.makeText(BeADonatorActivity.this, "fff", Toast.LENGTH_SHORT).show();
                }

            }
            @Override
            public void onFailure(Call<GetDivisionResponseData> call, Throwable t) {
                Toast.makeText(BeADonatorActivity.this, "fff", Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void showDivisionData(List<GetDivisionData> divisionDataList){
        AlertDialog.Builder builder     =new AlertDialog.Builder(BeADonatorActivity.this);
        LayoutInflater layoutInflater   =LayoutInflater.from(BeADonatorActivity.this);
        View view                       =layoutInflater.inflate(R.layout.recyclerview,null);
        builder.setView(view);
        alertDialog   = builder.create();
        alertDialog.setCancelable(true);

        recyclerView=view.findViewById(R.id.recyclerViewId);
        divisionCustomAdapter = new DivisionCustomAdapter(BeADonatorActivity.this,divisionDataList,onContactClickListener1);
        recyclerView.setLayoutManager(new LinearLayoutManager(BeADonatorActivity.this));
        recyclerView.setAdapter(divisionCustomAdapter);


        alertDialog.show();


    }


    public void  getDistrict(String id){
        apiInterface.getDistrict(id).enqueue(new Callback<GetDistrictResponseData>() {
            @Override
            public void onResponse(Call<GetDistrictResponseData> call, Response<GetDistrictResponseData> response) {
                if (response.code()==200){
                    districtDataList=new ArrayList<>();
                    thanaDataList=new ArrayList<>();
                    assert response.body() != null;
                    districtDataList.addAll(response.body().getGetDistrictData());
                    if (districtDataList.size()>0){
                        showDistrict(districtDataList);
                        // Toast.makeText(MainActivity.this, String.valueOf(thanaDataList.size()), Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    Toast.makeText(BeADonatorActivity.this, "fff", Toast.LENGTH_SHORT).show();
                }            }

            @Override
            public void onFailure(Call<GetDistrictResponseData> call, Throwable t) {
                Toast.makeText(BeADonatorActivity.this, "fff", Toast.LENGTH_SHORT).show();

            }
        });
    }
    private void showDistrict(List<GetDistrictData> districtDataList){
        AlertDialog.Builder builder     =new AlertDialog.Builder(BeADonatorActivity.this);
        LayoutInflater layoutInflater   =LayoutInflater.from(BeADonatorActivity.this);
        View view                       =layoutInflater.inflate(R.layout.recyclerview,null);
        builder.setView(view);
        alertDialog   = builder.create();
        alertDialog.setCancelable(false);

        recyclerView=view.findViewById(R.id.recyclerViewId);
        districtCustomAdapter = new DistrictCustomAdapter(BeADonatorActivity.this,districtDataList,onContactClickListener2);
        recyclerView.setLayoutManager(new LinearLayoutManager(BeADonatorActivity.this));
        recyclerView.setAdapter(districtCustomAdapter);


        alertDialog.show();


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

        // division item click
    @Override
    public void onContactClick1(int position) {
        thanaTextView.setText("");
        districtTextView.setText("");
        divisionTextView.setText(String.valueOf(divisionDataList.get(position).getDivision()));
        alertDialog.dismiss();
    }

    @Override
    public void onContactClick2(int position) {

    }
}