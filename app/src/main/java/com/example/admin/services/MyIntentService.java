package com.example.admin.services;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;


public class MyIntentService extends IntentService {

    private static final String TAG = "MyIntServiceTag";

    public MyIntentService() {
        super("MyIntentService");
    }

    @Override
    public void onCreate() {

        Log.d(TAG, "onCreate: ");

        super.onCreate();
    }

    @Override
    protected void onHandleIntent(Intent intent) {

        Log.d(TAG, "onHandleIntent: " + intent.getStringExtra("data"));

        for (int i = 0; i <5; i++){
            try {
                Thread.sleep(1000);
                Log.d(TAG, "onHandleIntent: Task " + i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onDestroy() {
        Log.d(TAG, "onDestroy: ");
        super.onDestroy();
    }
}
