package com.hakaton.binaryhakaton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.hakaton.binaryhakaton.databinding.ActivityKosaricaCekautBinding;

public class kosarica_cekaut extends AppCompatActivity {

    ActivityKosaricaCekautBinding binding;
    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityKosaricaCekautBinding.inflate(getLayoutInflater());
        View root = binding.getRoot();
        setContentView(root);
        db = FirebaseFirestore.getInstance();

        binding.platiButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                kupljena_kolica();
                Intent intent = new Intent(kosarica_cekaut.this, GlavniMenu.class);
                startActivity(intent);
                finish();
            }
        });

        binding.platiButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                kupljena_kolica();
                Intent intent = new Intent(kosarica_cekaut.this, GlavniMenu.class);
                startActivity(intent);
                finish();
            }
        });

        ArrayAdapter<String> placanja = new ArrayAdapter<>(kosarica_cekaut.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.Placanje));
        placanja.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.odaberiPlacanje.setAdapter(placanja);

        binding.odaberiPlacanje.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                switch (position){
                    case 1:
                        binding.pouzcem.setVisibility(View.VISIBLE);
                        binding.karticno.setVisibility(View.GONE);
                        break;
                    case 2:
                        binding.pouzcem.setVisibility(View.GONE);
                        binding.karticno.setVisibility(View.VISIBLE);
                        break;
                    default:
                        binding.pouzcem.setVisibility(View.GONE);
                        binding.karticno.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                binding.pouzcem.setVisibility(View.GONE);
                binding.karticno.setVisibility(View.GONE);
            }

        });
    }

    public void kupljena_kolica(){

        for(int i = 0; i < BazaHolder.kosarica.size(); i++ ){
            db.collection("Artikli").document(BazaHolder.kosarica.get(i)).delete()
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

}