package com.gaetanoippolito.model.observerPattern;

import com.gaetanoippolito.model.Persona;
import com.gaetanoippolito.model.StatoPacco;
import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;

public class Destinatario extends Persona implements ObserverDestinatario, Serializable {
    ///////////////////////////////// VARIABILI DI ISTANZA /////////////////////////////////
    // id del "serialVersionUID"
    @Serial
    private static final long serialVersionUID = 9L;
    private StatoPacco statoPacco;
    private ArrayList<ObservableCorriere> listaCorrieriDiOrdine;

    public Destinatario(String nome, String cognome, String indirizzo, String numeroDiTelefono, String cf,
                        StatoPacco statoPacco, ObservableCorriere corriereDelPacco){
        super(nome, cognome, indirizzo, numeroDiTelefono, cf);

        this.statoPacco = statoPacco;
        this.listaCorrieriDiOrdine = new ArrayList<>();
        this.listaCorrieriDiOrdine.add(corriereDelPacco);
    }

    public Destinatario(String nome, String cognome, String indirizzo, String numeroDiTelefono, String cf){
        this(nome, cognome, indirizzo, numeroDiTelefono, cf, StatoPacco.IN_PREPARAZIONE, null);
    }

    public StatoPacco getStatoPacco() {
        return statoPacco;
    }

    public void setStatoPacco(StatoPacco statoPacco) {
        this.statoPacco = statoPacco;
    }

    public ArrayList<ObservableCorriere> getListaCorrieriDiOrdine() {
        return listaCorrieriDiOrdine;
    }

    public void setListaCorrieriDiOrdine(ArrayList<ObservableCorriere> listaCorrieriDiOrdine) {
        this.listaCorrieriDiOrdine = listaCorrieriDiOrdine;
    }

    @Override
    public void updateStatoPacco(StatoPacco statoPacco){
        setStatoPacco(statoPacco);
    }

    @Override
    public String toString() {
        return "Persona{" +
                "nome='" + super.getNome() + '\'' +
                ", cognome='" + super.getCognome() + '\'' +
                ", indirizzo='" + super.getIndirizzo() + '\'' +
                ", numeroDiTelefono='" + super.getNumeroDiTelefono() + '\'' +
                ", cf='" + super.getCf() + '\'' +
                ", Stato Ordine='" + this.statoPacco + '\'' +
                '}';
    }
}
