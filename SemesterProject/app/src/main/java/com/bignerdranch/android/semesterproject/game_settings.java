package com.bignerdranch.android.semesterproject;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.support.v4.widget.CompoundButtonCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;


public class game_settings extends AppCompatActivity {

    private Button mBackButton;
    private ToggleButton mToggleButton;
    private ToggleButton sToggleButton;
    private EditText nameInput;
    private SharedPreferences prefs;
    private Switch mSwitch;
    MediaPlayer mediaPlayer;







    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_settings);

        final Switch mSwitch = (Switch) findViewById(R.id.switch1);


        //get user name data
        prefs = getSharedPreferences("MY_DATA", MODE_PRIVATE);

        nameInput =(EditText)findViewById(R.id.editUserName);

        String name = prefs.getString("MY_NAME", "");





        //back button
        mBackButton = (Button) findViewById(R.id.back_button);

        //on click listener for back button
        mBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(game_settings.this, MainActivity.class);
                startActivity(intent);
            }
        });

        /*toggle button for music
       // mToggleButton = (ToggleButton) findViewById(R.id.toggleButton);

        //on click listener for music
       // mToggleButton.setOnClickListener(new ToggleButton.OnClickListener() {

           // public void onClick(View v) {
                SharedPreferences sharedPreferences = PreferenceManager
                        .getDefaultSharedPreferences(getApplicationContext());
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean("toggleButton", mToggleButton.isChecked());
                editor.commit();
            }
        });

        //Toggle button for sound
       // sToggleButton = (ToggleButton) findViewById(R.id.toggleButton2);

        //on click listener for sound
       // sToggleButton.setOnClickListener(new ToggleButton.OnClickListener() {

            //public void onClick(View v) {
                SharedPreferences sharedPreferences2 = PreferenceManager
                        .getDefaultSharedPreferences(getApplicationContext());
                SharedPreferences.Editor editor = sharedPreferences2.edit();
                editor.putBoolean("stoggleButton", sToggleButton.isChecked());
                editor.commit();

            }


        });
*/

        mediaPlayer = MediaPlayer.create(getApplicationContext()
                ,R.raw.ben);




        mSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {




            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                //mSwitch.setChecked(true);

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




        nameInput.setText(name);

        //toggle button for music save preference
        SharedPreferences sharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(getApplicationContext());
        mToggleButton.setChecked(sharedPreferences.getBoolean("toggleButton", true));  //default is set to true

        //toggle button for sound save preference
        SharedPreferences sharedPreferences2 = PreferenceManager
                .getDefaultSharedPreferences(getApplicationContext());
        sToggleButton.setChecked(sharedPreferences.getBoolean("stoggleButton", true));  //default is false


        //toggle button for sound save preference
        SharedPreferences sharedPreferences3 = PreferenceManager
                .getDefaultSharedPreferences(getApplicationContext());
        mSwitch.setChecked(sharedPreferences.getBoolean("switch1", true));


    }
    public void saveData(View view){
        // Get user name
        String name = nameInput.getText().toString();






        // Save data for the user name
        SharedPreferences.Editor editor = prefs.edit();


        editor.putString("MY_NAME", name);


        editor.apply();



    }
}




