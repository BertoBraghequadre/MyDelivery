package com.gaetanoippolito.model;

import com.gaetanoippolito.model.database.MyDeliveryData;
import com.gaetanoippolito.model.magazziniereBuilder.Magazziniere;
import java.util.ArrayList;

/**
 * Questa classe rappresenta il Magazzino dove saranno contenuti i Pacchi e dove i Magazzinieri li usarenno per riempire
 * i Veicoli. Supponiamo per politica aziendale che il Magazzino sia singolo, per cui verrà applicato un Singleton
 */

public class Magazzino {
    // Attributi
    private String indirizzo;
    private int numeroCivico;
    private String cap;
    private ArrayList<Pacco> pacchi;
    private ArrayList<Magazziniere> magazziniere;
    // private static Magazzino instance =

    public Magazzino(String indirizzo, int numeroCivico, String cap) {
        this.indirizzo = indirizzo;
        this.numeroCivico = numeroCivico;
        this.cap = cap;
        this.pacchi = pacchi;
        this.magazziniere = magazziniere;
    }

    // Setter e Getter
    public String getIndirizzo() {
        return indirizzo;
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }

    public int getNumeroCivico() {
        return numeroCivico;
    }

    public void setNumeroCivico(int numeroCivico) {
        this.numeroCivico = numeroCivico;
    }

    public String getCap() {
        return cap;
    }

    public void setCap(String cap) {
        this.cap = cap;
    }

    public ArrayList<Pacco> getPacchi() {
        return pacchi;
    }

    public void setPacchi(ArrayList<Pacco> pacchi) {
        this.pacchi = pacchi;
    }

    public ArrayList<Magazziniere> getMagazziniere() {
        return magazziniere;
    }

    public void setMagazziniere(ArrayList<Magazziniere> magazziniere) {
        this.magazziniere = magazziniere;
    }

    @Override
    public String toString() {
        return "Magazzino{" +
                "indirizzo='" + indirizzo + '\'' +
                ", numeroCivico=" + numeroCivico +
                ", cap='" + cap + '\'' +
                ", pacchi=" + pacchi +
                ", magazziniere=" + magazziniere +
                '}';
    }
}
