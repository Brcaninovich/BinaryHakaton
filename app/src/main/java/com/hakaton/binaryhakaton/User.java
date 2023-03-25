package com.hakaton.binaryhakaton;

import java.util.ArrayList;

public class User {

    public String getUsername() {
        return username;
    }

    public String username, email, priv;

    public ArrayList<String> poruke = new ArrayList<String>();
    public ArrayList<String> favorites = new ArrayList<String>();
    public ArrayList<String> moji_dodani = new ArrayList<String>();
    public ArrayList<String> kosarica = new ArrayList<String>();


    public User() {

    }

    public User(String username, String email, String priv, ArrayList<String> poruke,ArrayList<String> favorites, ArrayList<String> moji_dodani, ArrayList<String> kosarica ) {
        this.username = username;
        this.email = email;
        this.priv = priv;
        this.poruke = poruke;
        this.favorites = favorites;
        this.moji_dodani = moji_dodani;
        this.kosarica = kosarica;
    }

}
