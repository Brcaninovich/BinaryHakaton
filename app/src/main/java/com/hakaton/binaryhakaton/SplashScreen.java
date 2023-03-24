package com.hakaton.binaryhakaton;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.airbnb.lottie.LottieAnimationView;

public class SplashScreen extends AppCompatActivity {

    TextView pik;
    LottieAnimationView lottie;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        pik = findViewById(R.id.pik);
        lottie = findViewById(R.id.lottie);




        //pik.animate().translationY(-1400).setDuration(2700).setStartDelay(0);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent i = new Intent(getApplicationContext(), GlavniMenu.class);
                startActivity(i);
                finish();

            }
        }, 3000);
    }
}