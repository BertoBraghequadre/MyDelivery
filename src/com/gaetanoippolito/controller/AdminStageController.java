package com.gaetanoippolito.controller;

import com.gaetanoippolito.controller.dialog.AggiungiAziendaController;
import com.gaetanoippolito.controller.dialog.RimuoviAziendaController;
import com.gaetanoippolito.model.Azienda;
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

    private Menu show;
    private Menu edit;
    private Menu azienda;
    private Menu logout;

    private MenuItem listaAzienda;
    private MenuItem listaVeicoli;
    private MenuItem listaColliDaConsegnare;
    private MenuItem aggiungiAzienda;
    private MenuItem rimuoviAzienda;
    private MenuItem exit;

    private MenuBar menuBarAdmin;

    /**
     * Metodo overridato che viene triggerato nel momento in cui viene inizializzata la view. Quì dentro si setta una
     * MenuBar e tutti i vari Item e Menu che sono al suo interno.
     */
    @FXML
    public void initialize(){
        // Creazione dei MenuItems per il Menu "show"
        this.listaAzienda = new MenuItem("Lista Azienda");
        this.listaVeicoli = new MenuItem("Lista Veicoli");
        this.listaColliDaConsegnare = new MenuItem("Lista Colli Da Consegnare");

        // Creazione dei MenuItems per il Menu "Azienda" che si trova all'interno del Menu "Edit"
        this.aggiungiAzienda = new MenuItem("Aggiungi Azienda");
        this.rimuoviAzienda = new MenuItem("Rimuovi Azienda");

        // Creazione del MenuItem per il Menu "Exit"
        this.exit = new MenuItem("Exit..");

        // Dichiaro i Menu
        this.show = new Menu("Show");

        this.edit = new Menu("Edit");
        this.azienda = new Menu("Azienda");

        this.logout = new Menu("Logout");

        // Associo i MenuItems ai Menu
        // Show -> Lista Azienda, Lista Veicoli, Lista Colli Da Consegnare
        this.show.getItems().addAll(this.listaAzienda, this.listaVeicoli, this.listaColliDaConsegnare);
        // Azienda -> Aggiungi Azienda, Rimuovi Azienda
        this.azienda.getItems().addAll(this.aggiungiAzienda, this.rimuoviAzienda);
        // Edit -> Azienda
        this.edit.getItems().addAll(this.azienda);
        // Logout -> Exit..
        this.logout.getItems().add(this.exit);

        // Aggiungo al MenuBar tutti i Menu con i MenuItems associati
        this.menuBarAdmin = new MenuBar();
        this.menuBarAdmin.getMenus().addAll(this.show, this.edit, this.logout);

        // Imposto il MenuBar in cima alla finestra
        this.vboxAdminStage.getChildren().add(0, this.menuBarAdmin);

        // Azione che si triggera se il bottone "AggiungiAzienda" viene cliccato
        this.aggiungiAzienda.setOnAction(event -> {
            gestisciAggiuntaAzienda();
        });

        // Azione che si triggera se il bottone "RimuoviAzienda" viene cliccato
        this.rimuoviAzienda.setOnAction(event -> {
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
}
