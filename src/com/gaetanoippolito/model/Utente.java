package com.gaetanoippolito.model;

import java.util.Objects;

/**
 * La classe "Utente" rappresenta l'astrazione di ogni singola persona che ha la possibilità di loggare
 * all'interno dell'applicazione.
 */

public abstract class Utente {
    // Attributi
    private String username;
    private String password;
    private String nome;
    private String cognome;
    private String email;

    // Costruttore
    public Utente(String username, String password, String nome, String cognome, String email){
        this.username = username;
        this.password = password;
        this.nome = nome;
        this.cognome = cognome;
        this.email = email;
    }

    // Per il Builder del magazziniere è stato creato questo costruttore vuoto
    public Utente(){}

    // Setters e getters
    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return this.cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Utente)) return false;
        Utente utente = (Utente) o;
        return Objects.equals(username, utente.username) && Objects.equals(password, utente.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, password);
    }

    @Override
    public String toString(){
        return String.format("Username: %s\n" +
                             "Password: %s\n" +
                             "Nome: %s\n" +
                             "Cognome: %s\n" +
                             "Email: %s", this.username, this.password, this.nome, this.cognome, this.email);
    }
}
