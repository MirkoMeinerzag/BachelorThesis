package com.projects.task_hijacking_testapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnShowBS = findViewById(R.id.btnShowBS);
        btnShowBS.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
            }
        });
    }
}
