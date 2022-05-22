package com.projects.alice.ATT2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.projects.alice.R;

public class A2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a2_att2);

        Intent startB2 = new Intent();
        startB2.setClassName("com.projects.bob", "com.projects.bob.ATT2.B1");
        try{
            startActivity(startB2);
        } catch (ActivityNotFoundException e){
            Log.e("Alice", "Missing activity: B1");
        }
        Log.i("Alice", "ATT2: A2 started");
    }
}
