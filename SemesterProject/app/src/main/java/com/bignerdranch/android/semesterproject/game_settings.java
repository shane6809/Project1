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
    EditText userName;
    TextView dataView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_settings);

        mBackButton = (Button) findViewById(R.id.back_button);
        mBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(game_settings.this, MainActivity.class);
                startActivity(intent);
            }
        });

        userName = (EditText) findViewById(R.id.editUserName);
    }

    public void saveData(View view) {
        SharedPreferences loginData = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = loginData.edit();
        editor.putString("userName", userName.getText().toString());
        editor.apply();
        String name = loginData.getString("userName", "");

        Toast.makeText(this, "Saved" + " " + name, Toast.LENGTH_LONG).show();

    }


}