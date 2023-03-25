package com.hakaton.binaryhakaton.ui.add;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.hakaton.binaryhakaton.Artikal;
import com.hakaton.binaryhakaton.BazaHolder;
import com.hakaton.binaryhakaton.R;
import com.hakaton.binaryhakaton.databinding.FragmentAddBinding;
import com.hakaton.binaryhakaton.databinding.FragmentDashboardBinding;
import com.hakaton.binaryhakaton.kategorija.Automobil;
import com.hakaton.binaryhakaton.kategorija.Odjeca;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class AddFragment extends Fragment {

    private FragmentAddBinding binding;
    private FirebaseFirestore db;
    private ArrayList<CardView> temp_slike = new ArrayList<CardView>();
    private ArrayList<ImageView> temp_img = new ArrayList<ImageView>();
    private ArrayList<String> lista_slika = new ArrayList<String>();
    private StorageReference storageReference;
    private String mDownloadUrl;
    private int brojac = 0;
    private int brojac_dva;
    private Uri imageUri;
    FirebaseAuth mAuth;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        AddViewModel addViewModel =
                new ViewModelProvider(this).get(AddViewModel.class);

        binding = FragmentAddBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        mAuth = FirebaseAuth.getInstance();
        if(mAuth.getUid() != null){
            binding.osnovneInfoSkrol.setVisibility(View.VISIBLE);
            binding.dodatneSlikeSkrolAutomobil.setVisibility(View.GONE);
            binding.dodatneInfoSkrolAutomobil.setVisibility(View.GONE);

            temp_slike.add(binding.cardDodaj1);
            temp_slike.add(binding.cardDodaj2);
            temp_slike.add(binding.cardDodaj3);
            temp_slike.add(binding.cardDodaj4);
            temp_slike.add(binding.cardDodaj5);
            temp_slike.add(binding.cardDodaj6);

            temp_img.add(binding.roundedImageView1);
            temp_img.add(binding.roundedImageView2);
            temp_img.add(binding.roundedImageView3);
            temp_img.add(binding.roundedImageView4);
            temp_img.add(binding.roundedImageView5);
            temp_img.add(binding.roundedImageView6);


            binding.dodajSlikuButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(brojac <5){
                        brojac++;
                        temp_slike.get(brojac).setVisibility(View.VISIBLE);
                        if(brojac == 5){
                            binding.dodajSlikuButton.setVisibility(View.GONE);
                        }
                    }
                }
            });

            binding.cardDodaj1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    brojac_dva = 0;
                    dodajSliku();
                }
            });

            binding.cardDodaj2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    brojac_dva = 1;
                    dodajSliku();
                }
            });

            binding.cardDodaj3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    brojac_dva = 2;
                    dodajSliku();
                }
            });

            binding.cardDodaj4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    brojac_dva = 3;
                    dodajSliku();
                }
            });

            binding.cardDodaj5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    brojac_dva = 4;
                    dodajSliku();
                }
            });

            binding.cardDodaj6.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    brojac_dva = 5;
                    dodajSliku();
                }
            });

            db = FirebaseFirestore.getInstance();

            ArrayAdapter<String> velicina = new ArrayAdapter<>(getActivity(),
                    android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.velicina));
            velicina.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            binding.odaberiVelicinu.setAdapter(velicina);

            ArrayAdapter<String> gender= new ArrayAdapter<>(getActivity(),
                    android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.gender));
            gender.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            binding.odaberiRod.setAdapter(gender);

            ArrayAdapter<String> sezona= new ArrayAdapter<>(getActivity(),
                    android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.Sezona));
            gender.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            binding.odaberiSezonu.setAdapter(sezona);

            ArrayAdapter<String> vrsta= new ArrayAdapter<>(getActivity(),
                    android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.Vrsta));
            vrsta.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            binding.odaberiVrsta.setAdapter(vrsta);




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
                    if(binding.odaberiKategoriju.getSelectedItem().equals("Automobil")){
                        binding.osnovneInfoSkrol.setVisibility(View.GONE);
                        binding.dodatneInfoSkrolOdjeca.setVisibility(View.GONE);
                        binding.dodatneInfoSkrolAutomobil.setVisibility(View.VISIBLE);
                    }else if(binding.odaberiKategoriju.getSelectedItem().equals("Garderoba")){
                        binding.osnovneInfoSkrol.setVisibility(View.GONE);
                        binding.dodatneInfoSkrolOdjeca.setVisibility(View.VISIBLE);
                    }

                }
            });

            binding.dodatnaSvojstvaButton2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    binding.dodatneInfoSkrolOdjeca.setVisibility(View.GONE);
                    binding.dodatneSlikeSkrolAutomobil.setVisibility(View.VISIBLE);
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



        }else {
            Toast.makeText(getActivity(), "Molimo prijavite se...", Toast.LENGTH_LONG).show();
            binding.osnovneInfoSkrol.setVisibility(View.GONE);
            binding.dodatneInfoSkrolAutomobil.setVisibility(View.GONE);
            binding.dodatneSlikeSkrolAutomobil.setVisibility(View.GONE);
        }


        return root;
    }

    private void upload_artikal(){


        final String naziv_artikla = binding.nazivInput.getText().toString();
        final String stanje = binding.odaberiStanje.getSelectedItem().toString();
        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("dd.MMM.yyyy", Locale.getDefault());
        final String objava_date = df.format(c);
        final String objava_user = BazaHolder.username;
        final String detaljni_opis = binding.detaljanOpisInput.getText().toString();
        final String lokacija = binding.odaberiGrad.getSelectedItem().toString();

        final String cijena = binding.cijenaInput.getText().toString();

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



            String velicina = binding.odaberiVelicinu.getSelectedItem().toString();
            String gender = binding.odaberiRod.getSelectedItem().toString();
            String Sezona = binding.odaberiSezonu.getSelectedItem().toString();
            String vrsta = binding.odaberiVrsta.getSelectedItem().toString();
            Odjeca odjeca = new Odjeca(velicina, gender, Sezona, vrsta);

            String kateg = binding.odaberiKategoriju.getSelectedItem().toString();

            Artikal artikal;

            if(binding.odaberiKategoriju.getSelectedItem().equals("Automobil")){
                artikal = new Artikal(naziv_artikla, stanje, objava_date, objava_user,
                        detaljni_opis, lokacija, lista_slika, cijena, automobil, false, kateg);
            }else{
                artikal = new Artikal(naziv_artikla, stanje, objava_date, objava_user,
                        detaljni_opis, lokacija, lista_slika, cijena, odjeca, false, kateg);

            }




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

    private void uploadImage(){
        String naziv_artikla = binding.nazivInput.getText().toString();
        SimpleDateFormat formater = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss", Locale.GERMAN);
        Date now = new Date();
        naziv_artikla = formater.format(now);
        storageReference = FirebaseStorage.getInstance().getReference("images/"+naziv_artikla);
        String finalNaziv_artikla = naziv_artikla;
        storageReference.putFile(imageUri)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        storageReference = FirebaseStorage.getInstance().getReference("/images");
                        StorageReference dateRef = storageReference.child(finalNaziv_artikla);
                        dateRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                mDownloadUrl = uri.toString();
                                lista_slika.add(mDownloadUrl);
                            }
                        });
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getActivity(), "Ne Radi slika", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void dodajSliku(){
        Intent intent = new Intent();
        intent.setType("image/");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, 100);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 100 && data != null && data.getData() != null){
            imageUri = data.getData();
            temp_img.get(brojac_dva).setImageURI(imageUri);
            uploadImage();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}