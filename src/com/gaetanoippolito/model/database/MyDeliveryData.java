package com.gaetanoippolito.model.database;

import com.gaetanoippolito.model.*;
import com.gaetanoippolito.model.observerPattern.Corriere;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Questa classe rappresenta l'astrazione del database dell'applicazione MyDelivery
 */

public class MyDeliveryData {
    ///////////////////////////////// VARIABILI DI ISTANZA /////////////////////////////////
    // Eager Initialization
    private static final MyDeliveryData instance = new MyDeliveryData();

    private static final String filenameAdmin = "listaAdmin.txt";
    private static final String filenameAzienda = "listaAziende.txt";
    private static final String filenameVeicoli = "listaVeicoli.txt";
    private static final String filenameClienti = "listaClienti.txt";
    private static final String filenameCorrieri = "listaCorrieri.txt";
    private static final String filenameOrdini = "listaOrdini.txt";
    private static final String filenamePacchi = "listaPacchi.txt";
    private static final String filenameCentroDiSmistamento = "listaCentriDiSmistamento.txt";
    /**@see Admin*/
    private Admin admin;
    /**@see Azienda*/
    private ObservableList<Azienda> aziende;
    /**@see Veicolo*/
    private ObservableList<Veicolo> veicoli;
    /**@see Cliente*/
    private ObservableList<Cliente> clienti;
    /**@see Corriere*/
    private ObservableList<Corriere> corrieri;
    /**@see Ordine*/
    private ObservableList<Ordine> ordini;
    /**@see Pacco*/
    private ObservableList<Pacco> pacchi;
    /**@see CentroDiSmistamento*/
    private ObservableList<CentroDiSmistamento> centriDiSmistamento;

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
        this.corrieri = FXCollections.observableArrayList();
        this.ordini = FXCollections.observableArrayList();
        this.pacchi = FXCollections.observableArrayList();
        this.centriDiSmistamento = FXCollections.observableArrayList();
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

    /**
     * Metodo che ritorna l'ObserableList in cui sono contenuti i corrieri
     * @return Ritorna una ObservableList di Corriere
     * @see Corriere
     */
    public ObservableList<Corriere> getCorrieri() {
        return this.corrieri;
    }

    /**
     * Metodo che ritorna l'ObserableList in cui sono contenuti gli ordini
     * @return Ritorna una ObservableList di Ordini
     * @see Ordine
     */
    public ObservableList<Ordine> getOrdini() {
        return this.ordini;
    }

    /**
     * Metodo che ritorna l'ObserableList in cui sono contenuti i pacchi
     * @return Ritorna una ObservableList di Pacchi
     * @see Pacco
     */
    public ObservableList<Pacco> getPacchi() {
        return this.pacchi;
    }

    public ObservableList<CentroDiSmistamento> getCentriDiSmistamento() {
        return centriDiSmistamento;
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
        return admin.getUsername().equals(username) && admin.getPassword().equals(password);
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
        List<Veicolo> veicoliDaRimuovere = azienda.getVeicoli();
        System.out.println("_______________");
        System.out.println(veicoliDaRimuovere);
        System.out.println("_______________");
        List<Corriere> corrieriDaRimuovere = azienda.getCorrieri();
        List<Ordine> ordiniDaRimuovere = new ArrayList<>();
        List<Pacco> pacchiDaRimuovere = new ArrayList<>();

        for(Ordine ordine : this.ordini){
            if(ordine.getAziendaDaOrdine().equals(azienda)){
                ordiniDaRimuovere.add(ordine);
                pacchiDaRimuovere.add(ordine.getPacco());
            }
        }

        this.veicoli.removeIf(veicoliDaRimuovere::contains);
        this.corrieri.removeIf(corrieriDaRimuovere::contains);
        this.pacchi.removeIf(pacchiDaRimuovere::contains);
        this.ordini.removeIf(ordiniDaRimuovere::contains);
        this.aziende.removeIf(azienda::equals);

        try{
            storePacchi();
            storeOrdini();
            storeCorrieri();
            storeVeicoli();
            storeAziende();
        } catch (IOException e){
            System.out.println("Salvataggio fallito");
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
    private void rimuoviVeicolo(Azienda azienda){
        for(Veicolo veicolo : azienda.getVeicoli()){
            this.veicoli.remove(veicolo);
        }
        try{
            storeVeicoli();
        }catch (IOException e){
            System.out.println("Errore nella cancellazione dei veicoli");
        }
    }

    public void rimuoviVeicolo(Veicolo veicolo){
        this.veicoli.remove(veicolo);
        try{
            storeVeicoli();
        }catch (IOException e){
            System.out.println("Errore nella cancellazione dei veicoli");
        }
    }

    public ArrayList<Veicolo> getVeicoloAzienda(Azienda azienda){
        ArrayList<Veicolo> veicoliAzienda = new ArrayList<>();

        for(Veicolo veicolo : azienda.getVeicoli()){
            veicoliAzienda.add(veicolo);
        }

        return veicoliAzienda;
    }

    public ArrayList<Veicolo> getVeicoloAziendaNotBusy(Azienda azienda){
        ArrayList<Veicolo> veicoliAzienda = new ArrayList<>();

        for(Veicolo veicolo : azienda.getVeicoli()){
            if(!veicolo.getIsBusy()){
                veicoliAzienda.add(veicolo);
            }
        }
        return veicoliAzienda;
    }

    public Veicolo getVeicoloDisponibile(Azienda azienda){
        List<Veicolo> veicoliDisponibili = azienda.getVeicoli();

        for(Veicolo veicolo : veicoliDisponibili){
            if(!veicolo.getIsBusy()){
                return veicolo;
            }
        }

        return null;
    }

    /**
     * Questo metodo lo si utilizza per salvare i dati di Azienda all'interno del file "listaVeicoli.txt"
     * @throws IOException
     */
    public void storeVeicoli() throws IOException{
        try (ObjectOutputStream objectOut = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(filenameVeicoli)))){

            ArrayList<Veicolo> veicoli = new ArrayList<>(this.veicoli);

            System.out.println("Salvataggio dei Veicoli in corso");
            objectOut.writeObject(veicoli);
            System.out.println(veicoli);
            System.out.println("Il file \"listaVeicoli.txt\" è stato salvato con successo");

        } catch (IOException e) {
            System.out.println("Errore nel salvataggio del File \"listaVeicoli.txt\"");
            e.printStackTrace();
        }
    }

