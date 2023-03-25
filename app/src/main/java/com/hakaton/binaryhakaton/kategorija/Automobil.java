package com.hakaton.binaryhakaton.kategorija;

import java.util.ArrayList;

public class Automobil {
    public String getGodiste() {
        return godiste;
    }

    public Automobil() {
    }

    public String godiste, kilometraza, gorivo, kilovati, transmisija, konjske_snage, boja, registracija, esp, klima, navigacija, tempomat;
    public String katt;


    public Automobil(String katt, String godiste, String kilometraza, String gorivo, String kilovati,
                     String transmisija, String konjske_snage, String boja, String registracija,
                     String esp, String klima, String navigacija, String tempomat) {
        this.katt = katt;
        this.godiste = godiste;
        this.kilometraza = kilometraza;
        this.gorivo = gorivo;
        this.kilovati = kilovati;
        this.transmisija = transmisija;
        this.konjske_snage = konjske_snage;
        this.boja = boja;
        this.registracija = registracija;
        this.esp = esp;
        this.klima = klima;
        this.navigacija = navigacija;
        this.tempomat = tempomat;
    }
}
