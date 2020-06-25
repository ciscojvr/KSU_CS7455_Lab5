/*

Name: Francisco Ozuna Diaz
Assignment: CS 7455 Lab 5
Lab Date: Due June 28, 2020 at 11:59 PM

 */

package com.example.lab5;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.Random;

public class RandomCharacterService extends Service {
    private char myRandomCharacter;
    private boolean isRandomGeneratorOn;

    private final int MIN = 0;
    private final int MAX = 25;

    char[] alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();

    private final String TAG = "RandomCharacterService";

    class RandomCharacterServiceBinder extends Binder {
        public RandomCharacterService getService() { return RandomCharacterService.this; }
    }

    private IBinder myBinder = new RandomCharacterServiceBinder();

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(TAG, "In OnStartCommand Thread ID is " + Thread.currentThread().getId());
        isRandomGeneratorOn = true;

        new Thread(new Runnable() {
            @Override
            public void run() {
                startRandomGenerator();
            }
        }).start();

        return START_STICKY;
    }

    private void startRandomGenerator() {
        while(isRandomGeneratorOn) {
            try {
                Thread.sleep(1000);
                if (isRandomGeneratorOn) {
                    int randomIdx = new Random().nextInt(MAX) + MIN;
                    myRandomCharacter = alphabet[randomIdx];
                    Log.i(TAG, "Thread ID is " + Thread.currentThread().getId() + ", Random Character is " + myRandomCharacter);
                }
            } catch (InterruptedException e) {
                Log.i(TAG, "Thread Interrupted.");
            }
        }
    }

    private void stopRandomGenerator() { isRandomGeneratorOn = false; }

    public char getRandomCharacter() { return myRandomCharacter; }

    @Override
    public void onDestroy() {
        super.onDestroy();
        stopRandomGenerator();
        Log.i(TAG,"Service Destroyed...");
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.i(TAG, "In onBind ...");
        return myBinder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.i(TAG, "In onUnbind...");
        return super.onUnbind(intent);
    }
}
