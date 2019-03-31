package com.bignerdranch.android.sensorpractice;

import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    /* Test 4 after File Sync, sensor template, light sensor */
    private SensorManager sensorManager;
    private Sensor lightSensor;
    private SensorEventListener lightSensorListener;


    //Sensor managers
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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

                if (sensorEvent.values[0] > 22001f && sensorEvent.values[0]< 29999) { // Brighter color for easy reading in medium-high light
                    getWindow().getDecorView().setBackgroundColor(Color.YELLOW);
                    Toast.makeText(MainActivity.this, "Luminosity" + value, Toast.LENGTH_SHORT).show();

                }

                if (sensorEvent.values[0] > 17001f && sensorEvent.values[0]< 22000) { // Brighter color for easy reading in medium light
                    getWindow().getDecorView().setBackgroundColor(Color.RED);
                    Toast.makeText(MainActivity.this, "Luminosity" + value, Toast.LENGTH_SHORT).show();

                }

                if (sensorEvent.values[0] > 12001f && sensorEvent.values[0]< 17000) { // Brighter color for easy reading in medium light
                    getWindow().getDecorView().setBackgroundColor(Color.BLUE);
                    Toast.makeText(MainActivity.this, "Luminosity" + value, Toast.LENGTH_SHORT).show();

                }

                else if (sensorEvent.values[0] < 12000f) { // Night mode for low light readability using DarkViolet
                    getWindow().getDecorView().setBackgroundColor(getResources().getColor(R.color.DarkViolet));
                    Toast.makeText(MainActivity.this, "Luminosity" + value, Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int i) {
            }
        };
    }

    // Register the listener
    @Override
    protected void onResume(){
        super.onResume();
        sensorManager.registerListener(lightSensorListener,
                lightSensor, SensorManager.SENSOR_DELAY_NORMAL);

    }

    //Unregister listener on pause
    @Override
    protected void onPause(){
        super.onPause();
        sensorManager.unregisterListener(lightSensorListener);
    }

}

