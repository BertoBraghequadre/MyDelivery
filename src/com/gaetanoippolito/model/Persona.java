package com.gaetanoippolito.model;

import java.io.Serial;
import java.io.Serializable;

public class Persona implements Serializable {
    @Serial
    private static final long serialVersionUID = 5L;
    // TODO: Commentare la classe Persona
    private String nome;
    private String cognome;
    private String indirizzo;
    private String numeroDiTelefono;
    private String cf;

    public Persona(String nome, String cognome, String indirizzo, String numeroDiTelefono, String cf) {
        this.nome = nome;
        this.cognome = cognome;
        this.indirizzo = indirizzo;
        this.numeroDiTelefono = numeroDiTelefono;
        this.cf = cf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }

    public String getNumeroDiTelefono() {
        return numeroDiTelefono;
    }

    public void setNumeroDiTelefono(String numeroDiTelefono) {
        this.numeroDiTelefono = numeroDiTelefono;
    }

    public String getCf() {
        return cf;
    }

    public void setCf(String cf) {
        this.cf = cf;
    }
}
