package com.example.admin.services;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

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
        }
    }
}
