package com.hakaton.binaryhakaton.ui.notifications;

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
import com.hakaton.binaryhakaton.databinding.FragmentNotificationsBinding;
import com.hakaton.binaryhakaton.ui.Favorites.RecyclerAdapter;

import java.util.ArrayList;

public class NotificationsFragment extends Fragment {

    private FragmentNotificationsBinding binding;
    ArrayList<Artikal> artikalArrayList;
    RecyclerAdapterr myAdapter;
    FirebaseFirestore db;
    FirebaseAuth mAuth;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        NotificationsViewModel notificationsViewModel =
                new ViewModelProvider(this).get(NotificationsViewModel.class);

        binding = FragmentNotificationsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        mAuth = FirebaseAuth.getInstance();

        db = FirebaseFirestore.getInstance();

        if(mAuth.getUid() != null){
            binding.recyclerView.setHasFixedSize(true);
            binding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            artikalArrayList = new ArrayList<Artikal>();
            myAdapter = new RecyclerAdapterr(getActivity(), artikalArrayList);
            binding.recyclerView.setAdapter(myAdapter);


            EventChangeListener();
        }else{
            Toast.makeText(getActivity(), "Molimo prijavite se...", Toast.LENGTH_LONG).show();
        }

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
                                if(dc.getDocument().toObject(Artikal.class).objavio_username.equals(BazaHolder.username)){
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

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}