package com.projects.alice.attack5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.projects.alice.R;

public class A1_Attack5 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        moveTaskToBack(true);
        overridePendingTransition(0, 0);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a1__attack5);

        Intent startA2 = new Intent(this, A2_Attack5.class);
        startA2.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(startA2);
    }
}
