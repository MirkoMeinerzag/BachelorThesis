package com.projects.alice.ATT2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.projects.alice.R;

public class A1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a1_att2);
        Intent startA2 = new Intent(this, A2.class);
        startActivity(startA2);
        Log.i("Alice", "ATT2: A1 started");
    }
}
