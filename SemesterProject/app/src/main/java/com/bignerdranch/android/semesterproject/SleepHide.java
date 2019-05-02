package com.bignerdranch.android.semesterproject;

import android.util.Log;
import java.lang.Runnable;
import android.content.Context;

import com.bignerdranch.android.semesterproject.ImageAdapter;


class SleepHide implements Runnable {
    private Context context;
    private int[] positions;
    private ImageAdapter adapter;

    public SleepHide(Context c, ImageAdapter b, int[] pos) {
        context = c;
        adapter = b;
        positions = pos;
        adapter.removeClick(pos[0]);
        adapter.removeClick(pos[1]);
    }

    public void run() {
        adapter.hide(positions[0]);
        adapter.hide(positions[1]);
        adapter.installClick(positions[0]);
        adapter.installClick(positions[1]);
    }

}