package com.example.mandys.knotsandkross;

import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

import java.util.concurrent.ThreadLocalRandom;

import nl.dionsegijn.konfetti.KonfettiView;
import nl.dionsegijn.konfetti.models.Shape;
import nl.dionsegijn.konfetti.models.Size;


public class WinnersActivity extends AppCompatActivity  {
    TextView txtView, txtView1;
    Button btn, btnExit;
    Animation congo, win, butn, butn1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // v = (ViewGroup) findViewById(android.R.id.content);
        setContentView(R.layout.activity_winners);
        final KonfettiView konfettiView = (KonfettiView)findViewById(R.id.viewKonfetti);
        konfettiView.build()
                .addColors(Color.YELLOW, Color.GREEN, Color.MAGENTA)
                .setDirection(0.0, 359.0)
                .setSpeed(1f, 5f)
                .setFadeOutEnabled(true)
                .setTimeToLive(2000L)
                .addShapes(Shape.CIRCLE, Shape.RECT)
                .addSizes(new Size(12, 5f))
                .setPosition(-50f, konfettiView.getWidth() + 50f, -50f, -50f)
                .stream(300, 5000L);
        konfettiView.animate();
        btn = (Button) findViewById(R.id.btnTogether);
        btnExit = (Button)findViewById(R.id.button);
        txtView = (TextView)findViewById(R.id.CongoText);
        txtView1 = (TextView)findViewById(R.id.txt_tog);
        String str = getIntent().getExtras().get("winner name").toString();
        txtView1.setText(str + " player is the winner.");

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                txtView.setVisibility(View.VISIBLE);
                txtView1.setVisibility(View.VISIBLE);
                btn.setVisibility(View.VISIBLE);
                congo = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fadein);
                txtView.startAnimation(congo);

                win = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fadein);
                txtView1.startAnimation(win);

                //txtView1.startAnimation(win);
                butn = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fadein);
                btn.startAnimation(butn);
                btnExit.startAnimation(butn);
                //butn1 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.button_bounce);
                //long durationAnim = 2000;
                //butn1.setDuration(durationAnim);

                btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent();
                        intent.setClass(WinnersActivity.this, MainGame.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(intent);
                        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);

                    }
                });
                btnExit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                    }
                });



            }
        }, 7000);

    }

}
