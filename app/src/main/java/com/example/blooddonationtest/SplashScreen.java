package com.example.blooddonationtest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ProgressBar;

public class SplashScreen extends AppCompatActivity {
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
        for ( int progress=1; progress<=100; progress=progress+1){
            try {
                Thread.sleep(50);
                progressBar.setProgress(progress);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
    public void startApp(){
        Intent intent =new Intent(SplashScreen.this,MainActivity.class);
        startActivity(intent);
        finish();
    }



}