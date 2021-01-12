package com.gaetanoippolito.model;

import com.gaetanoippolito.model.observer.Destinatario;

import java.time.LocalDate;

public class Pacco {
    private String codice;
    private Cliente mittente;
    private Destinatario destinatario;
    private double pesoPacco;
    private boolean isFragile;

    public Pacco(Cliente mittente, Destinatario destinatario, double pesoPacco, boolean isFragile){
        this.mittente = mittente;
        this.destinatario = destinatario;
        this.pesoPacco = pesoPacco;
        this.isFragile = isFragile;
        // TODO: i codici dovranno essere uguali alla size di tutti gli ordini
    }

    public String getCodice(){
        return this.codice;
    }

    public Cliente getMittente(){
        return this.mittente;
    }

    public Destinatario getDestinatario() {
        return this.destinatario;
    }

    public double getPesoPacco() {
        return this.pesoPacco;
    }

    public boolean isFragile() {
        return this.isFragile;
    }

    public void setMittente(Cliente mittente) {
        this.mittente = mittente;
    }

    public void setDestinatario(Destinatario destinatario) {
        this.destinatario = destinatario;
    }

    public void setPesoPacco(double pesoPacco) {
        this.pesoPacco = pesoPacco;
    }

    public void setFragile(boolean fragile) {
        isFragile = fragile;
    }
}
