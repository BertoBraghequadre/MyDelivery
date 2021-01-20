package com.gaetanoippolito.controller;

import com.gaetanoippolito.controller.dialog.AggiornaStatoOrdineDialogController;
import com.gaetanoippolito.model.observerPattern.Destinatario;
import com.gaetanoippolito.model.observerPattern.Ordine;
import com.gaetanoippolito.model.database.MyDeliveryData;
import com.gaetanoippolito.model.Corriere;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Optional;
import java.util.function.Predicate;

/**
 * Questa classe rappresenta il controller della view del Corriere.
 */

public class CorriereStageController {
    ///////////////////////////////// VARIABILI DI ISTANZA /////////////////////////////////
    private final String rootLoginStageFile = "src/com/gaetanoippolito/view/fxml/login.fxml";
    private final String rootAggiornaStatoDialogFile = "src/com/gaetanoippolito/view/fxml/dialog/aggiornaStatoOrdineDialog.fxml";
    /**@see Corriere*/
    private Corriere corriere;
    /**@see Ordine*/
    private ObservableList<Ordine> ordini;

    /**@see BorderPane*/
    @FXML
    private BorderPane corriereBorderPane;

    /**@see TableView*/
    @FXML
    private TableView<Ordine> spedizioneCorriereView;

    /**@see TableColumn*/
    @FXML
    private TableColumn<Ordine, String> codiceOrdineColonna;

    /**@see TableColumn*/
    @FXML
    private TableColumn<Ordine, String> destinazioneColonna;

    /**@see TableColumn*/
    @FXML
    private TableColumn<Ordine, String> scadenzaColonna;

    /**@see TableColumn*/
    @FXML
    private TableColumn<Ordine, String> veicoloColonna;

    /**@see TableColumn*/
    @FXML
    private TableColumn<Ordine, String> pesoContainerColonna;

    /**@see TableColumn*/
    @FXML
    private TableColumn<Ordine, String> statoOrdineColonna;

    /**@see Button*/
    @FXML
    private Button logoutButton;

    ////////////////////////////////////// METODI //////////////////////////////////////
    /**
     * Metodo overridato che viene triggerato nel momento in cui viene inizializzata la view
     */
    @FXML
    public void initialize(){
        // Impostiamo la grandezza massima della TableView per ogni colonna
        this.spedizioneCorriereView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        this.codiceOrdineColonna.setMaxWidth(Integer.MAX_VALUE * 16D);         //16%
        this.destinazioneColonna.setMaxWidth(Integer.MAX_VALUE * 16D);         //16%
        this.scadenzaColonna.setMaxWidth(Integer.MAX_VALUE * 16D);             //16%
        this.veicoloColonna.setMaxWidth(Integer.MAX_VALUE * 16D);              //16%
        this.pesoContainerColonna.setMaxWidth(Integer.MAX_VALUE * 16D);        //16%
        this.statoOrdineColonna.setMaxWidth(Integer.MAX_VALUE * 16D);          //16%
    }

    /**
     * Questo metodo è utilizzato per mostrare solo gli ordini di cui il corriere fa parte.
     */
    @FXML
    public void visualizzaOrdini(){
        try{
            // Creiamo un Predicate che verrà utilizzato per filtrare la lista. Il predicato permette alla lista
            // di riempirsi di solo ordini in cui il corriere di questa interfaccia ne fa parte.
            Predicate<Ordine> ordinePredicato = ordine -> ordine.getCorriereFromOrdine().equals(this.corriere);
            FilteredList<Ordine> listaFiltrataOrdini = new FilteredList<>(MyDeliveryData.getInstance().getOrdini());
            listaFiltrataOrdini.setPredicate(ordinePredicato);

            visualizzaCodiceOrdineColonna();
            visualizzaDestinazioneColonna();
            visualizzaScadenzaColonna();
            visualizzaVeicoloColonna();
            visualizzaPesoContainerColonna();
            visualizzaStatoOrdineColonna();

            this.ordini = listaFiltrataOrdini;
            this.spedizioneCorriereView.setItems(this.ordini);

        }catch (Exception e){
            Alert warning = new Alert(Alert.AlertType.WARNING);
            warning.setContentText("Non ci sono ordini");
            warning.show();
        }
    }

