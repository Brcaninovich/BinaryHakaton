package com.hakaton.binaryhakaton;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.hakaton.binaryhakaton.databinding.ActivityKosaricaCekautBinding;

public class kosarica_cekaut extends AppCompatActivity {

    ActivityKosaricaCekautBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityKosaricaCekautBinding.inflate(getLayoutInflater());
        View root = binding.getRoot();
        setContentView(root);

        binding.platiButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        binding.platiButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

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
}