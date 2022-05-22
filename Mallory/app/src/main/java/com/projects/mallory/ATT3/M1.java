package com.projects.mallory.ATT3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.projects.mallory.ATT4.M2;
import com.projects.mallory.R;

public class M1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_m1_att3);
        Intent intentM2 = new Intent(this, M2.class);
        intentM2.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(intentM2);
        Log.i("Mallory", "ATT3: M1 started.");
    }
}
