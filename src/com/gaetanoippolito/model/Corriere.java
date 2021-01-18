package com.gaetanoippolito.model;

import com.gaetanoippolito.model.observerPattern.Destinatario;
import com.gaetanoippolito.model.observerPattern.ObserverDestinatario;
import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;

public class Corriere extends Utente implements Serializable {
    ///////////////////////////////// VARIABILI DI ISTANZA /////////////////////////////////
    // id del "serialVersionUID"
    @Serial
    private static final long serialVersionUID = 7L;
    private String idCorriere;
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

    public ArrayList<ObserverDestinatario> getListaDestinatari() {
        return this.listaDestinatari;
    }

    public boolean getIsBusy(){
        return this.isBusy;
    }

    public void setIdCorriere(String idCorriere) {
        this.idCorriere = idCorriere;
    }

    public void setListaDestinatari(ArrayList<ObserverDestinatario> listaDestinatari) {
        this.listaDestinatari = listaDestinatari;
    }

    public void setIsBusy(boolean isBusy){
        this.isBusy = isBusy;
    }

    @Override
    public String toString() {
        return "Corriere{" +
                "idCorriere='" + idCorriere + '\'' +
                ", listaDestinatari=" + listaDestinatari +
                ", isBusy=" + isBusy +
                '}';
    }
}
