package com.gaetanoippolito.model;

/**
 * La seguente classe rappresenta l'astrazione di quella figura che controlla e gestisce l'applicazione. Quindi sarà
 * colui che potrà avere il controllo sulle operazioni della piattaforma.
 * Questa classe è figlia di Utente.
 * @see Utente
 */
public class Admin extends Utente{
    ///////////////////////////////// VARIABILI DI ISTANZA /////////////////////////////////
    // Lazy Initialization
    private static Admin istance;

    //////////////////////////////////// COSTRUTTORE ////////////////////////////////////
    /**
     * Questo è il Costruttore della classe Admin. Esso è privato in quanto è stato applicato un Singleton.
     * Tutto ciò che riceve, lo passerà alla sua classe padre
     * @param username Rappresenta l'username dell'Admin con cui logga all'interno dell'applicazione
     * @param password Rappresenta la password dell'Admin con cui logga all'interno dell'applicazione
     * @param nome Rappresenta il nome dell'Admin
     * @param cognome Rappresenta il cognome dell'Admin
     * @param email Rappresenta l'email dell'Admin
     */
    private Admin(String username, String password, String nome, String cognome, String email){
        // Chiamo il costruttore della super classe di Admin per settare le variabili di istanza
        super(username, password, nome, cognome, email);
    }

    ///////////////////////////////////// GETTER /////////////////////////////////////
    
    /**
     * Questo metodo ritorna l'istanza della classe Admin e setta le variabili di istanza
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
