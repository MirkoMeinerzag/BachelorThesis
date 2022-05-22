package com.projects.task_hijacking_testapp;

import android.content.ActivityNotFoundException;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class TestSyncReceiver extends BroadcastReceiver {

    private static final String INTENT_INIT_ATT_1 = "testapp.intent.action.INIT_ATT1";
    private static final String INTENT_HIJACK_ATT_1 = "testapp.intent.action.HIJACK_ATT1";
    private static final String INTENT_INIT_ATT_2 = "testapp.intent.action.INIT_ATT2";
    private static final String INTENT_HIJACK_ATT_2 = "testapp.intent.action.HIJACK_ATT2";
    private static final String INTENT_INIT_ATT_3 = "testapp.intent.action.INIT_ATT3";
    private static final String INTENT_HIJACK_ATT_3 = "testapp.intent.action.HIJACK_ATT3";
    private static final String INTENT_INIT_ATT_4 = "testapp.intent.action.INIT_ATT4";
    private static final String INTENT_HIJACK_ATT_4 = "testapp.intent.action.HIJACK_ATT4";

    private static final String MALLORY_PACKAGE_NAME = "com.projects.mallory";
    private static final String ALICE_PACKAGE_NAME = "com.projects.alice";

    private static final String TAG = "TestSyncReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        String receivedAction = intent.getAction();
        Log.d(TAG, "Receiver called with action: " + receivedAction);
        switch(receivedAction){
            case INTENT_INIT_ATT_1:
                InitializeAttOne(context);
                break;
            case INTENT_HIJACK_ATT_1:
                Log.i(TAG, "Hijacking for ATT1 needs to happen through adb monkey");
                break;
            case INTENT_INIT_ATT_2:
                InitializeAttTwo(context);
                break;
            case INTENT_HIJACK_ATT_2:
                HijackAttTwo(context);
                break;
            case INTENT_INIT_ATT_3:
                InitializeAttThree(context);
                break;
            case INTENT_HIJACK_ATT_3:
                HijackAttThree(context);
                break;
            case INTENT_INIT_ATT_4:
                InitializeAttFour(context);
                break;
            case INTENT_HIJACK_ATT_4:
                HijackAttFour(context);
                break;
        }
    }

    private void InitializeAttOne(Context ctx) {
        Intent initStrandHogg = new Intent();
        initStrandHogg.setClassName(MALLORY_PACKAGE_NAME, MALLORY_PACKAGE_NAME + ".ATT1.M1");
        initStrandHogg.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        try{
            ctx.startActivity(initStrandHogg);
            Log.i(TAG, "Initialized ATT1: StrandHogg");
        } catch ( ActivityNotFoundException actEx) {
            Log.e(TAG, "Error initializing ATT1: Activity M1 missing");
        }
    }

    private void InitializeAttTwo(Context ctx){
        Intent startA1 = new Intent();
        startA1.setClassName(ALICE_PACKAGE_NAME, ALICE_PACKAGE_NAME + ".ATT2.A1");
        startA1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        try {
            ctx.startActivity(startA1);
            Log.i(TAG, "Initialized ATT2: Victim reparenting");
        }catch(ActivityNotFoundException e){
            Log.e(TAG, "Error initializing ATT2: Activity A1 missing");
        }
    }

    private void HijackAttTwo(Context ctx) {
        Intent startM1 = new Intent();
        startM1.setClassName(MALLORY_PACKAGE_NAME, MALLORY_PACKAGE_NAME + ".ATT2.M1");
        startM1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        Intent startM2 = new Intent();
        startM2.setClassName(MALLORY_PACKAGE_NAME, MALLORY_PACKAGE_NAME + ".ATT2.M2");
        try{
            ctx.startActivities(new Intent[]{startM1, startM2});
            Log.i(TAG, "Performed ATT2");
        }catch(ActivityNotFoundException e){
            Log.e(TAG, "Error while hijacking in ATT2: M1 or M2 missing");
        }
    }

    private void InitializeAttThree(Context ctx) {
        Intent intentM1 = new Intent();
        intentM1.setClassName(MALLORY_PACKAGE_NAME, MALLORY_PACKAGE_NAME + ".ATT3.M1");
        intentM1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        try {
            ctx.startActivity(intentM1);
            Log.i(TAG, "Initialized ATT3: Attacker reparenting");
        } catch (ActivityNotFoundException e) {
            Log.e(TAG, "Error initializing ATT3: Activity M1 missing");
        }
    }

    private void HijackAttThree(Context ctx) {
        Intent startA1 = new Intent();
        startA1.setClassName(ALICE_PACKAGE_NAME, ALICE_PACKAGE_NAME + ".ATT3.A1");
        startA1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        try {
            ctx.startActivity(startA1);
            Log.i(TAG, "Performed ATT3");
        } catch (ActivityNotFoundException e) {
            Log.e(TAG, "Error performing ATT3: Activity A1 missing");
        }
    }

    private void InitializeAttFour(Context ctx){
        Intent startA1 = new Intent();
        startA1.setClassName(ALICE_PACKAGE_NAME, ALICE_PACKAGE_NAME + ".ATT4.A1");
        startA1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        Intent startM1 = new Intent();
        startM1.setClassName(MALLORY_PACKAGE_NAME, MALLORY_PACKAGE_NAME + ".ATT4.M1");
        startM1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        try{
            ctx.startActivity(startM1);
            ctx.startActivity(startA1);
            Log.i(TAG, "Initialized ATT4: Task infiltration");
        }catch(ActivityNotFoundException e){
            Log.e(TAG, "Error while initializing ATT4: M1 or A2 missing");
        }
    }

    private void HijackAttFour(Context ctx){
        Intent startHijacking = new Intent();
        startHijacking.setAction("com.projects.mallory.HIJACKATT4");
        startHijacking.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
        ctx.sendBroadcast(startHijacking);
        Log.i(TAG, "Performed ATT4");
    }
}