package com.gaetanoippolito.model;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

/**
 * Questa classe rappresenta il Centro di smistamento dove il corriere aggiorna lo stato dell'ordine
 */
public class CentroDiSmistamento implements Serializable {
    ///////////////////////////////// VARIABILI DI ISTANZA /////////////////////////////////
    // id del "serialVersionUID"
    /**@see Serializable*/
    @Serial
    private static final long serialVersionUID = 10L;

    private String nomeCentroDiSmistamento;
    private String indirizzo;
    private String numeroCivico;

    //////////////////////////////////// COSTRUTTORE ////////////////////////////////////
    /**
     * Questo è il costruttore del Centro di smistamento.
     * @param nomeCentroDiSmistamento Rappresenta il nome del centro di smistamento
     * @param indirizzo Rappresenta l'indirizzo del centro di smistamento
     * @param numeroCivico Rappresenta il numero civico dell'indirizzo di dove si trova il centro di smistamento
     */
    public CentroDiSmistamento(String nomeCentroDiSmistamento, String indirizzo, String numeroCivico){
        this.nomeCentroDiSmistamento = nomeCentroDiSmistamento;
        this.indirizzo = indirizzo;
        this.numeroCivico = numeroCivico;
    }

    //////////////////////////////////// GETTER ////////////////////////////////////
    /**
     * Questo è il metodo che ritorna il nome del centro di smistamento
     * @return Ritorna una stringa che rappresenta il nome del centro di smistamento
     */
    public String getNomeCentroDiSmistamento() {
        return this.nomeCentroDiSmistamento;
    }

    /**
     * Questo è il metodo che ritorna l'indirizzo del centro di smistamento
     * @return Ritorna una stringa che rappresenta l'indirizzo del centro di smistamento
     */
    public String getIndirizzo() {
        return this.indirizzo;
    }

    /**
     * Questo è il metodo che ritorna il numero civico del centro di smistamento
     * @return Ritorna una stringa che rappresenta il numero civico del centro di smistamento
     */
    public String getNumeroCivico() {
        return this.numeroCivico;
    }

    //////////////////////////////////// SETTER ////////////////////////////////////
    /**
     * Questo metodo setta il nome del centro di smistamento
     * @param nomeCentroDiSmistamento Rappresenta il nome da settare al centro di smistamento
     */
    public void setNomeCentroDiSmistamento(String nomeCentroDiSmistamento) {
        this.nomeCentroDiSmistamento = nomeCentroDiSmistamento;
    }

    /**
     * Questo metodo setta l'indirizzo del centro di smistamento
     * @param indirizzo Rappresenta l'indirizz da settare al centro di smistamento
     */
    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }

    /**
     * Questo metodo setta il numero civico del centro di smistamento
     * @param numeroCivico Rappresenta il numero civico da settare al centro di smistamento
     */
    public void setNumeroCivico(String numeroCivico) {
        this.numeroCivico = numeroCivico;
    }

    //////////////////////////////////// METODI ////////////////////////////////////
    /**
     * Metodo overridato dalla classe object che ritorna vero se l'istanza che chiama il metodo è uguale all'istanza
     * della classe all'interno del parametro di input di equals.
     * @see Object
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CentroDiSmistamento)) return false;
        CentroDiSmistamento that = (CentroDiSmistamento) o;
        return numeroCivico == that.numeroCivico && Objects.equals(nomeCentroDiSmistamento, that.nomeCentroDiSmistamento) && Objects.equals(indirizzo, that.indirizzo);
    }

    /**
     * Questo metodo overridato della classe Object ritorna l'hashcode della classe
     * @return Rappresenta il codice della claasse
     */
    @Override
    public int hashCode() {
        return Objects.hash(nomeCentroDiSmistamento, indirizzo, numeroCivico);
    }

    /**
     * Questo è un metodo overridato dalla classe Object che ritorna una stringa rappresentante l'istanza della
     * classe da cui viene chiamato.
     * @return Ritorna una stringa con le informazioni dell'istanza della classe Destinatario
     * @see Object
     */
    @Override
    public String toString() {
        return String.format("%s: %s", this.nomeCentroDiSmistamento, this.indirizzo + ", " + this.numeroCivico);
    }
}
