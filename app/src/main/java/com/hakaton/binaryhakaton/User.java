package com.hakaton.binaryhakaton;

import java.util.ArrayList;

public class User {

    public String getUsername() {
        return username;
    }

    public String username, email, priv;

    public ArrayList<String> poruke = new ArrayList<String>();


    public User() {

    }

    public User(String username, String email, String priv, ArrayList<String> poruke) {
        this.username = username;
        this.email = email;
        this.priv = priv;
        this.poruke = poruke;
    }

}
