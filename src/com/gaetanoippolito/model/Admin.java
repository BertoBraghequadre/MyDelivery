package com.gaetanoippolito.model;

/**
 * La seguente classe rappresenta l'astrazione di quella figura che controlla e gestisce l'applicazione. Quindi sarà
 * colui che potrà avere il controllo sulle operazioni della piattaforma.
 * La classe Admin rappresenta anche il "Director" per il pattern "Builder" in quanto l'admin avrà la possibilità di
 * aggiungere altri
 */

public class Admin extends Utente{
    // Costruttore
    public Admin(String username, String password, String nome, String cognome, String email){
        super(username, password, nome, cognome, email);
    }
}
