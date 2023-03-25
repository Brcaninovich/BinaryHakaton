package com.hakaton.binaryhakaton;

import com.hakaton.binaryhakaton.kategorija.Automobil;
import com.hakaton.binaryhakaton.kategorija.Odjeca;

import java.util.ArrayList;

public class Artikal {
    public Artikal(String naziv_oglasa, String stanje, String objava_date,
                   String objavio_username, String detaljni_opis, String lokacija,
                   ArrayList<String> slike, String cijena, Automobil kategorija, boolean report, String kateg) {
        this.naziv_oglasa = naziv_oglasa;
        this.stanje = stanje;
        this.objava_date = objava_date;
        this.objavio_username = objavio_username;
        this.detaljni_opis = detaljni_opis;
        this.lokacija = lokacija;
        this.slike = slike;
        this.cijena = cijena;
        this.kategorija = kategorija;
        this.reportan = report;
        this.kateg = kateg;
    }

    public Artikal(String naziv_oglasa, String stanje, String objava_date,
                   String objavio_username, String detaljni_opis, String lokacija,
                   ArrayList<String> slike, String cijena, Odjeca kategorija, boolean report, String kateg) {
        this.naziv_oglasa = naziv_oglasa;
        this.stanje = stanje;
        this.objava_date = objava_date;
        this.objavio_username = objavio_username;
        this.detaljni_opis = detaljni_opis;
        this.lokacija = lokacija;
        this.slike = slike;
        this.cijena = cijena;
        this.odjeca = kategorija;
        this.reportan = report;
        this.kateg = kateg;

    }




    public String naziv_oglasa, stanje, objava_date, objavio_username, detaljni_opis, lokacija;
    public boolean reportan;
    public String kateg;
    public ArrayList<String> slike = new ArrayList<String>();

    public Artikal() {
    }

    public String cijena;


    public Automobil kategorija;
    public Odjeca odjeca;
}
