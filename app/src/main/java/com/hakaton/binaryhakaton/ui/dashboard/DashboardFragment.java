package com.hakaton.binaryhakaton.ui.dashboard;

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
import com.hakaton.binaryhakaton.databinding.FragmentDashboardBinding;
import com.hakaton.binaryhakaton.kosarica_cekaut;
import com.hakaton.binaryhakaton.settingsscrol;
import com.hakaton.binaryhakaton.ui.home.RecyclerViewModel;

import java.util.ArrayList;

public class DashboardFragment extends Fragment {

    private FragmentDashboardBinding binding;
    ArrayList<Artikal> artikalArrayList;
    RecyclerAdapterrrr myAdapter;

    FirebaseFirestore db;
    FirebaseAuth mAuth;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        DashboardViewModel dashboardViewModel =
                new ViewModelProvider(this).get(DashboardViewModel.class);

        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        mAuth = FirebaseAuth.getInstance();

        if(mAuth.getUid() != null){
            BazaHolder.rebase();
        }


        db = FirebaseFirestore.getInstance();
        binding.recyclerView.setHasFixedSize(true);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        artikalArrayList = new ArrayList<Artikal>();
        myAdapter = new RecyclerAdapterrrr(getActivity(), artikalArrayList);
        binding.recyclerView.setAdapter(myAdapter);

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

        binding.kupiSve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), kosarica_cekaut.class);
                startActivity(intent);
                getActivity().finish();
            }
        });

        EventChangeListener();

        return root;
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
                                for(int i = 0; i < BazaHolder.kosarica.size(); i++){
                                    if(dc.getDocument().toObject(Artikal.class).naziv_oglasa.equals(BazaHolder.kosarica.get(i))){
                                        artikalArrayList.add(dc.getDocument().toObject(Artikal.class));
                                    }
                                }
                            }
                            myAdapter.notifyDataSetChanged();

                        }
                        if(artikalArrayList.isEmpty()){

                        }
                    }
                });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}