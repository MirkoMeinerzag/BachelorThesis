package com.projects.alice.attack5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;

import com.projects.alice.R;

public class A2_Attack5 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a2__attack5);

        moveTaskToBack(true);
        overridePendingTransition(0, 0);

        Intent startB2 = new Intent();
        startB2.setAction("com.projects.bob.STARTB2ATT5");
        startB2.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        try{
            startActivity(startB2);
        } catch (ActivityNotFoundException e){
            // TODO: Activity not found, not all test apps installed
        }
    }
}
