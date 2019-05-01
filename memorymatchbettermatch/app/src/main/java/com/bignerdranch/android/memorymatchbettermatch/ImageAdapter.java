package com.bignerdranch.android.memorymatchbettermatch;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceScreen;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.view.ViewGroup;
import android.os.Handler;
import android.widget.Toast;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import android.util.Log;

import static android.content.Context.MODE_PRIVATE;


public class ImageAdapter extends BaseAdapter {
    public static int scored;
    private Context mContext;
    private Integer[] mTiles;
    private List imageViews;
    private int mCurrentPosition = -1;
    private int countPairs = 0;
    public static int score = 0;






    int defaultInt = 0;
    int hiScore;









    public ImageAdapter(Context c) {



        mContext = c;
        List tiles = new ArrayList();
        for(int i=0; i<12; i++) {
            tiles.add(i);
            tiles.add(i);
        }
        Collections.shuffle(tiles);
        mTiles = (Integer[]) tiles.toArray(new Integer[0]);
        getView();
    }

    private void getView() {
        imageViews = new ArrayList();
        for(int position = 0; position < getCount(); position++) {
            ImageView imageView;

            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams(200, 200));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(2, 2, 2, 2);

            imageView.setImageResource(R.drawable.question);
            imageViews.add(imageView);

            installClick(position);
        }
    }

    public int getCount() {
        return 24;
    }

    public Object getItem(int position) {
        return imageViews.get(position);
    }

    public long getItemId(int position) {
        return mTiles[position].longValue();
    }


    public synchronized View getView(int position, View convertView, ViewGroup parent) {
        return (ImageView) imageViews.get(position);
    }



    public void installClick(int position) {

        final ImageAdapter self = this;
        ImageView imageView =(ImageView)  imageViews.get(position);
        imageView.setOnClickListener(new View.OnClickListener() {


            public void onClick(View v) {
                int pos = imageViews.indexOf((ImageView) v);
                 show(pos);



                if (mCurrentPosition == -1 || mCurrentPosition == pos) {

                    mCurrentPosition = pos;
                } else {

                    if (mTiles[pos] == mTiles[mCurrentPosition]) {

                        score= score +500;

                        Toast.makeText(mContext,
                                "Match! Score: "+ score, Toast.LENGTH_SHORT).show();

                        countPairs++;



                        removeClick(pos);
                        removeClick(mCurrentPosition);

                    }


                    else {
                        int aux[] = {mCurrentPosition, pos};
                        SleepHide update = new SleepHide(mContext, self, aux);
                        Handler mHandler = new Handler();
                        mHandler.postDelayed(update, 1000);

                        score = score -100;



                        Toast.makeText(mContext,
                                "No Match! Score: "+ score,Toast.LENGTH_SHORT).show();


                    }


                    if (countPairs ==12){

                        Toast.makeText(mContext,
                                "You have Won! Your Score was " + score, Toast.LENGTH_SHORT).show();



                            scored = score;




                    }


                    mCurrentPosition = -1;


                }



            }

        });

    }



    public void removeClick(int position) {
        ImageView aux;
        aux = (ImageView) imageViews.get(position);
        aux.setOnClickListener(null);

    }

    public void hide(int position) {
        ImageView img;
        img = (ImageView) imageViews.get(position);
        int piece = mTiles[position];
        img.setImageResource(R.drawable.question);

    }

    public void show(int position) {
        ImageView img;
        img = (ImageView) imageViews.get(position);
        int piece = mTiles[position];
        img.setImageResource(mDrawable[piece]);

    }

    // Drawables, for tiles
    private Integer[] mDrawable = {
            R.drawable.chin,
            R.drawable.cry,
            R.drawable.fist,
            R.drawable.glasses,
            R.drawable.hands,
            R.drawable.horse,
            R.drawable.princess,
            R.drawable.rabbit,
            R.drawable.rat,
            R.drawable.sheep,
            R.drawable.whistle,
            R.drawable.dog,
    };
}