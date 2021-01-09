package com.gaetanoippolito.controller;

import com.gaetanoippolito.controller.dialog.AggiungiAziendaController;
import com.gaetanoippolito.controller.dialog.RimuoviAziendaController;
import com.gaetanoippolito.model.Azienda;
import com.gaetanoippolito.model.Veicolo;
import com.gaetanoippolito.model.database.MyDeliveryData;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Optional;

/**
 * Questa classe rappresenta il Controller della finestra con cui l'Admin interagisce e monitora le azienda con cui
 * collabora, i veicoli delle aziende e gli ordini effettuati dal cliente.
 */

public class AdminStageController {
    // Attributi
    private final String rootAdminStageFile = "src/com/gaetanoippolito/view/fxml/adminStage.fxml";
    private final String rootAggiungiAziendaDialog = "src/com/gaetanoippolito/view/fxml/dialog/aggiungiAziendaDialog.fxml";
    private final String rootRimuoviAziendaDialog = "src/com/gaetanoippolito/view/fxml/dialog/rimuoviAziendaDialog.fxml";

    @FXML
    private VBox vboxAdminStage;

    TableView<Azienda> aziendaTableView = new TableView<>();
    // Questa TableView viene assegnata la prima volta dal metodo "visualizzaVeicoli()"
    TableView<Veicolo> veicoloTableView;
    // TableView<Ordine> ordineTableView = new TableView<>();

    private ObservableList<Azienda> aziende = MyDeliveryData.getInstance().getAziende();
    private ObservableList<Veicolo> veicoli = MyDeliveryData.getInstance().getVeicoli();

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
        edit.getItems().addAll(azienda);
        // Logout -> Exit..
        logout.getItems().add(exitItem);

        // Aggiungo al MenuBar tutti i Menu con i MenuItems associati
        MenuBar menuBarAdmin = new MenuBar();
        menuBarAdmin.getMenus().addAll(show, edit, logout);

        // Imposto il MenuBar in cima alla finestra
        this.vboxAdminStage.getChildren().add(0, menuBarAdmin);

        // Visualizziamo la tabella con le informazioni delle aziende associate di Default
        visualizzaAziende();

        // TODO: // finire di commentare

        // Azione che si triggera se il bottone "AggiungiAzienda" viene cliccato
        aggiungiAziendaItem.setOnAction(event -> {
            gestisciAggiuntaAzienda();
        });

        // Azione che si triggera se il bottone "RimuoviAzienda" viene cliccato
        rimuoviAziendaItem.setOnAction(event -> {
            gestisciRimuoviAzienda();
        });

        listaAziendaItem.setOnAction(event -> {
            visualizzaAziende();
        });

        listaVeicoliItem.setOnAction(event -> {
            visualizzaVeicoli();
        });
    }

    /**
     * Quando viene cliccato sul bottone "AggiungiAzienda" questo metodo viene richiamato. Il suo scopo è quello
     * di aggiungere una nuova azienda alla lista di aziende presenti.
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
     * Quando viene cliccato sul bottone "rimuoviAzienda" questo metodo viene richiamato. Il suo scopo è quello
     * di rimuovere un'azienda
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
     * */
    public void visualizzaAziende(){
        int i = this.vboxAdminStage.getChildren().size();
        if(i == 2){
            this.vboxAdminStage.getChildren().remove(i - 1);
            this.vboxAdminStage.getChildren().add(i - 1, this.aziendaTableView);
        }
        else{
            TableColumn<Azienda, String> colonnaNomeAzienda = new TableColumn<>("Nome Azienda");
            TableColumn<Azienda, String> colonnaPartitaIVA = new TableColumn<>("Partita IVA");

            colonnaNomeAzienda.setCellValueFactory(nomeAzienda -> new SimpleStringProperty(nomeAzienda.getValue().getNome()));
            this.aziendaTableView.getColumns().add(popolaCelleAzienda(colonnaNomeAzienda));

            colonnaPartitaIVA.setCellValueFactory(partitaIVA -> new SimpleStringProperty(partitaIVA.getValue().getPartitaIVA()));
            this.aziendaTableView.getColumns().add(popolaCelleAzienda(colonnaPartitaIVA));

            // Impostiamo la grandezza massima della TableView per ogni colonna
            this.aziendaTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
            colonnaNomeAzienda.setMaxWidth(Integer.MAX_VALUE * 50D);    // 50%
            colonnaPartitaIVA.setMaxWidth(Integer.MAX_VALUE * 50D);     // 50%

            this.aziendaTableView.setItems(this.aziende);
            this.vboxAdminStage.getChildren().add(i, this.aziendaTableView);
        }
    }

    /**
     *
     */
    public void visualizzaVeicoli(){
        int i = this.vboxAdminStage.getChildren().size();
        this.vboxAdminStage.getChildren().remove(i - 1);

        if(i == 2 && this.veicoloTableView == null){
            this.veicoloTableView = new TableView<>();

            TableColumn<Veicolo, String> colonnaTipoVeicolo = new TableColumn<>("Tipo Veicolo");
            TableColumn<Veicolo, String> colonnaCapienzaVeicolo = new TableColumn<>("Capienza Veicolo");
            TableColumn<Veicolo, String> colonnaCodiceVeicolo = new TableColumn<>("Codice");
            TableColumn<Veicolo, String> colonnaAziendaAssociata = new TableColumn<>("Azienda Associata");

            colonnaTipoVeicolo.setCellValueFactory(tipoVeicolo -> new SimpleStringProperty(tipoVeicolo.getValue().getTipoVeicolo().toString()));
            this.veicoloTableView.getColumns().add(popolaCelleVeicolo(colonnaTipoVeicolo));

            colonnaCapienzaVeicolo.setCellValueFactory(capienzaVeicolo -> new SimpleStringProperty(String.valueOf(capienzaVeicolo.getValue().getCapienzaContainer())));
            this.veicoloTableView.getColumns().add(popolaCelleVeicolo(colonnaCapienzaVeicolo));

            colonnaCodiceVeicolo.setCellValueFactory(codiceVeicolo -> new SimpleStringProperty(String.valueOf(codiceVeicolo.getValue().getCodice())));
            this.veicoloTableView.getColumns().add(popolaCelleVeicolo(colonnaCodiceVeicolo));

            colonnaAziendaAssociata.setCellValueFactory(nomeAzienda -> new SimpleStringProperty(nomeAzienda.getValue().getAziendaAssociata()));
            this.veicoloTableView.getColumns().add(popolaCelleVeicolo(colonnaAziendaAssociata));

            // Impostiamo la grandezza massima della TableView per ogni colonna
            this.veicoloTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
            colonnaTipoVeicolo.setMaxWidth(Integer.MAX_VALUE * 25D);       // 25%
            colonnaCapienzaVeicolo.setMaxWidth(Integer.MAX_VALUE * 25D);   // 25%
            colonnaCodiceVeicolo.setMaxWidth(Integer.MAX_VALUE * 25D);     // 25%
            colonnaAziendaAssociata.setMaxWidth(Integer.MAX_VALUE * 25D);  // 25%

            this.veicoloTableView.setItems(this.veicoli);

            this.vboxAdminStage.getChildren().add(i - 1, this.veicoloTableView);
        }
        else{
            this.vboxAdminStage.getChildren().add(i - 1, this.veicoloTableView);
        }
    }

    /**
     *
     */
    public TableColumn<Veicolo, String> popolaCelleVeicolo(TableColumn<Veicolo, String> tableColumn){
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
     *
     */
    private TableColumn<Azienda, String> popolaCelleAzienda(TableColumn<Azienda, String> tableColumn){
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
}
