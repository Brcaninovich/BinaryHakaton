package com.hakaton.binaryhakaton;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;

public class LoadingScreen extends AppCompatActivity {

    TextView prodaja;
    LottieAnimationView lottie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading_screen);



        prodaja = findViewById(R.id.prodaja);
        lottie = findViewById(R.id.lottie);




        //prodaja.animate().translationY(-1400).setDuration(2700).setStartDelay(0);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);


            }
        }, 6000);
    }
}

