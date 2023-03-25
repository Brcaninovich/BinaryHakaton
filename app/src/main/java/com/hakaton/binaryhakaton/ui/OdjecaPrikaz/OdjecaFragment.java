package com.hakaton.binaryhakaton.ui.OdjecaPrikaz;

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
import com.hakaton.binaryhakaton.databinding.FragmentAutomobilBinding;
import com.hakaton.binaryhakaton.databinding.FragmentOdjecaBinding;
import com.hakaton.binaryhakaton.kategorija.Automobil;
import com.hakaton.binaryhakaton.kategorija.Odjeca;
import com.hakaton.binaryhakaton.obavljanje_kupovine_auta;

public class OdjecaFragment extends Fragment {

    private FragmentOdjecaBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        OdjecaViewModel odjecaViewModel =
                new ViewModelProvider(this).get(OdjecaViewModel.class);

        binding = FragmentOdjecaBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        Artikal artikal = BazaHolder.getTemp_artikal();
        Odjeca odjeca = artikal.odjeca;
        Glide
                .with(getContext())
                .load(artikal.slike.get(0))
                .apply(new RequestOptions().override(1000, 600))
                .centerCrop()
                .into(binding.slikeAutomobil);

        binding.naslovOdjece.setText(artikal.naziv_oglasa);
        binding.cijenaAutomobila.setText(artikal.cijena);
        binding.stanjeAutomobila.setText(artikal.stanje);
        binding.datumAutomobil.setText(artikal.objava_date);
        binding.objavaAutomobil.setText("Objavio: " + artikal.objavio_username);
        binding.velicinaOdjece.setText("Velicina: " + odjeca.velicina);
        binding.genderOdjece.setText("Rod: " + odjeca.rod);
        binding.sezonaOdjece.setText("Sezona: " + odjeca.sezona);
        binding.vrstaOdjece.setText("Vrsta: " + odjeca.vrsta);
        binding.opisOdjece.setText(artikal.detaljni_opis);

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

            binding.dodajKosarica.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(!BazaHolder.kosarica.contains(artikal.naziv_oglasa)){
                        BazaHolder.kosarica.add(artikal.naziv_oglasa);
                        Toast.makeText(getActivity(), "Artikal ste dodali u kosaricu", Toast.LENGTH_SHORT).show();
                        BazaHolder.update_account();
                    }else{
                        Toast.makeText(getActivity(), "Artikal ste vec dodali", Toast.LENGTH_SHORT).show();

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

            binding.dodajKosarica.setOnClickListener(new View.OnClickListener() {
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