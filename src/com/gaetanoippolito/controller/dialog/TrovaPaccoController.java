package com.gaetanoippolito.controller.dialog;

import com.gaetanoippolito.model.Pacco;
import com.gaetanoippolito.model.database.MyDeliveryData;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanExpression;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class TrovaPaccoController {
    @FXML
    private TextField codicePaccoTextField;

    @FXML
    public Pacco processaTracciamentoPacco(){
        int codiceInserito = Integer.parseInt(this.codicePaccoTextField.getText().trim());

        Pacco pacco = MyDeliveryData.getInstance().tracciaPacco(codiceInserito);
        if(pacco != null){
            return pacco;
        }

        return null;
    }

    public BooleanExpression disabilitaOkButton() {
        // "createBooleanBinding" accetta una funzione lamba che rappresenta la nostra espressione booleana
        // e le proprietà osservabili.
        // Ritorna una espressione booleana ad ogni cambiamento del testo dei vari TextField
        // "textProperty" ritorna il testo corrente
        /**@see Bindings*/
        return Bindings.createBooleanBinding(() -> this.codicePaccoTextField.getText().trim().isEmpty(),
                                                   this.codicePaccoTextField.textProperty());
    }
}
