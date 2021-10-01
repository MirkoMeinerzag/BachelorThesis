package com.projects.alice.attack4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.projects.alice.R;

public class A1_Attack4 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        moveTaskToBack(true);
        overridePendingTransition(0, 0);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a1__attack4);
    }
}
