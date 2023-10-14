package com.example.flipacoin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    public static final Random RANDOM = new Random();
    private ImageView coin;
    private Button flip_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        coin = (ImageView) findViewById(R.id.coin);                                                           //Definícia premenných v activity_main.xml
        flip_button = (Button) findViewById(R.id.flip_button);
        flip_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                flip_coin();                                                                                 //Ak používateľ klikne na tlačidlo flip_button spusti funkciu flip_coin
            }
        });
    }

    private void flip_coin() {                                                                                //Vytvorenie funkcie flip_coin
        Animation fadeOut = new AlphaAnimation(1,0);                                                          //Nastavenie animácie fadeOut
        fadeOut.setInterpolator(new AccelerateInterpolator());
        fadeOut.setDuration(1000);
        fadeOut.setFillAfter(true);
        fadeOut.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                boolean choice = RANDOM.nextFloat() > 0.5f;                                                   //Náhodný výber (bool choice) true/false
                coin.setImageResource(choice ? R.drawable.tails : R.drawable.heads);                          //Výber strany mince

                if (choice == true){                                                                          //Výber zobrazeného textu (tails/heads)
                    Toast.makeText(MainActivity.this, "Tails!", Toast.LENGTH_SHORT).show();      //Zobrazenie textu "Tails!" v MainActivity
                }
                else {
                    Toast.makeText(MainActivity.this, "Heads!", Toast.LENGTH_SHORT).show();      //Zobrazenie textu "Heads!" v MainActivity
                }

                Animation fadeIn = new AlphaAnimation(0,1);                                                  //Nastavenie animácie fadeIn
                fadeIn.setInterpolator(new DecelerateInterpolator());
                fadeIn.setDuration(3000);
                fadeIn.setFillAfter(true);

                coin.startAnimation(fadeIn);

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        coin.startAnimation(fadeOut);

    }

}
