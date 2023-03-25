package com.hakaton.binaryhakaton;

import android.app.Application;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.hakaton.binaryhakaton.kategorija.Automobil;

import java.util.ArrayList;

public class BazaHolder extends Application {

    static FirebaseAuth mAuth;
    static FirebaseDatabase realtime_database;
    static DatabaseReference reference_rd;

    public static Artikal temp_artikal;
    public static String temp_automobil;

    public static Artikal getTemp_artikal() {
        return temp_artikal;
    }

    public static void setTemp_artikal(Artikal temp_artikal) {
        BazaHolder.temp_artikal = temp_artikal;
    }

    public static String username;
    public static String email;
    public static String priv;

    public static ArrayList<String> poruke = new ArrayList<String>();
    public static ArrayList<String> favorites = new ArrayList<String>();
    public static ArrayList<String> moji_dodani = new ArrayList<String>();

    public static ArrayList<String> kosarica = new ArrayList<String>();

    @Override
    public void onCreate() {
        super.onCreate();

        mAuth = FirebaseAuth.getInstance();
        realtime_database = FirebaseDatabase.getInstance();
        if(mAuth.getUid() != null){
            reference_rd = realtime_database.getReference("Users").child(mAuth.getUid());

            reference_rd.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    User post = dataSnapshot.getValue(User.class);
                    username = post.username;
                    email = post.email;
                    priv = post.priv;
                    poruke = post.poruke;
                    favorites = post.favorites;
                    moji_dodani = post.moji_dodani;
                    kosarica = post.kosarica;
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }

    }

    public static void rebase(){
        reference_rd = realtime_database.getReference("Users").child(mAuth.getUid());

        reference_rd.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                User post = dataSnapshot.getValue(User.class);
                username = post.username;
                email = post.email;
                priv = post.priv;
                poruke = post.poruke;
                favorites = post.favorites;
                moji_dodani = post.moji_dodani;
                kosarica = post.kosarica;

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public static void odjava(){
        username = null;
        email = null;
        priv = null;
        poruke = null;
        favorites = null;
        moji_dodani = null;
    }

    public static void update_account(){

        /*public User(String username, String email, String priv, ArrayList<String> poruke,ArrayList<String> favorites, ArrayList<String> moji_dodani) {
            this.username = username;
            this.email = email;
            this.priv = priv;
            this.poruke = poruke;
            this.favorites = favorites;
            this.moji_dodani = moji_dodani;
        }*/
        User user = new User(username, email, priv, poruke, favorites, moji_dodani, kosarica );
        reference_rd = realtime_database.getReference("Users").child(mAuth.getUid());
        reference_rd.setValue(user).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });

    }

    public static void update_artikal(){

    }
}
