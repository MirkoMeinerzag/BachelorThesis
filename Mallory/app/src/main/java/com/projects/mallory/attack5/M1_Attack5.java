package com.projects.mallory.attack5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;

import com.projects.mallory.R;

public class M1_Attack5 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_m1__attack5);
        Log.i("MALLORY", "M1_Attack5 Activity started.");
        Intent startM2 = new Intent(this, M2_Attack5.class);
        startActivity(startM2);
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