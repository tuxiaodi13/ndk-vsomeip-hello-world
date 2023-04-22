package com.example.vsomeiphelloworld;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.system.ErrnoException;
import android.system.Os;
import android.util.Log;

import java.io.File;

public class ServiceVsomeipHelloWorldClient extends Service {
    private static final String TAG = "ServiceVsomeipHelloWorldClient";

    public ServiceVsomeipHelloWorldClient() {
    }

    @Override
    public void onCreate() {
        super.onCreate();

        Log.d(TAG, "onCreate()");
    }

    @Override
    public void onDestroy() {
        Log.d(TAG, "onDestroy()");

        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "onBind()");

        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand()");

        initVSomeIp();

        System.loadLibrary("ServiceVsomeipHelloWorldClientJNI");

        Log.d(TAG, runNative());

        return START_REDELIVER_INTENT;
    }

    public native String runNative();


    private void initVSomeIp() {
        File vsomeipBaseDir = new File(getCacheDir(), "vsomeip");
        boolean ret = vsomeipBaseDir.mkdir();
        Log.d(TAG, "init_vsomeip() mkdir ret=" + ret);

        try {
            Os.setenv("VSOMEIP_BASE_PATH", vsomeipBaseDir.getAbsolutePath() + "/", true);
        } catch (ErrnoException e) {
            e.printStackTrace();
        }

        Log.d(TAG, "vsomeipBaseDir: " + vsomeipBaseDir.getAbsolutePath());
        Log.d(TAG, "Os.getenv(\"VSOMEIP_BASE_PATH\"): " + Os.getenv("VSOMEIP_BASE_PATH"));
    }
}

