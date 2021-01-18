package com.gaetanoippolito.model;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

public class CentroDiSmistamento implements Serializable {
    // id del "serialVersionUID"
    @Serial
    private static final long serialVersionUID = 10L;

    private String nomeCentroDiSmistamento;
    private String indirizzo;
    private String numeroCivico;

    public CentroDiSmistamento(String nomeCentroDiSmistamento, String indirizzo, String numeroCivico){
        this.nomeCentroDiSmistamento = nomeCentroDiSmistamento;
        this.indirizzo = indirizzo;
        this.numeroCivico = numeroCivico;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getNomeCentroDiSmistamento() {
        return this.nomeCentroDiSmistamento;
    }

    public String getIndirizzo() {
        return this.indirizzo;
    }

    public String getNumeroCivico() {
        return this.numeroCivico;
    }

    public void setNomeCentroDiSmistamento(String nomeCentroDiSmistamento) {
        this.nomeCentroDiSmistamento = nomeCentroDiSmistamento;
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }

    public void setNumeroCivico(String numeroCivico) {
        this.numeroCivico = numeroCivico;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CentroDiSmistamento)) return false;
        CentroDiSmistamento that = (CentroDiSmistamento) o;
        return numeroCivico == that.numeroCivico && Objects.equals(nomeCentroDiSmistamento, that.nomeCentroDiSmistamento) && Objects.equals(indirizzo, that.indirizzo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nomeCentroDiSmistamento, indirizzo, numeroCivico);
    }

    @Override
    public String toString() {
        return "CentroDiSmistamento{" +
                "nomeCentroDiSmistamento='" + nomeCentroDiSmistamento + '\'' +
                ", indirizzo='" + indirizzo + '\'' +
                ", numeroCivico=" + numeroCivico +
                '}';
    }
}
