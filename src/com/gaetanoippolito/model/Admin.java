package com.gaetanoippolito.model;

import com.gaetanoippolito.model.magazziniereBuilder.MagazziniereBuilder;

import java.time.LocalDate;

/**
 * La seguente classe rappresenta l'astrazione di quella figura che controlla e gestisce l'applicazione. Quindi sarà
 * colui che potrà avere il controllo sulle operazioni della piattaforma.
 * La classe Admin rappresenta anche il "Director" per il pattern "Builder" in quanto l'admin avrà la possibilità di
 * aggiungere altri magazzinieri
 */

public class Admin extends Utente{
    // Lazy Initialization
    private static Admin istance;
    private MagazziniereBuilder magazziniereBuilder;

    // Costruttori
    private Admin(String username, String password, String nome, String cognome, String email){
        super(username, password, nome, cognome, email);
    }

    private Admin(String username, String password, String nome, String cognome, String email, MagazziniereBuilder magazziniereBuilder){
        this(username, password, nome, cognome, email);
        this.magazziniereBuilder = magazziniereBuilder;
    }

    /**
     * Questo metodo setta il MagazziniereBuilder
     * @param magazziniereBuilder rappresenta il builder del magazziniere
     * @see MagazziniereBuilder
     */
    public void setMagazziniereBuilder(MagazziniereBuilder magazziniereBuilder){
        this.magazziniereBuilder = magazziniereBuilder;
    }

    /**
     * Questo metodo ritorna l'istanza della classe Admin e setta per la prima volta
     * @param username è una stringa che rappresenta l'username dell'Admin
     * @param password è una stringa che rappresenta la password dell'Admin
     * @param nome è una stringa che rappresenta il nome dell'Admin
     * @param cognome è una stringa che rappresenta il cognome dell'Admin
     * @param email è una stringa che rappresenta l'email dell'Admin
     * @return ritorna l'istanza di Admin
     */
    public static Admin getInstance(String username, String password, String nome, String cognome, String email){
         return istance = new Admin(username, password, nome, cognome, email);
    }

    /**
     * Questo metodo ritorna l'istanza della classe Admin
     * @return ritorna l'istanza di Admin
     */
    public static Admin getInstance(){
        return istance;
    }

    /**
     * Questo metodo serve a buildare il magazziniere. Tramite l'istanza del MagazziniereBuilder chiamiamo i metodi "build..."
     * @param username rappresenta l'username dato in input dall'utente
     * @param password rappresenta la password data in input dall'utente
     * @param nome rappresenta il nome dato in input dall'utente
     * @param cognome rappresenta il cognome dato in input dall'utente
     * @param email rappresenta l'email dato in input dall'utente
     * @param eta rappresenta l'età data in input dall'utente
     * @param sesso rappresenta il sesso dato in input dall'utente
     * @param cf rappresenta il codice fiscale dato in input dall'utente
     * @param dataDiAssunzione rappresenta la data di assunzione data in input dall'utente
     * @see LocalDate
     * @see Genere
     */
    public void buildMagazziniere(String username, String password, String nome, String cognome, String email, int eta, Genere sesso, String cf, LocalDate dataDiAssunzione){
        this.magazziniereBuilder.buildUsername(username);
        this.magazziniereBuilder.buildPassword(password);
        this.magazziniereBuilder.buildNome(nome);
        this.magazziniereBuilder.buildCognome(cognome);
        this.magazziniereBuilder.buildEmail(email);
        this.magazziniereBuilder.buildEta(eta);
        this.magazziniereBuilder.buildSesso(sesso);
        this.magazziniereBuilder.buildCF(cf);
        this.magazziniereBuilder.buildDataDiAssunzione(dataDiAssunzione);
    }
}
