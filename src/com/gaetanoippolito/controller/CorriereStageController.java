package com.gaetanoippolito.controller;

import com.gaetanoippolito.controller.dialog.AggiornaStatoOrdineDialogController;
import com.gaetanoippolito.controller.dialog.NextFitDialogController;
import com.gaetanoippolito.model.Ordine;
import com.gaetanoippolito.model.database.MyDeliveryData;
import com.gaetanoippolito.model.observerPattern.Corriere;
import com.gaetanoippolito.model.observerPattern.ObservableCorriere;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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

public class CorriereStageController {
    private final String rootLoginStageFile = "src/com/gaetanoippolito/view/fxml/login.fxml";
    private final String rootAggiornaStatoDialogFile = "src/com/gaetanoippolito/view/fxml/dialog/aggiornaStatoOrdineDialog.fxml";
    private ObservableCorriere corriere;
    private ObservableList<Ordine> ordini = FXCollections.observableArrayList();

    @FXML
    private BorderPane corriereBorderPane;

    @FXML
    private TableView<Ordine> spedizioneCorriereView;
    @FXML
    private TableColumn<Ordine, String> codiceOrdineColonna;
    @FXML
    private TableColumn<Ordine, String> destinazioneColonna;
    @FXML
    private TableColumn<Ordine, String> scadenzaColonna;
    @FXML
    private TableColumn<Ordine, String> veicoloColonna;
    @FXML
    private TableColumn<Ordine, String> pesoContainerColonna;
    @FXML
    private TableColumn<Ordine, String> statoOrdineColonna;
    @FXML
    private TableColumn<Ordine, String> centroDiSmistamentoColonna;

    @FXML
    private Button logoutButton;

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


        visualizzaCodiceOrdineColonna();
        visualizzaDestinazioneColonna();
        visualizzaScadenzaColonna();
        visualizzaVeicoloColonna();
        visualizzaPesoContainerColonna();
        visualizzaStatoOrdineColonna();
    }

    @FXML
    public void visualizzaOrdini(){
        try{
            this.ordini.add(MyDeliveryData.getInstance().getOrdineDelCorriere((Corriere) this.corriere));

            visualizzaCodiceOrdineColonna();
            visualizzaDestinazioneColonna();
            visualizzaScadenzaColonna();
            visualizzaVeicoloColonna();
            visualizzaPesoContainerColonna();
            visualizzaStatoOrdineColonna();

            this.spedizioneCorriereView.setItems(this.ordini);

        }catch (Exception e){
            Alert warning = new Alert(Alert.AlertType.WARNING);
            warning.setContentText("Non ci sono ordini");
            warning.show();
        }
    }

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

    public void setCorriere(Corriere corriere){
        this.corriere = corriere;

        System.out.println(this.corriere);
    }

    public void setOrdiniDiCorriere(Ordine ordine){
        this.ordini.add(ordine);
        System.out.println(this.ordini);
    }

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

    private void visualizzaDestinazioneColonna(){
        // "SimpleStringProperty" rende una stringa osservabile data una stringa
        this.destinazioneColonna.setCellValueFactory(destinazioneColonna -> new SimpleStringProperty(
                String.valueOf(destinazioneColonna.getValue().getDestinatario().getIndirizzo())));

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

    private void visualizzaPesoContainerColonna(){
        // "SimpleStringProperty" rende una stringa osservabile data una stringa
        this.pesoContainerColonna.setCellValueFactory(pesoContainerColonna -> new SimpleStringProperty(
                String.valueOf(pesoContainerColonna.getValue().getOrdineDelVeicolo().getPesoInContainer())));

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

    private void visualizzaStatoOrdineColonna(){
        // "SimpleStringProperty" rende una stringa osservabile data una stringa
        this.statoOrdineColonna.setCellValueFactory(statoOrdineColonna -> new SimpleStringProperty(
                String.valueOf(statoOrdineColonna.getValue().getPacco().getStatoPacco())));

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

    @FXML
    private void gestioneCambiaStatoOrdine(){
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
            aggiornaStatoOrdineDialogController.processaCambiaStatoOrdine();
        }
    }
}
