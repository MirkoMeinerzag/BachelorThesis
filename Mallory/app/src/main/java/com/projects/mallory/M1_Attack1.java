package com.projects.mallory;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class M1_Attack1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        moveTaskToBack(true);
        overridePendingTransition(0, 0);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_m1__attack1);
    }
}
