package com.hakaton.binaryhakaton.ui.add;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.hakaton.binaryhakaton.Artikal;
import com.hakaton.binaryhakaton.R;
import com.hakaton.binaryhakaton.databinding.FragmentAddBinding;
import com.hakaton.binaryhakaton.databinding.FragmentDashboardBinding;
import com.hakaton.binaryhakaton.kategorija.Automobil;

import java.util.ArrayList;

public class AddFragment extends Fragment {

    private FragmentAddBinding binding;
    private FirebaseFirestore db;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        AddViewModel addViewModel =
                new ViewModelProvider(this).get(AddViewModel.class);

        binding = FragmentAddBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        binding.osnovneInfoSkrol.setVisibility(View.VISIBLE);
        binding.dodatneSlikeSkrolAutomobil.setVisibility(View.GONE);
        binding.dodatneInfoSkrolAutomobil.setVisibility(View.GONE);


        db = FirebaseFirestore.getInstance();

        ArrayAdapter<String> kantoni = new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.kantoni));
        kantoni.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.odaberiKanton.setAdapter(kantoni);

        ArrayAdapter<String> kategorija = new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.Kategorija));
        kantoni.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.odaberiKategoriju.setAdapter(kategorija);

        ArrayAdapter<String> stanje = new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.Stanje_Artikla));
        kantoni.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.odaberiStanje.setAdapter(stanje);

        ArrayAdapter<String> gorivo = new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.Gorivo));
        kantoni.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.odaberiGorivo.setAdapter(gorivo);

        ArrayAdapter<String> transmisija = new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.Transmisija));
        kantoni.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.odaberiTransmisiju.setAdapter(transmisija);

        ArrayAdapter<String> da_ne = new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.Da_ne));
        kantoni.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.odabirRegistracija.setAdapter(da_ne);
        binding.odabirEsp.setAdapter(da_ne);
        binding.odabirKlima.setAdapter(da_ne);
        binding.odabirNavigacija.setAdapter(da_ne);
        binding.odabirTempomat.setAdapter(da_ne);

        binding.odaberiKanton.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                switch (position){
                    case 1:
                        ArrayAdapter<String> gradovi = new ArrayAdapter<>(getActivity(),
                                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.UnskoSanski));
                        gradovi.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        binding.odaberiGrad.setAdapter(gradovi);
                        binding.gradLayout.setVisibility(View.VISIBLE);
                        break;
                    case 2:
                        gradovi = new ArrayAdapter<>(getActivity(),
                                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.Posavski));
                        gradovi.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        binding.odaberiGrad.setAdapter(gradovi);
                        binding.gradLayout.setVisibility(View.VISIBLE);
                        break;
                    case 3:
                        gradovi = new ArrayAdapter<>(getActivity(),
                                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.Tuzlanski));
                        gradovi.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        binding.odaberiGrad.setAdapter(gradovi);
                        binding.gradLayout.setVisibility(View.VISIBLE);
                        break;
                    case 4:
                        gradovi = new ArrayAdapter<>(getActivity(),
                                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.ZenickoDobojski));
                        gradovi.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        binding.odaberiGrad.setAdapter(gradovi);
                        binding.gradLayout.setVisibility(View.VISIBLE);
                        break;
                    case 5:
                        gradovi = new ArrayAdapter<>(getActivity(),
                                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.BosanskoPodrinjski));
                        gradovi.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        binding.odaberiGrad.setAdapter(gradovi);
                        binding.gradLayout.setVisibility(View.VISIBLE);
                        break;
                    case 6:
                        gradovi = new ArrayAdapter<>(getActivity(),
                                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.SrednjoBosanski));
                        gradovi.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        binding.odaberiGrad.setAdapter(gradovi);
                        binding.gradLayout.setVisibility(View.VISIBLE);
                        break;
                    case 7:
                        gradovi = new ArrayAdapter<>(getActivity(),
                                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.HercegovackoNeretva));
                        gradovi.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        binding.odaberiGrad.setAdapter(gradovi);
                        binding.gradLayout.setVisibility(View.VISIBLE);
                        break;
                    case 8:
                        gradovi = new ArrayAdapter<>(getActivity(),
                                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.ZapadnoHerceg));
                        gradovi.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        binding.odaberiGrad.setAdapter(gradovi);
                        binding.gradLayout.setVisibility(View.VISIBLE);
                        break;
                    case 9:
                        gradovi = new ArrayAdapter<>(getActivity(),
                                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.KantonSarajevo));
                        gradovi.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        binding.odaberiGrad.setAdapter(gradovi);
                        binding.gradLayout.setVisibility(View.VISIBLE);
                        break;
                    case 10:
                        gradovi = new ArrayAdapter<>(getActivity(),
                                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.Kanton10));
                        gradovi.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        binding.odaberiGrad.setAdapter(gradovi);
                        binding.gradLayout.setVisibility(View.VISIBLE);
                        break;
                    default:
                        binding.gradLayout.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                binding.gradLayout.setVisibility(View.GONE);
            }

        });

        binding.dodatnaSvojstvaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.osnovneInfoSkrol.setVisibility(View.GONE);
                binding.dodatneInfoSkrolAutomobil.setVisibility(View.VISIBLE);
            }
        });

        binding.dodajSlike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.dodatneInfoSkrolAutomobil.setVisibility(View.GONE);
                binding.dodatneSlikeSkrolAutomobil.setVisibility(View.VISIBLE);
            }
        });

        binding.dodajArtikal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                upload_artikal();
            }
        });



        return root;
    }

    private void upload_artikal(){


        final String naziv_artikla = binding.nazivInput.getText().toString();
        final String stanje = binding.odaberiStanje.getSelectedItem().toString();
        final String objava_date = "15.02.2023";
        final String objava_user = "Dzeno";
        final String detaljni_opis = binding.detaljanOpisInput.getText().toString();
        final String lokacija = binding.odaberiGrad.getSelectedItem().toString();
        ArrayList<String> slike = new ArrayList<String>();
        final String cijena = binding.cijenaInput.getText().toString();

        /*public Automobil(String godiste, String kilometraza, String gorivo, String kilovati,
                String transmisija, String konjske_snage, String boja, String registracija,
                String esp, String klima, String navigacija, String tempomat) {*/

         /*public Artikal(String naziv_oglasa, String stanje, String objava_date,
                String objavio_username, String detaljni_opis, String lokacija,
                ArrayList<String> slike, Float cijena, Object kategorija) {*/
        final String godiste = binding.godisteInput.getText().toString();
        final String kilometraza = binding.kilometrazaInput.getText().toString();
        final String gorivo = binding.odaberiGorivo.getSelectedItem().toString();
        final String kilovati = binding.inputKilovati.getText().toString();
        final String transmisija = binding.odaberiTransmisiju.getSelectedItem().toString();
        final String konjske_snage = binding.inputKonjskaSnaga.getText().toString();
        final String boja = binding.inputBoja.getText().toString();
        final String registracija = binding.odabirRegistracija.getSelectedItem().toString();
        final String esp = binding.odabirEsp.getSelectedItem().toString();
        final String klima = binding.odabirKlima.getSelectedItem().toString();
        final String navigacija = binding.odabirNavigacija.getSelectedItem().toString();
        final String tempomat = binding.odabirTempomat.getSelectedItem().toString();

        Automobil automobil = new Automobil("Automobil", godiste, kilometraza, gorivo,kilovati,
                transmisija, konjske_snage, boja, registracija, esp, klima, navigacija, tempomat);

        Artikal artikal = new Artikal(naziv_artikla, stanje, objava_date, objava_user,
                detaljni_opis, lokacija, slike, cijena, automobil);

        db.collection("Artikli").document(naziv_artikla)
                .set(artikal)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(getActivity(), "Radi", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getActivity(), e.getMessage().toString(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}