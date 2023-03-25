package com.hakaton.binaryhakaton;

import java.util.ArrayList;

public class Artikal {
    public Artikal(String naziv_oglasa, String stanje, String objava_date,
                   String objavio_username, String detaljni_opis, String lokacija,
                   ArrayList<String> slike, String cijena, Object kategorija) {
        this.naziv_oglasa = naziv_oglasa;
        this.stanje = stanje;
        this.objava_date = objava_date;
        this.objavio_username = objavio_username;
        this.detaljni_opis = detaljni_opis;
        this.lokacija = lokacija;
        this.slike = slike;
        this.cijena = cijena;
        this.kategorija = kategorija;
    }

    public String naziv_oglasa, stanje, objava_date, objavio_username, detaljni_opis, lokacija;
    public ArrayList<String> slike = new ArrayList<String>();

    public Artikal() {
    }

    public String cijena;
    public Object kategorija;
}
