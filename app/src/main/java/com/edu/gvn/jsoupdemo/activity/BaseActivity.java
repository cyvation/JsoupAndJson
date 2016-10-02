package com.edu.gvn.jsoupdemo.activity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.edu.gvn.jsoupdemo.service.PlayService;

/**
 * Created by hnc on 30/09/2016.
 */

public class BaseActivity extends AppCompatActivity {
    private static final String KEY_PREFERENCE = "com.edu.gvn.jsoupdemo.shareref";
    private static final String TAG = BaseActivity.class.getSimpleName();
    public PlayService mPlayService;
    private boolean isServiceConnected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences firstRunPreferences = getSharedPreferences(KEY_PREFERENCE, Context.MODE_PRIVATE);
        if (firstRunPreferences.getBoolean("first.run", true)) {
            startService(new Intent(this, PlayService.class));
            Log.i(TAG, "onResume: first");
            firstRunPreferences.edit().putBoolean("first.run", false).apply();
        }

    }

    @Override
    protected void onStart() {
        super.onStart();
        Intent iConn = new Intent(this, PlayService.class);
        startService(iConn);
        bindService(iConn, mServiceConn, Context.BIND_AUTO_CREATE);

    }

    @Override
    protected void onStop() {
        super.onStop();
        if (isServiceConnected) {
            unbindService(mServiceConn);
            isServiceConnected = false;
        }
    }

    private ServiceConnection mServiceConn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            PlayService.LocalBinder localBinder = (PlayService.LocalBinder) service;
            mPlayService = localBinder.getService();
            isServiceConnected = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            isServiceConnected = false;
        }
    };
}
