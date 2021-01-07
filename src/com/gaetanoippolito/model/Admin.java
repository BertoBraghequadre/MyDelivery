package com.gaetanoippolito.model;

/**
 * La seguente classe rappresenta l'astrazione di quella figura che controlla e gestisce l'applicazione. Quindi sarà
 * colui che potrà avere il controllo sulle operazioni della piattaforma.
 * La classe Admin rappresenta anche il "Director" per il pattern "Builder" in quanto l'admin avrà la possibilità di
 * aggiungere altri
 */

public class Admin extends Utente{
    // Lazy Initialization
    private static Admin istance;

    // Costruttore
    private Admin(String username, String password, String nome, String cognome, String email){
        super(username, password, nome, cognome, email);
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
}
