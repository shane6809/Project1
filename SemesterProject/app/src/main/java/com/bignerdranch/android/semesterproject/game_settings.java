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

    private EditText nameInput;
    private SharedPreferences prefs;








    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_settings);




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




        nameInput.setText(name);




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




