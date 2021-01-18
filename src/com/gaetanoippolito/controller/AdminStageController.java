package com.gaetanoippolito.controller;

import com.gaetanoippolito.controller.dialog.AggiungiAziendaController;
import com.gaetanoippolito.controller.dialog.NextFitDialogController;
import com.gaetanoippolito.controller.dialog.RimuoviAziendaController;
import com.gaetanoippolito.model.*;
import com.gaetanoippolito.model.database.MyDeliveryData;
import com.gaetanoippolito.model.Corriere;
import com.gaetanoippolito.model.observerPattern.Ordine;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

/**
 * Questa classe rappresenta il Controller della finestra con cui l'Admin interagisce e monitora le azienda con cui
 * collabora, i veicoli delle aziende e gli ordini effettuati dal cliente.
 */
public class AdminStageController {
    ///////////////////////////////// VARIABILI DI ISTANZA /////////////////////////////////
    private final String rootLoginStageFile = "src/com/gaetanoippolito/view/fxml/login.fxml";
    private final String rootAggiungiAziendaDialog = "src/com/gaetanoippolito/view/fxml/dialog/aggiungiAziendaDialog.fxml";
    private final String rootRimuoviAziendaDialog = "src/com/gaetanoippolito/view/fxml/dialog/rimuoviAziendaDialog.fxml";
    private final String rootNextFitDialog = "src/com/gaetanoippolito/view/fxml/dialog/nextFitDialog.fxml";

    @FXML
    /**@see VBox*/
    private VBox vboxAdminStage;

    /**@see Azienda*/
    private TableView<Azienda> aziendaTableView = new TableView<>();

    // Questa TableView viene assegnata la prima volta dal metodo "visualizzaVeicoli()"
    /**@see Veicolo*/
    private TableView<Veicolo> veicoloTableView;
    // Questa TableView viene assegnata la prima volta dal metodo "visualizzaColliDaConsegnare()"
    /**@see*/
    private TableView<Pacco> colliTableView;

    private ObservableList<Azienda> aziende = MyDeliveryData.getInstance().getAziende();
    private ObservableList<Veicolo> veicoli = MyDeliveryData.getInstance().getVeicoli();
    private ObservableList<Ordine> ordini = MyDeliveryData.getInstance().getOrdini();
    private ObservableList<Pacco> pacchi = MyDeliveryData.getInstance().getPacchi();
    private ObservableList<Corriere> corrieri = MyDeliveryData.getInstance().getCorrieri();

