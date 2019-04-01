package com.bignerdranch.android.semesterproject;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class game_settings extends AppCompatActivity {

    private Button mBackButton;

    private EditText nameInput;
    private SharedPreferences prefs;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_settings);

        prefs = getSharedPreferences("MY_DATA", MODE_PRIVATE);
        nameInput =(EditText)findViewById(R.id.editUserName);
        String name = prefs.getString("MY_NAME", "");

        mBackButton = (Button) findViewById(R.id.back_button);
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
        // Get input text.
        String name = nameInput.getText().toString();


        // Save data.
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("MY_NAME", name);
        editor.apply();


    }
}




