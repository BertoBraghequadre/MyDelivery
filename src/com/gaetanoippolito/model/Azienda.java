package com.gaetanoippolito.model;

import java.util.ArrayList;

public class Azienda {
    // Attributi
    private String nome;
    private String partitaIVA;
    private ArrayList<Veicolo> veicoli;
    // TODO: Aggiungere i Corrieri

    // TODO: Finire l'azienda, Creare i Dialog per aggiungere e rimuovere aziende, Fare in modo di aggiungere su un file le nuove aziende
    // Costruttore
    public Azienda(String nome, String partitaIVA, ArrayList<Veicolo> veicoli){
        this.nome = nome;
        this.partitaIVA = partitaIVA;
        this.veicoli = veicoli;
    }

    // Setter e Getter
    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getPartitaIVA() {
        return this.partitaIVA;
    }

    public void setPartitaIVA(String partitaIVA) {
        this.partitaIVA = partitaIVA;
    }

    public ArrayList<Veicolo> getVeicoli() {
        return this.veicoli;
    }

    public void setVeicoli(ArrayList<Veicolo> veicoli) {
        this.veicoli = veicoli;
    }
}
