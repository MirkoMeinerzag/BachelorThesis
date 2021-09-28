package com.projects.mallory;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityManager;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import java.util.List;

public class M1_Attack3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        moveTaskToBack(true);
        overridePendingTransition(0, 0);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_m1__attack3);

        Intent intentM2 = new Intent();
        intentM2.setAction("com.projects.mallory.StartM2Att3");
        intentM2.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        try{
            startActivity(intentM2);
        }catch (ActivityNotFoundException e){

        }
    }
}
