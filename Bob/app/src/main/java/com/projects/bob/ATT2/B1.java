package com.projects.bob.ATT2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.projects.bob.R;

public class B1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b2_att2);
        Log.i("Bob", "ATT2: B1 started");
    }
}
