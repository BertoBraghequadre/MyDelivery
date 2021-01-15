package com.gaetanoippolito.controller;

import com.gaetanoippolito.model.Ordine;
import com.gaetanoippolito.model.database.MyDeliveryData;
import com.gaetanoippolito.model.observerPattern.Corriere;
import com.gaetanoippolito.model.observerPattern.ObservableCorriere;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.util.ArrayList;

public class CorriereStageController {
    private ObservableCorriere corriere;
    private ObservableList<Ordine> ordini = FXCollections.observableArrayList();

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
    private Button bottoneVisualizzaOrdini;

    @FXML
    public void initialize(){
        // Impostiamo la grandezza massima della TableView per ogni colonna
        this.spedizioneCorriereView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        this.codiceOrdineColonna.setMaxWidth(Integer.MAX_VALUE * 14D);         //14%
        this.destinazioneColonna.setMaxWidth(Integer.MAX_VALUE * 14D);         //14%
        this.scadenzaColonna.setMaxWidth(Integer.MAX_VALUE * 14D);             //14%
        this.veicoloColonna.setMaxWidth(Integer.MAX_VALUE * 14D);              //14%
        this.pesoContainerColonna.setMaxWidth(Integer.MAX_VALUE * 14D);        //14%
        this.statoOrdineColonna.setMaxWidth(Integer.MAX_VALUE * 14D);          //14%
        this.centroDiSmistamentoColonna.setMaxWidth(Integer.MAX_VALUE * 14D);  //14%

        /*
        visualizzaCodiceOrdineColonna();
        visualizzaDestinazioneColonna();
        visualizzaScadenzaColonna();
        visualizzaVeicoloColonna();
        visualizzaPesoContainerColonna();
        visualizzaStatoOrdineColonna();

         */
        // TODO: visualizzaCentroDiSmistamentoColonna();
    }

    @FXML
    public void visualizzaOrdini(){
        System.out.println(this.corriere);

        this.ordini.add(MyDeliveryData.getInstance().getOrdineDelCorriere((Corriere) this.corriere));
        this.spedizioneCorriereView.setItems(this.ordini);

        visualizzaCodiceOrdineColonna();
        visualizzaDestinazioneColonna();
        visualizzaScadenzaColonna();
        visualizzaVeicoloColonna();
        visualizzaPesoContainerColonna();
        visualizzaStatoOrdineColonna();
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

    /*
    private void visualizzaCentroDiSmistamentoColonna(){
        // "SimpleStringProperty" rende una stringa osservabile data una stringa
        this.centroDiSmistamentoColonna.setCellValueFactory(pesoContainerColonna -> new SimpleStringProperty(
                String.valueOf(pesoContainerColonna.getValue().getOrdineDelVeicolo().getCapienzaContainer())));

        // Personalizziamo la cella e quello che vogliamo vedere
        this.centroDiSmistamentoColonna.setCellFactory(pesoContainerColonna  -> new TableCell<>(){
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
    */

}
