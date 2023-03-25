package com.hakaton.binaryhakaton.ui.dashboard;

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

public class RecyclerAdapterrrr extends RecyclerView.Adapter<RecyclerAdapterrrr.MyViewHolder>{
    Context context;
    ArrayList<Artikal> artikalArrayList;
    public boolean clicked = false;

    public RecyclerAdapterrrr(Context context, ArrayList<Artikal> artikalArrayList) {
        this.context = context;
        this.artikalArrayList = artikalArrayList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(context).inflate(R.layout.kosarica_item, parent, false);

        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {


        Artikal artikal = artikalArrayList.get(position);
        holder.naziv_oglasa.setText(artikal.naziv_oglasa);
        holder.cijena.setText(artikal.cijena.toString() + " KM");



        holder.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    //funkcija dodat se
            }
        });

    }

    @Override
    public int getItemCount() {
        return artikalArrayList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{


        TextView naziv_oglasa,  cijena;
        Button btn;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            naziv_oglasa = itemView.findViewById(R.id.naziv_oglasa);
            cijena = itemView.findViewById(R.id.cijena_oglasa);
            btn = itemView.findViewById(R.id.kosarica_button);
        }
    }


}
