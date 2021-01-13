package com.gaetanoippolito.model.builderPattern;

import com.gaetanoippolito.model.*;
import com.gaetanoippolito.model.observerPattern.Corriere;
import com.gaetanoippolito.model.observerPattern.Destinatario;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

public abstract class BuilderOrdine implements Serializable {
    // id del "serialVersionUID"
    @Serial
    private static final long serialVersionUID = 14L;

    Ordine ordine;

    public abstract void buildMittente(Cliente mittente);
    public abstract void buildDestinatario(Destinatario destinatario);
    public abstract void buildStatoOrdine(StatoOrdine stato);
    public abstract void buildDataDiConsegna(LocalDate dataDiConsegna);
    public abstract void buildAzienda(Azienda azienda);
    public abstract void buildVeicolo(Veicolo veicolo);
    public abstract void buildCorriere(Corriere corriere);

    public void creaOrdine(){
        this.ordine = new Ordine();
    }

    public final Ordine getOrdine(){
        return this.ordine;
    }
}
