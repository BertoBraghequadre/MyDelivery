package com.gaetanoippolito.model;

import com.gaetanoippolito.model.observerPattern.Corriere;
import com.gaetanoippolito.model.observerPattern.Destinatario;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Random;

public class Ordine implements Serializable {
    ///////////////////////////////// VARIABILI DI ISTANZA /////////////////////////////////
    // id del "serialVersionUID"
    @Serial
    private static final long serialVersionUID = 10L;

    private Pacco pacco;
    private Cliente mittente;
    private Destinatario destinatario;
    private LocalDate dataDiConsegna;
    private Azienda ordineDaAzienda;
    private Corriere ordineDelCorriere;
    private Veicolo ordineDelVeicolo;

    public Ordine(){}

    public Pacco getPacco() {
        return this.pacco;
    }

    public LocalDate getDataDiConsegna() {
        return this.dataDiConsegna;
    }

    public Cliente getMittente(){
        return this.mittente;
    }

    public Destinatario getDestinatario(){
        return this.destinatario;
    }

    public Azienda getOrdineDaAzienda() {
        return this.ordineDaAzienda;
    }

    public Veicolo getOrdineDelVeicolo() {
        return this.ordineDelVeicolo;
    }

    public Corriere getCorriereFromOrdine(){
        return this.ordineDelCorriere;
    }

    public void setPacco(Pacco pacco) {
        this.pacco = pacco;
    }

    public void setDataDiConsegna(LocalDate dataDiConsegna) {
        this.dataDiConsegna = dataDiConsegna;
    }

    public void setMittente(Cliente mittente){
        this.mittente = mittente;
    }

    public void setDestinatario(Destinatario destinatario){
        this.destinatario = destinatario;
    }

    public void setOrdineDaAzienda(Azienda ordineDaAzienda) {
        this.ordineDaAzienda = ordineDaAzienda;
    }

    public void setOrdineDelVeicolo(Veicolo ordineDelVeicolo) {
        this.ordineDelVeicolo = ordineDelVeicolo;
    }

    public void setOrdineDelCorriere(Corriere corriere){
        this.ordineDelCorriere = corriere;
    }

    public double generaPeso(){
        Random random = new Random();

        int maxSize = 80;
        return ((double) random.nextInt(maxSize)) + 0.1d;
    }

    public boolean generaFragile(){
        Random random = new Random();

        if((random.nextInt(10) + 1) <= 5){
            return true;
        }
        else{
            return false;
        }
    }

    @Override
    public String toString() {
        return "Ordine{" +
                "pacco=" + pacco +
                ", mittente=" + mittente +
                ", destinatario=" + destinatario +
                ", dataDiConsegna=" + dataDiConsegna +
                ", ordineDaAzienda=" + ordineDaAzienda +
                ", ordineDelCorriere=" + ordineDelCorriere +
                ", ordineDelVeicolo=" + ordineDelVeicolo +
                '}';
    }
}
