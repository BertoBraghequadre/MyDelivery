package com.gaetanoippolito.controller.dialog;

import com.gaetanoippolito.model.observerPattern.Ordine;
import com.gaetanoippolito.model.Pacco;
import com.gaetanoippolito.model.database.MyDeliveryData;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanExpression;
import javafx.fxml.FXML;
import javafx.scene.control.*;

/**
 * Questa classe rappresenta il controller del Dialog per cercare un pacco dato il suo codice.
 */

public class TrovaPaccoController {
    ///////////////////////////////// VARIABILI DI ISTANZA /////////////////////////////////
    /**@see Pacco*/
    private Pacco pacco;

    /**@see TextField*/
    @FXML
    private TextField codicePaccoTextField;

    ////////////////////////////////////// METODI //////////////////////////////////////
    /**
     * Questo metodo restituisce il pacco dato il suo codice.
     * @param codice Rappresenta il codice del pacco da cercare.
     * @return Ritorna il Pacco dato il suo codice, se esiste.
     */
    public Pacco processaTracciamentoPacco(String codice){
        int codiceInserito = Integer.parseInt(codice);

        Pacco paccoDaCercare = MyDeliveryData.getInstance().tracciaPacco(codiceInserito);

        if(paccoDaCercare != null){
            this.pacco = paccoDaCercare;
            return this.pacco;
        }

        return null;
    }

    /**
     * Questo metodo viene utilizzato per restituire l'ordine in cui è contenuto il Pacco.
     * @return Ritorna l'ordine se e solo se il pacco esiste.
     */
    public Ordine ordineDelPacco(){
        return MyDeliveryData.getInstance().getOrdineDelPacco(processaTracciamentoPacco(this.codicePaccoTextField.getText().trim()));
    }

    /**
     * Questo metodo serve a disabilitare l'OK button se il textField è vuoto, in maniera dinamica
     * @return ritorna una espressione booleana osservabile
     */
    public BooleanExpression disabilitaOkButton() {
        // "createBooleanBinding" accetta una funzione lamba che rappresenta la nostra espressione booleana
        // e le proprietà osservabili.
        // Ritorna una espressione booleana ad ogni cambiamento del testo del TextField
        // "textProperty" ritorna il testo corrente
        /**@see Bindings*/
        return Bindings.createBooleanBinding(() -> this.codicePaccoTextField.getText().trim().isEmpty(),
                                                   this.codicePaccoTextField.textProperty());
    }
}
