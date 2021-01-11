package com.gaetanoippolito.model;

import java.time.LocalDate;

public class Pacco {
    private String codice;
    private Cliente mittente;
    private Persona destinatario;
    private double pesoPacco;
    private boolean isFragile;

    public Pacco(Cliente mittente, Persona destinatario, double pesoPacco, boolean isFragile){
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

    public Persona getDestinatario() {
        return destinatario;
    }

    public double getPesoPacco() {
        return pesoPacco;
    }

    public boolean isFragile() {
        return this.isFragile;
    }

    public void setMittente(Cliente mittente) {
        this.mittente = mittente;
    }

    public void setDestinatario(Persona destinatario) {
        this.destinatario = destinatario;
    }

    public void setPesoPacco(double pesoPacco) {
        this.pesoPacco = pesoPacco;
    }

    public void setFragile(boolean fragile) {
        isFragile = fragile;
    }
}
