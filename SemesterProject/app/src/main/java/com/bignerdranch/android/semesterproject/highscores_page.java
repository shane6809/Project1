package com.bignerdranch.android.semesterproject;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;




    public class highscores_page extends AppCompatActivity {

        TextView tv_score;

        private Button mBackButton;
        private SharedPreferences prefs;

        int lastScore= ImageAdapter.scored;
        int best1, best2, best3;
        String name, name1, name2, name3;




        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_highscores_page);

            //View for score board
            tv_score = (TextView) findViewById(R.id.tv_score);


            //shared preferences for scores
            SharedPreferences preferences = getSharedPreferences("PREFS", 0);
            //lastScore = preferences.getInt("lastScore",0);
            best1 = preferences.getInt("best1", 0);
            best2 = preferences.getInt("best2", 0);
            best3 = preferences.getInt("best3", 0);


            //Shared Preferences for Name
            prefs = getSharedPreferences("MY_DATA", MODE_PRIVATE);
            name = prefs.getString("MY_NAME", "");
           // name1 = prefs.getString("name1","");
           // name2 = prefs.getString("name2","");
           // name3 = prefs.getString("name3","");

            //SharedPreferences.Editor editor = preferences.edit();
            //editor.putString("MY_NAME",name);
           // editor.apply();


            //statements to save score to board
            if(lastScore > best3){
                best3 = lastScore;
                SharedPreferences.Editor editor = preferences.edit();
                editor.putInt("best3",best3);
                editor.apply();
            }

            if(lastScore > best2){
                int temp= best2;
                best2=lastScore;
                best3=temp;

                SharedPreferences.Editor editor = preferences.edit();
                editor.putInt("best3",best3);
                editor.putInt("best2",best2);
                editor.apply();
            }


            if(lastScore > best1){
                int temp= best1;
                best1=lastScore;
                best2=temp;

                SharedPreferences.Editor editor = preferences.edit();
                editor.putInt("best2",best2);
                editor.putInt("best1",best1);
                //editor.putString("name2", name2);
               editor.putString("name", name1);
                editor.apply();
            }



            //text for score board results, and past scores
            tv_score.setText("LAST SCORE: " + name + "\n" + lastScore + "\n" +
                    "Top Score: " + name1 + "\n" + best1 + "\n" +
                    "Second Place: " + best2 + "\n" +
                    "Third Place: " + best3);



            //button to leave high scores.
            mBackButton = (Button) findViewById(R.id.back_button);
            mBackButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(highscores_page.this, MainActivity.class);
                    startActivity(intent);
                }
            });


        }
    }

