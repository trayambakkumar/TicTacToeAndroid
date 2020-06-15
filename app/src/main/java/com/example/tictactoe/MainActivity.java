package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //     0 - X, 1 - O
    int activePlayer = 0;
    //    0 - X, 1 - O, 2 - Blank
    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};
    int[][] winPositions =  {{0, 1, 2}, {3, 4, 5}, {6, 7, 8},
                            {0, 3, 6}, {1, 4, 7}, {2, 5, 8},
                            {0, 4, 8}, {2, 4, 6}};
    int count = 0;
    boolean gameActive = true;
    public void playerTap(View view) {
        ImageView image = (ImageView) view;
        int imageTag = Integer.parseInt(image.getTag().toString());
        if(!gameActive) {
            gameReset();
            return;
        }
        if(count == 9) {
            gameReset();
            return;
        }
        if(gameState[imageTag] == 2) {
            count++;
            gameState[imageTag] = activePlayer;
            image.setTranslationY(-1000f);
            if (activePlayer == 0) {
                image.setImageResource(R.drawable.x);
                activePlayer = 1;
            } else {
                image.setImageResource(R.drawable.o);
                activePlayer = 0;
            }
            image.animate().translationYBy(1000f).setDuration(300);
        }
        for(int[] winPosition : winPositions) {
            if(gameState[winPosition[0]] == gameState[winPosition[1]] && gameState[winPosition[1]] == gameState[winPosition[2]] && gameState[winPosition[0]] != 2) {
                gameActive = false;
                if(gameState[winPosition[0]] == 0) {
                    Toast toast = Toast.makeText(getApplicationContext(), "X Won", Toast.LENGTH_SHORT);
                    toast.show();
                } else {
                    Toast toast = Toast.makeText(getApplicationContext(), "O Won", Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        }
        if(count == 9) {
            Toast toast = Toast.makeText(getApplicationContext(), "Tie", Toast.LENGTH_SHORT);
            toast.show();
        }
    }
    public void gameReset() {
        count = 0;
        gameActive = true;
        activePlayer = 0;
        for(int i = 0; i < gameState.length; i++) {
            gameState[i] = 2;
        }
        ((ImageView) findViewById(R.id.imageView0)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView1)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView2)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView3)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView4)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView5)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView6)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView7)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView8)).setImageResource(0);
    }
}
