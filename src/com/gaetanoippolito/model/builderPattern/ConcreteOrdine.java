package com.gaetanoippolito.model.builderPattern;

import com.gaetanoippolito.model.*;
import com.gaetanoippolito.model.observerPattern.Corriere;
import com.gaetanoippolito.model.observerPattern.Destinatario;

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
        ordine.setDestinatario(persona);
    }

    @Override
    public void buildDataDiConsegna(LocalDate dataDiConsegna){
        ordine.setDataDiConsegna(dataDiConsegna);
    }

    @Override
    public void buildAzienda(Azienda azienda){
        ordine.setOrdineDaAzienda(azienda);
    }

    @Override
    public void buildVeicolo(Veicolo veicolo){
        ordine.setOrdineDelVeicolo(veicolo);
    }

    @Override
    public void buildCorriere(Corriere corriere){
        ordine.setOrdineDelCorriere(corriere);
    }
}
