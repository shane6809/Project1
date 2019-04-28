package com.bignerdranch.android.memorymatchbettermatch;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;

import static com.bignerdranch.android.memorymatchbettermatch.ImageAdapter.score;

public class MainActivity extends Activity
{
Button highscore;





@Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buildTiles();


        highscore =(Button) findViewById(R.id.highscore);
        highscore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(MainActivity.this,BestActivity.class);
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
