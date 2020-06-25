/*

Name: Francisco Ozuna Diaz
Assignment: CS 7455 Lab 5
Lab Date: Due June 28, 2020 at 11:59 PM

 */

package com.example.lab5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private final String TAG = "MainActivity";
    private Button startServiceButton;
    private Button stopServiceButton;
    private Button bindServiceButton;
    private Button unbindServiceButton;
    private Button getRandomCharacterButton;
    private TextView randomCharacterTextView;

    private Intent serviceIntent;

    private RandomCharacterService myService;
    private boolean isServiceBound;
    private ServiceConnection myServiceConnection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startServiceButton = (Button) findViewById(R.id.button_startService);
        stopServiceButton = (Button) findViewById(R.id.button_stopService);
        bindServiceButton = (Button) findViewById(R.id.button_bindService);
        unbindServiceButton = (Button) findViewById(R.id.button_unbindService);
        getRandomCharacterButton = (Button) findViewById(R.id.button_getRandomCharacter);

        randomCharacterTextView = (TextView) findViewById(R.id.textView_randomCharacter);

        serviceIntent = new Intent(getApplicationContext(), RandomCharacterService.class);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_startService:
                startService(serviceIntent);
                break;
            case R.id.button_stopService:
                stopService(serviceIntent);
                break;
            case R.id.button_bindService:
                bindService();
                break;
            case R.id.button_unbindService:
                unbindService();
                break;
            case R.id.button_getRandomCharacter:
                setRandomCharacter();
                break;
        }
    }

    private void bindService() {
        if (myServiceConnection == null) {
            myServiceConnection = new ServiceConnection() {
                @Override
                public void onServiceConnected(ComponentName name, IBinder iBinder) {
                    RandomCharacterService.RandomCharacterServiceBinder myServiceBinder = (RandomCharacterService.RandomCharacterServiceBinder)iBinder;
                    myService = myServiceBinder.getService();
                    isServiceBound = true;
                }

                @Override
                public void onServiceDisconnected(ComponentName componentName) {
                    isServiceBound = false;
                }
            };
        }
        bindService(serviceIntent, myServiceConnection, Context.BIND_AUTO_CREATE);
    }

    private void unbindService() {
        if (isServiceBound) {
            unbindService(myServiceConnection);
            isServiceBound = false;
        }
    }

    private void setRandomCharacter() {
        if (isServiceBound) {
            randomCharacterTextView.setText("Random Character: " + myService.getRandomCharacter());
        } else {
            randomCharacterTextView.setText("Service is not bound.");
        }
    }
}