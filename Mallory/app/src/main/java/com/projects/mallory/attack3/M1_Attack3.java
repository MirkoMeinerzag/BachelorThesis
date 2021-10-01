package com.projects.mallory.attack3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;

import com.projects.mallory.R;

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
