package com.gaetanoippolito.model.builderPattern;

import com.gaetanoippolito.model.*;
import com.gaetanoippolito.model.observerPattern.Destinatario;
import com.gaetanoippolito.model.observerPattern.Stato;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;


public class ConcreteOrdine extends BuilderOrdine implements Serializable {
    // id del "serialVersionUID"
    @Serial
    private static final long serialVersionUID = 13L;

    @Override
    public void buildMittente(Cliente mittente){
        ordine.setMittente(mittente);
    }

    @Override
    public void buildDestinatario(Destinatario persona){
        ordine.attach(persona);
    }

    @Override
    public void buildDataDiConsegna(LocalDate dataDiConsegna){
        ordine.setDataDiConsegna(dataDiConsegna);
    }

    public void buildStatoOrdine(Stato stato){
        ordine.setStatoPacco(stato);
    }

    @Override
    public void buildAzienda(Azienda azienda){
        ordine.setOrdineDaAzienda(azienda);
    }
}
