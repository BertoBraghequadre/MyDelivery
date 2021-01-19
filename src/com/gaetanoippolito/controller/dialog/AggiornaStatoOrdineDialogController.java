package com.gaetanoippolito.controller.dialog;

import com.gaetanoippolito.model.CentroDiSmistamento;
import com.gaetanoippolito.model.Corriere;
import com.gaetanoippolito.model.StatoOrdine;
import com.gaetanoippolito.model.database.MyDeliveryData;
import com.gaetanoippolito.model.observerPattern.Ordine;
import com.gaetanoippolito.model.observerPattern.Stato;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.util.StringConverter;
import java.io.IOException;

/**
 * Questa classe rappresenta il Controller del Dialog per aggiornare lo stato degli ordini associati ad un
 * Cliente.
 */

public class AggiornaStatoOrdineDialogController {
    ///////////////////////////////// VARIABILI DI ISTANZA /////////////////////////////////
    /**@see ComboBox*/
    @FXML
    private ComboBox<CentroDiSmistamento> aggiornaStatoComboBox;

    ////////////////////////////////////// METODI //////////////////////////////////////
    /**
     * Metodo overridato che viene triggerato nel momento in cui viene inizializzata la view. Quì dentro si popolano
     * gli items della Combobox con i dati dei Centri di Smistamento.
     */
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

    /**
     * Metodo che genera una stringa formattata.
     * @param centroDiSmistamento Rappresenta il centro di smistamento di cui vogliamo mostrare le informazioni in un
     *                            particolare formato.
     * @return Ritorna la stringa formattata.
     */
    private String generaStringa(CentroDiSmistamento centroDiSmistamento){
        return String.format("%d - %s",  MyDeliveryData.getInstance().getCentriDiSmistamento().indexOf(centroDiSmistamento) + 1,
                centroDiSmistamento.getNomeCentroDiSmistamento());
    }

    /**
     * Questo metodo si triggera solo quando il tasto OK è stato Cliccato dal Corriere. Il suo scopo è quello di
     * cambiare lo stato di ogni ordine che fanno parte del Corriere.
     * @param corriere Rappresenta il corriere che cambia lo stato dei suoi ordini
     */
    public void processaCambiaStatoOrdine(Corriere corriere){
        for(Ordine ordine : MyDeliveryData.getInstance().getOrdineDelCorriere(corriere)){
            ordine.setStatoPacco(new Stato(StatoOrdine.IN_TRANSITO, this.aggiornaStatoComboBox.getValue().getNomeCentroDiSmistamento()));
        }

        try{
            MyDeliveryData.getInstance().storeOrdini();
        } catch (IOException e){
            System.out.println("Errore nel salvataggio dell'ordine");
            e.printStackTrace();
        }
    }
}
