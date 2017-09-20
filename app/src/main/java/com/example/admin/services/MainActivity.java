package com.example.admin.services;

import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "TAG";
    MyBoundService myBoundService;
    private boolean isBoundConnected;
    List<String> dummyData = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Services are application components


    }

    public void startService(View view) {

        String data = "someData";

        //create a normal intent to start the service
        Intent normalIntent = new Intent(this,MyService.class);

//        create a intent to start intent service
        Intent intentIntent = new Intent(this, MyIntentService.class);

        //create a  intent for bound service
        Intent boundIntent = new Intent(this, MyBoundService.class);


        switch (view.getId()){

            case R.id.btnNormalService:
                normalIntent.putExtra("data",data);
                startService(normalIntent);

                break;

            case R.id.btnStopNormalService:
                stopService(normalIntent);

                break;

            case R.id.btnStartIntentService:
                intentIntent.putExtra("data","Here is some data");
                startService(intentIntent);
                break;

            case R.id.btnBoundToService:
                boundIntent.putExtra("data","datadata");
                bindService(boundIntent,serviceConnection, Context.BIND_AUTO_CREATE);

                break;

            case R.id.btnUnBoundToService:
                if(isBoundConnected) {
                    unbindService(serviceConnection);
                }
                break;

            case R.id.btnBoundInitData:
                if(isBoundConnected){
                    myBoundService.initData(10);
                    Toast.makeText(this, "Bata initialized", Toast.LENGTH_SHORT).show();
                }
                break;

            case R.id.btnBoundGetData:
                if(isBoundConnected){
                     dummyData = myBoundService.getDummyData();
                    for (String s: dummyData
                         ) {
                        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
                    }
                }
                break;

        }
    }

    ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            Log.d(TAG, "onServiceConnected: ");
            MyBoundService.MyBinder myBinder = (MyBoundService.MyBinder) iBinder;
            myBoundService = myBinder.getService();
            isBoundConnected = true;
            Toast.makeText(myBoundService, "Service is bound", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            Log.d(TAG, "onServiceDisconnected: ");
            Toast.makeText(myBoundService, "Service is disconnected", Toast.LENGTH_SHORT).show();
            isBoundConnected = false;

        }
    };
}
