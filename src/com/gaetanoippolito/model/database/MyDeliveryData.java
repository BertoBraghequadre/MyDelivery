package com.gaetanoippolito.model.database;

import com.gaetanoippolito.model.Admin;
import com.gaetanoippolito.model.Azienda;
import com.gaetanoippolito.model.Veicolo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 * Questa classe rappresenta l'astrazione del database dell'applicazione MyDelivery
 */

public class MyDeliveryData {
    // Attributi
    // Eager Initialization
    private static final MyDeliveryData instance = new MyDeliveryData();
    private static final String filenameAdmin = "listaAdmin.txt";
    private static final String filenameAzienda = "listaAziende.txt";
    private static final String filenameVeicoli = "listaVeicoli.txt";
    /**@see Admin*/
    private Admin admin;
    /**@see Azienda*/
    private ObservableList<Azienda> aziende;
    /**@see Veicolo*/
    private ObservableList<Veicolo> veicoli;

    // Costruttore
    private MyDeliveryData(){
        this.aziende = FXCollections.observableArrayList();
        this.veicoli = FXCollections.observableArrayList();
    }

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

    public ObservableList<Azienda> getAziende() {
        return this.aziende;
    }

    public void setAziende(ObservableList<Azienda> aziende) {
        this.aziende = aziende;
    }

    public ObservableList<Veicolo> getVeicoli() {
        return this.veicoli;
    }

    public void setVeicoli(ObservableList<Veicolo> veicoli) {
        this.veicoli = veicoli;
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

    ///////////////////////////// ZONA ADMIN /////////////////////////////////

    /**
     * Questo metodo lo si utilizza per caricare il file txt dove sono contenuti i dati degli Admin
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

    ///////////////////////////// ZONA AZIENDA /////////////////////////////////

    /**
     * Questo metodo lo si utilizza per caricare i dati di Azienda
     */
    public void loadAziende(){
        try(ObjectInputStream objectIn = new ObjectInputStream(new BufferedInputStream(new FileInputStream(filenameAzienda))))
        {
            try{
                this.aziende.setAll((ArrayList<Azienda>)objectIn.readObject());
            } catch (ClassNotFoundException e){
                e.printStackTrace();
            }

            System.out.println(this.aziende);
            System.out.println("File \"listaAziende.txt\" caricato con successo");

        } catch (Exception e) {
            System.out.println("Errore nel caricamento del File \"listaAziende\"");
            e.printStackTrace();
        }
    }

    /**
     * Questo metodo serve ad aggiungere delle Aziende all'interno di MyDeliveryData e controlla se l'azienda che viene
     * aggiunta non è già presente.
     * @param aziendaNuova rappresenta l'azienda selezionata dall'utente da aggiungere
     * @see Azienda
     */
    public void aggiungiAzienda(Azienda aziendaNuova){
        String aziendaNuovaPartitaIVA = aziendaNuova.getPartitaIVA();
        for(Azienda azienda : this.aziende){
            if(aziendaNuovaPartitaIVA.equals(aziendaNuova.getPartitaIVA())){
                System.out.println("Azienda già esistente");
            }
            else{
                this.aziende.add(aziendaNuova);
            }
        }

        try{
            storeAziende();
        } catch (IOException e){
            System.out.println("Salvataggio fallito");
        }
    }

    /**
     * Questo metodo serve a rimuovere un'azienda all'interno di MyDeliveryData e salvare
     * @param azienda rappresenta l'azienda da rimuovere
     */
    private void rimuoviAzienda(Azienda azienda){
        this.aziende.remove(azienda);
        rimuoviVeicoli(azienda);
        try{
            storeAziende();
        } catch (IOException e){
            System.out.println("Rimozione fallita");
        }
    }

    /**
     * Questo metodo serve per cercare l'azienda selezionata dall'Admin
     * @param partitaIVA rappresenta la chiave per cercare l'azienda all'interno di MyDeliveryData
     * @return Questo metodo ritorna "true" se l'azienda è stata trovata, altrimenti ritorna false
     */
    public boolean cercaAzienda(String partitaIVA){
        Azienda aziendaTrovata;

        for(Azienda azienda : this.aziende){
            if(azienda.getPartitaIVA().equals(partitaIVA)){
                aziendaTrovata = azienda;
                rimuoviAzienda(aziendaTrovata);
                return true;
            }
        }
        return false;
    }

    /**
     * Questo metodo lo si utilizza per salvare i dati di Azienda all'interno di un file txt
     * chiamato "listaAziende.txt"
     */
    public void storeAziende() throws IOException{
        try (ObjectOutputStream objectOut = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(filenameAzienda)))){

            ArrayList<Azienda> aziende = new ArrayList<>(this.aziende);

            objectOut.writeObject(aziende);
            System.out.println(aziende);
            System.out.println("Il file \"listaAziende.txt\" è stato salvato con successo");

        } catch (IOException e) {
            System.out.println("Errore nel salvataggio del File");
            e.printStackTrace();
        }
    }

    ///////////////////////////// ZONA VEICOLI /////////////////////////////////
    /**
     * Questo metodo lo si utilizza per caricare i dati di Azienda
     */
    public void loadVeicoli(){
        try(ObjectInputStream objectIn = new ObjectInputStream(new BufferedInputStream(new FileInputStream(filenameVeicoli))))
        {
            try{
                this.veicoli.setAll((ArrayList<Veicolo>)objectIn.readObject());
            } catch (ClassNotFoundException e){
                e.printStackTrace();
            }

            System.out.println("File \"listaVeicoli.txt\" caricato con successo");

        } catch (Exception e) {
            System.out.println("Errore nel caricamento del File \"listaVeicoli\"");
            e.printStackTrace();
        }
    }

    /**
     *
     */
    public void aggiungiVeicoli(Veicolo veicolo){
        this.veicoli.add(veicolo);
    }

    private void rimuoviVeicoli(Azienda azienda){
        for(Veicolo veicolo : azienda.getVeicoli()){
            this.veicoli.remove(veicolo);
        }
        try{
            storeVeicoli();
        }catch (IOException e){
            System.out.println("Errore nella cancellazione dei veicoli");
        }
    }

    /**
     * Questo metodo lo si utilizza per salvare i dati di Azienda all'interno di un file txt
     * chiamato "listaAziende.txt"
     */
    public void storeVeicoli() throws IOException{
        try (ObjectOutputStream objectOut = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(filenameVeicoli)))){

            ArrayList<Veicolo> veicoli = new ArrayList<>(this.veicoli);

            objectOut.writeObject(veicoli);
            System.out.println(veicoli);
            System.out.println("Il file \"listaVeicoli.txt\" è stato salvato con successo");

        } catch (IOException e) {
            System.out.println("Errore nel salvataggio del File \"listaVeicoli.txt\"");
            e.printStackTrace();
        }
    }

    ///////////////////////////// ZONA CLIENTI /////////////////////////////////

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
