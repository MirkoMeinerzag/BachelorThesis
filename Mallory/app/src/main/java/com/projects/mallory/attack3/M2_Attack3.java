package com.projects.mallory.attack3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.projects.mallory.R;

public class M2_Attack3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        moveTaskToBack(true);
        overridePendingTransition(0, 0);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_m2__attack3);
    }
}
