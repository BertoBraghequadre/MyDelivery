package com.gaetanoippolito.model.database;

import com.gaetanoippolito.model.Admin;
import com.gaetanoippolito.model.Azienda;
import com.gaetanoippolito.model.Cliente;
import com.gaetanoippolito.model.Veicolo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Questa classe rappresenta l'astrazione del database dell'applicazione MyDelivery
 */

public class MyDeliveryData {
    ///////////////////////////////// VARIABILI DI ISTANZA /////////////////////////////////
    // Eager Initialization
    private static final MyDeliveryData instance = new MyDeliveryData();

    private static final String filenameAdmin = "listaClienti.txt";
    private static final String filenameAzienda = "listaAziende.txt";
    private static final String filenameVeicoli = "listaVeicoli.txt";
    private static final String filenameClienti = "listaClienti.txt";
    /**@see Admin*/
    private Admin admin;
    /**@see Azienda*/
    private ObservableList<Azienda> aziende;
    /**@see Veicolo*/
    private ObservableList<Veicolo> veicoli;
    /**@see Cliente*/
    private ObservableList<Cliente> clienti;

    //////////////////////////////////// COSTRUTTORE ////////////////////////////////////
    /**
     * Questo rappresenta il costruttore della classe "MyDeliveryData". Esso è privato siccome è stato applicato un
     * Singleton, infatti viene richiamato un'unica volta al momento dell'inizializzazione all'interno della classe
     * stessa. Ciò che fa al momento della creazione è quello di inizializzare le ObservableList.
     */
    private MyDeliveryData(){
        this.aziende = FXCollections.observableArrayList();
        this.veicoli = FXCollections.observableArrayList();
        this.clienti = FXCollections.observableArrayList();
    }

    ///////////////////////////////////// GETTER /////////////////////////////////////
    /**
     * Metodo che ritorna l'unica istanza della classe (siccome è stato applicato un Singleton)
     * @return Ritorna l'istanza di MyDelivery
     */
    public static MyDeliveryData getInstance(){
        return instance;
    }

    /**
     * Metodo che ritorna l'ObserableList in cui sono contenute le aziende
     * @return Ritorna una ObservableList di Aziende
     * @see Azienda
     */
    public ObservableList<Azienda> getAziende() {
        return this.aziende;
    }

    /**
     * Metodo che ritorna l'ObserableList in cui sono contenuti i veicoli
     * @return Ritorna una ObservableList di Veicolo
     * @see Veicolo
     */
    public ObservableList<Veicolo> getVeicoli() {
        return this.veicoli;
    }

    ///////////////////////////////////// SETTER /////////////////////////////////////
    /**
     * Questo metodo setta l'Admin
     * @param admin Rappresenta l'admin da settare all'interno della classe
     * @see Admin
     */
    public void setAdmin(Admin admin){
        this.admin = admin;
    }

    /**
     * Questo metodo setta l'ObservableList di Azienda
     * @param aziende Rappresenta l'ObservableList di Azienda da settare all'interno della classe
     * @see Azienda
     */
    public void setAziende(ObservableList<Azienda> aziende) {
        this.aziende = aziende;
    }

    /**
     * Questo metodo setta l'ObservableList di Veicolo
     * @param veicoli Rappresenta l'ObservableList di Veicolo da settare all'interno della classe
     * @see Veicolo
     */
    public void setVeicoli(ObservableList<Veicolo> veicoli) {
        this.veicoli = veicoli;
    }

    ////////////////////////////////////// METODI //////////////////////////////////////
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

    ///////////////////////////////// METODI: ZONA ADMIN /////////////////////////////////

    /**
     * Questo metodo lo si utilizza per caricare i dati del file "listaClienti.txt" dove sono contenuti i
     * dati degli Admin.
     */
    public void loadAdmins() {
        /**@see Path*/
        Path path = Paths.get(filenameAdmin);
        String input;

        // Questo fa tutto il lavoro del "buffer", in cui si bufferizza il file dato un path
        try(BufferedReader br = Files.newBufferedReader(path)){
            while((input = br.readLine()) != null){
                String[] itemPieces = input.split("   -   ");
                System.out.println(itemPieces);

                String username = itemPieces[0];
                String password = itemPieces[1];
                String nome = itemPieces[2];
                String cognome = itemPieces[3];
                String email = itemPieces[4];

                /**@see Admin*/
                Admin admin = Admin.getInstance(username, password, nome, cognome, email);
                this.setAdmin(admin);
            }
        } catch (IOException e){
            System.out.println("Caricamento file \"listaClienti.txt\" fallito");
        }
    }

    //////////////////////////////// METODI: ZONA AZIENDA ////////////////////////////////

