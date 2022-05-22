package com.projects.mallory.ATT4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;

import com.projects.mallory.R;

public class M1 extends AppCompatActivity {

    private BroadcastReceiver br;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_m1_att4);

        br = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                Intent startM2 = new Intent();
                startM2.setClassName("com.projects.mallory", "com.projects.mallory.ATT4.M2");
                startM2.setFlags(Intent. FLAG_ACTIVITY_NEW_TASK);
                startActivity(startM2);
            }
        };

        IntentFilter filter = new IntentFilter("com.projects.mallory.HIJACKATT4");
        registerReceiver(br, filter);
        Log.i("Mallory", "ATT4: M1 started.");
    }

    @Override
    protected  void onDestroy(){
        super.onDestroy();
        if(br != null){
            unregisterReceiver(br);
        }
    }
}
