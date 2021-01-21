package com.gaetanoippolito.model.database;

import com.gaetanoippolito.model.*;
import com.gaetanoippolito.model.Corriere;
import com.gaetanoippolito.model.observerPattern.Ordine;
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
    /**@see Ordine */
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

    /**
     * Metodo che ritorna l'ObserableList in cui sono contenuti i centri di smistamento
     * @return Ritorna una ObservableList di CentroDiSmistamento
     * @see CentroDiSmistamento
     */
    public ObservableList<CentroDiSmistamento> getCentriDiSmistamento() {
        return this.centriDiSmistamento;
    }

    /**
     * Metodo che ritorna l'istanza di Admin
     * @return Ritorna l'istanza di Admin
     * @see Admin
     */
    public Admin getAdmin() {
        return this.admin;
    }

    /**
     * Metodo che ritorna l'ObserableList in cui sono contenuti i clienti
     * @return Ritorna una ObservableList dei Clienti
     * @see Cliente
     */
    public ObservableList<Cliente> getClienti() {
        return this.clienti;
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

    /**
     * Questo metodo setta l'ObservableList di Ordine
     * @param ordini Rappresenta l'ObservableList di Ordine da settare all'interno della classe
     * @see Ordine
     */
    public void setOrdini(ObservableList<Ordine> ordini) {
        this.ordini = ordini;
    }

    /**
     * Questo metodo setta l'ObservableList di Cliente
     * @param clienti Rappresenta l'ObservableList di Cliente da settare all'interno della classe
     * @see Cliente
     */
    public void setClienti(ObservableList<Cliente> clienti) {
        this.clienti = clienti;
    }

    /**
     * Questo metodo setta l'ObservableList di Corriere
     * @param corrieri Rappresenta l'ObservableList di Corriere da settare all'interno della classe
     * @see Corriere
     */
    public void setCorrieri(ObservableList<Corriere> corrieri) {
        this.corrieri = corrieri;
    }

    /**
     * Questo metodo setta l'ObservableList di Pacco
     * @param pacchi Rappresenta l'ObservableList di Pacco da settare all'interno della classe
     * @see Pacco
     */
    public void setPacchi(ObservableList<Pacco> pacchi) {
        this.pacchi = pacchi;
    }

    /**
     * Questo metodo setta l'ObservableList di CentroDiSmistamento
     * @param centriDiSmistamento Rappresenta l'ObservableList di CentroDiSmistamento da settare all'interno della classe
     * @see CentroDiSmistamento
     */
    public void setCentriDiSmistamento(ObservableList<CentroDiSmistamento> centriDiSmistamento) {
        this.centriDiSmistamento = centriDiSmistamento;
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
        } catch (FileNotFoundException e) {
            System.out.println("File \"listaAdmin.txt\" vuoto");
        } catch (IOException e){
            System.out.println("Errore nel caricamento del File \"listaAdmin.txt\"");
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

            System.out.println("File \"listaAziende.txt\" caricato con successo");

        } catch (FileNotFoundException e) {
            System.out.println("File \"listaAziende.txt\" vuoto");
        } catch (IOException e){
            System.out.println("Errore nel caricamento del File \"listaAziende.txt\"");
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
     * "listaAziende.txt". Il cancellamento dell'azienda implica il cancellamento in MyDeliveryData dei Veicoli,
     * degli ordini, dei corrieri e dei pacchi che fanno parte dell'azienda che si intende di rimuovere.
     * @param azienda rappresenta l'azienda che l'utente vuole rimuovere
     */
    private void rimuoviAzienda(Azienda azienda){
        List<Veicolo> veicoliDaRimuovere = azienda.getVeicoli();
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

        } catch (FileNotFoundException e) {
            System.out.println("File \"listaVeicoli.txt\" vuoto");
        } catch (IOException e){
            System.out.println("Errore nel caricamento del File \"listaVeicoli.txt\"");
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
     * Questo metodo restituisce un ArrayList di Veicolo data un'azienda. I veicoli restituiti sono tutti
     * NotBusy, ovvero veicoli liberi e che possono essere utilizzati per trasportare pacchi.
     */
    public ArrayList<Veicolo> getVeicoloAziendaNotBusy(Azienda azienda){
        ArrayList<Veicolo> veicoliAzienda = new ArrayList<>();

        for(Veicolo veicolo : azienda.getVeicoli()){
            if(!veicolo.getIsBusy()){
                veicoliAzienda.add(veicolo);
            }
        }
        return veicoliAzienda;
    }

    /**
     * Questo metodo lo si utilizza per salvare i dati di Veicolo all'interno del file "listaVeicoli.txt"
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
    /**
     * Questo metodo lo si utilizza per caricare i dati del file "listaCorrieri.txt" dove sono contenuti i
     * dati dei Corrieri.
     */
    public void loadCorrieri() throws IOException{
        try(ObjectInputStream objectIn = new ObjectInputStream(new BufferedInputStream(new FileInputStream(filenameCorrieri)))) {
            try{
                this.corrieri.setAll((ArrayList<Corriere>)objectIn.readObject());
            } catch (ClassNotFoundException e){
                e.printStackTrace();
            }

            System.out.println("File \"listaCorrieri.txt\" caricato con successo");

        } catch (FileNotFoundException e) {
            System.out.println("File \"listaCorrieri.txt\" vuoto");
        } catch (IOException e){
            System.out.println("Errore nel caricamento del File \"listaCorrieri.txt\"");
        }
    }

    /**
     * Questo metodo è utilizzato durante il login del corriere. Controlla se i dati immessi dall'utente sia uguali
     * ad un'istanza di Corriere presente in MyDeliveryData. Se trova un'occorenza in cui "nome, cognome e id" siano
     * uguali ad un corriere presente in MyDeliveryData, ritorna quel corriere, altrimenti lancia un Exception
     * @param nome Rappresenta il nome del corriere inserito nel login.
     * @param cognome Rappresenta il cognome del corriere inserito nel login.
     * @param id Rappresenta l'id del corriere inserito nel login.
     * @return Ritorna l'istanza del corriere che rispetta la condizione imposta dal blocco If
     * @throws Exception
     */
    public Corriere loginCorriere(String nome, String cognome, String id) throws Exception{
        for(Corriere corriere : this.corrieri){
            if(corriere.getIdCorriere().equals(id) && corriere.getNome().equals(nome) && corriere.getCognome().equals(cognome)){

                return corriere;
            }
        }

    throw new Exception();
    }

    /**
     * Questo metodo è utilizzato durante la registrazione di un Corriere. Se il corriere che si vuole aggiungere
     * è il primo della lista, allora non vi saranno controlli e verrà aggiunto in MyDeliveryData, altrimenti
     * verrà utilizzato il metodo "cercaCorriere(String id)" in cui se trova almeno un'occorrenza del corriere
     * all'interno di MyDeliveryData, ritorna true altrimenti ritorna false. In caso ritornasse true, non verrà
     * salvato nessun corriere, essendo già esistente, altrimenti verrà inserito all'interno della ObservableList
     * di corrieri e salvato all'interno del file.
     * @param corriereNuovo Rappresenta il Corriere che si vuole aggiungere
     * @return Ritorna true se il corriere è stato aggiunto, altrimenti ritorna false.
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

    /**
     * Metodo utilizzato per controllare se il corriere inserito esiste. Questo controllo è svolto tramite il suo
     * ID. Nel caso cui trovi un'occerrenza, ritornerà true altrimenti false.
     * @param id Rappresenta l'id del corriere
     * @return Ritorna true se è stata trovata almeno una o più occorrenze, altrimenti ritorna false.
     */
    public boolean cercaCorriere(String id){
        int contatore = 0;

        for(Corriere corriere : this.corrieri){
            if(corriere.getIdCorriere().equals(id)){
                contatore++;
            }
        }

        return contatore != 0;
    }

    /**
     * Metodo utilizzato per restituire un ArrayList di Corrieri, data un'azienda, disponibili e che quindi non
     * stanno già lavornado per una spedizione. L'ArrayList restituita sarà di Corrieri che fanno tutti parte
     * della stessa azienda.
     * @param aziendaDelCorriere Rappresenta l'azienda del corriere.
     * @return Ritorna un ArrayList di corrieri disponibili facenti parte tutti della stessa azienda.
     */
    public ArrayList<Corriere> getCorrieriDisponibili(Azienda aziendaDelCorriere){
        ArrayList<Corriere> corriereDisponibile = new ArrayList<>();

        for(Corriere corriere : aziendaDelCorriere.getCorrieri()){
            if(!corriere.getIsBusy()){
                corriereDisponibile.add(corriere);
            }
        }

        return corriereDisponibile;
    }

    /**
     * Metodo che restituisce la List di Ordini del Corriere dato come parametro in input.
     * @param corriereDiOrdine Rappresenta il Corriere di cui vogliamo sapere quanti ordini deve spedire.
     * @return Ritorna una Lista di ordini dato un corriere.
     */
    public List<Ordine> getOrdineDelCorriere(Corriere corriereDiOrdine){
        List<Ordine> ordini = new ArrayList<>();

        for(Ordine ordine : this.ordini){
            if(ordine.getCorriereFromOrdine().getIdCorriere().equals(corriereDiOrdine.getIdCorriere())){
                ordini.add(ordine);
            }
        }

        return ordini;
    }

    /**
     * Questo metodo lo si utilizza per salvare i dati di Corriere all'interno del file "listaCorrieri.txt"
     * @throws IOException
     */
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
    /**
     * Questo metodo lo si utilizza per caricare i dati del file "listaOrdini.txt" dove sono contenuti i
     * dati degli Ordini.
     */
    public void loadOrdini() throws IOException{
        try(ObjectInputStream objectIn = new ObjectInputStream(new BufferedInputStream(new FileInputStream(filenameOrdini)))) {
            try{
                this.ordini.setAll((ArrayList<Ordine>)objectIn.readObject());
            } catch (ClassNotFoundException e){
                e.printStackTrace();
            }

            System.out.println(this.ordini);
            System.out.println("File \"listaOrdini.txt\" caricato con successo");

        } catch (FileNotFoundException e) {
            System.out.println("File \"listaOrdini.txt\" vuoto");
        } catch (IOException e){
            System.out.println("Errore nel caricamento del File \"listaOrdini.txt\"");
        }
    }

    /**
     * Questo metodo lo si utilizza per aggiungere un Ordine all'interno di MyDeliveryData
     * @param ordine Rappresenta l'ordine creato da voler aggiungere.
     */
    public void aggiungiOrdine(Ordine ordine){
        this.ordini.add(ordine);

        try{
            storeOrdini();
            storePacchi();
        } catch (IOException e){
            System.out.println("Errore nel salvataggio degli ordini");
        }
    }

    /**
     * Questo metodo lo si utilizza per restituire una ObservableList di Ordine dato un mittente, così da mostrare
     * quanti ordini ha effettuato un mittente (ovvero un Cliente iscritto alla piattaforma).
     * @param mittente Rappresenta il Cliente che invia che genera ordini (per cui è il mittente di quegli ordini).
     * @return Ritorna una ObservableList di Ordini effettuati dal mittente dato come paramentro al metodo.
     */
    public ObservableList<Ordine> getOrdiniDaMittente(Cliente mittente){
        ObservableList<Ordine> listaMittenteOrdini = FXCollections.observableArrayList();

        for(Ordine ordine : this.ordini){
            if(ordine.getMittente().equals(mittente)){
                listaMittenteOrdini.add(ordine);
            }
        }

        return listaMittenteOrdini;
    }

    /**
     * Questo metodo lo si utilizza per restituire una ArrayList di Ordini data un azienda, così da ottenere solo
     * gli ordini che fanno parte di quell'azienda.
     * @param azienda Rappresenta l'azienda presso cui sono stati effettuati degli ordini.
     * @return Ritorna una ArrayList di Ordine effettuati presso un'azienda.
     */
    public ArrayList<Ordine> getOrdiniDaAzienda(Azienda azienda){
        ArrayList<Ordine> ordiniDiAzienda = new ArrayList<>();

        for(Ordine ordine : this.ordini){
            if(ordine.getAziendaDaOrdine().equals(azienda) && !ordine.getPresoInCarico()){
                ordiniDiAzienda.add(ordine);
            }
        }

        return ordiniDiAzienda;
    }

    /**
     * Questo metodo è utilizzato per ottenere l'Ordine generato da un cliente in cui è presente il pacco passato
     * come parametro di input al metodo.
     * @param pacco Rappresenta il Pacco dell'ordine che stiamo cercando.
     * @return Ritorna l'Ordine in cui è presente il Pacco passato in input.
     */
    public Ordine getOrdineDelPacco(Pacco pacco){
        if(pacco != null){
            for(Ordine ordine : this.ordini){
                if(ordine.getPacco().getCodice() == pacco.getCodice()){

                    return ordine;
                }
            }
        }

        return null;
    }

    /**
     * Questo metodo lo si utilizza per salvare i dati di Ordine all'interno del file "listaOrdini.txt"
     * @throws IOException
     */
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
    /**
     * Questo metodo lo si utilizza per caricare i dati del file "listaPacchi.txt" dove sono contenuti i
     * dati dei Pacchi.
     */
    public void loadPacchi() throws IOException{
        try(ObjectInputStream objectIn = new ObjectInputStream(new BufferedInputStream(new FileInputStream(filenamePacchi)))) {
            try{
                this.pacchi.setAll((ArrayList<Pacco>)objectIn.readObject());
            } catch (ClassNotFoundException e){
                e.printStackTrace();
            }

            System.out.println("File \"listaPacchi.txt\" caricato con successo");

        } catch (FileNotFoundException e) {
            System.out.println("File \"listaOrdini.txt\" vuoto");
        } catch (IOException e){
            System.out.println("Errore nel caricamento del File \"listaOrdini.txt\"");
        }
    }

    /**
     * Questo metodo aggiunge un Pacco all'interno di MyDeliveryData.
     * @param pacco Rappresenta il Pacco da voler aggiungere.
     */
    public void aggiungiPacco(Pacco pacco){
        this.pacchi.add(pacco);
    }

    /**
     * Questo metodo restituisce il pacco dato il suo codice, se esiste.
     * @param codicePacco Rappresenta il codice del pacco che si vuole cercare.
     * @return Ritorna il pacco se esiste all'interno di MyDeliveryData.
     */
    public Pacco tracciaPacco(int codicePacco){
        for(Pacco pacco : this.pacchi){
            if(pacco.getCodice() == codicePacco){
                return pacco;
            }
        }

        return null;
    }

    /**
     * Questo metodo lo si utilizza per salvare i dati di Pacchi all'interno del file "listaPacchi.txt"
     * @throws IOException
     */
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
    /**
     * Questo metodo lo si utilizza per caricare i dati del file "listaClienti.txt" dove sono contenuti i
     * dati i Clienti.
     */
    public void loadClienti() throws IOException{
        try(ObjectInputStream objectIn = new ObjectInputStream(new BufferedInputStream(new FileInputStream(filenameClienti)))) {
            try{
                this.clienti.setAll((ArrayList<Cliente>)objectIn.readObject());
            } catch (ClassNotFoundException e){
                e.printStackTrace();
            }

            System.out.println("File \"listaClienti.txt\" caricato con successo");

        } catch (FileNotFoundException e) {
            System.out.println("File \"listaClienti.txt\" vuoto");
        } catch (IOException e){
            System.out.println("Errore nel caricamento del File \"listaClienti.txt\"");
        }
    }

    /**
     * Questo metodo aggiunge un cliente all'interno di MyDeliveryData. Se la ObservableList in cui sono contenuti
     * tutti i Clienti è vuota, allora il cliente viene aggiunto senza controlli, altrimenti viene utilizzato il
     * metodo "cercaEsistenzaCliente(String username)" che controlla se il cliente da noi inserito già esiste.
     * Se il metodo restituisce true, allora il cliente esiste e non verrà salvato, altrimenti se restituisce false
     * il cliente verrà aggiunto all'interno di MyDeliveryData e salvato nel file "listaClienti.txt"
     * @param clienteNuovo Rappresenta il cliente che si sta registrando alla piattaforma.
     * @return Ritorna true se il cliente è stato aggiunto, altrimenti ritorna false.
     */
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

    /**
     * Questo metodo cerca, tramite l'username del cliente, se il cliente esiste. Nel caso in cui trova almeno
     * un'occorrenza del username dato in input al metodo, allora ritornerà true, altrimenti ritornerà false.
     * @param username Rappresenta l'username del cliente da cercare.
     * @return Ritorna true se il cliente esiste, altrimenti ritorna false.
     */
    private boolean cercaEsistenzaCliente(String username){
        int contatore = 0;

        for(Cliente cliente : this.clienti){
            if(cliente.getUsername().equals(username)){
                contatore++;
            }
        }

        return contatore != 0;
    }

    /**
     * Questo metodo permette ad un cliente di accedere all'interno della piattaforma MyDelivery. Se, dato un cliente,
     * il suo username e la sua password corrispondono ad un cliente già esistente all'interno di MyDeliveryData, il
     * metodo restuisce il cliente che vuole accedere. Altrimenti il metodo lancia un Exception.
     * @param loginCliente Rappresenta il cliente che vuole provare ad accedere alla piattaforma
     * @return Ritorna il Cliente che rispetta i requisiti di accesso e che quindi può effettuare il login
     * @throws Exception
     */
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

    /**
     * Questo metodo lo si utilizza per salvare i dati di Clienti all'interno del file "listaClienti.txt"
     * @throws IOException
     */
    public void storeClienti() throws IOException{
        try (ObjectOutputStream objectOut = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(filenameClienti)))){

            ArrayList<Cliente> cliente = new ArrayList<>(this.clienti);

            objectOut.writeObject(cliente);
            System.out.println("Il file \"listaClienti.txt\" è stato salvato con successo");

        } catch (IOException e) {
            System.out.println("Errore nel salvataggio del File \"listaClienti.txt\"");
            e.printStackTrace();
        }
    }

    ///////////////////////////// METODI: ZONA CENTRI DI SMISTAMENTO /////////////////////////////////
    /**
     * Questo metodo lo si utilizza per caricare i dati del file "listaCentriDiSmistamento.txt" dove sono
     * contenuti i dati dei Centri di Smistamento.
     */
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
        } catch (FileNotFoundException e) {
            System.out.println("File \"listaCentriDiSmistamento.txt\" vuoto");
        } catch (IOException e){
            System.out.println("Errore nel caricamento del File \"listaCentriDiSmistamento.txt\"");
        }
    }

    /**
     * Metodo che aggiunge un centro di smistamento all'interno di MyDeliveryData.
     * @param centroDiSmistamentoNuovo Rappresenta il centro di smistamento da voler aggiungere
     */
    public void aggiungiCentroDiSmistamento(CentroDiSmistamento centroDiSmistamentoNuovo){
        this.centriDiSmistamento.add(centroDiSmistamentoNuovo);

        try{
            storeCentriDiSmistamento();
        } catch (IOException e){
            System.out.println("Errore nel salvataggio dei centri di smistamento");
            e.printStackTrace();
        }
    }

    /**
     * Metodo che rimuove un centro di smistamento all'interno di MyDeliveryData.
     * @param centroDiSmistamento Rappresenta il centro di smistamento da voler rimuovere
     */
    public void rimuoviCentroDiSmistamento(CentroDiSmistamento centroDiSmistamento){
        this.centriDiSmistamento.remove(centroDiSmistamento);

        try{
            storeCentriDiSmistamento();
        } catch (IOException e){
            System.out.println("Errore nella rimozione del centro di smistamento");
            e.printStackTrace();
        }
    }

    /**
     * Questo metodo lo si utilizza per salvare i dati di CentroDiSmistamento all'interno del file
     * "listaCentriDiSmistamento.txt"
     * @throws IOException
     */
    public void storeCentriDiSmistamento() throws IOException{
        try (ObjectOutputStream objectOut = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(filenameCentroDiSmistamento)))){

            ArrayList<CentroDiSmistamento> centroDiSmistamento = new ArrayList<>(this.centriDiSmistamento);

            objectOut.writeObject(centroDiSmistamento);
            System.out.println("Il file \"listaCentriDiSmistamento.txt\" è stato salvato con successo");

        } catch (IOException e) {
            System.out.println("Errore nel salvataggio del File \"listaCentriDiSmistamento.txt\"");
            e.printStackTrace();
        }
    }
}