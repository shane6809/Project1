package com.bignerdranch.android.semesterproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class game_select extends AppCompatActivity {
    private Button mBackButton;
    private Button mGame1Button;
    private Button mGame2Button;
    private Button mGame3Button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_game_select);
        {
            mBackButton=(Button)findViewById(R.id.back_button);
            mBackButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(game_select.this, MainActivity.class);
                    startActivity(intent);
                }
            });

            mGame1Button=(Button)findViewById(R.id.game1);
            mGame1Button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(game_select.this, game_page.class);
                    startActivity(intent);
                }
            });

            mGame2Button=(Button)findViewById(R.id.game2);
            mGame2Button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(game_select.this, game_page.class);
                    startActivity(intent);
                }
            });

            mGame3Button=(Button)findViewById(R.id.game3);
            mGame3Button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(game_select.this, game_page.class);
                    startActivity(intent);
                }
            });

        }

    }

}
