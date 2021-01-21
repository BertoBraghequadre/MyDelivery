package com.gaetanoippolito.model.builderPattern;

import com.gaetanoippolito.model.*;
import com.gaetanoippolito.model.observerPattern.Destinatario;
import com.gaetanoippolito.model.observerPattern.Ordine;
import com.gaetanoippolito.model.observerPattern.Stato;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * Questa classe rappresenta il Builder del pattern "Builder". Specifica una interfaccia atta alla creazione del
 * Product.
 */
public abstract class BuilderOrdine implements Serializable {
    // id del "serialVersionUID"
    /**@see Serializable*/
    @Serial
    private static final long serialVersionUID = 14L;

    /**@see Ordine*/
    Ordine ordine;

    /**
     * Questo è il metodo astratto che permette il build del mittente all'interno della classe Ordine.
     * @param mittente Rappresenta il mittente dell'ordine
     */
    public abstract void buildMittente(Cliente mittente);
    /**
     * Questo è il metodo astratto che permette il build del destinatario all'interno della classe Ordine.
     * @param destinatario Rappresenta il destinatario dell'ordine
     */
    public abstract void buildDestinatario(Destinatario destinatario);
    /**
     * Questo è il metodo astratto che permette il build della data di consegna all'interno della classe Ordine.
     * @param dataDiConsegna Rappresenta la data di consegna dell'ordine
     */
    public abstract void buildDataDiConsegna(LocalDate dataDiConsegna);
    /**
     * Questo è il metodo astratto che permette il build dello stato dell'ordine all'interno della classe Ordine.
     * @param stato Rappresenta lo stato dell'ordine dell'ordine
     */
    public abstract void buildStatoOrdine(Stato stato);
    /**
     * Questo è il metodo astratto che permette il build dell'azienda all'interno della classe Ordine.
     * @param azienda Rappresenta l'azienda a cui è stato fatto un ordine
     */
    public abstract void buildAzienda(Azienda azienda);

    /**
     * Questo metodo permette la creazione di un'istanza di Ordine da buildare
     */
    public void creaOrdine(){
        this.ordine = new Ordine();
    }

    /**
     * Questo metodo ritorna un Ordine buildato dal seguente Pattern
     * @return Ritorna un Ordine buildato
     */
    public final Ordine getOrdine(){
        return this.ordine;
    }
}
