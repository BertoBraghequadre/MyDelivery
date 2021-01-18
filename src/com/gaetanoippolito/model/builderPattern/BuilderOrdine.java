package com.gaetanoippolito.model.builderPattern;

import com.gaetanoippolito.model.*;
import com.gaetanoippolito.model.observerPattern.Destinatario;
import com.gaetanoippolito.model.observerPattern.Ordine;
import com.gaetanoippolito.model.observerPattern.Stato;

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
    public abstract void buildDataDiConsegna(LocalDate dataDiConsegna);
    public abstract void buildStatoOrdine(Stato stato);
    public abstract void buildAzienda(Azienda azienda);

    public void creaOrdine(){
        this.ordine = new Ordine();
    }

    public final Ordine getOrdine(){
        return this.ordine;
    }
}
