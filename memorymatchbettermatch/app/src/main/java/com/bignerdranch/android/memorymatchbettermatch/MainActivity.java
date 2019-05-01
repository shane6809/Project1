package com.bignerdranch.android.memorymatchbettermatch;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;

public class MainActivity extends Activity
{
Button highscore;

int scorer = ImageAdapter.scored;

//private SharedPreferences prefs;



@Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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

                Intent intent =new Intent(MainActivity.this,BestActivity.class);
                startActivity(intent);
                finish();


            }
        });
    }

    private void buildTiles()
    {
        GridView gridview = (GridView) findViewById(R.id.gridview);
        gridview.setAdapter(new ImageAdapter(this));
    }



}
