package com.gaetanoippolito.controller;

import com.gaetanoippolito.model.Ordine;
import com.gaetanoippolito.model.observerPattern.Corriere;
import com.gaetanoippolito.model.observerPattern.ObservableCorriere;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class CorriereStageController {
    private ObservableCorriere corriere;
    private Ordine ordine;

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


    }

    public void setCorriere(Corriere corriere){
        this.corriere = corriere;
        System.out.println(this.corriere);
    }

    public void setOrdiniDiCorriere(Ordine ordine){
        this.ordine = ordine;
        System.out.println(this.ordine);
    }
}
