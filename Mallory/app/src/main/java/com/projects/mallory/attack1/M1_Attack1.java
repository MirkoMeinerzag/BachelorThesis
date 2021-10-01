package com.projects.mallory.attack1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.projects.mallory.R;

public class M1_Attack1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        moveTaskToBack(true);
        overridePendingTransition(0, 0);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_m1__attack1);
    }
}
