package com.bignerdranch.android.justmemorygame;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;

import java.util.Random;

public class Game4x4 extends AppCompatActivity implements View.OnClickListener {

    private int numberOfElements;

    private MemoryButton[] buttons;

    private int[] buttonGraphicLocations;
    private int[] buttonGraphics;

    private MemoryButton selectedButton1;
    private MemoryButton selectedButton2;
    private boolean isBusy = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game4x4);

        GridLayout gridLayout = (GridLayout)findViewById
                (R.id.grid_layout_4x4);

        int numColumns = gridLayout.getColumnCount();
        int numRows = gridLayout.getRowCount();


        numberOfElements = numColumns * numRows;

        buttons = new MemoryButton[numberOfElements];


        buttonGraphics = new int[numberOfElements/2];

        buttonGraphicLocations[0] = R.drawable.biggoat;
        buttonGraphicLocations[1] = R.drawable.bird;
        buttonGraphicLocations[2] = R.drawable.dog;
        buttonGraphicLocations[3] = R.drawable.goat;
        buttonGraphicLocations[4] = R.drawable.horse;
        buttonGraphicLocations[5] = R.drawable.rabbit;
        buttonGraphicLocations[6] = R.drawable.sheep;
        buttonGraphicLocations[7] = R.drawable.turkey;

        buttonGraphicLocations = new int[numberOfElements];

        shuffleButtonGraphics();

        for (int r = 0; r < numRows; r++) {
            for(int c=0;c<numColumns;c++) {
                MemoryButton tempButton = new MemoryButton(this, r, c, buttonGraphics[buttonGraphicLocations[r * numColumns + c]]);
                tempButton.setId(View.generateViewId());
                tempButton.setOnClickListener(this);
                buttons[r * numColumns + c] = tempButton;
                gridLayout.addView(tempButton);
            }
        }
    }

    protected void shuffleButtonGraphics() {
        Random rand = new Random();

        for (int i = 0; i < numberOfElements; i++) {

            int temp = buttonGraphicLocations[i];
            int swapIndex = rand.nextInt(16);

            buttonGraphicLocations[i] = buttonGraphicLocations[swapIndex];
            buttonGraphicLocations[swapIndex] = temp;
        }

    }

    @Override
    public void onClick(View v) {
        if (isBusy) {
            return;
        }
        MemoryButton button = (MemoryButton)v;
        if (button.isMatched) {
            return;
        }
        if (selectedButton1 == null) {
            selectedButton1 = button;
            selectedButton1.flip();
            return;
        }
        if (selectedButton1.getId() == button.getId()) {

            return;
        }
        if (selectedButton1.getFrontDrawableId() == button.getFrontDrawableId()) {

            button.flip();

            button.setMatched(true);

            selectedButton1.setEnabled(false);
            selectedButton2.setEnabled(false);

            selectedButton1 = null;

            return;
        }

        else {
            selectedButton2 = button;
            selectedButton2.flip();
            isBusy = true;

            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {

                @Override
                public void run() {
                    selectedButton2.flip();
                    selectedButton1.flip();
                    selectedButton1 = null;
                    selectedButton2 = null;
                    isBusy = false;
                }
            }, 500);
        }
    }
}
