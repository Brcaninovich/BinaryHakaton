package com.hakaton.binaryhakaton.ui.Favorites;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

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
import com.hakaton.binaryhakaton.databinding.FragmentFavoritesBinding;
import com.hakaton.binaryhakaton.settingsscrol;
import com.hakaton.binaryhakaton.ui.home.RecyclerViewModel;

import java.util.ArrayList;

public class FavoritesFragment extends Fragment {

    private FragmentFavoritesBinding binding;
    ArrayList<Artikal> artikalArrayList;
    RecyclerAdapter myAdapter;
    FirebaseFirestore db;
    FirebaseAuth mAuth;




    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        FavoritesViewModel favoritesViewModel =
                new ViewModelProvider(this).get(FavoritesViewModel.class);

        binding = FragmentFavoritesBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        mAuth = FirebaseAuth.getInstance();

        db = FirebaseFirestore.getInstance();
        if(mAuth.getUid() != null){
            binding.recyclerView.setHasFixedSize(true);
            binding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            artikalArrayList = new ArrayList<Artikal>();
            myAdapter = new RecyclerAdapter(getActivity(), artikalArrayList);
            binding.recyclerView.setAdapter(myAdapter);

            EventChangeListener();
        }else{
            Toast.makeText(getActivity(), "Molimo prijavite se...", Toast.LENGTH_LONG).show();
        }

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
                                for(int i = 0; i < BazaHolder.favorites.size(); i++){
                                    if(dc.getDocument().toObject(Artikal.class).naziv_oglasa.equals(BazaHolder.favorites.get(i))){
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