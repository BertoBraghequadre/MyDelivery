package com.gaetanoippolito.controller.dialog;

import com.gaetanoippolito.model.Azienda;
import com.gaetanoippolito.model.database.MyDeliveryData;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanExpression;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;

/**
 * Questa classe rappresenta il controller del dialog che permette l'utilizzo dell'algortimo NextFit.
 */

public class NextFitDialogController {
    ///////////////////////////////// VARIABILI DI ISTANZA /////////////////////////////////
    /**@see ChoiceBox*/
    @FXML
    private ChoiceBox<Azienda> aziendeChoiceBox;

    ////////////////////////////////////// METODI //////////////////////////////////////
    /**
     * Metodo overridato che viene triggerato nel momento in cui viene inizializzata la view. Questo metodo inoltre
     * setta gli items della choiceBox di aziende.
     */
    @FXML
    public void initialize(){
        this.aziendeChoiceBox.setItems(MyDeliveryData.getInstance().getAziende());
    }

    /**
     * Questo metodo ritorna l'Azienda selelezionata dall'utente con cui processare l'algoritmo NextFit
     * @return Ritorna l'azienda selezionata dall'utente
     */
    public Azienda aziendaSelezionata(){
        return aziendeChoiceBox.getValue();
    }

    /**
     * Questo metodo serve a disabilitare l'OK button se l'azienda non è stata selezionata in maniera dinamica
     * @return ritorna una espressione booleana osservabile
     */
    // Una BooleanExpression ritorna una espressione booleana osservabile
    public BooleanExpression disabilitaTastoOK(){
        // "createBooleanBinding" accetta una funzione lamba che rappresenta la nostra espressione booleana
        // e le proprietà osservabili.
        return Bindings.createBooleanBinding(() -> aziendeChoiceBox.getValue() == null, aziendeChoiceBox.valueProperty());
    }
}
