package com.gaetanoippolito.model.observerPattern;

import com.gaetanoippolito.model.StatoOrdine;
import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

/**
 * Questa classe rappresenta lo stato che viene aggiornato tramite il pattern Observer
 */
public class Stato implements Serializable {
    ///////////////////////////////// VARIABILI DI ISTANZA /////////////////////////////////
    // id del "serialVersionUID"
    /**@see Serializable*/
    @Serial
    private static final long serialVersionUID = 14L;
    private StatoOrdine statoOrdine;
    private String posizione;

    //////////////////////////////////// COSTRUTTORE ////////////////////////////////////
    /**
     * Questo è il costruttore della classe Stato.
     * @param statoOrdine Rappresenta lo stato dell'ordine
     * @param posizione Rappresenta la posizione dell'ordine
     */
    public Stato(StatoOrdine statoOrdine, String posizione) {
        this.statoOrdine = statoOrdine;
        this.posizione = posizione;
    }

    ///////////////////////////////////// GETTER /////////////////////////////////////
    /**
     * Metodo che restituisce lo stato dell'ordine
     * @return Ritorna lo stato dell'ordine
     */
    public StatoOrdine getStato() {
        return this.statoOrdine;
    }

    /**
     * Metodo che restituisce la posizione dell'ordine
     * @return Ritorna la posizione dell'ordine
     */
    public String getPosizione() {
        return this.posizione;
    }

    ///////////////////////////////////// SETTER /////////////////////////////////////
    /**
     * Metodo che setta lo stato dell'ordine
     * @param statoOrdine Rappresenta lo stato dell'ordine
     */
    public void setStatoOrdine(StatoOrdine statoOrdine) {
        this.statoOrdine = statoOrdine;
    }

    /**
     * Metodo che setta la posizione dell'ordine
     * @param posizione Rappresenta la posizione dell'ordine
     */
    public void setPosizione(String posizione) {
        this.posizione = posizione;
    }

    ///////////////////////////////////// METODI /////////////////////////////////////
    /**
     * Metodo overridato dalla classe object che ritorna vero se l'istanza che chiama il metodo è uguale all'istanza
     * della classe all'interno del parametro di input di equals.
     * @see Object
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Stato)) return false;
        Stato stato = (Stato) o;
        return statoOrdine == stato.statoOrdine && Objects.equals(posizione, stato.posizione);
    }

    /**
     * Questo metodo overridato della classe Object ritorna l'hashcode della classe
     * @return Rappresenta il codice della claasse
     */
    @Override
    public int hashCode() {
        return Objects.hash(statoOrdine, posizione);
    }

    /**
     * Questo è un metodo overridato dalla classe Object che ritorna una stringa rappresentante l'istanza della
     * classe da cui viene chiamato.
     * @return Ritorna una stringa con le informazioni dell'istanza della classe Destinatario
     * @see Object
     */
    @Override
    public String toString() {
        return String.format("%s: %s", this.statoOrdine, this.posizione);
    }
}
