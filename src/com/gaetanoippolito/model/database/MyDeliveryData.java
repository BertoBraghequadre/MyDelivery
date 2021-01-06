package com.gaetanoippolito.model.database;

import com.gaetanoippolito.model.Admin;
import javafx.collections.ObservableList;

import java.io.BufferedReader;
import java.io.BufferedWriter;
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
    private static String filenameAdmin = "com.gaetanoippolito.listaAdmin.txt";
    private ObservableList<Admin> listaAdmin;

    // Costruttore
    private MyDeliveryData(){};

    // Getter
    public ObservableList<Admin> getListaAdmin() {
        return this.listaAdmin;
    }

    public static MyDeliveryData getInstance(){
        return instance;
    }

    // Metodi
    public void aggiungiAdmin(Admin admin){
        this.listaAdmin.add(admin);
    }

    /**
     * Questo metodo lo si utilizza per caricare tutti i file txt dove sono contenuti i dati degli Admin
     * */
    public void loadAdmins() throws IOException {
        Path path = Paths.get(filenameAdmin);
        String input;

        // Questo fa tutto il lavoro del "buffer", in cui si bufferizza il file dato un path
        try(BufferedReader br = Files.newBufferedReader(path)){
            while((input = br.readLine()) != null){
                String[] itemPieces = input.split("\t");

                String username = itemPieces[0];
                String password = itemPieces[1];
                String nome = itemPieces[2];
                String cognome = itemPieces[3];
                String email = itemPieces[4];

                Admin admin = new Admin(username, password, nome, cognome, email);
                this.listaAdmin.add(admin);
            }
        }
    }

    /**
     *
     */
    public void storeAdmins() throws IOException{
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
}
