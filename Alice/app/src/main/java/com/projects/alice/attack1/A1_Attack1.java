package com.projects.alice.attack1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;

import com.projects.alice.R;

public class A1_Attack1 extends AppCompatActivity {

    private BroadcastReceiver br;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a1__attack1);

        br = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                Intent startM1 = new Intent();
                startM1.setAction("com.projects.mallory.STARTM1ATT1");
                try{
                    startActivity(startM1);
                } catch (ActivityNotFoundException ex){
                    //TODO: Activity not found, not all test apps installed
                }
            }
        };
        IntentFilter filter = new IntentFilter("com.projects.alice.HIJACKATT1");
        registerReceiver(br, filter);
        Log.i("ALICE", "A1_Attack1 Activity started.");

        RegisterFinishReceiver();
    }

    @Override
    protected  void onDestroy(){
        super.onDestroy();
        if(br != null){
            unregisterReceiver(br);
        }
        if(finishReceiver != null){
            unregisterReceiver(finishReceiver);
        }
    }

    private void RegisterFinishReceiver(){
        IntentFilter filter = new IntentFilter();
        filter.addAction("com.projects.task_hijacking_testapp.FINISH");
        registerReceiver(finishReceiver, filter);
    }

    BroadcastReceiver finishReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            finish();
        }
    };

    public void finish() {
        super.finish();
    }
}
