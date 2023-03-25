package com.hakaton.binaryhakaton.ui.AutomobilPrikaz;

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
import com.hakaton.binaryhakaton.Artikal;
import com.hakaton.binaryhakaton.BazaHolder;
import com.hakaton.binaryhakaton.databinding.FragmentAutomobilBinding;
import com.hakaton.binaryhakaton.kategorija.Automobil;

public class AutomobilFragment extends Fragment {

    private FragmentAutomobilBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        AutomobilViewModel automobilViewModel =
                new ViewModelProvider(this).get(AutomobilViewModel.class);

        binding = FragmentAutomobilBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        Artikal artikal = BazaHolder.getTemp_artikal();
        Automobil automobil = artikal.kategorija;
        Glide
                .with(getContext())
                .load(artikal.slike.get(0))
                .apply(new RequestOptions().override(1000, 600))
                .centerCrop()
                .into(binding.slikeAutomobil);

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

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}