package com.example.blooddonationtest;

import android.content.Context;
import android.content.SharedPreferences;

public class SharePref {

    public void saveId(Context context,String id){
        SharedPreferences sharedPreferences=context.getSharedPreferences("details", context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString("id",id);
        editor.commit();
    }

    public String loadId(Context context){
        SharedPreferences sharedPreferences=context.getSharedPreferences("details",context.MODE_PRIVATE);
        String id=sharedPreferences.getString("id","");
        return id;
    }

    public void rememberData(Context context,String rememberEmail,String rememberPassword,int checkStatus){
        SharedPreferences sharedPreferences=context.getSharedPreferences("rememberData", context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString("rememberEmail",rememberEmail);
        editor.putString("rememberPassword",rememberPassword);
        editor.putInt("rememberCheckStatus",checkStatus);
        editor.commit();
    }
    public int loadRememberCheckStatus(Context context){
        SharedPreferences sharedPreferences=context.getSharedPreferences("rememberData",context.MODE_PRIVATE);
        int checkStatus=sharedPreferences.getInt("rememberCheckStatus",0);
        return checkStatus;
    }
    public String loadRememberEmail(Context context){
        SharedPreferences sharedPreferences=context.getSharedPreferences("rememberData",context.MODE_PRIVATE);
        String rememberEmail=sharedPreferences.getString("rememberEmail","");
        return rememberEmail;
    }
    public String loadRememberPassword(Context context){
        SharedPreferences sharedPreferences=context.getSharedPreferences("rememberData",context.MODE_PRIVATE);
        String rememberPassword=sharedPreferences.getString("rememberPassword","");
        return rememberPassword;
    }


}
