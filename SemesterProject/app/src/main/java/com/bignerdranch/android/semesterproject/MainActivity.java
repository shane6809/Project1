package com.bignerdranch.android.semesterproject;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
    private Button mStartButton;
    private Button mHighScoresButton;
    private Button mSettingsButton;
    private Button mHelpButton;
    boolean mExternalStorageAvailable = false;
    boolean mExternalStorageWriteable = false;







    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate(Bundle)called");
        setContentView(R.layout.activity_main);


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
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "onPause() called");
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




