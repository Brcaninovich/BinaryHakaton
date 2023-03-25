package com.hakaton.binaryhakaton;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class settingsscrol extends AppCompatActivity {

    LinearLayout odjava;
    LinearLayout podrska_korisnicima;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settingsscrol);
        odjava = (LinearLayout) findViewById(R.id.odjava);
        podrska_korisnicima =(LinearLayout) findViewById(R.id.podrska_korisnicima);


        odjava.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //binding.loginLayout.setVisibility(View.GONE);//Cover u slucaju da radi na visible/hide
                //binding.odabirLayout.setVisibility(View.VISIBLE);//Cover u slucaju da radi na visible/hide
                Intent intent = new Intent(settingsscrol.this,GlavniMenu.class);
                startActivity(intent);
            }
        });

        podrska_korisnicima.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //binding.loginLayout.setVisibility(View.GONE);//Cover u slucaju da radi na visible/hide
                //binding.odabirLayout.setVisibility(View.VISIBLE);//Cover u slucaju da radi na visible/hide
                Intent intent = new Intent(settingsscrol.this,SupportService.class);
                startActivity(intent);
            }
        });
    }
}
