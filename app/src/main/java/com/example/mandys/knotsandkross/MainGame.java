package com.example.mandys.knotsandkross;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class MainGame extends AppCompatActivity {
    String winner = "";
    String head = "";
    int activePlayer = 0; // 0 is for Knot
    int [] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};// 2 means not-played location
    int [][] winningLocations = {{0, 1, 2}, {3, 4, 5},
            {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8},
            {0, 4, 8}, {2, 4, 6}};
    boolean gameOver = false;
    Intent i;

    public void getImageLogic(View view){
        ImageView tapped = (ImageView) view;

        int tappedLocation = Integer.parseInt(view.getTag().toString());

        if(gameState[tappedLocation] == 2 && !gameOver) {

            gameState[tappedLocation] = activePlayer;

            tapped.setTranslationY(-3000f);

            if (activePlayer == 0) {
                tapped.setImageResource(R.drawable.krk);
                final Toast toast = Toast.makeText(getApplicationContext(), "Kross player's move", Toast.LENGTH_SHORT);
                toast.show();
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        toast.cancel();
                    }
                }, 1000);
                activePlayer = 1;
            } else if (activePlayer == 1) {
                tapped.setImageResource(R.drawable.krkn);
                final Toast toast = Toast.makeText(getApplicationContext(), "Knot's player's move", Toast.LENGTH_SHORT);
                toast.show();
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        toast.cancel();
                    }
                }, 1000);

                activePlayer = 0;
            }
            tapped.animate().translationYBy(3000f).setDuration(500);


            for (int[] winningPosition : winningLocations) {

                if (gameState[winningPosition[0]] == gameState[winningPosition[1]]
                        && gameState[winningPosition[1]] == gameState[winningPosition[2]]
                        && gameState[winningPosition[0]] != 2) {

                    if (activePlayer == 0) {
                        //Toast.makeText(getApplicationContext(), "Player B is winner:)", Toast.LENGTH_SHORT).show();
                        winner = "Kross's player is the winner";
                        head = "Congratulations!!";


                    } else if (activePlayer == 1) {
                        //Toast.makeText(getApplicationContext(), "Player A is winner:)", Toast.LENGTH_SHORT).show();
                        winner = "Knot's player is the winner";
                        head = "Congratulations!!";
                    }
                /*else{
                    winner = "Match is drawn!!Equal wits eh...";
                    head = "Well Played!!";
                    }*/
                    gameOver = true;
                    Intent intent = new Intent(MainGame.this, WinnersActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    Bundle extras = new Bundle();
                    extras.putString("winner name", winner);
                    extras.putString("header", head);
                    intent.putExtras(extras);
                    startActivity(intent);
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);

                }
            }
        }
        else{
            //Toast.makeText(getApplicationContext(), "Match is drawn", Toast.LENGTH_SHORT).show();
            winner = "Match is drawn!!";
            head = "Well Played!!";
            Intent intent = new Intent(MainGame.this, WinnersActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            Bundle extras = new Bundle();
            extras.putString("winner name", winner);
            extras.putString("header", head);
            intent.putExtras(extras);
            startActivity(intent);
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);


        }


    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_game);
    }
}
