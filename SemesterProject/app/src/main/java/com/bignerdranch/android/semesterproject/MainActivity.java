package com.bignerdranch.android.semesterproject;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.nfc.Tag;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final String TAG="Memory Match";

    /*button variables */
    private Button mStartButton;
    private Button mHighScoresButton;
    private Button mSettingsButton;
    private Button mHelpButton;

    /*Storage variables*/
    boolean mExternalStorageAvailable = false;
    boolean mExternalStorageWriteable = false;


    /*light sensor variables*/
    private SensorManager sensorManager;
    private Sensor lightSensor;
    private SensorEventListener lightSensorListener;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate(Bundle)called");
        setContentView(R.layout.activity_main);
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

        lightSensor =
                sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);

        //message for hardware not having the correct sensor
        if (lightSensor == null) {
            Toast.makeText(this, "You do not have the necessary sensor",
                    Toast.LENGTH_SHORT).show();
            finish();

        }

        // Create listener
        lightSensorListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent sensorEvent) {
                float value = sensorEvent.values[0];

                // Code for screen change based on light
                if (sensorEvent.values[0] > 30000f) { // Brighter color for easy reading in high light
                    getWindow().getDecorView().setBackgroundColor(Color.WHITE);
                    Toast.makeText(MainActivity.this, "Luminosity" + value, Toast.LENGTH_SHORT).show();

                }

                if (sensorEvent.values[0] > 22001f && sensorEvent.values[0] < 29999) { // Brighter color for easy reading in medium-high light
                    getWindow().getDecorView().setBackgroundColor(Color.YELLOW);
                    Toast.makeText(MainActivity.this, "Luminosity" + value, Toast.LENGTH_SHORT).show();

                }

                if (sensorEvent.values[0] > 17001f && sensorEvent.values[0] < 22000) { // Brighter color for easy reading in medium light
                    getWindow().getDecorView().setBackgroundColor(Color.RED);
                    Toast.makeText(MainActivity.this, "Luminosity" + value, Toast.LENGTH_SHORT).show();

                }

                if (sensorEvent.values[0] > 12001f && sensorEvent.values[0] < 17000) { // Brighter color for easy reading in medium light
                    getWindow().getDecorView().setBackgroundColor(Color.BLUE);
                    Toast.makeText(MainActivity.this, "Luminosity" + value, Toast.LENGTH_SHORT).show();

                } else if (sensorEvent.values[0] < 12000f) { // Night mode for low light readability using DarkViolet
                    getWindow().getDecorView().setBackgroundColor(getResources().getColor(R.color.DarkViolet));
                    Toast.makeText(MainActivity.this, "Luminosity" + value, Toast.LENGTH_SHORT).show();

                }
            }
            @Override
            public void onAccuracyChanged(Sensor sensor, int i) {
            }
        };

        //check external storage state
        String state = Environment.getExternalStorageState();

        if (Environment.MEDIA_MOUNTED.equals(state)) {
            // can read and write the media
            mExternalStorageAvailable = mExternalStorageWriteable = true;
        } else if (Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {

            // only read the media
            mExternalStorageAvailable = true;
            mExternalStorageWriteable = false;
        } else {
            // can not read or write
            mExternalStorageAvailable = mExternalStorageWriteable = false;
        }



        mStartButton=(Button)findViewById(R.id.start_button);
        mStartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //start button
                Intent intent = new Intent(MainActivity.this, game_select.class);
                startActivity(intent);

            }
        });

        mHelpButton=(Button)findViewById(R.id.help_button);
        mHelpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, game_help.class);
                startActivity(intent);
            }
        });

        mSettingsButton=(Button)findViewById(R.id.settings_button);
        mSettingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, game_settings.class);
                startActivity(intent);
            }
        });

        mHighScoresButton=(Button)findViewById(R.id.high_button);
        mHighScoresButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, highscores_page.class);
                startActivity(intent);
            }
        });




    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, "onStart() called");


    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume() called");
        sensorManager.registerListener(lightSensorListener,
                lightSensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "onPause() called");
        sensorManager.unregisterListener(lightSensorListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG, "onStop() called");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy() called");
    }






}




