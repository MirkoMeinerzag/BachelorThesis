package com.projects.mallory.attack2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.projects.mallory.R;

public class M1_Attack2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_m1__attack2);

        Intent startM2 = new Intent(this, M2_Attack2.class);
        startActivity(startM2);
    }
}