    /**
     * Questo metodo lo si utilizza per caricare i dati del file "listaAziende.txt" dove sono contenuti i
     * dati degli Admin.
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
     * Questo metodo serve ad aggiungere delle Aziende all'interno di MyDeliveryData. Dopo aver finito l'aggiunta,
     * salva il contenuto nel file "listaAziende.txt".
     * @param aziendaNuova rappresenta l'azienda selezionata dall'utente da aggiungere all'interno di MyDeliveryData
     * @see Azienda
     */
    public void aggiungiAzienda(Azienda aziendaNuova){
        this.aziende.add(aziendaNuova);

        try{
            storeAziende();
        } catch (IOException e){
            System.out.println("Salvataggio fallito");
        }
    }

    /**
     * Questo metodo serve a rimuovere un'azienda all'interno di MyDeliveryData e salvare il contenuto all'interno di
     * "listaAziende.txt"
     * @param azienda rappresenta l'azienda che l'utente vuole rimuovere
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
            if(controllaPartitaIVA(azienda, partitaIVA)){
                aziendaTrovata = azienda;
                rimuoviAzienda(aziendaTrovata);
                return true;
            }
        }
        return false;
    }

    /**
     * Metodo che controlla se esiste un'azienda con una specifica partita IVA
     * @param azienda Rappresenta l'azienda da confrontare con la partita IVA data dall'utente
     * @param partitaIVA Rappresenta la partita IVA data dall'utente
     * @return Ritorna true se esiste un'azienda con la partita IVA data in input, altrimenti ritorna false
     */
    private boolean controllaPartitaIVA(Azienda azienda, String partitaIVA){
        return azienda.getPartitaIVA().equals(partitaIVA);
    }

    /**
     * Questo metodo lo si utilizza per salvare i dati di Azienda all'interno del file "listaAziende.txt"
     * @throws IOException
     */
    private void storeAziende() throws IOException{
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

    //////////////////////////////// METODI: ZONA VEICOLI ////////////////////////////////

    /**
     * Questo metodo lo si utilizza per caricare i dati del file "listaVeicoli.txt" dove sono contenuti i
     * dati dei Veicoli.
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
     * Questo metodo aggiunge un Veicolo all'interno della ObservableList di MyDeliveryData
     * @param veicolo Rappresenta il veicolo che bisogna aggiungere
     */
    public void aggiungiVeicoli(Veicolo veicolo){
        this.veicoli.add(veicolo);
        try{
            storeVeicoli();
        }catch (IOException e){
            System.out.println("Errore nell'aggiunta del veicolo");
        }
    }

    /**
     * Questo metodo rimuove un Veicolo all'interno della ObservableList di MyDeliveryData una volta che è
     * stata rimossa l'azienda a cui il Veicolo è associata.
     * @param azienda Rappresenta l'azienda a cui il Veicolo è associato
     */
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
     * Questo metodo lo si utilizza per salvare i dati di Azienda all'interno del file "listaVeicoli.txt"
     * @throws IOException
     */
    private void storeVeicoli() throws IOException{
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

    ///////////////////////////// METODI: ZONA CLIENTI /////////////////////////////////
    public void loadClienti() throws IOException{
        Path path = Paths.get(filenameClienti);
        String input;

        // Questo fa tutto il lavoro del "buffer", in cui si bufferizza il file dato un path
        try(BufferedReader br = Files.newBufferedReader(path)){
            while((input = br.readLine()) != null){
                String[] itemPieces = input.split("\t");

                String usernameCliente = itemPieces[0];
                String passwordCliente = itemPieces[1];
                String nomeCliente = itemPieces[2];
                String cognomeCliente = itemPieces[3];
                String emailCliente = itemPieces[4];
                String indirizzoCliente = itemPieces[5];
                String cfCliente = itemPieces[6];
                String numeroDiTelefonoCliente = itemPieces[7];

                Cliente cliente = new Cliente(usernameCliente, passwordCliente, nomeCliente, cognomeCliente,
                                              emailCliente, indirizzoCliente, cfCliente, numeroDiTelefonoCliente);
                this.clienti.add(cliente);
            }
        }
    }


    public void aggiungiCliente(Cliente clienteNuovo){
        clienteNuovo.setIdCliente(String.valueOf(this.clienti.size()));
        this.clienti.add(clienteNuovo);

        try{
            storeClienti();
        } catch (IOException e){
            System.out.println("Errore nel salvataggio del File \"listaClienti\"");
        }
    }

    public void storeClienti() throws IOException{
        Path path = Paths.get(filenameClienti);

        try(BufferedWriter bw = Files.newBufferedWriter(path)){
            for(Cliente cliente : this.clienti){
                bw.write(String.format("%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s",
                        cliente.getUsername(),
                        cliente.getPassword(),
                        cliente.getNome(),
                        cliente.getCognome(),
                        cliente.getEmail(),
                        cliente.getIndirizzo(),
                        cliente.getCf(),
                        cliente.getNumeroDiTelefono()));

                bw.newLine();
            }
        }
    }
}
