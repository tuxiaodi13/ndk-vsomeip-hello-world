package com.example.vsomeiphelloworld;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("MainActivityJNI");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Android Studio reference example of calling a native method
        TextView tv = findViewById(R.id.sample_text);
        tv.setText(stringFromJNI());

        startService(new Intent(this, ServiceVsomeipHelloWorldService.class));
        startService(new Intent(this, ServiceVsomeipHelloWorldClient.class));
    }

    public native String stringFromJNI();
}
