package com.projects.alice.attack2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;

import com.projects.alice.R;

public class A2_Attack2 extends AppCompatActivity {

    private BroadcastReceiver br;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a2__attack2);

        br = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                Intent startB2 = new Intent();
                startB2.setAction("com.projects.bob.STARTB2ATT2");
                try{
                    startActivity(startB2);
                } catch (ActivityNotFoundException ex){
                    Log.e("ERROR_TESTAPP", "Not all activities necessary for testing are installed!");
                }
            }
        };

        IntentFilter filter = new IntentFilter("com.projects.alice.HIJACKATT2");
        registerReceiver(br, filter);
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        unregisterReceiver(br);
    }

}
