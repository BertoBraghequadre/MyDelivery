package com.gaetanoippolito.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

public class Azienda implements Serializable {
    // id del "serialVersionUID" di default
    private static final long serialVersionUID = 1L;

    // Attributi
    private String nome;
    private String partitaIVA;
    private ArrayList<Veicolo> veicoli;
    // TODO: Aggiungere i Corrieri

    // Costruttore
    public Azienda(String nome, String partitaIVA, ArrayList<Veicolo> veicoli){
        this.nome = nome;
        this.partitaIVA = partitaIVA;
        this.veicoli = veicoli;
    }

    public Azienda(String nome, String partitaIVA){
        this(nome, partitaIVA, new ArrayList<>());
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Azienda)) return false;
        Azienda azienda = (Azienda) o;
        return Objects.equals(partitaIVA, azienda.partitaIVA);
    }

    @Override
    public int hashCode() {
        return Objects.hash(partitaIVA);
    }

    @Override
    public String toString() {
        return "Azienda{" +
                "nome='" + nome + '\'' +
                ", partitaIVA='" + partitaIVA + '\'' +
                ", veicoli=" + veicoli +
                '}';
    }
}
