package com.example.blooddonationtest;

import android.app.Activity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import androidx.appcompat.app.AlertDialog;


public class CustomProgress {
    AlertDialog alertDialog;
    Activity activity;

    CustomProgress(Activity activity) {
        this.activity = activity;
    }

    public void showProgress(){
        AlertDialog.Builder builder     =new AlertDialog.Builder(activity);
        LayoutInflater inflater=activity.getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.progress_dialog,null));
        builder.setCancelable(true);




        alertDialog   = builder.create();

        alertDialog.show();
    }

    void dismissProgress(){
        showProgress();
        alertDialog.dismiss();
    }
}
