package com.projects.task_hijacking_testapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityManager;
import android.app.TaskStackBuilder;
import android.content.ActivityNotFoundException;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        moveTaskToBack(false);
        // TODO: Clear backstack of alice, bob and malory
    }

    public void InitializeAttOne(View view) {
        Intent startA1 = new Intent();
        startA1.setAction("com.projects.alice.STARTA1ATT1");
        startA1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_NO_ANIMATION | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        try {
            startActivity(startA1);
        } catch (ActivityNotFoundException e) {
            Log.e("ERROR_TESTAPP", "Not all activities necessary for testing are installed!");
        }
    }

    public void HijackAttOne(View view){
        Intent startHijacking = new Intent();
        startHijacking.setAction("com.projects.alice.HIJACKATT1");
        startHijacking.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
        getApplicationContext().sendBroadcast(startHijacking);
    }

    public void InitializeAttTwo(View view) {
        Intent startA1 = new Intent();
        startA1.setAction("com.projects.alice.STARTA1ATT2");
        startA1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_NO_ANIMATION | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        try{
            startActivity(startA1);
        }catch (ActivityNotFoundException e){
            Log.e("ERROR_TESTAPP", "Not all activities necessary for testing are installed!");
        }

        Intent startM1 = new Intent();
        startM1.setAction("com.projects.mallory.STARTM1ATT2");
        startM1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_NO_ANIMATION | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        try{
            startActivity(startM1);
        }catch (ActivityNotFoundException e){
            Log.e("ERROR_TESTAPP", "Not all activities necessary for testing are installed!");
        }
    }

    public void HijackAttTwo(View view) {
        Intent startHijacking = new Intent();
        startHijacking.setAction("com.projects.alice.HIJACKATT2");
        startHijacking.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES | Intent.FLAG_ACTIVITY_NO_ANIMATION);
        getApplicationContext().sendBroadcast(startHijacking);

    }

    public void InitializeAttThree(View view) {
        // TODO: Clear backstack
        Intent intentM1 = new Intent();
        intentM1.setAction("com.projects.mallory.StartM1Att3");
        intentM1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_NO_ANIMATION | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        try {
            startActivity(intentM1);
            // TODO: Update backstack view
        } catch (ActivityNotFoundException e) {
            Log.e("ERROR_TESTAPP", "Not all activities necessary for testing are installed!");
        }
    }

    public void HijackAttThree(View view) {
        Intent startA1 = new Intent();
        startA1.setAction("com.projects.alice.STARTA1ATT3");
        startA1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        try {
            startActivity(startA1);
        } catch (ActivityNotFoundException e) {
            Log.e("ERROR_TESTAPP", "Not all activities necessary for testing are installed!");
        }
    }

    public void InitializeAttFour(View view) {
        Intent intentM1 = new Intent();
        intentM1.setAction("com.projects.mallory.StartM1Att4");
        intentM1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_NO_ANIMATION | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        try {
            startActivity(intentM1);
            // TODO: Update backstack view
        } catch (ActivityNotFoundException e) {
            Log.e("ERROR_TESTAPP", "Not all activities necessary for testing are installed!");
        }
    }

    public void HijackAttFour(View view) {
        Intent startA1 = new Intent();
        startA1.setAction("com.projects.alice.STARTA1ATT4");
        startA1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        try {
            startActivity(startA1);
        } catch (ActivityNotFoundException e) {
            Log.e("ERROR_TESTAPP", "Not all activities necessary for testing are installed!");
        }
    }

    public void InitializeAttFive(View view) {
        Intent startA1 = new Intent();
        startA1.setAction("com.projects.alice.STARTA1ATT5");
        startA1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_NO_ANIMATION | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        try {
            startActivity(startA1);
        }catch(ActivityNotFoundException e){
            Log.e("ERROR_TESTAPP", "Not all activities necessary for testing are installed!");
        }
    }

    public void HijackAttFive(View view) {
        Intent startM1 = new Intent();
        startM1.setAction("com.projects.mallory.STARTM1ATT5");
        startM1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_NO_ANIMATION | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        Log.d("DEBUG", "Intents created");

        try{
            startActivity(startM1);
        }catch(ActivityNotFoundException e){
            Log.e("ERROR_TESTAPP", "Not all activities necessary for testing are installed!");
        }

    }

    public void InitializeAttSix(View view) {
        Intent startA1 = new Intent();
        startA1.setAction("com.projects.alice.STARTA1ATT6");
        startA1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_NO_ANIMATION | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        Intent startM1 = new Intent();
        startM1.setAction("com.projects.mallory.STARTM1ATT6");
        startM1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_NO_ANIMATION | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        try{
            startActivity(startM1);
            startActivity(startA1);
        }catch(ActivityNotFoundException e){
            Log.e("ERROR_TESTAPP", "Not all activities necessary for testing are installed!");
        }
    }

    public void HijackAttSix(View view) {
        Intent startHijacking = new Intent();
        startHijacking.setAction("com.projects.mallory.HIJACKATT6");
        startHijacking.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
        getApplicationContext().sendBroadcast(startHijacking);
    }

    private void UpdateBackStackView() {
        //TODO: Implement
    }
}
