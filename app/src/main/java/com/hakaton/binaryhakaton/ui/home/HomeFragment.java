package com.hakaton.binaryhakaton.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import com.hakaton.binaryhakaton.Artikal;
import com.hakaton.binaryhakaton.BazaHolder;
import com.hakaton.binaryhakaton.LoginForm;
import com.hakaton.binaryhakaton.databinding.FragmentHomeBinding;
import com.hakaton.binaryhakaton.settingsscrol;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    ArrayList<Artikal> artikalArrayList;
    RecyclerViewModel myAdapter;

    Boolean pretraga = false;

    FirebaseFirestore db;
    FirebaseAuth mAuth;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        mAuth = FirebaseAuth.getInstance();

        binding.searchBarLayout.setVisibility(View.GONE);

        if(mAuth.getUid() != null){
            BazaHolder.rebase();
        }

        db = FirebaseFirestore.getInstance();

        binding.recyclerView.setHasFixedSize(true);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        artikalArrayList = new ArrayList<Artikal>();
        myAdapter = new RecyclerViewModel(getActivity(), artikalArrayList);
        binding.recyclerView.setAdapter(myAdapter);


        final TextView textView = binding.textHome;
        homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        binding.personButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mAuth.getUid() == null){
                    Intent intent = new Intent(getActivity(), LoginForm.class);
                    startActivity(intent);
                }else{
                    Intent intent = new Intent(getActivity(), settingsscrol.class);
                    startActivity(intent);
                }

            }
        });

        binding.findButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pretraga = !pretraga;
                if(pretraga){
                    binding.searchBarLayout.setVisibility(View.VISIBLE);
                }else{
                    binding.searchBarLayout.setVisibility(View.GONE);
                }
            }
        });

        binding.pretragaBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                artikalArrayList.clear();
                EventChangeListener2();
                myAdapter.notifyDataSetChanged();
            }
        });

        EventChangeListener();

        return root;
    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void EventChangeListener2(){

        db.collection("Artikli")
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                        if(error != null){
                            Log.e("Firestore error", error.getMessage());
                            return;
                        }

                        for(DocumentChange dc : value.getDocumentChanges()){
                            if(dc.getType() == DocumentChange.Type.ADDED){
                                if(dc.getDocument().toObject(Artikal.class).naziv_oglasa.equals(binding.nazivInput.getText().toString())){
                                    artikalArrayList.add(dc.getDocument().toObject(Artikal.class));
                                }
                            }
                            myAdapter.notifyDataSetChanged();

                        }
                        if(artikalArrayList.isEmpty()){

                        }
                    }
                });
    }

    private void EventChangeListener(){

        db.collection("Artikli")
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                        if(error != null){
                            Log.e("Firestore error", error.getMessage());
                            return;
                        }

                        for(DocumentChange dc : value.getDocumentChanges()){
                            if(dc.getType() == DocumentChange.Type.ADDED){
                                    artikalArrayList.add(dc.getDocument().toObject(Artikal.class));
                            }
                            myAdapter.notifyDataSetChanged();

                        }
                        if(artikalArrayList.isEmpty()){

                        }
                    }
                });
    }
}