    ///////////////////////////// METODI: ZONA CORRIERI ////////////////////////////////
    public void loadCorrieri() throws IOException{
        try(ObjectInputStream objectIn = new ObjectInputStream(new BufferedInputStream(new FileInputStream(filenameCorrieri)))) {
            try{
                this.corrieri.setAll((ArrayList<Corriere>)objectIn.readObject());
            } catch (ClassNotFoundException e){
                e.printStackTrace();
            }

            System.out.println(this.corrieri);
            System.out.println("File \"listaCorriere.txt\" caricato con successo");

        } catch (Exception e) {
            System.out.println("Errore nel caricamento del File \"listaCorriere\"");
            e.printStackTrace();
        }
    }

    public Corriere loginCorriere(String nome, String id) throws Exception{
        System.out.println(this.corrieri);

        for(Corriere corriere : this.corrieri){
            if(corriere.getIdCorriere().equals(id) && corriere.getNome().equals(nome)){

                return corriere;
            }
        }

    throw new Exception();
    }

    /*
    private boolean cercaEsistenzaCorriere(String nomeCorriere){
        for(Corriere corriere : this.corrieri){
            if(corriere.getUsername().equals(nomeCorriere)){
                return true;
            }
        }

        return false;
    }
    */

    public boolean aggiungiCorrieri(Corriere corriereNuovo){
        if(this.corrieri.size() == 0){
            this.corrieri.add(corriereNuovo);

            try{
                storeCorrieri();

            } catch(IOException e){
                System.out.println("Errore nel salvataggio dei corrieri");
            }
            return true;
        }
        else{
            if(cercaCorriere(corriereNuovo.getIdCorriere())){
                return false;
            }
            else{
                this.corrieri.add(corriereNuovo);

                try{
                    storeCorrieri();

                } catch(IOException e){
                    System.out.println("Errore nel salvataggio dei corrieri");
                }
            }
        }

        return true;
    }

    public boolean cercaCorriere(String id){
        int contatore = 0;

        for(Corriere corriere : this.corrieri){
            if(corriere.getIdCorriere().equals(id)){
                contatore++;
            }
        }

        return contatore != 0;
    }

    public ArrayList<Corriere> getCorrieriDisponibili(Azienda aziendaDelCorriere){
        ArrayList<Corriere> corriereDisponibile = new ArrayList<>();

        for(Corriere corriere : aziendaDelCorriere.getCorrieri()){
            if(!corriere.getIsBusy()){
                corriereDisponibile.add(corriere);
            }
        }

        return corriereDisponibile;
    }

    public Ordine getOrdineDelCorriere(Corriere corriereDiOrdine) throws Exception{
        for(Ordine ordine : this.ordini){
            if(ordine.getCorriereFromOrdine().getIdCorriere().equals(corriereDiOrdine.getIdCorriere())){
                return ordine;
            }
        }

        throw new Exception();
    }

