package com.gaetanoippolito.model.observerPattern;

import com.gaetanoippolito.model.Persona;
import com.gaetanoippolito.model.StatoOrdine;
import java.util.ArrayList;

public class Destinatario extends Persona implements ObserverDestinatario{
    private StatoOrdine statoOrdine;
    private ArrayList<ObservableCorriere> listaCorrieriDiOrdine;

    public Destinatario(String nome, String cognome, String indirizzo, String numeroDiTelefono, String cf,
                        StatoOrdine statoOrdine, ObservableCorriere corriereDelPacco){
        super(nome, cognome, indirizzo, numeroDiTelefono, cf);
        this.statoOrdine = statoOrdine;

        this.listaCorrieriDiOrdine = new ArrayList<>();
        this.listaCorrieriDiOrdine.add(corriereDelPacco);
    }

    public StatoOrdine getStatoOrdine() {
        return statoOrdine;
    }

    public void setStatoOrdine(StatoOrdine statoOrdine) {
        this.statoOrdine = statoOrdine;
    }

    public ArrayList<ObservableCorriere> getListaCorrieriDiOrdine() {
        return listaCorrieriDiOrdine;
    }

    public void setListaCorrieriDiOrdine(ArrayList<ObservableCorriere> listaCorrieriDiOrdine) {
        this.listaCorrieriDiOrdine = listaCorrieriDiOrdine;
    }

    @Override
    public void updateStatoOrdine(StatoOrdine statOrdine){
        setStatoOrdine(statOrdine);
    }
}
