package com.gaetanoippolito.model.builderOrdine;

import com.gaetanoippolito.model.*;
import com.gaetanoippolito.model.observer.Destinatario;

import java.time.LocalDate;

public abstract class BuilderOrdine {
    Ordine ordine;

    public abstract void buildMittente(Cliente mittente);
    public abstract void buildDestinatario(Destinatario destinatario);
    public abstract void buildStatoOrdine(StatoOrdine stato);
    public abstract void buildDataDiConsegna(LocalDate dataDiConsegna);
    public abstract void buildAzienda(Azienda azienda);
    public abstract void buildVeicolo(Veicolo veicolo);
    //public abstract void buildCorriere(Corriere corriere);

    public void creaOrdine(){
        this.ordine = new Ordine();
    }

    public final Ordine getOrdine(){
        return this.ordine;
    }
}
