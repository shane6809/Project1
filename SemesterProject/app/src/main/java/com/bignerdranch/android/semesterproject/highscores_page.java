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

        int lastScore = ImageAdapter.scored;
        int best1, best2, best3;
        String temper;
        String name, name1, name2, name3;




        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_highscores_page);

            tv_score = (TextView) findViewById(R.id.tv_score);

            SharedPreferences preferences = getSharedPreferences("PREFS", 0);
            //lastScore = preferences.getInt("lastScore",0);
            best1 = preferences.getInt("best1", 0);
            best2 = preferences.getInt("best2", 0);
            best3 = preferences.getInt("best3", 0);
            lastScore=preferences.getInt("last",lastScore);

            prefs = getSharedPreferences("MY_DATA", MODE_PRIVATE);
            name = prefs.getString("MY_NAME", "");
           // name1 = prefs.getString("name1","");
           // name2 = prefs.getString("name2","");
           // name3 = prefs.getString("name3","");

            //SharedPreferences.Editor editor = preferences.edit();
            //editor.putString("MY_NAME",name);
           // editor.apply();



            if (lastScore > best3) {
                best3 = lastScore;
                //name3 = name;
                SharedPreferences.Editor editor = preferences.edit();
                editor.putInt("best3", best3);
                //editor.putString("name3", name3);
                editor.apply();
            }

            if (lastScore > best2) {
                int temp = best2;
                //temper = name2;
                best2 = lastScore;
               // name2 =name;
                best3 = temp;
               // name3 = temper;

                SharedPreferences.Editor editor = preferences.edit();
                editor.putInt("best3", best3);
                editor.putInt("best2", best2);
                //editor.putString("name3", name3);
               // editor.putString("name2", name2);
                editor.apply();
            }
            if(lastScore > best1){
                int temp= best1;
               // temper = name;
                best1=lastScore;
                name1 = name;
                best2=temp;
                //name2 = temper;

                SharedPreferences.Editor editor = preferences.edit();
                editor.putInt("best2",best2);
                editor.putInt("best1",best1);
                //editor.putString("name2", name2);
               editor.putString("name", name1);
                editor.apply();
            }




            tv_score.setText("LAST SCORE: " + name + "\n" + lastScore + "\n" +
                    "Top Score: " + name1 + "\n" + best1 + "\n" +
                    "Second Place: " + best2 + "\n" +
                    "Third Place: " + best3);


            SharedPreferences.Editor editor = preferences.edit();
            editor.putInt ("last",lastScore);
            editor.putString("name",name1);
            editor.apply();

           /* mBackButton = (Button) findViewById(R.id.back_button);
            mBackButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(highscores_page.this, MainActivity.class);
                    startActivity(intent);
                }
            });

            */
        }
    }

