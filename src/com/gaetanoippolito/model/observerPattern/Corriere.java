package com.gaetanoippolito.model.observerPattern;

import com.gaetanoippolito.model.Ordine;
import com.gaetanoippolito.model.StatoOrdine;
import com.gaetanoippolito.model.Utente;
import com.gaetanoippolito.model.Veicolo;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;

public class Corriere extends Utente implements ObservableCorriere, Serializable {
    ///////////////////////////////// VARIABILI DI ISTANZA /////////////////////////////////
    // id del "serialVersionUID"
    @Serial
    private static final long serialVersionUID = 7L;

    private int idCorriere;
    private Ordine ordineAssociato;
    private ArrayList<ObserverDestinatario> listaDestinatari;
    private boolean isBusy;

    public Corriere(String username, String password, String nome, String cognome, String email, int idCorriere,
                    Ordine ordineAssociato, Destinatario destinatario){
        super(username, password, nome, cognome, email);
        this.idCorriere = idCorriere;
        this.ordineAssociato = ordineAssociato;
        this.isBusy = false;

        this.listaDestinatari = new ArrayList<>();
        this.listaDestinatari.add(destinatario);
    }

    public Corriere(String nome, String cognome, int idCorriere){
        this("", "", nome, cognome, "", idCorriere, null, null);
    }

    public int getIdCorriere() {
        return idCorriere;
    }

    public Ordine getOrdineAssociato() {
        return ordineAssociato;
    }

    public ArrayList<ObserverDestinatario> getListaDestinatari() {
        return listaDestinatari;
    }

    public boolean getIsBusy(){
        return this.isBusy;
    }

    public void setIdCorriere(int idCorriere) {
        this.idCorriere = idCorriere;
    }

    public void setOrdineAssociato(Ordine ordineAssociato) {
        this.ordineAssociato = ordineAssociato;
    }

    public void setListaDestinatari(ArrayList<ObserverDestinatario> listaDestinatari) {
        this.listaDestinatari = listaDestinatari;
    }

    public void setIsBusy(boolean isBusy){
        this.isBusy = isBusy;
    }

    @Override
    public void aggiungiDestinatario(Destinatario destinatario){
        this.listaDestinatari.add(destinatario);
    }

    @Override
    public void rimuoviDestinatario(Destinatario destinatario){
        this.listaDestinatari.remove(destinatario);
    }

    @Override
    public void notificaDestintari(StatoOrdine ordine){
        this.ordineAssociato.setStatoOrdine(ordine);

        for(ObserverDestinatario destinatario : this.listaDestinatari){
            destinatario.updateStatoOrdine(this.ordineAssociato.getStatoOrdine());
        }
    }

    @Override
    public String toString(){
        return String.format("Nome: %s\n" +
                             "Cognome: %s\n" +
                             "IdCorriere: %s\n" +
                             "isBusy: %s",
                             super.getNome(), super.getCognome(), this.idCorriere, this.isBusy);
    }
}
