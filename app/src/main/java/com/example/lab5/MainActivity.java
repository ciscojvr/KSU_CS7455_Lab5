/*

Name: Francisco Ozuna Diaz
Assignment: CS 7455 Lab 5
Lab Date: Due June 28, 2020 at 11:59 PM

 */

package com.example.lab5;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button startServiceButton;
    Button stopServiceButton;
    Button bindServiceButton;
    Button unbindServiceButton;
    Button getRandomNumberButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startServiceButton = (Button) findViewById(R.id.button_startService);
        startServiceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        stopServiceButton = (Button) findViewById(R.id.button_stopService);
        stopServiceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        bindServiceButton = (Button) findViewById(R.id.button_bindService);
        bindServiceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        unbindServiceButton = (Button) findViewById(R.id.button_unbindService);
        unbindServiceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        getRandomNumberButton = (Button) findViewById(R.id.button_getRandomNumber);
        getRandomNumberButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}