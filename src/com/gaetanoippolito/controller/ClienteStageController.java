package com.gaetanoippolito.controller;

import com.gaetanoippolito.controller.dialog.CreaOrdineController;
import com.gaetanoippolito.model.Cliente;
import com.gaetanoippolito.model.Ordine;
import com.gaetanoippolito.model.database.MyDeliveryData;
import com.gaetanoippolito.model.observerPattern.Corriere;
import javafx.beans.property.SimpleStringProperty;
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

public class ClienteStageController {
    private final String rootLoginStageFile = "src/com/gaetanoippolito/view/fxml/login.fxml";
    private final String rootCreaOrdineDialog = "src/com/gaetanoippolito/view/fxml/dialog/creaOrdineDialog.fxml";
    private LoginController loginController;
    private Cliente cliente;
    private ObservableList<Ordine> ordini = MyDeliveryData.getInstance().getOrdini();

    @FXML
    private BorderPane stageClienteBorderPane;

    @FXML
    private TableView<Ordine> ordineView;
    @FXML
    private TableColumn<Ordine, String> mittenteColonna;
    @FXML
    private TableColumn<Ordine, String> destinatarioColonna;
    @FXML
    private TableColumn<Ordine, String> statoOrdineColonna;
    @FXML
    private TableColumn<Ordine, String> dataDiConsegnaColonna;
    @FXML
    private TableColumn<Ordine, String> corriereColonna;
    @FXML
    private TableColumn<Ordine, String> nomeAziendaColonna;
    @FXML
    private TableColumn<Ordine, String> codicePaccoColonna;

    @FXML
    private Button visualizzaOrdiniButton;

    @FXML
    private Button logoutButton;

    @FXML
    public void initialize(){
        // Impostiamo la grandezza massima della TableView per ogni colonna
        this.ordineView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        this.mittenteColonna.setMaxWidth(Integer.MAX_VALUE * 14D);         //14%
        this.destinatarioColonna.setMaxWidth(Integer.MAX_VALUE * 14D);     //14%
        this.statoOrdineColonna.setMaxWidth(Integer.MAX_VALUE * 14D);      //14%
        this.dataDiConsegnaColonna.setMaxWidth(Integer.MAX_VALUE * 14D);   //14%
        this.corriereColonna.setMaxWidth(Integer.MAX_VALUE * 14D);         //14%
        this.nomeAziendaColonna.setMaxWidth(Integer.MAX_VALUE * 14D);      //14%
        this.codicePaccoColonna.setMaxWidth(Integer.MAX_VALUE * 14D);      //14%
    }

    public void setCliente(Cliente cliente){
        this.cliente = cliente;
    }

    public void setOrdini(ObservableList<Ordine> ordini){
        this.ordini = ordini;
    }

    public Cliente getCliente(){
        return this.cliente;
    }

    public ObservableList<Ordine> getOrdini(){
        return this.ordini;
    }

    @FXML
    public void gestioneOrdineDialog(){
        CreaOrdineController creaOrdineController;
        Dialog<ButtonType> creaOrdineDialog = new Dialog<>();
        FXMLLoader loader = new FXMLLoader();
        Alert alert = new Alert(Alert.AlertType.WARNING);

        // Settiamo il proprietario e il titolo della finestra Dialog che si crea
        creaOrdineDialog.initOwner(this.stageClienteBorderPane.getScene().getWindow());
        creaOrdineDialog.setTitle("Crea un ordine");

        // Carichiamo il File fxml dov'è presente il Dialog
        try{
            Parent root = loader.load(new FileInputStream(rootCreaOrdineDialog));
            creaOrdineDialog.getDialogPane().setContent(root);
        } catch (IOException e){
            System.out.println("File not found");
            e.printStackTrace();
        }

        // Aggiungiamo i Bottoni nel dialog
        creaOrdineDialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        creaOrdineDialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);

        // inizializziamo  il controller
        creaOrdineController = loader.getController();

        creaOrdineController.setMittente(this.cliente);

        // Settiamo il bottone a "non cliccabile" in base al ritorno dinamico del testo dei vari TextField
        creaOrdineDialog.getDialogPane().lookupButton(ButtonType.OK).disableProperty().bind(creaOrdineController.disabilitaOkButton());

        // Aspettiamo l'input dell'utente
        Optional<ButtonType> result = creaOrdineDialog.showAndWait();

        popolaCelle();

