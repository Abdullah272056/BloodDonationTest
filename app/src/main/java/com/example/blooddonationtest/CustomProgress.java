package com.example.blooddonationtest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import androidx.appcompat.app.AlertDialog;

public class CustomProgress {
    AlertDialog alertDialog;
    Context context;

    CustomProgress(Context context) {
        this.context = context;
    }

    void showProgress(){
        AlertDialog.Builder builder     =new AlertDialog.Builder(context);
        LayoutInflater layoutInflater   =LayoutInflater.from(context);
        View view                       =layoutInflater.inflate(R.layout.progress_dialog,null);
        builder.setView(view);
        alertDialog   = builder.create();

        alertDialog.show();
    }

    void dismissProgress(){
        alertDialog.dismiss();
    }
}