    /**
     * Questo metodo è utilizzato per tornare nell'interfaccia login.
     */
    @FXML
    public void ritornaInterfacciaLogin(){
        try{
            MyDeliveryData.getInstance().storeOrdini();
        } catch (IOException e){
            System.out.println("Errore nel salvataggio degli ordini");
            e.printStackTrace();
        }

        // Chiude la finestra dell'Admin
        Stage stage = (Stage)this.logoutButton.getScene().getWindow();
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

    /**
     * Questo metodo setta il corriere.
     * @param corriere Rappresenta il corriere degli ordini
     */
    public void setCorriere(Corriere corriere){
        this.corriere = corriere;
    }

    /**
     * Questo metodo è utilizzato visualizzare il contenuto della colonna "OrdineColonna" della tableView
     * degli ordini
     */
    private void visualizzaCodiceOrdineColonna(){
        // "SimpleStringProperty" rende una stringa osservabile data una stringa
        this.codiceOrdineColonna.setCellValueFactory(codiceOrdine -> new SimpleStringProperty(
                String.valueOf(codiceOrdine.getValue().getPacco().getCodice())));

        // Personalizziamo la cella e quello che vogliamo vedere
        this.codiceOrdineColonna.setCellFactory(codiceOrdineColonna -> new TableCell<>(){
            @Override
            protected void updateItem(String codiceOrdine, boolean empty){
                super.updateItem(codiceOrdine, empty);
                if(empty || codiceOrdine == null){
                    setText(null);
                } else {
                    setText(codiceOrdine);
                }
            }
        });
    }

    /**
     * Questo metodo è utilizzato visualizzare il contenuto della colonna "Destinazione" della tableView
     * degli ordini
     */
    private void visualizzaDestinazioneColonna(){
        // "SimpleStringProperty" rende una stringa osservabile data una stringa
        this.destinazioneColonna.setCellValueFactory(destinazione -> {
            Destinatario destinatario = (Destinatario) destinazione.getValue().getDestinatari().get(0);
            return new SimpleStringProperty(destinatario.getIndirizzo());
        });
        // Personalizziamo la cella e quello che vogliamo vedere
        this.destinazioneColonna.setCellFactory(destinazioneColonna -> new TableCell<>(){
            @Override
            protected void updateItem(String destinazione, boolean empty){
                super.updateItem(destinazione, empty);
                if(empty || destinazione == null){
                    setText(null);
                } else {
                    setText(destinazione);
                }
            }
        });
    }

    /**
     * Questo metodo è utilizzato visualizzare il contenuto della colonna "Scadenza" della tableView degli ordini
     */
    private void visualizzaScadenzaColonna(){
        // "SimpleStringProperty" rende una stringa osservabile data una stringa
        this.scadenzaColonna.setCellValueFactory(scadenzaOrdine -> new SimpleStringProperty(
                String.valueOf(scadenzaOrdine.getValue().getDataDiConsegna())));

        // Personalizziamo la cella e quello che vogliamo vedere
        this.scadenzaColonna.setCellFactory(scadenzaColonna -> new TableCell<>(){
            @Override
            protected void updateItem(String scadenza, boolean empty){
                super.updateItem(scadenza, empty);
                if(empty || scadenza == null){
                    setText(null);
                } else {
                    setText(scadenza);
                }
            }
        });
    }

    /**
     * Questo metodo è utilizzato visualizzare il contenuto della colonna "Veicolo" della tableView degli ordini
     */
    private void visualizzaVeicoloColonna(){
        // "SimpleStringProperty" rende una stringa osservabile data una stringa
        this.veicoloColonna.setCellValueFactory(veicoloColonna -> new SimpleStringProperty(
                String.valueOf(veicoloColonna.getValue().getOrdineDelVeicolo().getCodice() + ": " +
                               veicoloColonna.getValue().getOrdineDelVeicolo().getTipoVeicolo())));

        // Personalizziamo la cella e quello che vogliamo vedere
        this.veicoloColonna.setCellFactory(veicoloColonna -> new TableCell<>(){
            @Override
            protected void updateItem(String veicolo, boolean empty){
                super.updateItem(veicolo, empty);
                if(empty || veicolo == null){
                    setText(null);
                } else {
                    setText(veicolo);
                }
            }
        });
    }

    /**
     * Questo metodo è utilizzato visualizzare il contenuto della colonna "Peso Container" della tableView
     * degli ordini
     */
    private void visualizzaPesoContainerColonna(){
        // "SimpleStringProperty" rende una stringa osservabile data una stringa
        this.pesoContainerColonna.setCellValueFactory(pesoContainerColonna -> new SimpleStringProperty(
                String.format("%s", pesoContainerColonna.getValue().getOrdineDelVeicolo().getPesoInContainer())));

        // Personalizziamo la cella e quello che vogliamo vedere
        this.pesoContainerColonna.setCellFactory(pesoContainerColonna  -> new TableCell<>(){
            @Override
            protected void updateItem(String pesoContainer, boolean empty){
                super.updateItem(pesoContainer, empty);
                if(empty || pesoContainer == null){
                    setText(null);
                } else {
                    setText(pesoContainer);
                }
            }
        });
    }

    /**
     * Questo metodo è utilizzato visualizzare il contenuto della colonna "Stato Ordine" della tableView degli ordini
     */
    private void visualizzaStatoOrdineColonna(){
        // "SimpleStringProperty" rende una stringa osservabile data una stringa
        this.statoOrdineColonna.setCellValueFactory(statoOrdineColonna -> new SimpleStringProperty(
                String.valueOf(statoOrdineColonna.getValue().getStatoOrdine())));

        // Personalizziamo la cella e quello che vogliamo vedere
        this.statoOrdineColonna.setCellFactory(statoOrdineColonna  -> new TableCell<>(){
            @Override
            protected void updateItem(String statoOrdine, boolean empty){
                super.updateItem(statoOrdine, empty);
                if(empty || statoOrdine == null){
                    setText(null);
                } else {
                    setText(statoOrdine);
                }
            }
        });
    }

    /**
     * Questo metodo è utilizzato per la visualizzazione e gestione del Dialog "CambiaStatoOrdineDialog".
     * Questo metodo permette il cambiamento e la visualizzazione dello stato dell'ordine da parte del corriere.
     */
    @FXML
    private void gestioneCambiaStatoOrdine(){
        Alert warning = new Alert(Alert.AlertType.WARNING);
        AggiornaStatoOrdineDialogController aggiornaStatoOrdineDialogController;
        Dialog<ButtonType> aggiornaStatoOrdineDialog = new Dialog<>();
        FXMLLoader loader = new FXMLLoader();

        // Settiamo il proprietario e il titolo della finestra Dialog che si crea
        aggiornaStatoOrdineDialog.initOwner(this.corriereBorderPane.getScene().getWindow());
        aggiornaStatoOrdineDialog.setTitle("Cambia stato dell'ordine");

        // Carichiamo il File fxml dov'è presente il Dialog
        try{
            Parent root = loader.load(new FileInputStream(rootAggiornaStatoDialogFile));
            aggiornaStatoOrdineDialog.getDialogPane().setContent(root);
        } catch (IOException e){
            System.out.println("File not found");
            e.printStackTrace();
        }

        // Aggiungiamo i Bottoni nel dialog
        aggiornaStatoOrdineDialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        aggiornaStatoOrdineDialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);

        // inizializziamo  il controller
        aggiornaStatoOrdineDialogController = loader.getController();

        // Aspettiamo l'input dell'utente
        Optional<ButtonType> result = aggiornaStatoOrdineDialog.showAndWait();

        if(result.isPresent() && result.get() == ButtonType.OK){
            Corriere currentCorriere = this.corriere;
            aggiornaStatoOrdineDialogController.processaCambiaStatoOrdine(currentCorriere);
            visualizzaOrdini();
        }
        else{
            warning.setContentText("Operazione Annullata");
            warning.show();
        }
    }
}
