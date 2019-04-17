package com.bignerdranch.android.semesterproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

public class game_page extends AppCompatActivity {

    ImageView mCurView = null;

    private int countPairs = 0;
    final int[] drawable = new int[] {R.drawable.cry, R.drawable.cat,
            R.drawable.chin, R.drawable.fist, R.drawable.glasses,
            R.drawable.hands, R.drawable.princess, R.drawable.whistle };

    int[] mPosition = {0,1,2,3,4,5,6,7,0,1,2,3,4,5,6,7};
    int mCurrentPosition = -1;




    private Button mBackButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_page);


        final GridView gridView = findViewById(R.id.gridView);
        ImageAdapter imageAdapter = new ImageAdapter(this);
        gridView.setAdapter(imageAdapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(mCurrentPosition <0){

                    mCurrentPosition = position;


                    mCurView = (ImageView)view;
                    ((ImageView)view).setImageResource
                            (drawable[mPosition[position]]);



                }

                else{

                    if(mCurrentPosition == position){

                        ((ImageView)view).setImageResource
                                (R.drawable.question);




                    }

                    else if (mPosition[mCurrentPosition] != mPosition[position]) {

                        mCurView.setImageResource(R.drawable.question);
                        Toast.makeText(getApplicationContext(),
                                "No Match",Toast.LENGTH_SHORT).show();

                    }

                    else{


                        ((ImageView)view).setImageResource
                                (drawable[mPosition[position]]);




                        Toast.makeText(getApplicationContext(),
                                "Match",Toast.LENGTH_SHORT).show();

                        countPairs++;

                        if(countPairs ==8 && countPairs <9){

                            gridView.setOnItemClickListener(null);

                            Toast.makeText(getApplicationContext(),
                                    "Try Again for a Perfect Score",Toast.LENGTH_SHORT).show();

                        }


                        if(countPairs ==8 && countPairs >9){

                            gridView.setOnItemClickListener(null);

                            Toast.makeText(getApplicationContext(),
                                    "You have Won", Toast.LENGTH_SHORT).show();

                        }

                    }


                    mCurrentPosition = -1;





                }
            }
        });


    }

}



