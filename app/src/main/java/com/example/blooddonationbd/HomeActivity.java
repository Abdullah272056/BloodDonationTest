package com.example.blooddonationbd;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    String adminId="hIFyeCPR8gh9VghjneEr0l8xEJj1";

    RecyclerView recyclerView;
    String userId;
    DatabaseReference singleUserDatabaseReference, allUserDatabaseReference,adminInfoDatabaseReference;

    List<UserInformation> singleUserInformationList, allUserInformationList;


    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    Intent intent;
    TextView toolbarTextView;

    CustomAdapter customAdapter;

    String memberType;

    FirebaseRecyclerOptions<UserInformation> options;


    List<AdminModelClass> adminInfoData;
    String adminNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // receive user id
       // userId=getIntent().getStringExtra("userId");
        userId=new SharePref().loadId(HomeActivity.this);

        recyclerView=findViewById(R.id.recyclerViewId);
        drawerLayout=findViewById (R.id.drawerLayoutId);
        navigationView=findViewById (R.id.myNavigationViewId);

        toolbarTextView=findViewById (R.id.toolbarTextViewId);
        toolbarTextView.setText("Home Page");

        toolbar=findViewById (R.id.toolbarId);
        if (toolbar!=null){
            setSupportActionBar (toolbar);
        }
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_baseline_menu_24);




        // dataBase access with id
        singleUserDatabaseReference= FirebaseDatabase.getInstance().getReference("UserInformation").child(userId);
        // data base init
        allUserDatabaseReference= FirebaseDatabase.getInstance().getReference("allUserInfo");

        adminInfoDatabaseReference= FirebaseDatabase.getInstance().getReference("adminInfo");
//        String id=adminInfoDatabaseReference.push().getKey();
//        AdminModelClass adminModelClass=new AdminModelClass("abdullah272056@gmail.com","01994215664",id);
//        adminInfoDatabaseReference.child(id).setValue(adminModelClass);


        singleUserInformationList=new ArrayList<>();
        allUserInformationList=new ArrayList<>();
        adminInfoData=new ArrayList<>();