    ////////////////////////////////////// METODI //////////////////////////////////////
    /**
     * Metodo overridato che viene triggerato nel momento in cui viene inizializzata la view. Quì dentro si setta una
     * MenuBar e tutti i vari Item e Menu che sono al suo interno.
     */
    @FXML
    public void initialize(){
        // Creazione dei MenuItems per il Menu "show"
        MenuItem listaAziendaItem = new MenuItem("Lista Azienda");
        MenuItem listaVeicoliItem = new MenuItem("Lista Veicoli");
        MenuItem listaColliDaConsegnareItem = new MenuItem("Lista Colli Da Consegnare");

        // Creazione dei MenuItems per il Menu "Azienda" che si trova all'interno del Menu "Edit"
        MenuItem aggiungiAziendaItem = new MenuItem("Aggiungi Azienda");
        MenuItem rimuoviAziendaItem = new MenuItem("Rimuovi Azienda");

        // Creazione del MenuItem per il Menu "Exit"
        MenuItem exitItem = new MenuItem("Exit..");

        // Creazione del MenuItem per il nextFit
        MenuItem nextFitITem = new MenuItem("NextFit");

        // Dichiaro i Menu
        Menu show = new Menu("Show");

        Menu edit = new Menu("Edit");
        Menu azienda = new Menu("Azienda");

        Menu logout = new Menu("Logout");

        // Associo i MenuItems ai Menu
        // Show -> Lista Azienda, Lista Veicoli, Lista Colli Da Consegnare
        show.getItems().addAll(listaAziendaItem, listaVeicoliItem, listaColliDaConsegnareItem);
        // Azienda -> Aggiungi Azienda, Rimuovi Azienda
        azienda.getItems().addAll(aggiungiAziendaItem, rimuoviAziendaItem);
        // Edit -> Azienda
        edit.getItems().addAll(azienda, nextFitITem);
        // Logout -> Exit..
        logout.getItems().add(exitItem);

        // Aggiungo al MenuBar tutti i Menu con i MenuItems associati
        MenuBar menuBarAdmin = new MenuBar();
        menuBarAdmin.getMenus().addAll(show, edit, logout);

        // Imposto il MenuBar in cima alla finestra
        this.vboxAdminStage.getChildren().add(0, menuBarAdmin);

        // Visualizziamo la tabella con le informazioni delle aziende associate di Default
        visualizzaAziende();

        // Azione che si triggera se il bottone "AggiungiAzienda" viene cliccato
        aggiungiAziendaItem.setOnAction(event -> {
            gestisciAggiuntaAzienda();
        });

        // Azione che si triggera se il bottone "RimuoviAzienda" viene cliccato
        rimuoviAziendaItem.setOnAction(event -> {
            gestisciRimuoviAzienda();
        });

        // Azione che si triggera se il bottone "Lista Azienda" viene cliccato
        listaAziendaItem.setOnAction(event -> {
            visualizzaAziende();
        });

        // Azione che si triggera se il bottone "Lista Veicoli" viene cliccato
        listaVeicoliItem.setOnAction(event -> {
            visualizzaVeicoli();
        });

        // Azione che si triggera se il bottone "Lista Colli da consegnare" viene cliccato
        listaColliDaConsegnareItem.setOnAction(event -> {
            visualizzaColliDaConsegnare();
        });

        nextFitITem.setOnAction(event -> {
            gestoreNextFit();

            Predicate<Ordine> isPresoInCarico = Ordine::getPresoInCarico;
            FilteredList<Ordine> listaFiltrataDiOrdini = new FilteredList<>(MyDeliveryData.getInstance().getOrdini());
            listaFiltrataDiOrdini.setPredicate(isPresoInCarico);

            List<Corriere> listaCorrieriDiAzienda = MyDeliveryData.getInstance().getCorrieriDisponibili(listaFiltrataDiOrdini.get(0).getAziendaDaOrdine());

            int indiceCorriere = 0;
            Veicolo veicoloPrecedente = MyDeliveryData.getInstance().getOrdini().get(0).getOrdineDelVeicolo();

            for(int i = 0; i < MyDeliveryData.getInstance().getOrdini().size(); i++){
                System.out.println("***********************************************");
                System.out.println("ITERATO TOT VOLTE: " + i);
                if(!veicoloPrecedente.equals(MyDeliveryData.getInstance().getOrdini().get(i).getOrdineDelVeicolo()) && MyDeliveryData.getInstance().getOrdini().get(i).getPresoInCarico()){
                    indiceCorriere++;
                    veicoloPrecedente = MyDeliveryData.getInstance().getOrdini().get(i).getOrdineDelVeicolo();
                    MyDeliveryData.getInstance().getOrdini().get(i).setOrdineDelCorriere(listaCorrieriDiAzienda.get(indiceCorriere));
                    listaCorrieriDiAzienda.get(indiceCorriere).setIsBusy(true);
                }
                else if(MyDeliveryData.getInstance().getOrdini().get(i).getPresoInCarico()){
                    MyDeliveryData.getInstance().getOrdini().get(i).setOrdineDelCorriere(listaCorrieriDiAzienda.get(indiceCorriere));
                    listaCorrieriDiAzienda.get(indiceCorriere).setIsBusy(true);
                    System.out.println("peppe: " + indiceCorriere);
                }
            }

            try{
                MyDeliveryData.getInstance().storeVeicoli();
                MyDeliveryData.getInstance().storeOrdini();
                MyDeliveryData.getInstance().storePacchi();
                MyDeliveryData.getInstance().storeCorrieri();
                MyDeliveryData.getInstance().storeAziende();
                MyDeliveryData.getInstance().storeClienti();
            } catch (IOException e){
                System.out.println("Errore durante il salvataggio");
                e.printStackTrace();
            }
        });

        // Azione che si triggera se il bottone "Exit.." viene cliccato
        exitItem.setOnAction(event -> {
            ritornaInterfacciaLogin();
        });
    }

