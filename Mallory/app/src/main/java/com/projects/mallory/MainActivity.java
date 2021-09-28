package com.projects.mallory;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intentM2 = new Intent();
        intentM2.setAction("com.projects.mallory.StartM2Att3");
        intentM2.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        try{
            startActivity(intentM2);
        }catch (ActivityNotFoundException e){

        }
    }
}
