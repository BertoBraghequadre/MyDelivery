package com.gaetanoippolito.controller.dialog;

import com.gaetanoippolito.model.CentroDiSmistamento;
import com.gaetanoippolito.model.Corriere;
import com.gaetanoippolito.model.StatoPacco;
import com.gaetanoippolito.model.database.MyDeliveryData;
import com.gaetanoippolito.model.observerPattern.Ordine;
import com.gaetanoippolito.model.observerPattern.Stato;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.util.StringConverter;

import java.io.IOException;

public class AggiornaStatoOrdineDialogController {
    @FXML
    private ComboBox<CentroDiSmistamento> aggiornaStatoComboBox;

    @FXML
    public void initialize(){
        this.aggiornaStatoComboBox.setItems(MyDeliveryData.getInstance().getCentriDiSmistamento());

        this.aggiornaStatoComboBox.setCellFactory(param -> new ListCell<>() {
            @Override
            protected void updateItem(CentroDiSmistamento centroDiSmistamento, boolean empty){
                super.updateItem(centroDiSmistamento, empty);
                if(centroDiSmistamento == null || empty){
                    setText(null);
                } else {
                    setText(generaStringa(centroDiSmistamento));
                }
            }
        });

        this.aggiornaStatoComboBox.setConverter(new StringConverter<>() {
            @Override
            public String toString(CentroDiSmistamento centroDiSmistamento) {
                if(centroDiSmistamento == null){
                    return null;
                } else {
                    return generaStringa(centroDiSmistamento);
                }
            }

            @Override
            public CentroDiSmistamento fromString(String s) {
                return null;
            }
        });

        this.aggiornaStatoComboBox.getSelectionModel().selectFirst();
    }

    private String generaStringa(CentroDiSmistamento centroDiSmistamento){
        return String.format("%d - %s",  MyDeliveryData.getInstance().getCentriDiSmistamento().indexOf(centroDiSmistamento) + 1,
                centroDiSmistamento.getNomeCentroDiSmistamento());
    }

    public void processaCambiaStatoOrdine(Corriere corriere){
        System.out.println("===============================");
        System.out.println(MyDeliveryData.getInstance().getCorrieri());
        System.out.println("===============================");

        for(Ordine ordine : MyDeliveryData.getInstance().getOrdineDelCorriere(corriere)){
            ordine.setStatoPacco(new Stato(StatoPacco.IN_TRANSITO, this.aggiornaStatoComboBox.getValue().getNomeCentroDiSmistamento()));
        }

        try{
            MyDeliveryData.getInstance().storeOrdini();
        } catch (IOException e){
            System.out.println("Errore nel salvataggio dell'ordine");
            e.printStackTrace();
        }
    }
}
