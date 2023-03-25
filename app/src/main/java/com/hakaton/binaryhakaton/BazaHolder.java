package com.hakaton.binaryhakaton;

import android.app.Application;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class BazaHolder extends Application {

    static FirebaseAuth mAuth;
    static FirebaseDatabase realtime_database;
    static DatabaseReference reference_rd;

    public static Artikal temp_artikal;

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
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