//        customAdapter=new CustomAdapter2(HomeActivity.this,allUserInformationList);
        recyclerView.setLayoutManager(new LinearLayoutManager(HomeActivity.this));


        options = new FirebaseRecyclerOptions.Builder<UserInformation>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("allUserInfo"), UserInformation.class)
                        .build();









        // call navigationDrawer for getting navigation drawer
        navigationDrawer();


        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @SuppressLint("RtlHardcoded")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId ()){
                    case R.id.homeItemIdId:
                        drawerLayout.closeDrawer(Gravity.LEFT);
                        break;
                    case R.id.profileItemId:
                        intent=new Intent(HomeActivity.this, ProfileActivity.class);
                        startActivity(intent);
                        finish();
                        drawerLayout.closeDrawer(Gravity.LEFT);
                        break;
                    case R.id.notificationItemId:
                        Toast.makeText(HomeActivity.this, " working progress ", Toast.LENGTH_SHORT).show();

                        break;

                    case R.id.beADonatorItemId:
                         int beDonateStatus=singleUserInformationList.size();
                        if (beDonateStatus>0){
                            Toast.makeText(HomeActivity.this, "Already  your are donator ", Toast.LENGTH_SHORT).show();
                        }else {
                            Intent intent=new Intent(HomeActivity.this, BeADonatorActivity.class);
                            startActivity(intent);
                            finish();
                        }

                        break;

                    case R.id.aboutUsItemIdId:
                        Toast.makeText(HomeActivity.this, " working progress ", Toast.LENGTH_SHORT).show();
                        break;

                    case R.id.shareItemIdId:
                        Toast.makeText(HomeActivity.this, " working progress ", Toast.LENGTH_SHORT).show();

                        break;
                    case R.id.feedbackItemId:
                        Toast.makeText(HomeActivity.this, " working progress ", Toast.LENGTH_SHORT).show();

                        break;
                    case R.id.logOutItemId:

                        intent=new Intent(HomeActivity.this, LogInActivity.class);
                        startActivity(intent);
                        finish();
                        new SharePref().rememberData(HomeActivity.this,"","",0);
                       // Toast.makeText(HomeActivity.this, " working progress ", Toast.LENGTH_SHORT).show();
                        break;


                }
                return false;
            }

        });



       // memberType=singleUserInformationList.get(0).getMemberType();

    }

    @Override
    protected void onStart() {
        super.onStart();


        singleUserDatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                singleUserInformationList.clear();
                for (DataSnapshot studentSnapshot:snapshot.getChildren()){
                    UserInformation userInformation=studentSnapshot.getValue(UserInformation.class);
                    singleUserInformationList.add(userInformation);
                }
                int size= singleUserInformationList.size();
                if ( size>0){

                    adminInfoDatabaseReference.addValueEventListener(new ValueEventListener() {
                      @Override
                             public void onDataChange(@NonNull DataSnapshot snapshot) {
                               adminInfoData.clear();
                                 for (DataSnapshot studentSnapshot:snapshot.getChildren()){
                                   AdminModelClass adminModelClass=studentSnapshot.getValue(AdminModelClass.class);
                                   adminInfoData.add(adminModelClass);

                                 }
                          adminNumber=adminInfoData.get(0).getPhone();
                          customAdapter =new CustomAdapter(options,HomeActivity.this,singleUserInformationList.get(0).getMemberType(),adminNumber);
                          recyclerView.setAdapter(customAdapter);
                          customAdapter.startListening();
                      }
                      @Override
                      public void onCancelled(@NonNull DatabaseError error) {

                      }
                    }
                    );


                }else {
                    adminInfoDatabaseReference.addValueEventListener(new ValueEventListener() {

                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            adminInfoData.clear();
                            for (DataSnapshot studentSnapshot:snapshot.getChildren()){
                                AdminModelClass adminModelClass=studentSnapshot.getValue(AdminModelClass.class);
                                adminInfoData.add(adminModelClass);

                            }
                            adminNumber=adminInfoData.get(0).getPhone();
                            customAdapter =new CustomAdapter(options,HomeActivity.this,"user",adminNumber);
                            recyclerView.setAdapter(customAdapter);
                            customAdapter.startListening();
                        }
                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    }
                    );

                }



            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    @Override
    protected void onStop() {
        super.onStop();
        customAdapter.stopListening();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        MenuItem menuItem = menu.findItem(R.id.search);
        //set Search View Action
        androidx.appcompat.widget.SearchView searchView = (androidx.appcompat.widget.SearchView) menuItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query){
                //String s=query.toLowerCase().trim();

                processSearch(query);
                return false;

            }

            @Override
            public boolean onQueryTextChange(String newText) {
                // String s=newText.toLowerCase().trim();
               processSearch(newText);
                return true;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }



    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState){
        super.onPostCreate(savedInstanceState);

//        // get all user information
//        allUserDatabaseReference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                allUserInformationList.clear();
//                for (DataSnapshot studentSnapshot:snapshot.getChildren()){
//                    UserInformation userInformation=studentSnapshot.getValue(UserInformation.class);
//                    allUserInformationList.add(userInformation);
//                   // Toast.makeText(HomeActivity.this, String.valueOf(allUserInformationList.size()), Toast.LENGTH_SHORT).show();
//
//                }
//                recyclerView.setAdapter(customAdapter);
//            }
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });

        // get single user information
        singleUserDatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                singleUserInformationList.clear();
                for (DataSnapshot studentSnapshot:snapshot.getChildren()){
                    UserInformation userInformation=studentSnapshot.getValue(UserInformation.class);
                    singleUserInformationList.add(userInformation);

                    /////////////////
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }

    // create for drawerLayout
    public void navigationDrawer() {

        ActionBarDrawerToggle actionBarDrawerToggle=new ActionBarDrawerToggle(
                HomeActivity.this,drawerLayout,toolbar,R.string.open,R.string.closed){
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                Toast.makeText (HomeActivity.this, "Open", Toast.LENGTH_SHORT).show ();
            }
            @Override
            public void onDrawerClosed(View drawerView){
                super.onDrawerClosed(drawerView);
                Toast.makeText (HomeActivity.this, "Closed", Toast.LENGTH_SHORT).show ();
            }
        };
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
    }

    public  void processSearch(String s){

        FirebaseRecyclerOptions<UserInformation> options =
                new FirebaseRecyclerOptions.Builder<UserInformation>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("allUserInfo").orderByChild("thanaName").startAt(s).endAt(s+"\uf8ff"), UserInformation.class)
                        .build();

        customAdapter =new CustomAdapter(options,HomeActivity.this,singleUserInformationList.get(0).getMemberType(),adminNumber);
        customAdapter.startListening();
        recyclerView.setAdapter(customAdapter);


    }



}