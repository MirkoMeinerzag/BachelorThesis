package com.projects.task_hijacking_testapp;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityOptions;
import android.content.ActivityNotFoundException;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.lang.reflect.*;

public class TestSyncReceiver extends BroadcastReceiver {

    private static final String INTENT_ACTION_TEST_1 = "testapp.intent.action.ACTION_START_TEST_1";
    private static final String INTENT_ACTION_TEST_2 = "testapp.intent.action.ACTION_START_TEST_2";
    private static final String INTENT_ACTION_TEST_3 = "testapp.intent.action.ACTION_START_TEST_3";
    private static final String INTENT_ACTION_TEST_4 = "testapp.intent.action.ACTION_START_TEST_4";
    private static final String INTENT_ACTION_TEST_5 = "testapp.intent.action.ACTION_START_TEST_5";
    private static final String INTENT_ACTION_TEST_6 = "testapp.intent.action.ACTION_START_TEST_6";
    private static final String INTENT_ACTION_RESET_TASKS = "testapp.intent.action.ACTION_RESET_TASKS";


    private static final String MALLORY_PACKAGE_NAME = "com.projects.mallory";
    private static final String ALICE_PACKAGE_NAME = "com.projects.alice";

    private static final String TAG = "TestSyncReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        String receivedAction = intent.getAction();
        Log.d(TAG, "Receiver called with action: " + receivedAction);
        switch(receivedAction){
            case INTENT_ACTION_RESET_TASKS:
                ResetTasks(context);
                break;
            case INTENT_ACTION_TEST_1:
                Log.i(TAG, "Started Test 1");
                InitializeAttOne(context);
                try{
                    TimeUnit.SECONDS.sleep(2);
                }catch(InterruptedException e){

                }
                HijackAttOne(context);
                Log.i(TAG, "Finished Test 1");
                break;
            case INTENT_ACTION_TEST_2:
                Log.i(TAG, "Started Test 2");
                InitializeAttTwo(context);
                try{
                    TimeUnit.SECONDS.sleep(2);
                }catch(InterruptedException e){

                }
                HijackAttTwo(context);
                Log.i(TAG, "Finished Test 2");
                break;
            case INTENT_ACTION_TEST_3:
                //TODO: Not working, fix issue: Activity needs to be started from launcher
                Log.i(TAG, "Started Test 3");
                InitializeAttThree(context);
                try{
                    TimeUnit.SECONDS.sleep(2);
                }catch(InterruptedException e){

                }
                //HijackAttThree(context);
                //Log.i(TAG, "Finished Test 3");
                break;
            case INTENT_ACTION_TEST_4:
                Log.i(TAG, "Started Test 4");
                InitializeAttFour(context);
                try{
                    TimeUnit.SECONDS.sleep(2);
                }catch(InterruptedException e){

                }
                HijackAttFour(context);
                Log.i(TAG, "Finished Test 4");
                break;

            case INTENT_ACTION_TEST_5:
                Log.i(TAG, "Started Test 5");
                InitializeAttFive(context);
                try{
                    TimeUnit.SECONDS.sleep(2);
                }catch(InterruptedException e){

                }
                HijackAttFive(context);
                Log.i(TAG, "Finished Test 5");
                break;
            case INTENT_ACTION_TEST_6:
                Log.i(TAG, "Started Test 6");
                InitializeAttSix(context);
                try{
                    TimeUnit.SECONDS.sleep(2);
                }catch(InterruptedException e){

                }
                HijackAttSix(context);
                Log.i(TAG, "Finished Test 6");
                break;
        }
    }

    private void ResetTasks(Context ctx){
        Intent finishAllRemainingTestActivities = new Intent();
        finishAllRemainingTestActivities.setAction("com.projects.task_hijacking_testapp.FINISH");
        finishAllRemainingTestActivities.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
        ctx.sendBroadcast(finishAllRemainingTestActivities);
    }

    private void InitializeAttOne(Context ctx) {
        Intent startA1 = new Intent();
        Log.i(TAG, "Class of context: " + ctx.getClass());
        startA1.setClassName(ALICE_PACKAGE_NAME, ALICE_PACKAGE_NAME + ".attack1.A1_Attack1");
        startA1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        try {
            ctx.startActivity(startA1);
            Log.i(TAG, "Initialized Attack 1");
        } catch (ActivityNotFoundException e) {
            Log.e(TAG, "Error initializing Attack 1: Activity A1 missing");
        }
    }

    private void HijackAttOne(Context ctx){
        Intent startHijacking = new Intent();
        startHijacking.setAction("com.projects.alice.HIJACKATT1");
        startHijacking.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
        ctx.sendBroadcast(startHijacking);
    }

    private void InitializeAttTwo(Context ctx){
        Intent startA1 = new Intent();
        startA1.setClassName(ALICE_PACKAGE_NAME, ALICE_PACKAGE_NAME + ".attack2.A1_Attack2");
        startA1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        try{
            ctx.startActivity(startA1);
        }catch (ActivityNotFoundException e){
            Log.e(TAG, "Error initializing Attack 2: Activity A1 missing");
        }

        Intent startM1 = new Intent();
        startM1.setClassName(MALLORY_PACKAGE_NAME, MALLORY_PACKAGE_NAME + ".attack2.M1_Attack2");
        startM1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_NO_ANIMATION | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        try{
            ctx.startActivity(startM1);
        }catch (ActivityNotFoundException e){
            Log.e(TAG, "Error initializing Attack 2: Activity M1 missing");
        }
    }

    private void HijackAttTwo(Context ctx) {
        Intent startHijacking = new Intent();
        startHijacking.setAction("com.projects.alice.HIJACKATT2");
        startHijacking.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES | Intent.FLAG_ACTIVITY_NO_ANIMATION);
        ctx.sendBroadcast(startHijacking);
    }

    private void InitializeAttThree(Context ctx) {
        Intent intentM1 = new Intent();
        intentM1.setClassName(MALLORY_PACKAGE_NAME, MALLORY_PACKAGE_NAME + ".attack3.M1_Attack3");
        intentM1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        try {
            ctx.startActivity(intentM1);
        } catch (ActivityNotFoundException e) {
            Log.e(TAG, "Error initializing Attack 3: Activity M1 missing");
        }
    }

    private void HijackAttThree(Context ctx) {
        /*Intent startA1 = new Intent();
        startA1.setClassName(ALICE_PACKAGE_NAME, ALICE_PACKAGE_NAME + ".attack3.A1_Attack3");
        startA1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        try {
            ctx.startActivity(startA1);
        } catch (ActivityNotFoundException e) {
            Log.e(TAG, "Error performing Attack 3: Activity A1 missing");
        }
        Intent intent = new Intent();
        intent.setComponent(new ComponentName(ALICE_PACKAGE_NAME, ALICE_PACKAGE_NAME + ".attack3.A1_Attack3"));
        intent.setAction(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        ctx.startActivity(intent);*/
        String command = "am start -n com.projects.alice/com.projects.alice.attack3.A1_Attack3";
        try {
            //Process p = Runtime.getRuntime().exec(command);

        }catch (Exception ex){
            Log.e(TAG, "Exception while hijacking in scenario three: " + ex.toString());
        }
    }

    private void InitializeAttFour(Context ctx){
        Intent intentM1 = new Intent();
        intentM1.setClassName(MALLORY_PACKAGE_NAME, MALLORY_PACKAGE_NAME + ".attack4.M1_Attack4");
        intentM1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_NO_ANIMATION | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        try {
            ctx.startActivity(intentM1);
            Log.i(TAG, "Initialized Attack 4.");
            // TODO: Update backstack view
        } catch (ActivityNotFoundException e) {
            Log.e(TAG, "Error initializing Attack 4: Activity M1 missing");
        }
    }

    private void HijackAttFour(Context ctx){
        Intent startA1 = new Intent();
        startA1.setClassName(ALICE_PACKAGE_NAME, ALICE_PACKAGE_NAME + ".attack4.A1_Attack4");
        startA1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        try {
            ctx.startActivity(startA1);
        } catch (ActivityNotFoundException e) {
            Log.e(TAG, "Error performing Attack 4: Activity A1 missing");
        }
    }

    private void InitializeAttFive(Context ctx) {
        Intent startA1 = new Intent();
        startA1.setClassName(ALICE_PACKAGE_NAME, ALICE_PACKAGE_NAME + ".attack5.A1_Attack5");
        startA1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_NO_ANIMATION | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        try {
            ctx.startActivity(startA1);
        }catch(ActivityNotFoundException e){
            Log.e(TAG, "Not all activities necessary for testing are installed!");
        }
    }

    private void HijackAttFive(Context ctx) {
        Intent startM1 = new Intent();
        startM1.setClassName(MALLORY_PACKAGE_NAME, MALLORY_PACKAGE_NAME + ".attack5.M1_Attack5");
        startM1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_NO_ANIMATION | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        try{
            ctx.startActivity(startM1);
        }catch(ActivityNotFoundException e){
            Log.e(TAG, "Not all activities necessary for testing are installed!");
        }

    }

    private void InitializeAttSix(Context ctx){
        Intent startA1 = new Intent();
        startA1.setClassName(ALICE_PACKAGE_NAME, ALICE_PACKAGE_NAME + ".attack6.A1_Attack6");
        startA1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_NO_ANIMATION | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        Intent startM1 = new Intent();
        startM1.setClassName(MALLORY_PACKAGE_NAME, MALLORY_PACKAGE_NAME + ".attack6.M1_Attack6");
        startM1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_NO_ANIMATION | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        try{
            ctx.startActivity(startM1);
            ctx.startActivity(startA1);
        }catch(ActivityNotFoundException e){
            Log.e("ERROR_TESTAPP", "Not all activities necessary for testing are installed!");
        }
    }

    private void HijackAttSix(Context ctx){
        Intent startHijacking = new Intent();
        startHijacking.setAction("com.projects.mallory.HIJACKATT6");
        startHijacking.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
        ctx.sendBroadcast(startHijacking);
    }
}


/*try{
    Parcel reply = null;
    Parcel data = null;
    IBinder amBinder = (IBinder)Class.forName("android.os.ServiceManager").getMethod("getService", String.class).invoke(null, "activity");
    Field tokenField = Activity.class.getDeclaredField("mToken");
    tokenField.setAccessible(true);
    IBinder token = (IBinder)tokenField.get(this);
    reply = Parcel.obtain();
    data = Parcel.obtain();

}catch (ClassNotFoundException clsExc){
    Log.e(TAG, "AMS class not found");
}catch (NoSuchMethodException noMethodEx){
    Log.e(TAG, "'getCurrentTasks' not found");
}catch(SecurityException secEx){
    Log.e(TAG, "Securty Manager is present, cannot use reflection");
}catch(Exception ex){
    Log.e(TAG, "Exception occured: " + ex.toString());
}*/