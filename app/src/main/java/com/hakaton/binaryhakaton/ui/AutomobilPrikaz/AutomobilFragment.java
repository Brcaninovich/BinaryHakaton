package com.hakaton.binaryhakaton.ui.AutomobilPrikaz;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.hakaton.binaryhakaton.Artikal;
import com.hakaton.binaryhakaton.BazaHolder;
import com.hakaton.binaryhakaton.LoginForm;
import com.hakaton.binaryhakaton.databinding.FragmentAutomobilBinding;
import com.hakaton.binaryhakaton.kategorija.Automobil;
import com.hakaton.binaryhakaton.obavljanje_kupovine_auta;

public class AutomobilFragment extends Fragment {

    private FragmentAutomobilBinding binding;
    int brojac;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        AutomobilViewModel automobilViewModel =
                new ViewModelProvider(this).get(AutomobilViewModel.class);

        binding = FragmentAutomobilBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        Artikal artikal = BazaHolder.getTemp_artikal();
        Automobil automobil = artikal.kategorija;
        brojac = 0;
        Glide
                .with(getContext())
                .load(artikal.slike.get(brojac))
                .apply(new RequestOptions().override(1000, 600))
                .centerCrop()
                .into(binding.slikeAutomobil);

        binding.promslikuBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(brojac == artikal.slike.size() - 1){
                    brojac = 0;
                }else{
                    brojac++;
                }


                Glide
                        .with(getContext())
                        .load(artikal.slike.get(brojac))
                        .apply(new RequestOptions().override(1000, 600))
                        .centerCrop()
                        .into(binding.slikeAutomobil);
            }
        });

        binding.naslovAutomobila.setText(artikal.naziv_oglasa);
        binding.cijenaAutomobila.setText(artikal.cijena);
        binding.stanjeAutomobila.setText(artikal.stanje);
        binding.datumAutomobil.setText(artikal.objava_date);
        binding.objavaAutomobil.setText("Objavio: " + artikal.objavio_username);
        binding.godisteAutomobila.setText("Godiste: " + automobil.godiste);
        binding.kilometrazaAutomobila.setText("Kilometraza: " + automobil.kilometraza);
        binding.gorivoAutomobila.setText("Gorivo: " + automobil.gorivo);
        binding.kilovatiAutomobila.setText("Kilovati: " + automobil.kilovati);
        binding.transmisijaAutomobila.setText("Transmisija: " + automobil.transmisija);
        binding.konjskaSnagaAutomobila.setText("KS: " + automobil.konjske_snage);
        binding.bojaAutomobila.setText(automobil.boja);
        binding.registracijaAutomobila.setText("Registracija: " + automobil.registracija);
        binding.espAutomobila.setText("ESP: " + automobil.esp);
        binding.klimaAutomobila.setText("Klima: " + automobil.klima);
        binding.navigacijaAutomobila.setText("Navigacija: " + automobil.navigacija);
        binding.tempomatAutomobila.setText("Tempomat: " + automobil.tempomat);
        binding.opisAutomobila.setText(artikal.detaljni_opis);

        FirebaseAuth mAuth;
        mAuth = FirebaseAuth.getInstance();
        if(mAuth.getUid() != null){
            binding.favoriteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(!BazaHolder.favorites.contains(artikal.naziv_oglasa)){
                        BazaHolder.favorites.add(artikal.naziv_oglasa);
                        Toast.makeText(getActivity(), "Oznacili ste sa svidja mi se", Toast.LENGTH_SHORT).show();
                        BazaHolder.update_account();
                    }else{
                        Toast.makeText(getActivity(), "Artikal ste vec oznacili ste sa svidja mi se", Toast.LENGTH_SHORT).show();

                    }
                }
            });

            binding.ugovoriKupovinu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (BazaHolder.username.equals(artikal.objavio_username)) {
                        Toast.makeText(getActivity(), "Ne mozete kupiti sam svoj artikal", Toast.LENGTH_SHORT).show();
                    }else{
                        BazaHolder.temp_automobil = artikal.naziv_oglasa;
                        Intent intent = new Intent(getActivity(), obavljanje_kupovine_auta.class);
                        startActivity(intent);
                        getActivity().finish();
                    }
                    
                }
            });

        }else{
            binding.favoriteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                        Toast.makeText(getActivity(), "Molimo, prijavite se....", Toast.LENGTH_SHORT).show();
                }
            });

            binding.ugovoriKupovinu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(getActivity(), "Molimo, prijavite se....", Toast.LENGTH_SHORT).show();

                }
            });
        }



        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}