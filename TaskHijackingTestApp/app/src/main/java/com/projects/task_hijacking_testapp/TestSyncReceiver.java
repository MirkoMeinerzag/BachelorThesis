package com.projects.task_hijacking_testapp;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.ActivityNotFoundException;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.nfc.Tag;
import android.os.IBinder;
import android.os.Parcel;
import android.util.Log;
import java.util.concurrent.TimeUnit;
import android.view.View;

import java.lang.reflect.Field;
import java.lang.reflect.Method;


public class TestSyncReceiver extends BroadcastReceiver {

    private static final String INTENT_ACTION_TEST_1 = "testapp.intent.action.ACTION_START_TEST_1";
    private static final String INTENT_ACTION_TEST_2 = "testapp.intent.action.ACTION_START_TEST_2";
    private static final String INTENT_ACTION_TEST_3 = "testapp.intent.action.ACTION_START_TEST_3";

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
                break;
            case INTENT_ACTION_TEST_3:
                InitializeAttThree(context);
                try{
                    TimeUnit.SECONDS.sleep(2);
                }catch(InterruptedException e){

                }
                HijackAttThree(context);
                break;

        }
    }

    private void InitializeAttOne(Context ctx) {
        Intent startA1 = new Intent();
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
        Intent startA1 = new Intent();
        startA1.setClassName(ALICE_PACKAGE_NAME, ALICE_PACKAGE_NAME + ".attack3.A1_Attack3");
        startA1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        try {
            ctx.startActivity(startA1);
        } catch (ActivityNotFoundException e) {
            Log.e(TAG, "Error performing Attack 3: Activity A1 missing");
        }
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