package com.projects.mallory.ATT1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.projects.mallory.R;

public class M2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        moveTaskToBack(true);
        overridePendingTransition(0, 0);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_m2_att1);
        Log.i("Mallory", "ATT1: M2 started.");
    }
}