    /**
     * Quando viene cliccato sul bottone "Aggiungi Azienda" questo metodo viene richiamato. Il suo scopo è quello di
     * mostrare la finestra dialog "aggiungiAziendaDialog.fxml" e di controllare se l'utente ha premuto sul tasto "OK"
     * o sul tasto "CANCEL". Il metodo aspetta che i bottoni vengano premuti e in base a ciò che è stato premuto svolge
     * un'operazione.
     */
    public void gestisciAggiuntaAzienda(){
        AggiungiAziendaController aggiungiAziendaController;
        Dialog<ButtonType> aggiungiAziendaDialog = new Dialog<>();
        FXMLLoader loader = new FXMLLoader();

        // Settiamo il proprietario e il titolo della finestra Dialog che si crea
        aggiungiAziendaDialog.initOwner(this.vboxAdminStage.getScene().getWindow());
        aggiungiAziendaDialog.setTitle("Aggiungi un'azienda");

        // Carichiamo il File fxml dov'è presente il Dialog
        try{
            Parent root = loader.load(new FileInputStream(rootAggiungiAziendaDialog));
            aggiungiAziendaDialog.getDialogPane().setContent(root);
        } catch (IOException e){
            System.out.println("File not found");
            e.printStackTrace();
        }

        // Aggiungiamo i Bottoni nel dialog
        aggiungiAziendaDialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        aggiungiAziendaDialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);

        // inizializziamo  il controller
        aggiungiAziendaController = loader.getController();

        // Settiamo il bottone a "non cliccabile" in base al ritorno dinamico del testo dei vari TextField
        aggiungiAziendaDialog.getDialogPane().lookupButton(ButtonType.OK).disableProperty().bind(aggiungiAziendaController.disabilitaOkButton());

        // Aspettiamo l'input dell'utente
        Optional<ButtonType> result = aggiungiAziendaDialog.showAndWait();

