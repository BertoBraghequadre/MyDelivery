package com.gaetanoippolito.controller.dialog;

import com.gaetanoippolito.model.Azienda;
import com.gaetanoippolito.model.database.MyDeliveryData;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanExpression;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;

public class NextFitDialogController {
    @FXML
    private ChoiceBox<Azienda> aziendeChoiceBox;

    @FXML
    public void initialize(){
        this.aziendeChoiceBox.setItems(MyDeliveryData.getInstance().getAziende());
    }

    public Azienda aziendaSelezionata(){
        return aziendeChoiceBox.getValue();
    }

    // Una BooleanExpression ritorna una espressione booleana osservabile
    public BooleanExpression disabilitaTastoOK(){
        // "createBooleanBinding" accetta una funzione lamba che rappresenta la nostra espressione booleana
        // e le proprietà osservabili.
        // Semplificando: Ritorna una espressione booleana ad ogni cambiamento del testo dei vari TextField
        // "textProperty" ritorna il testo corrente
        return Bindings.createBooleanBinding(() -> aziendeChoiceBox.getValue() == null, aziendeChoiceBox.valueProperty());
    }
}
