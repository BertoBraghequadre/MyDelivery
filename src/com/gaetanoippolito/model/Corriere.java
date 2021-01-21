package com.gaetanoippolito.model;

import com.gaetanoippolito.model.observerPattern.Destinatario;
import com.gaetanoippolito.model.observerPattern.ObserverDestinatario;
import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

/**
 * Questa classe rappresenta l'astrazione del corriere.
 */

public class Corriere extends Utente implements Serializable {
    ///////////////////////////////// VARIABILI DI ISTANZA /////////////////////////////////
    // id del "serialVersionUID"
    /**@see Serializable*/
    @Serial
    private static final long serialVersionUID = 7L;
    private String idCorriere;
    /**@see ObserverDestinatario*/
    private boolean isBusy;

    //////////////////////////////////// COSTRUTTORE ////////////////////////////////////
    /**
     * Questo è il costruttore del corriere.
     * @param username Rappresenta l'username del Corriere
     * @param password Rappresenta la password del Corriere
     * @param nome Rappresenta il nome del Corriere
     * @param cognome Rappresenta il cognome del Corriere
     * @param email Rappresenta l'email del corriere
     * @param idCorriere Rappresenta l'id del Corriere
     */
    public Corriere(String username, String password, String nome, String cognome, String email, String idCorriere){
        super(username, password, nome, cognome, email);
        this.idCorriere = idCorriere;
        this.isBusy = false;
    }

    /**
     * Questo è il costruttore del corriere. Questo costruttore è utilizzato quando il corriere si registra
     * sulla piattaforma MyDelivery
     * @param nome Rappresenta il nome del Corriere
     * @param cognome Rappresenta il cognome del Corriere
     * @param idCorriere Rappresenta l'id del Corriere
     */
    public Corriere(String nome, String cognome, String idCorriere){
        this("", "", nome, cognome, "", idCorriere);
    }

    ///////////////////////////////////// GETTER /////////////////////////////////////
    /**
     * Questo metodo ritorna l'id del corriere
     * @return Ritorna una stringa che rappresenta l'id del corriere
     */
    public String getIdCorriere() {
        return this.idCorriere;
    }

    /**
     * Questo metodo true se il corriere è impegnato nella spedizione di un ordine, oppure false se non è impegnato
     * nella spedizione di un ordine
     * @return Questo metodo ritorna true quando il corriere è impegnato, altrimenti ritorna false
     */
    public boolean getIsBusy(){
        return this.isBusy;
    }

    ///////////////////////////////////// SETTER /////////////////////////////////////
    /**
     * Questo metodo setta l'id del corriere
     * @param idCorriere Rappresenta l'id da settare
     */
    public void setIdCorriere(String idCorriere) {
        this.idCorriere = idCorriere;
    }

    /**
     * Questo metodo setta un corriere a impegnato o non impegnato
     * @param isBusy Rappresenta se il corriere è impegnato o non è impegnato
     */
    public void setIsBusy(boolean isBusy){
        this.isBusy = isBusy;
    }

    ///////////////////////////////////// METODI /////////////////////////////////////
    /**
     * Questo è un metodo overridato dalla classe Object che ritorna una stringa rappresentante l'istanza della
     * classe da cui viene chiamato.
     * @return Ritorna una stringa con le informazioni dell'istanza della classe Destinatario
     * @see Object
     */
    @Override
    public String toString() {
        return String.format("%s %s", super.getNome(), super.getCognome());
    }

    /**
     * Metodo overridato dalla classe object che ritorna vero se l'istanza che chiama il metodo è uguale all'istanza
     * della classe all'interno del parametro di input di equals.
     * @see Object
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Corriere)) return false;
        if (!super.equals(o)) return false;
        Corriere corriere = (Corriere) o;
        return Objects.equals(idCorriere, corriere.idCorriere);
    }

    /**
     * Questo metodo overridato della classe Object ritorna l'hashcode della classe
     * @return Rappresenta il codice della classe
     */
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), idCorriere);
    }
}
