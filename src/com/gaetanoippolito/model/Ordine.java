package com.gaetanoippolito.model;

import java.time.LocalDate;
import java.util.Random;

public class Ordine {
    private Pacco pacco;
    private Cliente mittente;
    private Persona destinatario;
    private StatoOrdine statoOrdine;
    private LocalDate dataDiConsegna;
    private Azienda ordineDaAzienda;
    // TODO: Aggiungere i corrieri
    // private Corriere ordineDelCorriere;
    private Veicolo ordineDelVeicolo;

    public Ordine(){
        pacco = new Pacco(getMittente(), getDestinatario(), generaPeso(), generaFragile());
    }

    public Pacco getPacco() {
        return this.pacco;
    }

    public StatoOrdine getStatoOrdine() {
        return this.statoOrdine;
    }

    public LocalDate getDataDiConsegna() {
        return this.dataDiConsegna;
    }

    public Cliente getMittente(){
        return this.mittente;
    }

    public Persona getDestinatario(){
        return this.destinatario;
    }

    public Azienda getOrdineDaAzienda() {
        return this.ordineDaAzienda;
    }

    public Veicolo getOrdineDelVeicolo() {
        return this.ordineDelVeicolo;
    }

    public void setPacco(Pacco pacco) {
        // TODO: Permettere la creazione dei pacchi tramite il builder
        this.pacco = pacco;
    }

    public void setStatoOrdine(StatoOrdine statoOrdine) {
        this.statoOrdine = statoOrdine;
    }

    public void setDataDiConsegna(LocalDate dataDiConsegna) {
        this.dataDiConsegna = dataDiConsegna;
    }

    public void setMittente(Cliente mittente){
        this.mittente = mittente;
    }

    public void setDestinatario(Persona destinatario){
        this.destinatario = destinatario;
    }

    public void setOrdineDaAzienda(Azienda ordineDaAzienda) {
        this.ordineDaAzienda = ordineDaAzienda;
    }

    public void setOrdineDelVeicolo(Veicolo ordineDelVeicolo) {
        this.ordineDelVeicolo = ordineDelVeicolo;
    }

    private double generaPeso(){
        Random random = new Random();

        int maxSize = 80;
        return ((double) random.nextInt(maxSize)) + 0.1d;
    }

    private boolean generaFragile(){
        Random random = new Random();

        if((random.nextInt(10) + 1) <= 5){
            return true;
        }
        else{
            return false;
        }
    }
}
