package com.bignerdranch.android.simplememorymatch;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ImageView curView = null;
    private int countPair = 0;
    final int[] drawable = new int[] {R.drawable.blue, R.drawable.turkey,
            R.drawable.goat, R.drawable.bird, R.drawable.rabbit,
            R.drawable.horse, R.drawable.sheep, R.drawable.dog };

    int[] pos = {0,1,2,3,4,5,6,7,0,1,2,3,4,5,6,7};
    int currentPos = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GridView gridView = findViewById(R.id.gridView);
        ImageAdapter imageAdapter = new ImageAdapter(this);
        gridView.setAdapter(imageAdapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(currentPos <0){

                    currentPos = position;
                    curView = (ImageView)view;
                    ((ImageView)view).setImageResource
                            (drawable[pos[position]]);

                }

                else{

                    if(currentPos == position){

                        ((ImageView)view).setImageResource
                                (R.drawable.question);
                    }

                    else if (pos[currentPos] != pos[position]) {

                        curView.setImageResource(R.drawable.question);
                        Toast.makeText(getApplicationContext(),
                                "No Match",Toast.LENGTH_SHORT).show();

                    }

                    else{

                        ((ImageView)view).setImageResource
                                (drawable[pos[position]]);

                        countPair++;

                        if(countPair == 0){

                            Toast.makeText(getApplicationContext(),
                                    "You have Won",Toast.LENGTH_SHORT).show();

                        }

                    }

                    currentPos = -1;

                }
            }
        });


    }
}
