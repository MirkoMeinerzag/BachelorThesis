package com.projects.task_hijacking_testapp;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.nfc.Tag;
import android.os.IBinder;
import android.os.Parcel;
import android.util.Log;

import java.lang.reflect.Field;
import java.lang.reflect.Method;


public class TestSyncReceiver extends BroadcastReceiver {

    private static final String INTENT_ACTION_TEST_1 = "testapp.intent.action.ACTION_START_TEST_1";

    private static final String TAG = "TestSyncReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        String receivedAction = intent.getAction();
        Log.d(TAG, "Receiver called with action: " + receivedAction);
        switch(receivedAction){
            case INTENT_ACTION_TEST_1:
                Log.d(TAG, "Intent received");
                try{
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
                }
        }
    }
}
