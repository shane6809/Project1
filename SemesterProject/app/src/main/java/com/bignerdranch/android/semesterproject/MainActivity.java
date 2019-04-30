package com.bignerdranch.android.semesterproject;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.nfc.Tag;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
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

    //Media player for background music
    MediaPlayer mediaPlayer;
    private SharedPreferences prefs;
    private Switch mSwitch;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate(Bundle)called");
        setContentView(R.layout.activity_main);

        final Switch mSwitch = (Switch) findViewById(R.id.switch1);

        mediaPlayer = MediaPlayer.create(getApplicationContext()
                ,R.raw.ben);

        prefs = getSharedPreferences("MY_DATA", MODE_PRIVATE);



        mSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {




            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {


                SharedPreferences sharedPreferences3 = PreferenceManager.
                        getDefaultSharedPreferences(getApplicationContext());
                SharedPreferences.Editor editor = sharedPreferences3.edit();
                editor.putBoolean("switch1", mSwitch.isChecked());
                editor.commit();






                if (isChecked) {
                    // The toggle is enabled


                    mediaPlayer.start();

                    editor.putBoolean("switch1", true);
                    editor.commit();


                } else {
                    // The toggle is disabled
                    // mediaPlayer.reset();
                    mediaPlayer.pause();
                    // mediaPlayer = null;
                    editor.putBoolean("switch1", false);
                    editor.commit();

                }


            }
        });



        SharedPreferences sharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(getApplicationContext());

//toggle button for sound save preference
        SharedPreferences sharedPreferences3 = PreferenceManager
                .getDefaultSharedPreferences(getApplicationContext());
        mSwitch.setChecked(sharedPreferences.getBoolean("switch1", true));




        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

        lightSensor =
                sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);

        //message for hardware not having the correct sensor
        if (lightSensor == null) {
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

                }

                if (sensorEvent.values[0] > 22001f && sensorEvent.values[0] < 29999) { // Brighter color for easy reading in medium-high light
                    getWindow().getDecorView().setBackgroundColor(Color.YELLOW);

                }

                if (sensorEvent.values[0] > 17001f && sensorEvent.values[0] < 22000) { // Brighter color for easy reading in medium light
                    getWindow().getDecorView().setBackgroundColor(Color.RED);

                }

                if (sensorEvent.values[0] > 12001f && sensorEvent.values[0] < 17000) { // Brighter color for easy reading in medium light
                    getWindow().getDecorView().setBackgroundColor(Color.BLUE);

                } else if (sensorEvent.values[0] < 12000f) { // Night mode for low light readability using DarkViolet
                    getWindow().getDecorView().setBackgroundColor(getResources().getColor(R.color.DarkViolet));

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

      /*  mSettingsButton=(Button)findViewById(R.id.settings_button);
        mSettingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, game_settings.class);
                startActivity(intent);
            }
        });

        */

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




