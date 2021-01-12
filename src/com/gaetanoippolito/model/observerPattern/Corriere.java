package com.gaetanoippolito.model.observerPattern;

import com.gaetanoippolito.model.Ordine;
import com.gaetanoippolito.model.StatoOrdine;
import com.gaetanoippolito.model.Utente;
import java.util.ArrayList;

public class Corriere extends Utente implements ObservableCorriere{
    private String idCorriere;
    private Ordine ordineAssociato;
    private ArrayList<ObserverDestinatario> listaDestinatari;

    public Corriere(String username, String password, String nome, String cognome, String email, String idCorriere,
                    Ordine ordineAssociato, Destinatario destinatario){
        super(username, password, nome, cognome, email);
        this.idCorriere = idCorriere;
        this.ordineAssociato = ordineAssociato;

        this.listaDestinatari = new ArrayList<>();
        this.listaDestinatari.add(destinatario);
    }

    public String getIdCorriere() {
        return idCorriere;
    }

    public Ordine getOrdineAssociato() {
        return ordineAssociato;
    }

    public ArrayList<ObserverDestinatario> getListaDestinatari() {
        return listaDestinatari;
    }

    public void setIdCorriere(String idCorriere) {
        this.idCorriere = idCorriere;
    }

    public void setOrdineAssociato(Ordine ordineAssociato) {
        this.ordineAssociato = ordineAssociato;
    }

    public void setListaDestinatari(ArrayList<ObserverDestinatario> listaDestinatari) {
        this.listaDestinatari = listaDestinatari;
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
}
