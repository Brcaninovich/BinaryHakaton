package com.hakaton.binaryhakaton;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.hakaton.binaryhakaton.databinding.ActivitySettingsscrolBinding;

public class settingsscrol extends AppCompatActivity {

    LinearLayout moji_artikli;
    LinearLayout odjava;
    LinearLayout podrska_korisnicima;
    FirebaseAuth mAuth;

    ActivitySettingsscrolBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySettingsscrolBinding.inflate(getLayoutInflater());
        View root = binding.getRoot();
        setContentView(root);

        mAuth = FirebaseAuth.getInstance();



        odjava = (LinearLayout) findViewById(R.id.odjava);
        podrska_korisnicima =(LinearLayout) findViewById(R.id.podrska_korisnicima);



        odjava.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.signOut();
                //binding.loginLayout.setVisibility(View.GONE);//Cover u slucaju da radi na visible/hide
                //binding.odabirLayout.setVisibility(View.VISIBLE);//Cover u slucaju da radi na visible/hide
                Intent intent = new Intent(settingsscrol.this,GlavniMenu.class);
                startActivity(intent);
                BazaHolder.odjava();
                finish();
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