        // Se il tasto "OK" è stato cliccato aggiungi l'azienda, altrimenti annulla l'operazione
        if(result.isPresent() && result.get() == ButtonType.OK){
            aggiungiAziendaController.processaAggiuntaAzienda();
        }
        else{
            System.out.println("Operazione Annullata");
        }
    }

    /**
     * Quando viene cliccato sul bottone "Rimuovi Azienda" questo metodo viene richiamato. Il suo scopo è quello di
     * mostrare la finestra dialog "rimuoviAziendaDialog.fxml" e di controllare se l'utente ha premuto sul tasto "OK"
     * o sul tasto "CANCEL". Il metodo aspetta che i bottoni vengano premuti e in base a ciò che è stato premuto svolge
     * un'operazione.
     */
    public void gestisciRimuoviAzienda(){
        RimuoviAziendaController rimuoviAziendaController;
        Dialog<ButtonType> rimuoviAziendaDialog = new Dialog<>();
        FXMLLoader loader = new FXMLLoader();

        // Settiamo il proprietario e il titolo della finestra Dialog che si crea
        rimuoviAziendaDialog.initOwner(this.vboxAdminStage.getScene().getWindow());
        rimuoviAziendaDialog.setTitle("Rimuovi un'azienda");

        // Carichiamo il File fxml dov'è presente il Dialog
        try{
            Parent root = loader.load(new FileInputStream(rootRimuoviAziendaDialog));
            rimuoviAziendaDialog.getDialogPane().setContent(root);
        } catch (IOException e){
            System.out.println("File not found");
            e.printStackTrace();
        }

        // Aggiungiamo i Bottoni nel dialog
        rimuoviAziendaDialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        rimuoviAziendaDialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);

        // inizializziamo  il controller
        rimuoviAziendaController = loader.getController();

        // Aspettiamo l'input dell'utente
        Optional<ButtonType> result = rimuoviAziendaDialog.showAndWait();

        // Se il tasto "OK" è stato cliccato rimuovi l'azienda, altrimenti annulla l'operazione
        if(result.isPresent() && result.get() == ButtonType.OK){
            if(rimuoviAziendaController.processaRimozioneAzienda()){
                System.out.println("Rimozione avvenuta con successo!");
            }
            else{
                System.out.println("Azienda non trovata");
            }
        }
        else{
            System.out.println("Operazione Annullata");
        }
    }

    /**
     * Questo metodo crea una tableView con le Colonne e celle settate in base ai valori della lista degli admin
     * @see TableView
     * @see TableColumn
     */
    public void visualizzaAziende(){
        // Salviamo la grandezza della Vbox in a quanti nodi sono presenti al suo interno. Serve per controllare se
        // una tabella è già presente all'interno della VBox.
        int i = this.vboxAdminStage.getChildren().size();

        // Se "i" è uguale a 2 vuol dire che una tabella è già presente. Questa verrà rimossa e aggiunta la tabella
        // che vogliamo mostrare, ovvero la tabella che mostra la lista degli admin.
        if(i == 2){
            this.vboxAdminStage.getChildren().remove(i - 1);
            this.vboxAdminStage.getChildren().add(i - 1, this.aziendaTableView);
        }
        // Questo else viene richiamato se "i" è minore di 2, siccome questo indica che non ci sono tabelle all'interno
        // della VBox. Quindi la tabella verrà creata per la prima volta da questo blocco e salvata all'interno di
        // "aziendaTableView"
        else{
            // Creazione delle colonne e del nome dell'intestazione
            TableColumn<Azienda, String> colonnaNomeAzienda = new TableColumn<>("Nome Azienda");
            TableColumn<Azienda, String> colonnaPartitaIVA = new TableColumn<>("Partita IVA");

            // Si setta il valore delle celle in base al ritorno della funzione lamba
            // "SimpleStringProperty" rende una stringa osservabile data una stringa
            colonnaNomeAzienda.setCellValueFactory(nomeAzienda -> new SimpleStringProperty(nomeAzienda.getValue().getNome()));
            this.aziendaTableView.getColumns().add(popolaCelleAzienda(colonnaNomeAzienda));

            colonnaPartitaIVA.setCellValueFactory(partitaIVA -> new SimpleStringProperty(partitaIVA.getValue().getPartitaIVA()));
            this.aziendaTableView.getColumns().add(popolaCelleAzienda(colonnaPartitaIVA));

            // Impostiamo la grandezza massima della TableView per ogni colonna
            this.aziendaTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
            colonnaNomeAzienda.setMaxWidth(Integer.MAX_VALUE * 50D);    // 50%
            colonnaPartitaIVA.setMaxWidth(Integer.MAX_VALUE * 50D);     // 50%

            this.aziendaTableView.setItems(this.aziende);
            // Aggiungiamo alla VBox la tabella dopo il MenuBar creato durante l'initialize
            this.vboxAdminStage.getChildren().add(i, this.aziendaTableView);
        }
    }

    /**
     * Questo metodo crea una tableView con le Colonne e celle settate in base ai valori della lista dei veicoli
     * @see TableView
     * @see TableColumn
     */
    public void visualizzaVeicoli(){
        // Salviamo la grandezza della Vbox in a quanti nodi sono presenti al suo interno. Serve per controllare se
        // una tabella è già presente all'interno della VBox.
        int i = this.vboxAdminStage.getChildren().size();

        // Visto che la lista delle aziende viene visualizzata di default, quando viene richiamato questo metodo
        // rimuoviamo la lista delle aziende
        this.vboxAdminStage.getChildren().remove(i - 1);

        // Durante questo if statement viene creato per la prima volta la tabella e inizializzata.
        if(i == 2 && this.veicoloTableView == null){
            this.veicoloTableView = new TableView<>();

            // Creazione delle colonne e del nome dell'intestazione
            TableColumn<Veicolo, String> colonnaTipoVeicolo = new TableColumn<>("Tipo Veicolo");
            TableColumn<Veicolo, String> colonnaCapienzaVeicolo = new TableColumn<>("Capienza Veicolo");
            TableColumn<Veicolo, String> colonnaCodiceVeicolo = new TableColumn<>("Codice");
            TableColumn<Veicolo, String> colonnaAziendaAssociata = new TableColumn<>("Azienda Associata");
            TableColumn<Veicolo, String> colonnaVeicoloDisponibile = new TableColumn<>("Non Disponibile");

            // Si setta il valore delle celle in base al ritorno della funzione lamba
            // "SimpleStringProperty" rende una stringa osservabile data una stringa
            colonnaTipoVeicolo.setCellValueFactory(tipoVeicolo -> new SimpleStringProperty(tipoVeicolo.getValue().getTipoVeicolo().toString()));
            this.veicoloTableView.getColumns().add(popolaCelleVeicolo(colonnaTipoVeicolo));

            colonnaCapienzaVeicolo.setCellValueFactory(capienzaVeicolo -> new SimpleStringProperty(String.valueOf(capienzaVeicolo.getValue().getCapienzaContainer())));
            this.veicoloTableView.getColumns().add(popolaCelleVeicolo(colonnaCapienzaVeicolo));

            colonnaCodiceVeicolo.setCellValueFactory(codiceVeicolo -> new SimpleStringProperty(String.valueOf(codiceVeicolo.getValue().getCodice())));
            this.veicoloTableView.getColumns().add(popolaCelleVeicolo(colonnaCodiceVeicolo));

            colonnaAziendaAssociata.setCellValueFactory(nomeAzienda -> new SimpleStringProperty(nomeAzienda.getValue().getAziendaAssociata().getNome()));
            this.veicoloTableView.getColumns().add(popolaCelleVeicolo(colonnaAziendaAssociata));

            colonnaVeicoloDisponibile.setCellValueFactory(veicoloDisponibile -> new SimpleStringProperty(String.valueOf(veicoloDisponibile.getValue().getIsBusy())));
            this.veicoloTableView.getColumns().add(popolaCelleVeicolo(colonnaVeicoloDisponibile));

            // Impostiamo la grandezza massima della TableView per ogni colonna
            this.veicoloTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
            colonnaTipoVeicolo.setMaxWidth(Integer.MAX_VALUE * 20D);         // 20%
            colonnaCapienzaVeicolo.setMaxWidth(Integer.MAX_VALUE * 20D);     // 20%
            colonnaCodiceVeicolo.setMaxWidth(Integer.MAX_VALUE * 20D);       // 20%
            colonnaAziendaAssociata.setMaxWidth(Integer.MAX_VALUE * 20D);    // 20%
            colonnaVeicoloDisponibile.setMaxWidth(Integer.MAX_VALUE * 20D);  // 20%

            this.veicoloTableView.setItems(this.veicoli);

            // Aggiungiamo alla VBox la tabella dopo il MenuBar creato durante l'initialize
            this.vboxAdminStage.getChildren().add(i - 1, this.veicoloTableView);
        }
        // Questo else viene chiamato se e solo se la tabella dei veicoli è stata già creata (ciò che avviene nel blocco
        // if). In questo blocco viene aggiunta la tabella nella VBox.
        else{
            this.vboxAdminStage.getChildren().add(i - 1, this.veicoloTableView);
        }
    }

    public void visualizzaColliDaConsegnare(){
        // Salviamo la grandezza della Vbox in a quanti nodi sono presenti al suo interno. Serve per controllare se
        // una tabella è già presente all'interno della VBox.
        int i = this.vboxAdminStage.getChildren().size();

        // Visto che la lista delle aziende viene visualizzata di default, quando viene richiamato questo metodo
        // rimuoviamo la lista delle aziende
        this.vboxAdminStage.getChildren().remove(i - 1);

        // Durante questo if statement viene creato per la prima volta la tabella e inizializzata.
        if(i == 2 && this.colliTableView == null){
            this.colliTableView = new TableView<>();

            // Creazione delle colonne e del nome dell'intestazione
            TableColumn<Pacco, String> colonnaCodicePacco = new TableColumn<>("Codice Pacco");
            TableColumn<Pacco, String> colonnaPesoPacco = new TableColumn<>("Peso Pacco");

            // Si setta il valore delle celle in base al ritorno della funzione lamba
            // "SimpleStringProperty" rende una stringa osservabile data una stringa
            colonnaCodicePacco.setCellValueFactory(codicePacco -> new SimpleStringProperty(String.valueOf(codicePacco.getValue().getCodice())));
            this.colliTableView.getColumns().add(popolaCellePacchi(colonnaCodicePacco));

            colonnaPesoPacco.setCellValueFactory(pesoPacco -> new SimpleStringProperty(String.valueOf(pesoPacco.getValue().getPesoPacco())));
            this.colliTableView.getColumns().add(popolaCellePacchi(colonnaPesoPacco));

            // Impostiamo la grandezza massima della TableView per ogni colonna
            this.colliTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
            colonnaCodicePacco.setMaxWidth(Integer.MAX_VALUE * 50D); // 50%
            colonnaPesoPacco.setMaxWidth(Integer.MAX_VALUE * 50D);   // 50%

            this.colliTableView.setItems(this.pacchi);

            // Aggiungiamo alla VBox la tabella dopo il MenuBar creato durante l'initialize
            this.vboxAdminStage.getChildren().add(i - 1, this.colliTableView);
        }
        // Questo else viene chiamato se e solo se la tabella dei veicoli è stata già creata (ciò che avviene nel blocco
        // if). In questo blocco viene aggiunta la tabella nella VBox.
        else{
            this.vboxAdminStage.getChildren().add(i - 1, this.colliTableView);
        }
    }

    /**
     * Questo metodo viene chiamato quando bisogna aggiungere elementi alla lista dei veicoli.
     * @param tableColumn rappresenta la colonna che deve essere popolata
     * @return Ritorna la tabella popolata
     */
    private TableColumn<Pacco, String> popolaCellePacchi(TableColumn<Pacco, String> tableColumn){
        tableColumn.setCellFactory(column -> new TableCell<>(){
            @Override
            protected void updateItem(String string, boolean empty){
                super.updateItem(string, empty);
                if(empty || string == null){
                    setText(null);
                } else {
                    setText(string);
                }
            }
        });

        return tableColumn;
    }

    /**
     * Questo metodo viene chiamato quando bisogna aggiungere elementi alla lista dei veicoli.
     * @param tableColumn rappresenta la colonna che deve essere popolata
     * @return Ritorna la tabella popolata
     */
    private TableColumn<Veicolo, String> popolaCelleVeicolo(TableColumn<Veicolo, String> tableColumn){
        tableColumn.setCellFactory(column -> new TableCell<>(){
            @Override
            protected void updateItem(String string, boolean empty){
                super.updateItem(string, empty);
                if(empty || string == null){
                    setText(null);
                } else {
                    setText(string);
                }
            }
        });

        return tableColumn;
    }

    /**
     * Questo metodo viene chiamato quando bisogna aggiungere elementi alla lista delle aziende.
     * @param tableColumn rappresenta la colonna che deve essere popolata
     * @return Ritorna la tabella popolata
     */
    private TableColumn<Azienda, String> popolaCelleAzienda(TableColumn<Azienda, String> tableColumn){
        // Personalizziamo la cella e quello che vogliamo vedere
        tableColumn.setCellFactory(column -> new TableCell<>(){
            @Override
            protected void updateItem(String string, boolean empty){
                super.updateItem(string, empty);
                if(empty || string == null){
                    setText(null);
                } else {
                    setText(string);
                }
            }
        });

        return tableColumn;
    }

    private void ritornaInterfacciaLogin(){
        // Chiude la finestra dell'Admin
        Stage stage = (Stage)vboxAdminStage.getScene().getWindow();
        stage.close();

        // Creazione di una nuova finestra
        Stage loginStage = new Stage();

        try{
            FXMLLoader loader = new FXMLLoader();
            Parent root = loader.load(new FileInputStream(rootLoginStageFile));

            loginStage.setTitle("My Delivery");
            loginStage.setScene(new Scene(root, 550, 450));
            loginStage.show();

            loginStage.show();

        } catch (IOException e){
            e.printStackTrace();
            System.out.println("Errore nel caricamento del file");
        }
    }

    private void gestoreNextFit(){
        NextFitDialogController nextFitDialogController;
        Dialog<ButtonType> nextFitDialog = new Dialog<>();
        FXMLLoader loader = new FXMLLoader();
        Alert warning = new Alert(Alert.AlertType.WARNING);

        // Settiamo il proprietario e il titolo della finestra Dialog che si crea
        nextFitDialog.initOwner(this.vboxAdminStage.getScene().getWindow());
        nextFitDialog.setTitle("Scegliere l'azienda con cui applicare l'algoritmo");

        // Carichiamo il File fxml dov'è presente il Dialog
        try{
            Parent root = loader.load(new FileInputStream(rootNextFitDialog));
            nextFitDialog.getDialogPane().setContent(root);
        } catch (IOException e){
            System.out.println("File not found");
            e.printStackTrace();
        }

        // Aggiungiamo i Bottoni nel dialog
        nextFitDialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        nextFitDialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);

        // inizializziamo  il controller
        nextFitDialogController = loader.getController();

        nextFitDialog.getDialogPane().lookupButton(ButtonType.OK).disableProperty().bind(nextFitDialogController.disabilitaTastoOK());

        // Aspettiamo l'input dell'utente
        Optional<ButtonType> result = nextFitDialog.showAndWait();

        // Se il tasto "OK" è stato cliccato rimuovi l'azienda, altrimenti annulla l'operazione
        if(result.isPresent() && result.get() == ButtonType.OK){

            ArrayList<Veicolo> veicoliNextFit = new ArrayList<>(MyDeliveryData.getInstance().getVeicoloAziendaNotBusy(nextFitDialogController.aziendaSelezionata()));
            ArrayList<Ordine> ordiniNextFit = new ArrayList<>(MyDeliveryData.getInstance().getOrdiniDaAzienda(nextFitDialogController.aziendaSelezionata()));
            ArrayList<Pacco> pacchiNextFit = new ArrayList<>();

            if(ordiniNextFit.size() == 0){
                warning.setContentText("Non ci sono ordini presso questa azienda");
                warning.show();
            }
            else if(veicoliNextFit.size() < ordiniNextFit.size()){
                warning.setContentText("Non ci sono abbastanza veicoli per questa azienda");
                warning.show();
            }
            else{
                for(Ordine ordine : ordiniNextFit){
                    pacchiNextFit.add(ordine.getPacco());
                }

                nextFit(veicoliNextFit, veicoliNextFit.size(), ordiniNextFit, ordiniNextFit.size());
            }
        }
        else{
            System.out.println("Operazione Annullata");
        }
    }

    private void nextFit(ArrayList<Veicolo> veicoliNextFit, int sizeVeicoli, ArrayList<Ordine> ordiniNextFit, int sizeOrdini){
        int j = 0;

        for(int i = 0; i < sizeOrdini; i++){
            while(j < sizeVeicoli){
                double tempWeight = veicoliNextFit.get(j).getCapienzaContainer();

                if((veicoliNextFit.get(j).getCapienzaContainer() - veicoliNextFit.get(j).getPesoInContainer()) >= ordiniNextFit.get(i).getPacco().getPesoPacco()){
                    this.veicoli.remove(veicoliNextFit.get(j));

                    veicoliNextFit.get(j).depositaPacco(ordiniNextFit.get(i).getPacco());

                    veicoliNextFit.get(j).setIsBusy(true);

                    associaOrdineVeicoloCorriere(veicoliNextFit.get(j), ordiniNextFit.get(i));

                    break;
                }
                else{
                    System.out.println("Errore nel nextFit");
                }

                j = (j + 1) % sizeVeicoli;
            }
        }
    }

    private void associaOrdineVeicoloCorriere(Veicolo veicoloAggiornato, Ordine ordine){
        this.ordini.remove(ordine);

        ordine.setPresoInCarico(true);

        ordine.setVeicoloDiOrdine(veicoloAggiornato);

        this.veicoli.add(veicoloAggiornato);
        this.ordini.add(ordine);
    }
}
