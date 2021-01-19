package com.gaetanoippolito.model.builderPattern;

import com.gaetanoippolito.model.*;
import com.gaetanoippolito.model.observerPattern.Destinatario;
import com.gaetanoippolito.model.observerPattern.Stato;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * Questa classe rappresenta il Concrete builder del pattern Builder. Costruisce il Product in base ai metodi definiti
 * nel Builder.
 */

public class ConcreteOrdine extends BuilderOrdine implements Serializable {
    // id del "serialVersionUID"
    /**@see Serializable*/
    @Serial
    private static final long serialVersionUID = 13L;

    /**
     * Questo metodo builda il mittente dell'ordine sfruttando il metodo "setMittente()" della classe Ordine.
     * @param mittente Rappresenta il mittente dell'ordine
     */
    @Override
    public void buildMittente(Cliente mittente){
        ordine.setMittente(mittente);
    }

    /**
     * Questo metodo builda il destinatario dell'ordine sfruttando il metodo "attach()" della classe Ordine. Essendo
     * un ordine un Observable, ovvero una classe che viene osservata dai destinatari (siccome devono reperire le
     * informazioni dell'ordine che viene modificato man mano) viene sfruttato il metodo "attach()"
     * @param destinatario Rappresenta il mittente dell'ordine
     */
    @Override
    public void buildDestinatario(Destinatario destinatario){
        ordine.attach(destinatario);
    }

    /**
     * Questo metodo builda la data di consegna dell'ordine sfruttando il metodo "setDataDiConsegna()" della
     * classe Ordine.
     * @param dataDiConsegna Rappresenta la data di consegna dell'ordine
     */
    @Override
    public void buildDataDiConsegna(LocalDate dataDiConsegna){
        ordine.setDataDiConsegna(dataDiConsegna);
    }

    /**
     * Questo metodo builda lo stato iniziale dell'ordine sfruttando il metodo "setStatoPacco()" della classe Ordine.
     * @param stato Rappresenta lo stato del pacco che fa parte dell'ordine dell'ordine
     */
    public void buildStatoOrdine(Stato stato){
        ordine.setStatoPacco(stato);
    }

    /**
     * Questo metodo builda l'azienda dell'ordine sfruttando il metodo "setAzienda()" della classe Ordine.
     * @param azienda Rappresenta l'azienda dell'ordine
     */
    @Override
    public void buildAzienda(Azienda azienda){
        ordine.setAzienda(azienda);
    }
}
