package com.gaetanoippolito.model.database;

import com.gaetanoippolito.model.Admin;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Questa classe rappresenta l'astrazione del database dell'applicazione MyDelivery
 */

public class MyDeliveryData {
    // Attributi
    // Eager Initialization
    private static final MyDeliveryData instance = new MyDeliveryData();
    private static final String filenameAdmin = "listaAdmin.txt";
    /**@see Admin*/
    private Admin admin;

    // Costruttore vuoto
    private MyDeliveryData(){}

    // Getter e setter
    /**
     * Ritorna l'unica istanza della classe (siccome è stato utilizzato un Singleton)
     */
    public static MyDeliveryData getInstance(){
        return instance;
    }

    /**
     * Questo metodo setta l'admin
     * @see Admin
     */
    public void setAdmin(Admin admin){
        this.admin = admin;
    }

    // Metodi
    /**
     * Questo metodo verifica il successo o il fallimento del login di un Admin
     * @param username rappresenta l'username digitato dall'admin per accedere nell'applicazione
     * @param password rappresenta la password digitata dall'admin per accedere nell'applicazione
     * @return Il metodo ritorna un boolean che sta a rappresentare il successo o il fallimento del login
     */
    public boolean verificaLoginAdmin(String username, String password){
        if(admin.getUsername().equals(username) && admin.getPassword().equals(password)){
            return true;
        }
        else{
            return false;
        }
    }

    /**
     * Questo metodo lo si utilizza per caricare tutti i file txt dove sono contenuti i dati degli Admin
     */
    public void loadAdmins() throws IOException {
        Path path = Paths.get(filenameAdmin);
        String input;

        // Questo fa tutto il lavoro del "buffer", in cui si bufferizza il file dato un path
        try(BufferedReader br = Files.newBufferedReader(path)){
            while((input = br.readLine()) != null){
                String[] itemPieces = input.split("   -   ");

                String username = itemPieces[0];
                String password = itemPieces[1];
                String nome = itemPieces[2];
                String cognome = itemPieces[3];
                String email = itemPieces[4];

                Admin admin = Admin.getInstance(username, password, nome, cognome, email);
                this.setAdmin(admin);
            }
        }
    }

    /**
     *
     */
    /*
    public void storeClienti() throws IOException{
        Path path = Paths.get(filenameAdmin);

        try(BufferedWriter bw = Files.newBufferedWriter(path)){
            for(Admin admin : this.listaAdmin){
                bw.write(String.format("%s\t%s\t%s\t%s\t%s",
                        admin.getUsername(),
                        admin.getPassword(),
                        admin.getNome(),
                        admin.getCognome(),
                        admin.getEmail()));

                bw.newLine();
            }
        }
    }
    */
}
