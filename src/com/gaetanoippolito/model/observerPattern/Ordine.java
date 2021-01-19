package com.gaetanoippolito.model.observerPattern;

import com.gaetanoippolito.model.*;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Ordine implements Serializable, ObservableOrdine {
    // id del "serialVersionUID"
    @Serial
    private static final long serialVersionUID = 10L;

    private Pacco pacco;
    private Cliente mittente;
    private LocalDate dataDiConsegna;
    private Azienda ordineDaAzienda;
    private Corriere ordineDelCorriere;
    private Veicolo ordineDelVeicolo;
    private boolean presoInCarico;
    private List<ObserverDestinatario> destinatari = new ArrayList<>();
    private Stato statoPacco;

    public Ordine(){
        this.presoInCarico = false;
    }

    public Pacco getPacco() {
        return this.pacco;
    }

    public LocalDate getDataDiConsegna() {
        return this.dataDiConsegna;
    }

    public Cliente getMittente(){
        return this.mittente;
    }

    public Azienda getAziendaDaOrdine() {
        return this.ordineDaAzienda;
    }

    public Veicolo getOrdineDelVeicolo() {
        return this.ordineDelVeicolo;
    }

    public Corriere getCorriereFromOrdine(){
        return this.ordineDelCorriere;
    }

    public boolean getPresoInCarico(){
        return this.presoInCarico;
    }

    public Stato getStatoPacco(){
        return this.statoPacco;
    }

    public List<ObserverDestinatario> getDestinatari() {
        return destinatari;
    }

    public void setDestinatari(List<ObserverDestinatario> destinatari) {
        this.destinatari = destinatari;
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

    public void setAzienda(Azienda ordineDaAzienda) {
        this.ordineDaAzienda = ordineDaAzienda;
    }

    public void setVeicoloDiOrdine(Veicolo ordineDelVeicolo) {
        this.ordineDelVeicolo = ordineDelVeicolo;
    }

    public void setOrdineDelCorriere(Corriere corriere){
        this.ordineDelCorriere = corriere;
    }

    public void setPresoInCarico(boolean presoInCarico){
        this.presoInCarico = presoInCarico;
    }

    public void setStatoPacco(Stato statoPacco){
        this.statoPacco = statoPacco;
        notifyObserver(statoPacco);
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
    public void attach(ObserverDestinatario destinatario) {
        this.destinatari.add(destinatario);
    }

    @Override
    public void detach(ObserverDestinatario destinatario) {
        this.destinatari.remove(destinatario);
    }

    @Override
    public void notifyObserver(Stato statoAggiornato) {
        for(ObserverDestinatario destinatario : this.destinatari){
            destinatario.update(statoAggiornato);
        }
    }

    @Override
    public String toString() {
        return "Ordine{" +
                "pacco=" + pacco +
                ", mittente=" + mittente +
                ", dataDiConsegna=" + dataDiConsegna +
                ", ordineDaAzienda=" + ordineDaAzienda +
                ", ordineDelCorriere=" + ordineDelCorriere +
                ", ordineDelVeicolo=" + ordineDelVeicolo +
                '}';
    }


}
