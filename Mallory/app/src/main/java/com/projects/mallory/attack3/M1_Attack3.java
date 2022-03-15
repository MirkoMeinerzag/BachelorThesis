package com.projects.mallory.attack3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;

import com.projects.mallory.R;

public class M1_Attack3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_m1__attack3);

        Intent intentM2 = new Intent(this, M2_Attack3.class);
        startActivity(intentM2);
    }
}
