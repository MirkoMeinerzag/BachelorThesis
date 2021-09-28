package com.projects.task_hijacking_testapp;

import java.util.HashMap;
import java.util.List;

public class BackStack {

    private HashMap<Integer, List<String>> backstack;

    private static BackStack INSTANCE;

    private BackStack(){
        backstack = new HashMap<>();
    }

    public static BackStack getInstance(){
        if(INSTANCE == null){
            INSTANCE = new BackStack();
        }

        return INSTANCE;
    }
}
