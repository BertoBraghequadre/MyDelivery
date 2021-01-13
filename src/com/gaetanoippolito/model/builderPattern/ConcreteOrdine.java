package com.gaetanoippolito.model.builderPattern;

import com.gaetanoippolito.model.*;
import com.gaetanoippolito.model.observerPattern.Corriere;
import com.gaetanoippolito.model.observerPattern.Destinatario;
import java.time.LocalDate;


public class ConcreteOrdine extends BuilderOrdine {
    @Override
    public void buildMittente(Cliente mittente){
        ordine.setMittente(mittente);
    }

    @Override
    public void buildDestinatario(Destinatario persona){
        ordine.setDestinatario(persona);
    }

    @Override
    public void buildStatoOrdine(StatoOrdine stato){
        ordine.setStatoOrdine(stato);
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
