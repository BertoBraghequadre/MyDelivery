package com.gaetanoippolito.model;

import com.gaetanoippolito.model.database.MyDeliveryData;
import com.gaetanoippolito.model.observerPattern.Destinatario;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;
import java.util.Random;

public class Pacco implements Serializable {
    ///////////////////////////////// VARIABILI DI ISTANZA /////////////////////////////////
    // id del "serialVersionUID"
    @Serial
    private static final long serialVersionUID = 12L;
    private int codice;
    /**@see Cliente*/
    private Cliente mittente;
    /**@see Destinatario*/
    private Destinatario destinatario;
    private double pesoPacco;
    private boolean isFragile;

    //////////////////////////////////// COSTRUTTORE ////////////////////////////////////
    /**
     * Questo è il costruttore del Pacco.
     * @param mittente Rappresenta colui che spedisce il pacco
     * @param destinatario Rappresenta colui che riceve il pacco
     * @param pesoPacco Rappresenta il peso del pacco
     * @param isFragile Rappresenta se il pacco è fragile
     */
    public Pacco(Cliente mittente, Destinatario destinatario, double pesoPacco, boolean isFragile){
        this.mittente = mittente;
        this.destinatario = destinatario;
        this.pesoPacco = pesoPacco;
        this.isFragile = isFragile;

        Random random = new Random();
        int maxRandomNumber = 56000;
        this.codice = (MyDeliveryData.getInstance().getPacchi().size() + 1) + random.nextInt(maxRandomNumber);
    }

    //////////////////////////////////// GETTER ////////////////////////////////////
    /**
     * Questo metodo restituisce il codice del pacco
     * @return Ritorna il codice del pacco
     */
    public int getCodice(){
        return this.codice;
    }

    /**
     * Questo metodo restituisce il mittente del pacco
     * @return Ritorna il mittente del pacco
     */
    public Cliente getMittente(){
        return this.mittente;
    }

    /**
     * Questo metodo restituisce il destinatario del pacco
     * @return Ritorna il destinatario del pacco
     */
    public Destinatario getDestinatario() {
        return this.destinatario;
    }

    /**
     * Questo metodo restituisce il peso del pacco
     * @return Ritorna il peso del pacco
     */
    public double getPesoPacco() {
        return this.pesoPacco;
    }

    /**
     * Questo metodo restituisce true se il pacco è fragile, altrimenti false
     * @return Ritorna true se il pacco è fragile, altrimenti ritorna false
     */
    public boolean getIsFragile() {
        return this.isFragile;
    }

    //////////////////////////////////// SETTER ////////////////////////////////////
    /**
     * Questo metodo setta il mittente del pacco
     * @param mittente Rappresenta colui che spedisce il pacco
     */
    public void setMittente(Cliente mittente) {
        this.mittente = mittente;
    }

    /**
     * Questo metodo setta il destinatario del pacco
     * @param destinatario Rappresenta colui che riceve il pacco
     */
    public void setDestinatario(Destinatario destinatario) {
        this.destinatario = destinatario;
    }

    /**
     * Questo metodo setta il peso del pacco
     * @param pesoPacco Rappresenta il peso del pacco
     */
    public void setPesoPacco(double pesoPacco) {
        this.pesoPacco = pesoPacco;
    }

    /**
     * Questo metodo setta la fragilità del pacco
     * @param fragile Rappresenta la fragilità del pacco
     */
    public void setFragile(boolean fragile) {
        isFragile = fragile;
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
        return "Pacco{" +
                "codice='" + codice + '\'' +
                ", mittente=" + mittente +
                ", destinatario=" + destinatario +
                ", pesoPacco=" + pesoPacco +
                ", isFragile=" + isFragile +
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
        if (!(o instanceof Pacco)) return false;
        Pacco pacco = (Pacco) o;
        return codice == pacco.codice;
    }

    /**
     * Questo metodo overridato della classe Object ritorna l'hashcode della classe
     * @return Rappresenta il codice della classe
     */
    @Override
    public int hashCode() {
        return Objects.hash(codice);
    }
}
