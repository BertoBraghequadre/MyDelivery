package com.gaetanoippolito.model.observerPattern;

import com.gaetanoippolito.model.Ordine;
import com.gaetanoippolito.model.Pacco;
import com.gaetanoippolito.model.StatoPacco;
import com.gaetanoippolito.model.Utente;
import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;

public class Corriere extends Utente implements ObservableCorriere, Serializable {
    ///////////////////////////////// VARIABILI DI ISTANZA /////////////////////////////////
    // id del "serialVersionUID"
    @Serial
    private static final long serialVersionUID = 7L;

    private String idCorriere;
    private Ordine ordineAssociato;
    private Pacco paccoAssociato;
    private ArrayList<ObserverDestinatario> listaDestinatari;
    private boolean isBusy;

    public Corriere(String username, String password, String nome, String cognome, String email, String idCorriere,
                    Destinatario destinatario){
        super(username, password, nome, cognome, email);
        this.idCorriere = idCorriere;
        this.isBusy = false;

        this.listaDestinatari = new ArrayList<>();
        this.listaDestinatari.add(destinatario);
    }

    public Corriere(String nome, String cognome, String idCorriere){
        this("", "", nome, cognome, "", idCorriere, null);
    }

    public String getIdCorriere() {
        return this.idCorriere;
    }

    public Ordine getOrdineAssociato() {
        return this.ordineAssociato;
    }

    public ArrayList<ObserverDestinatario> getListaDestinatari() {
        return this.listaDestinatari;
    }

    public boolean getIsBusy(){
        return this.isBusy;
    }

    public Pacco getPaccoAssociato(){
        return this.paccoAssociato;
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

    public void setIsBusy(boolean isBusy){
        this.isBusy = isBusy;
    }

    public void setPaccoAssociato(Pacco pacco){
        this.paccoAssociato = pacco;
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
    public void notificaDestintari(StatoPacco statoPacco){
        this.paccoAssociato.setStatoPacco(statoPacco);

        for(ObserverDestinatario destinatario : this.listaDestinatari){
            destinatario.updateStatoPacco(this.paccoAssociato.getStatoPacco());
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
