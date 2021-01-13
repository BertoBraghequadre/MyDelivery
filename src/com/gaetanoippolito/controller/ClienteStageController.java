package com.gaetanoippolito.controller;

import com.gaetanoippolito.controller.dialog.CreaOrdineController;
import com.gaetanoippolito.model.Azienda;
import com.gaetanoippolito.model.Cliente;
import com.gaetanoippolito.model.Ordine;
import com.gaetanoippolito.model.StatoOrdine;
import com.gaetanoippolito.model.observerPattern.Corriere;
import com.gaetanoippolito.model.observerPattern.Destinatario;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Optional;

public class ClienteStageController {
    private final String rootCreaOrdineDialog = "src/com/gaetanoippolito/view/fxml/dialog/creaOrdineDialog.fxml";
    private LoginController loginController;
    private Cliente cliente;

    @FXML
    private BorderPane stageClienteBorderPane;

    @FXML
    private TableView<Ordine> ordineView;
    @FXML
    private TableColumn<Cliente, String> mittenteColonna;
    @FXML
    private TableColumn<Destinatario, String> destinatarioColonna;
    @FXML
    private TableColumn<Ordine, StatoOrdine> statoOrdineColonna;
    @FXML
    private TableColumn<Corriere, String> corriereColonna;
    @FXML
    private TableColumn<Azienda, String> nomeAziendaColonna;

    @FXML
    public void initialize(){
        // Impostiamo la grandezza massima della TableView per ogni colonna
        this.ordineView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        this.mittenteColonna.setMaxWidth(Integer.MAX_VALUE * 20D);      //20%
        this.destinatarioColonna.setMaxWidth(Integer.MAX_VALUE * 20D);  //20%
        this.statoOrdineColonna.setMaxWidth(Integer.MAX_VALUE * 20D);   //20%
        this.corriereColonna.setMaxWidth(Integer.MAX_VALUE * 20D);      //20%
        this.nomeAziendaColonna.setMaxWidth(Integer.MAX_VALUE * 20D);   //20%
    }

    public void setCliente(Cliente cliente){
        this.cliente = cliente;
    }

    public Cliente getCliente(){
        return this.cliente;
    }

    @FXML
    public void gestioneOrdineDialog(){
        CreaOrdineController creaOrdineController;
        Dialog<ButtonType> creaOrdineDialog = new Dialog<>();
        FXMLLoader loader = new FXMLLoader();

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

        // Se il tasto "OK" è stato cliccato rimuovi l'azienda, altrimenti annulla l'operazione
        if(result.isPresent() && result.get() == ButtonType.OK) {
            if(creaOrdineController.processaCreazioneOrdine()){
                System.out.println("Operazione Riuscita");
            }
            else{
                System.out.println("Corrieri o veicoli non disponibili");
            }

            // Visualizza l'ordine
        }
        else{
            System.out.println("Operazione fallita");
        }

    }
}
