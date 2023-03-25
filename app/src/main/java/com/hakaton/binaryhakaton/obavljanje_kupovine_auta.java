package com.hakaton.binaryhakaton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.hakaton.binaryhakaton.databinding.ActivityObavljanjeKupovineAutaBinding;


public class obavljanje_kupovine_auta extends AppCompatActivity {

    ActivityObavljanjeKupovineAutaBinding binding;
    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityObavljanjeKupovineAutaBinding.inflate(getLayoutInflater());
        View root = binding.getRoot();
        setContentView(root);

        db = FirebaseFirestore.getInstance();

        binding.neZelimButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(obavljanje_kupovine_auta.this, GlavniMenu.class);
                startActivity(intent);
                finish();
            }
        });

        binding.obaviKupovinuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(obavljanje_kupovine_auta.this, "Uspjesno ste kupili automobil: " + BazaHolder.temp_automobil , Toast.LENGTH_SHORT).show();
                kupljen_auto();
                Intent intent = new Intent(obavljanje_kupovine_auta.this, GlavniMenu.class);
                startActivity(intent);
                finish();
            }
        });

    }

    public void kupljen_auto(){

        db.collection("Artikli").document(BazaHolder.temp_automobil).delete()
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    }
                });


    }
}