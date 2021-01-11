package com.gaetanoippolito.model.builderOrdine;

import com.gaetanoippolito.model.*;
import java.time.LocalDate;


public class ConcreteOrdine extends BuilderOrdine {
    @Override
    public void buildMittente(Cliente mittente){
        ordine.setMittente(mittente);
    }

    @Override
    public void buildDestinatario(Persona persona){
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

    /*
    @Override
    public void buildCorriere(Corriere corriere){

    }
    */
}
