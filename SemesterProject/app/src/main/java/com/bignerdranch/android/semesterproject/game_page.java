package com.bignerdranch.android.semesterproject;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;

public class game_page extends Activity
{
    Button highscore;
    Button mBackButton;

    int scorer = ImageAdapter.scored;

//private SharedPreferences prefs;



    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_page);
        buildTiles();

        // prefs = getSharedPreferences("MY_DATA", MODE_PRIVATE);

        // SharedPreferences sharedPreferences = PreferenceManager
        //  .getDefaultSharedPreferences(getApplicationContext());


        highscore =(Button) findViewById(R.id.highscore);
        highscore.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                SharedPreferences preferences = getSharedPreferences("PREFS", 0);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putInt("lastScore", scorer);
                editor.commit();

                Intent intent =new Intent(game_page.this, highscores_page.class);
                startActivity(intent);
                finish();


            }
        });

        mBackButton = (Button) findViewById(R.id.back_button);
        mBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(game_page.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void buildTiles()
    {
        GridView gridview = (GridView) findViewById(R.id.gridview);
        gridview.setAdapter(new ImageAdapter(this));
    }



}