        // Se il tasto "OK" è stato cliccato rimuovi l'azienda, altrimenti annulla l'operazione
        if(result.isPresent() && result.get() == ButtonType.OK) {
            if(creaOrdineController.processaCreazioneOrdine()){
                System.out.println("Operazione Riuscita");
                popolaCelle();
            }
            else{
                alert.setContentText("Corrieri o veicoli non disponibili per l'azienda selezionata");
                alert.show();

                System.out.println("Corrieri o veicoli non disponibili");
            }
        }
        else{
            alert.setContentText("Operazione annullata");
            alert.show();

            System.out.println("Operazione fallita");
        }

    }

    @FXML
    public void ritornaInterfacciaLogin(){
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

    private void visualizzaColonnaMittente(){
        // "SimpleStringProperty" rende una stringa osservabile data una stringa
        this.mittenteColonna.setCellValueFactory(ordine -> new SimpleStringProperty(
                     ordine.getValue().getMittente().getNome() + " " +
                        ordine.getValue().getMittente().getCognome()));

        // Personalizziamo la cella e quello che vogliamo vedere
        this.mittenteColonna.setCellFactory(mittenteColonna -> new TableCell<>(){
            @Override
            protected void updateItem(String nomeCognomeMittente, boolean empty){
                super.updateItem(nomeCognomeMittente, empty);
                if(empty || nomeCognomeMittente == null){
                    setText(null);
                } else {
                    setText(nomeCognomeMittente);
                }
            }
        });
    }

    private void visualizzaColonnaDestinatario(){
        // "SimpleStringProperty" rende una stringa osservabile data una stringa
        this.destinatarioColonna.setCellValueFactory(ordine -> new SimpleStringProperty(
                     ordine.getValue().getDestinatario().getNome() + " " +
                        ordine.getValue().getDestinatario().getCognome()));

        // Personalizziamo la cella e quello che vogliamo vedere
        this.destinatarioColonna.setCellFactory(destinatarioColonna -> new TableCell<>(){
            @Override
            protected void updateItem(String destinatarioNomeCognome, boolean empty){
                super.updateItem(destinatarioNomeCognome, empty);
                if(empty || destinatarioNomeCognome == null){
                    setText(null);
                } else {
                    setText(destinatarioNomeCognome);
                }
            }
        });
    }

    private void visualizzaColonnaStatoOrdine(){
        // "SimpleStringProperty" rende una stringa osservabile data una stringa
        this.statoOrdineColonna.setCellValueFactory(ordine -> new SimpleStringProperty(
                                                              String.valueOf(ordine.getValue().getPacco().getStatoPacco())));

        // Personalizziamo la cella e quello che vogliamo vedere
        this.statoOrdineColonna.setCellFactory(statoOrdineColonna -> new TableCell<>(){
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

    private void visualizzaColonnaDataDiConsegna(){
        // "SimpleStringProperty" rende una stringa osservabile data una stringa
        this.dataDiConsegnaColonna.setCellValueFactory(ordine -> new SimpleStringProperty(
                String.valueOf(ordine.getValue().getDataDiConsegna())
        ));

        // Personalizziamo la cella e quello che vogliamo vedere
        this.dataDiConsegnaColonna.setCellFactory(dataColonna -> new TableCell<>(){
            @Override
            protected void updateItem(String dataArrivoOrdine, boolean empty){
                super.updateItem(dataArrivoOrdine, empty);
                if(empty || dataArrivoOrdine == null){
                    setText(null);
                } else {
                    setText(dataArrivoOrdine);
                }
            }
        });
    }

    private void visualizzaColonnaCorriere(){
        // "SimpleStringProperty" rende una stringa osservabile data una stringa
        this.corriereColonna.setCellValueFactory(ordine -> new SimpleStringProperty(String.format("%s", ordine.getValue().getCorriereFromOrdine())));

        // Personalizziamo la cella e quello che vogliamo vedere
        this.corriereColonna.setCellFactory(corriereColonna -> new TableCell<>(){
            @Override
            protected void updateItem(String nomeCognomeCorriere, boolean empty){
                super.updateItem(nomeCognomeCorriere, empty);
                if(empty || nomeCognomeCorriere == null){
                    setText(null);
                } else {
                    setText(nomeCognomeCorriere);
                }
            }
        });
    }

    private void visualizzaColonnaAzienda(){
        // "SimpleStringProperty" rende una stringa osservabile data una stringa
        this.nomeAziendaColonna.setCellValueFactory(ordine -> new SimpleStringProperty(
                ordine.getValue().getAziendaDaOrdine().getNome()));

        // Personalizziamo la cella e quello che vogliamo vedere
        this.nomeAziendaColonna.setCellFactory(aziendaColonna -> new TableCell<>(){
            @Override
            protected void updateItem(String nomeAzienda, boolean empty){
                super.updateItem(nomeAzienda, empty);
                if(empty || nomeAzienda == null){
                    setText(null);
                } else {
                    setText(nomeAzienda);
                }
            }
        });
    }

    private void visualizzaColonnaCodicePacco(){
        // "SimpleStringProperty" rende una stringa osservabile data una stringa
        this.codicePaccoColonna.setCellValueFactory(ordine -> new SimpleStringProperty(String.valueOf(ordine.getValue().getPacco().getCodice())));

        // Personalizziamo la cella e quello che vogliamo vedere
        this.codicePaccoColonna.setCellFactory(codicePaccoColonna -> new TableCell<>(){
            @Override
            protected void updateItem(String codicePacco, boolean empty){
                super.updateItem(codicePacco, empty);
                if(empty || codicePacco == null){
                    setText(null);
                } else {
                    setText(codicePacco);
                }
            }
        });
    }

    @FXML
    public void popolaCelle(){
        visualizzaColonnaMittente();
        visualizzaColonnaDestinatario();
        visualizzaColonnaStatoOrdine();
        visualizzaColonnaDataDiConsegna();
        visualizzaColonnaAzienda();
        visualizzaColonnaCorriere();
        visualizzaColonnaCodicePacco();

        this.ordineView.setItems(MyDeliveryData.getInstance().getMittenteOrdini(this.cliente));
    }
}
