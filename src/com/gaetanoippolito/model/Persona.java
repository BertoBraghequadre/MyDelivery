package com.gaetanoippolito.model;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

/**
 * Questo metodo rappresenta l'astrazione della Persona
 */

public class Persona implements Serializable {
    ///////////////////////////////// VARIABILI DI ISTANZA /////////////////////////////////
    // id del "serialVersionUID"
    @Serial
    private static final long serialVersionUID = 5L;
    // TODO: Commentare la classe Persona
    private String nome;
    private String cognome;
    private String indirizzo;
    private String numeroDiTelefono;
    private String cf;

    //////////////////////////////////// COSTRUTTORE ////////////////////////////////////
    /**
     * Questo metodo rappresenta il costruttore della persona
     * @param nome Rappresenta il nome della persona
     * @param cognome Rappresenta il cognome della persona
     * @param indirizzo Rappresenta l'indirizzo della persona
     * @param numeroDiTelefono Rappresenta il numero di telefono della persona
     * @param cf Rappresenta il codice fiscale della persona
     */
    public Persona(String nome, String cognome, String indirizzo, String numeroDiTelefono, String cf) {
        this.nome = nome;
        this.cognome = cognome;
        this.indirizzo = indirizzo;
        this.numeroDiTelefono = numeroDiTelefono;
        this.cf = cf;
    }

    //////////////////////////////////// GETTER ////////////////////////////////////
    /**
     * Questo metodo restituisce il nome della persona
     * @return Ritorna il nome della persona
     */
    public String getNome() {
        return nome;
    }

    /**
     * Questo metodo restituisce il cognome della persona
     * @return Ritorna il cognome della persona
     */
    public String getCognome() {
        return cognome;
    }

    /**
     * Questo metodo restituisce l'indirizzo della persona
     * @return Ritorna l'indirizzo della persona
     */
    public String getIndirizzo() {
        return indirizzo;
    }

    /**
     * Questo metodo restituisce il numero di telefono della persona
     * @return Ritorna il numero di telefono della persona
     */
    public String getNumeroDiTelefono() {
        return numeroDiTelefono;
    }

    /**
     * Questo metodo restituisce il codice fiscale della persona
     * @return Ritorna il codice fiscale della persona
     */
    public String getCf() {
        return cf;
    }

    //////////////////////////////////// SETTER ////////////////////////////////////
    /**
     * Questo metodo setta il nome della persona
     * @param nome Rappresenta il nome da settare della persona
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Questo metodo setta il cognome della persona
     * @param cognome Rappresenta il cognome da settare della persona
     */
    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    /**
     * Questo metodo setta l'indirizzo della persona
     * @param indirizzo Rappresenta l'indirizzo da settare della persona
     */
    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }

    /**
     * Questo metodo setta il numero di telefono della persona
     * @param numeroDiTelefono Rappresenta il numero di telefono da settare della persona
     */
    public void setNumeroDiTelefono(String numeroDiTelefono) {
        this.numeroDiTelefono = numeroDiTelefono;
    }

    /**
     * Questo metodo setta il codice fiscale della persona
     * @param cf Rappresenta il codice fiscale da settare della persona
     */
    public void setCf(String cf) {
        this.cf = cf;
    }

    //////////////////////////////////// METODI ////////////////////////////////////
    /**
     * Questo è un metodo overridato dalla classe Object che ritorna una stringa rappresentante l'istanza della
     * classe da cui viene chiamato.
     * @return Ritorna una stringa con le informazioni dell'istanza della classe Destinatario
     * @see Object
     */
    @Override
    public String toString() {
        return "Persona{" +
                "nome='" + nome + '\'' +
                ", cognome='" + cognome + '\'' +
                ", indirizzo='" + indirizzo + '\'' +
                ", numeroDiTelefono='" + numeroDiTelefono + '\'' +
                ", cf='" + cf + '\'' +
                '}';
    }

    /**
     * Metodo overridato dalla classe object che ritorna vero se l'istanza che chiama il metodo è uguale all'istanza
     * della classe all'interno del parametro di input di equals.
     * @see Object
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Persona)) return false;
        Persona persona = (Persona) o;
        return Objects.equals(cf, persona.cf);
    }

    /**
     * Questo metodo overridato della classe Object ritorna l'hashcode della classe
     * @return Rappresenta il codice della classe
     */
    @Override
    public int hashCode() {
        return Objects.hash(cf);
    }
}
