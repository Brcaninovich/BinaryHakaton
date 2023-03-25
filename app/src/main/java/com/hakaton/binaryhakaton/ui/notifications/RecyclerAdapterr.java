package com.hakaton.binaryhakaton.ui.notifications;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.hakaton.binaryhakaton.Artikal;
import com.hakaton.binaryhakaton.BazaHolder;
import com.hakaton.binaryhakaton.R;

import java.util.ArrayList;

public class RecyclerAdapterr extends RecyclerView.Adapter<RecyclerAdapterr.MyViewHolder>{
    Context context;
    ArrayList<Artikal> artikalArrayList;
    public boolean clicked = false;

    public RecyclerAdapterr(Context context, ArrayList<Artikal> artikalArrayList) {
        this.context = context;
        this.artikalArrayList = artikalArrayList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(context).inflate(R.layout.card_item_rv_moji_artikli, parent, false);

        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {


        Artikal artikal = artikalArrayList.get(position);
        holder.naziv_oglasa.setText(artikal.naziv_oglasa);
        if(!artikal.slike.isEmpty()){
            Glide
                    .with(holder.itemView.getContext())
                    .load(artikal.slike.get(0))
                    .apply(new RequestOptions().override(1000, 450))
                    .centerCrop()
                    .into(holder.baner_slika);
        }
        holder.cijena.setText(artikal.cijena.toString() + " KM");


        holder.itemView.setOnClickListener (new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BazaHolder.setTemp_artikal(artikal);
                NavController navController = Navigation.findNavController(view);
                navController.navigate(R.id.navigation_automobil);
            }
        });

        holder.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseFirestore db;
                db = FirebaseFirestore.getInstance();
                db.collection("Artikli").document(BazaHolder.temp_automobil).delete()
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                artikalArrayList.remove(position);
                                notifyItemRemoved(position);
                                notifyItemRangeChanged(position, artikalArrayList.size());
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {

                            }
                        });
            }
        });

    }

    @Override
    public int getItemCount() {
        return artikalArrayList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{


        TextView naziv_oglasa,  cijena;
        ImageView baner_slika;
        LinearLayout layout;
        Button btn;
        Button btn2;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            naziv_oglasa = itemView.findViewById(R.id.naziv_oglasa);
            baner_slika = itemView.findViewById(R.id.oglas_slika);
            cijena = itemView.findViewById(R.id.cijena_oglasa);
            layout = itemView.findViewById(R.id.btn_holder);
            btn = itemView.findViewById(R.id.ukloni_artikal_button);
            btn2 = itemView.findViewById(R.id.edituj_artikal_button);
        }
    }

    public void edit_restoran(Artikal artikal){

        FirebaseFirestore db;
        db = FirebaseFirestore.getInstance();

        db.collection("Artikli").document(artikal.naziv_oglasa).set(artikal)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(context, "Updated", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    }
                });

    }

}