    public void storeCorrieri() throws IOException{
        try (ObjectOutputStream objectOut = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(filenameCorrieri)))){

            ArrayList<Corriere> corrieri = new ArrayList<>(this.corrieri);

            objectOut.writeObject(corrieri);
            System.out.println(corrieri);
            System.out.println("Il file \"listaCorrieri.txt\" è stato salvato con successo");

        } catch (IOException e) {
            System.out.println("Errore nel salvataggio del File \"listaCorrieri.txt\"");
            e.printStackTrace();
        }
    }

    ////////////////////////////// METODI: ZONA ORDINI ///////////////////////////////////
    public void loadOrdini() throws IOException{
        try(ObjectInputStream objectIn = new ObjectInputStream(new BufferedInputStream(new FileInputStream(filenameOrdini)))) {
            try{
                this.ordini.setAll((ArrayList<Ordine>)objectIn.readObject());
            } catch (ClassNotFoundException e){
                e.printStackTrace();
            }

            System.out.println(this.ordini);
            System.out.println("File \"listaOrdine.txt\" caricato con successo");

        } catch (Exception e) {
            System.out.println("Errore nel caricamento del File \"listaOrdine\"");
            e.printStackTrace();
        }
    }

    public void aggiungiOrdine(Ordine ordine){
        this.ordini.add(ordine);

        try{
            storeOrdini();
            storePacchi();
        } catch (IOException e){
            System.out.println("Errore nel salvataggio degli ordini");
        }
    }

    public ObservableList<Ordine> getMittenteOrdini(Cliente mittente){
        ObservableList<Ordine> listaMittenteOrdini = FXCollections.observableArrayList();

        for(Ordine ordine : this.ordini){
            if(ordine.getMittente().equals(mittente)){
                listaMittenteOrdini.add(ordine);
            }
        }

        return listaMittenteOrdini;
    }

    public ArrayList<Ordine> getOrdiniDaAzienda(Azienda azienda){
        ArrayList<Ordine> ordiniDiAzienda = new ArrayList<>();

        for(Ordine ordine : this.ordini){
            if(ordine.getAziendaDaOrdine().equals(azienda) && !ordine.getPresoInCarico()){
                ordiniDiAzienda.add(ordine);
            }
        }

        return ordiniDiAzienda;
    }

    public Ordine getOrdineDelPacco(Pacco pacco){
        if(pacco != null){
            for(Ordine ordine : this.ordini){
                if(ordine.getPacco().getCodice() == pacco.getCodice()){
                    System.out.println(ordine);
                    return ordine;
                }
            }
        }

        return null;
    }

    public void storeOrdini() throws IOException{
        try (ObjectOutputStream objectOut = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(filenameOrdini)))){

            ArrayList<Ordine> ordini = new ArrayList<>(this.ordini);

            objectOut.writeObject(ordini);
            System.out.println(ordini);
            System.out.println("Il file \"listaOrdine.txt\" è stato salvato con successo");

        } catch (IOException e) {
            System.out.println("Errore nel salvataggio del File \"listaOrdine.txt\"");
            e.printStackTrace();
        }
    }

    ///////////////////////////// METODI: ZONA PACCHI /////////////////////////////////
    public void loadPacchi() throws IOException{
        try(ObjectInputStream objectIn = new ObjectInputStream(new BufferedInputStream(new FileInputStream(filenamePacchi)))) {
            try{
                this.pacchi.setAll((ArrayList<Pacco>)objectIn.readObject());
            } catch (ClassNotFoundException e){
                e.printStackTrace();
            }

            System.out.println(this.pacchi);
            System.out.println("File \"listaPacchi.txt\" caricato con successo");

        } catch (Exception e) {
            System.out.println("Errore nel caricamento del File \"listaPacchi\"");
            e.printStackTrace();
        }
    }

    public void aggiungiPacco(Pacco pacco){
        this.pacchi.add(pacco);
    }

    public Pacco tracciaPacco(int codicePacco){
        for(Pacco pacco : this.pacchi){
            if(pacco.getCodice() == codicePacco){
                return pacco;
            }
        }

        return null;
    }

    public void storePacchi() throws IOException{
        try (ObjectOutputStream objectOut = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(filenamePacchi)))){

            ArrayList<Pacco> pacchi = new ArrayList<>(this.pacchi);

            objectOut.writeObject(pacchi);
            System.out.println(pacchi);
            System.out.println("Il file \"listaPacchi.txt\" è stato salvato con successo");

        } catch (IOException e) {
            System.out.println("Errore nel salvataggio del File \"listaPacchi.txt\"");
            e.printStackTrace();
        }
    }

    ///////////////////////////// METODI: ZONA CLIENTI /////////////////////////////////
    public void loadClienti() throws IOException{
        try(ObjectInputStream objectIn = new ObjectInputStream(new BufferedInputStream(new FileInputStream(filenameClienti)))) {
            try{
                this.clienti.setAll((ArrayList<Cliente>)objectIn.readObject());
            } catch (ClassNotFoundException e){
                e.printStackTrace();
            }

            System.out.println(this.clienti);
            System.out.println("File \"listaClienti.txt\" caricato con successo");

        } catch (Exception e) {
            System.out.println("Errore nel caricamento del File \"listaClienti\"");
            e.printStackTrace();
        }
    }


    public boolean aggiungiCliente(Cliente clienteNuovo){
        clienteNuovo.setIdCliente(String.valueOf(this.clienti.size() + 1));

        if(this.clienti.size() == 0){
            this.clienti.add(clienteNuovo);

            try{
                storeClienti();
            } catch (IOException e){
                System.out.println("Errore nel salvataggio del File \"listaClienti\"");
            }

            return true;
        }
        else{
            if(cercaEsistenzaCliente(clienteNuovo.getUsername())){
                System.out.println("Account già esistente");
                return false;
            }
            else{
                this.clienti.add(clienteNuovo);

                try{
                    storeClienti();
                } catch (IOException e){
                    System.out.println("Errore nel salvataggio del File \"listaClienti\"");
                }
            }
        }

        return true;
    }

    private boolean cercaEsistenzaCliente(String username){
        int contatore = 0;

        for(Cliente cliente : this.clienti){
            if(cliente.getUsername().equals(username)){
                contatore++;
            }
        }

        return contatore != 0;
    }

    public Cliente loginCliente(Cliente loginCliente) throws Exception{
        if(cercaEsistenzaCliente(loginCliente.getUsername())){
            for(Cliente cliente : this.clienti){
                if(loginCliente.getUsername().equals(cliente.getUsername())
                        && loginCliente.getPassword().equals(cliente.getPassword())){
                    return cliente;
                }
            }
        }

        throw new Exception();
    }

    public void storeClienti() throws IOException{
        try (ObjectOutputStream objectOut = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(filenameClienti)))){

            ArrayList<Cliente> cliente = new ArrayList<>(this.clienti);

            objectOut.writeObject(cliente);
            System.out.println(cliente);
            System.out.println("Il file \"listaClienti.txt\" è stato salvato con successo");

        } catch (IOException e) {
            System.out.println("Errore nel salvataggio del File \"listaClienti.txt\"");
            e.printStackTrace();
        }
    }

    ///////////////////////////// METODI: ZONA CENTRI DI SMISTAMENTO /////////////////////////////////
    public void loadCentroDiSmistamento() throws IOException{
        /**@see Path*/
        Path path = Paths.get(filenameCentroDiSmistamento);
        String input;

        // Questo fa tutto il lavoro del "buffer", in cui si bufferizza il file dato un path
        try(BufferedReader br = Files.newBufferedReader(path)){
            while((input = br.readLine()) != null){
                String[] itemPieces = input.split(" - ");

                String nomeCentroDiSmistamento = itemPieces[0];
                String indirizzo = itemPieces[1];
                String numeroCivico = itemPieces[2];


                /**@see CentroDiSmistamento*/
                CentroDiSmistamento centroDiSmistamento = new CentroDiSmistamento(nomeCentroDiSmistamento, indirizzo, numeroCivico);
                this.centriDiSmistamento.add(centroDiSmistamento);
            }
        } catch (IOException e){
            System.out.println("Caricamento file \"listaClienti.txt\" fallito");
        }
    }

    public void aggiungiCentroDiSmistamento(CentroDiSmistamento centroDiSmistamentoNuovo){
        this.centriDiSmistamento.add(centroDiSmistamentoNuovo);

        try{
            storeCentriDiSmistamento();
        } catch (IOException e){
            System.out.println("Errore nel salvataggio dei centri di smistamento");
            e.printStackTrace();
        }
    }

    public void rimuoviCentroDiSmistamento(CentroDiSmistamento centroDiSmistamento){
        this.centriDiSmistamento.remove(centroDiSmistamento);

        try{
            storeCentriDiSmistamento();
        } catch (IOException e){
            System.out.println("Errore nella rimozione del centro di smistamento");
            e.printStackTrace();
        }
    }

    public void storeCentriDiSmistamento() throws IOException{
        try (ObjectOutputStream objectOut = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(filenameCentroDiSmistamento)))){

            ArrayList<CentroDiSmistamento> centroDiSmistamento = new ArrayList<>(this.centriDiSmistamento);

            objectOut.writeObject(centroDiSmistamento);
            System.out.println(centroDiSmistamento);
            System.out.println("Il file \"listaCentriDiSmistamento.txt\" è stato salvato con successo");

        } catch (IOException e) {
            System.out.println("Errore nel salvataggio del File \"listaCentriDiSmistamento.txt\"");
            e.printStackTrace();
        }
    }
}
