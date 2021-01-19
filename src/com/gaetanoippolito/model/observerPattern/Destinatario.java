package com.gaetanoippolito.model.observerPattern;

import com.gaetanoippolito.model.Persona;
import com.gaetanoippolito.model.StatoPacco;
import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

/**
 * Questa classe rappresenta il ConcreteObserver del Pattern "Observer". Il Destinatario è una Persona che riceve
 * il pacco ed è anche colui che deve reperire le informazioni riguardo l'ordine.
 */

public class Destinatario extends Persona implements Serializable, ObserverDestinatario {
    ///////////////////////////////// VARIABILI DI ISTANZA /////////////////////////////////
    // id del "serialVersionUID"
    /**@see Serializable*/
    @Serial
    private static final long serialVersionUID = 9L;
    /**@see Stato*/
    private Stato statoPacco;

    //////////////////////////////////// COSTRUTTORE ////////////////////////////////////
    /**
     * Questa classe è il costruttore della classe Cliente.
     * @param nome Rappresenta il nome del destinatario
     * @param cognome Rappresenta il cognome del destinatario
     * @param indirizzo Rappresenta l'indirizzo del destinatario
     * @param numeroDiTelefono Rappresenta il numeroDiTelefono del destinatario
     * @param cf Rappresenta il codice fiscale del destinatario
     * @param statoPacco Rappresenta lo stato del pacco del destinatario
     */
    public Destinatario(String nome, String cognome, String indirizzo, String numeroDiTelefono, String cf,
                        Stato statoPacco){
        super(nome, cognome, indirizzo, numeroDiTelefono, cf);

        this.statoPacco = statoPacco;
    }

    /**
     * Questa classe è il costruttore della classe Cliente. Questo costruttore viene utilizzato in particolare quando
     * il Destinatario viene creato durante la creazione dell'ordine.
     * @param nome Rappresenta il nome del destinatario
     * @param cognome Rappresenta il cognome del destinatario
     * @param indirizzo Rappresenta l'indirizzo del destinatario
     * @param numeroDiTelefono Rappresenta il numeroDiTelefono del destinatario
     * @param cf Rappresenta il codice fiscale del destinatario
     */
    public Destinatario(String nome, String cognome, String indirizzo, String numeroDiTelefono, String cf){
        this(nome, cognome, indirizzo, numeroDiTelefono, cf, new Stato(StatoPacco.IN_PREPARAZIONE, "Deposito"));
    }

    ///////////////////////////////////// GETTER /////////////////////////////////////
    /**
     * Questo classe ritorna lo stato del pacco che il destinatario sta osservando
     * @return Ritorna lo stato del pacco osservato
     */
    public Stato getStatoPacco() {
        return statoPacco;
    }

    ///////////////////////////////////// SETTER /////////////////////////////////////
    /**
     * Questo classe setta lo stato del pacco che il destinatario sta osservando
     * @param statoPacco Rappresenta lo stato del pacco osservato dal destinatario
     */
    public void setStatoPacco(Stato statoPacco) {
        this.statoPacco = statoPacco;
    }

    ///////////////////////////////////// METODI /////////////////////////////////////
    /**
     * Questo metodo aggiorna lo stato del pacco che il destinatario sta osservando
     * @param statoAggiornato Rappresenta lo stato aggiornato.
     */
    @Override
    public void update(Stato statoAggiornato){
        this.statoPacco = statoAggiornato;
    }

    /**
     * Questo è un metodo overridato dalla classe Object che ritorna una stringa rappresentante l'istanza della
     * classe da cui viene chiamato.
     * @return Ritorna una stringa con le informazioni dell'istanza della classe Destinatario
     * @see Object
     */
    @Override
    public String toString() {
        return "Destinatario{" +
                "nome='" + super.getNome() + '\'' +
                ", cognome='" + super.getCognome() + '\'' +
                ", indirizzo='" + super.getIndirizzo() + '\'' +
                ", numeroDiTelefono='" + super.getNumeroDiTelefono() + '\'' +
                ", cf='" + super.getCf() + '\'' +
                ", Stato Ordine='" + this.statoPacco + '\'' +
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
        if (!(o instanceof Destinatario)) return false;
        Destinatario that = (Destinatario) o;
        return Objects.equals(statoPacco, that.statoPacco);
    }

    /**
     * Questo metodo overridato della classe Object ritorna l'hashcode della classe
     * @return Rappresenta il codice della claasse
     */
    @Override
    public int hashCode() {
        return Objects.hash(statoPacco);
    }
}
