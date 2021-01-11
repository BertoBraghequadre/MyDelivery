package com.gaetanoippolito.model;

import java.util.Objects;

/**
 * La classe "Utente" rappresenta l'astrazione di ogni singola persona che ha la possibilità di loggare e registrarsi
 * all'interno dell'applicazione.
 */
public abstract class Utente {
    ///////////////////////////////// VARIABILI DI ISTANZA /////////////////////////////////
    private String username;
    private String password;
    private String nome;
    private String cognome;
    private String email;

    //////////////////////////////////// COSTRUTTORE ////////////////////////////////////
    /**
     * Costruttore della classe Utente
     * @param username Rappresenta l'username dell'utente
     * @param password Rappresenta la password dell'utente
     * @param nome Rappresenta il nome dell'utente
     * @param cognome Rappresenta il cognome dell'utente
     * @param email Rappresenta l'email dell'utente
     */
    public Utente(String username, String password, String nome, String cognome, String email){
        this.username = username;
        this.password = password;
        this.nome = nome;
        this.cognome = cognome;
        this.email = email;
    }
    ///////////////////////////////////// GETTER /////////////////////////////////////

    /**
     * Metodo che ritorna l'username dell'utente
     * @return Ritorna l'username dell'utente
     */
    public String getUsername() {
        return this.username;
    }

    /**
     * Metodo che ritorna la password dell'utente
     * @return Ritorna la password dell'utente
     */
    public String getPassword() {
        return this.password;
    }

    /**
     * Metodo che ritorna il nome dell'utente
     * @return Ritorna il nome dell'utente
     */
    public String getNome() {
        return this.nome;
    }

    /**
     * Metodo che ritorna il cognome dell'utente
     * @return Ritorna il cognome dell'utente
     */
    public String getCognome() {
        return this.cognome;
    }

    /**
     * Metodo che ritorna l'email dell'utente
     * @return Ritorna l'email dell'utente
     */
    public String getEmail() {
        return this.email;
    }

    ///////////////////////////////////// SETTER /////////////////////////////////////

    /**
     * Metodo che setta la variabile di istanza Username
     * @param username Rappresenta il valore da settare all'interno della variabile di istanza Username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Metodo che setta la variabile di istanza Password
     * @param password Rappresenta il valore da settare all'interno della variabile di istanza Password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Metodo che setta la variabile di istanza Nome
     * @param nome Rappresenta il valore da settare all'interno della variabile di istanza Nome
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Metodo che setta la variabile di istanza Cognome
     * @param cognome Rappresenta il valore da settare all'interno della variabile di istanza Cognome
     */
    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    /**
     * Metodo che setta la variabile di istanza Email
     * @param email Rappresenta il valore da settare all'interno della variabile di istanza Email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    ////////////////////////////////////// METODI //////////////////////////////////////

    /**
     * Il metodo equals() che viene ereditato dalla classe Object. Serve per confrontare due oggetti, dove
     * restituisce true solo se si tratta di due riferimenti allo stesso oggetto.
     * @param o Rappresenta l'oggetto da paragonare.
     * @return Ritorna true se i due oggetti sono uguali
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Utente)) return false;
        Utente utente = (Utente) o;
        return Objects.equals(username, utente.username) && Objects.equals(password, utente.password);
    }

    /**
     * Restituisce un valore hash per un oggetto.
     * @return Rappresenta il valore hash per l'oggetto.
     */
    @Override
    public int hashCode() {
        return Objects.hash(username, password);
    }

    /**
     * Restituisce una stringa con tutte le informazioni di un'istanza dell'Azienda.
     * @return Ritorna una stringa personalizzata con le informazioni di un'istanza dell'Azienda.
     */
    @Override
    public String toString(){
        return String.format("Username: %s\n" +
                             "Password: %s\n" +
                             "Nome: %s\n" +
                             "Cognome: %s\n" +
                             "Email: %s", this.username, this.password, this.nome, this.cognome, this.email);
    }
}
