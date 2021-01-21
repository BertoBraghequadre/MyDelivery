package com.gaetanoippolito.model;

import com.gaetanoippolito.model.builderPattern.BuilderOrdine;
import com.gaetanoippolito.model.builderPattern.ConcreteOrdine;
import com.gaetanoippolito.model.observerPattern.Destinatario;
import com.gaetanoippolito.model.observerPattern.Ordine;
import com.gaetanoippolito.model.observerPattern.Stato;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Random;

/**
 * Questa classe rappresenta l'astrazione del cliente. Il cliente inoltre è il "Director" del pattern builder
 */
public class Cliente extends Utente implements Serializable {
    ///////////////////////////////// VARIABILI DI ISTANZA /////////////////////////////////
    // id del "serialVersionUID"
    /**@see Serializable*/
    @Serial
    private static final long serialVersionUID = 3L;

    private String idCliente;
    /**@see BuilderOrdine*/
    private BuilderOrdine builderOrdine;

    //////////////////////////////////// COSTRUTTORE ////////////////////////////////////
    /**
     * Questo è il costruttore del Cliente.
     * @param username Rappresenta l'username utilizzato dal Cliente all'interno della piattaforma
     * @param password Rappresenta la password utilizzato dal Cliente all'interno della piattaforma
     * @param nome Rappresenta il nome utilizzato dal Cliente all'interno della piattaforma
     * @param cognome Rappresenta il cognome utilizzato dal Cliente all'interno della piattaforma
     * @param email Rappresenta l'email utilizzato dal Cliente all'interno della piattaforma
     * @param indirizzo Rappresenta l'indirizzo utilizzato dal Cliente all'interno della piattaforma
     * @param cf Rappresenta il codice fiscale utilizzato dal Cliente all'interno della piattaforma
     * @param numeroDiTelefono Rappresenta il numero di telefono utilizzato dal Cliente all'interno della
     *                         piattaforma
     * @param ordine Rappresenta il builder dell'ordine
     */
    public Cliente(String username, String password, String nome, String cognome, String email, String indirizzo,
                   String cf, String numeroDiTelefono, BuilderOrdine ordine){

        super(username, password, nome, cognome, email, indirizzo, cf, numeroDiTelefono);
        this.builderOrdine = ordine;
    }

    /**
     * Questo è il costruttore del Cliente utilizzato durante il login del Cliente.
     * @param username Rappresenta l'username da controllare durante il login
     * @param password Rappresenta la password da controllare durante il login
     */
    public Cliente(String username, String password){
        this(username, password, "", "", "", "", "", "", new ConcreteOrdine());
    }

    ///////////////////////////////////// GETTER /////////////////////////////////////
    /**
     * Questo metodo ritorna il BuilderOrdine
     * @return Ritorna il BuilderOrdine
     */
    public BuilderOrdine getBuilderOrdine(){
        return this.builderOrdine;
    }

    /**
     * Questo metodo ritorna l'ID del Cliente
     * @return Ritorna il BuilderOrdine
     */
    public String getIdCliente(){
        return this.idCliente;
    }

    ///////////////////////////////////// SETTER /////////////////////////////////////
    /**
     * Questo metodo setta l'ID del cliente
     * @param idCliente Rappresenta l'ID del cliente
     */
    public void setIdCliente(String idCliente){
        this.idCliente = idCliente;
    }

    /**
     * Questo metodo setta il BuilderOrdine
     * @param builderOrdine Rappresenta il BuilderOrdine
     */
    public void setBuilderOrdine(BuilderOrdine builderOrdine){
        this.builderOrdine = builderOrdine;
    }

    ///////////////////////////////////// METODI /////////////////////////////////////
    /**
     * Questo è il metodo che avvia il Builder dell'ordine.
     * @param mittente Rappresenta il mittente dell'ordine da buildare
     * @param destinatario Rappresenta il destinatario dell'ordine da buildare
     * @param aziendaDiOrdine Rappresenta l'azienda dell'ordine da buildare
     * @return Ritorna un ordine buildato
     */
    public Ordine creaOrdine(Cliente mittente, Destinatario destinatario, Azienda aziendaDiOrdine){
        Random random = new Random();
        int maxDays = 25;
        long randomDays = random.nextInt(maxDays);

        this.builderOrdine.creaOrdine();
        this.builderOrdine.buildMittente(mittente);
        this.builderOrdine.buildDestinatario(destinatario);
        this.builderOrdine.buildStatoOrdine(new Stato(StatoOrdine.IN_PREPARAZIONE, "Deposito"));
        this.builderOrdine.buildDataDiConsegna(LocalDate.now().plusDays(randomDays));
        this.builderOrdine.buildAzienda(aziendaDiOrdine);

        return this.builderOrdine.getOrdine();
    }

    /**
     * Questo è un metodo overridato dalla classe Object che ritorna una stringa rappresentante l'istanza della
     * classe da cui viene chiamato.
     * @return Ritorna una stringa con le informazioni dell'istanza della classe Destinatario
     * @see Object
     */
    @Override
    public String toString(){
        return String.format("Username: %s\n" +
                        "Password: %s\n" +
                        "Nome: %s\n" +
                        "Cognome: %s\n" +
                        "Email: %s\n" +
                        "ID: %s",
                super.getUsername(), super.getPassword(), super.getNome(), super.getCognome(), super.getEmail(), this.idCliente);
    }

    /**
     * Metodo overridato dalla classe object che ritorna vero se l'istanza che chiama il metodo è uguale all'istanza
     * della classe all'interno del parametro di input di equals.
     * @see Object
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cliente)) return false;
        if (!super.equals(o)) return false;
        Cliente cliente = (Cliente) o;
        return Objects.equals(idCliente, cliente.idCliente);
    }

    /**
     * Questo metodo overridato della classe Object ritorna l'hashcode della classe
     * @return Rappresenta il codice della claasse
     */
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), idCliente);
    }
}
