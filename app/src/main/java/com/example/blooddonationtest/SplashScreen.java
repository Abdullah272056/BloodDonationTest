package com.example.blooddonationtest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ProgressBar;

public class SplashScreen extends AppCompatActivity {
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_splash_screen);

        progressBar=findViewById(R.id.progressbarId);

        Thread thread=new Thread(new Runnable(){
            @Override
            public void run() {
                doProgressBar();
                startApp();
            }
        });
        thread.start();

    }

    public void doProgressBar(){
        for ( int progress=10; progress<=100; progress=progress+10){
            try {
                Thread.sleep(100);
                //Thread.sleep(500);
                progressBar.setProgress(progress);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
    public void startApp(){
        Intent intent =new Intent(SplashScreen.this, LogInActivity.class);
        startActivity(intent);
        finish();
    }



}