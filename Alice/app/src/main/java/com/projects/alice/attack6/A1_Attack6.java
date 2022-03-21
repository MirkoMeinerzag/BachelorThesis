package com.projects.alice.attack6;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;

import com.projects.alice.R;

public class A1_Attack6 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        moveTaskToBack(true);
        overridePendingTransition(0, 0);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a1__attack6);
        Log.i("ALICE", "A1_Attack6 Activity started.");
        RegisterFinishReceiver();
    }

    @Override
    protected  void onDestroy(){
        super.onDestroy();
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