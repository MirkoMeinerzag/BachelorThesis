package com.projects.bob.attack2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.projects.bob.R;

public class B2_Attack2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        moveTaskToBack(true);
        overridePendingTransition(0, 0);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b2__attack2);

    }
}