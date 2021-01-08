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

    private ObservableList<Azienda> aziende = MyDeliveryData.getInstance().getAziende();

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

        // Visualizziamo la tabella
        TableView tableView = visualizzaAziende();
        tableView.setItems(this.aziende);
        this.vboxAdminStage.getChildren().add(1, tableView);

        // TODO: // Visualizzare i Veicoli, finire di commentare

        // Azione che si triggera se il bottone "AggiungiAzienda" viene cliccato
        aggiungiAziendaItem.setOnAction(event -> {
            gestisciAggiuntaAzienda();
        });

        // Azione che si triggera se il bottone "RimuoviAzienda" viene cliccato
        rimuoviAziendaItem.setOnAction(event -> {
            gestisciRimuoviAzienda();
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
            Azienda nuovaAzienda = aggiungiAziendaController.processaAggiuntaAzienda();
            System.out.println(nuovaAzienda);
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
            rimuoviAziendaController.processaRimozioneAzienda();
            System.out.println("Rimozione avvenuta con successo!");
        }
        else{
            System.out.println("Operazione Annullata");
        }
    }

    /**
     * Questo metodo crea una tableView con le Colonne e celle settate in base ai valori della lista degli admin
     * @return ritorna una TableView costruita
     * @see TableView
     * @see TableColumn
     * */
    public TableView<Azienda> visualizzaAziende(){
        TableView<Azienda> aziendeView = new TableView<>();

        TableColumn<Azienda, String> colonnaNomeAzienda = new TableColumn<>("Nome Azienda");
        TableColumn<Azienda, String> colonnaPartitaIVA = new TableColumn<>("Partita IVA");

        colonnaNomeAzienda.setCellValueFactory(nome -> new SimpleStringProperty(nome.getValue().getNome()));
        aziendeView.getColumns().add(popolaCelle(colonnaNomeAzienda));

        colonnaPartitaIVA.setCellValueFactory(partitaIVA -> new SimpleStringProperty(partitaIVA.getValue().getPartitaIVA()));
        aziendeView.getColumns().add(popolaCelle(colonnaPartitaIVA));

        // Impostiamo la grandezza massima della TableView per ogni colonna
        aziendeView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        colonnaNomeAzienda.setMaxWidth(Integer.MAX_VALUE * 50D);    // 25%
        colonnaPartitaIVA.setMaxWidth(Integer.MAX_VALUE * 50D);     // 25%

        return aziendeView;
    }

    /**
     *
     */
    private TableColumn<Azienda, String> popolaCelle(TableColumn<Azienda, String> tableColumn){
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
