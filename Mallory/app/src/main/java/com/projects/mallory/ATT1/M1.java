package com.projects.mallory.ATT1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.projects.mallory.R;

public class M1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_m1_att1);
        Log.i("Mallory", "ATT1: M1 started.");
        Intent intentM2 = new Intent(this, M2.class);
        intentM2.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(intentM2);
        moveTaskToBack(false);
    }
}
