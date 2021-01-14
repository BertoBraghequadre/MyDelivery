package com.gaetanoippolito.controller.dialog;

import com.gaetanoippolito.model.Ordine;
import com.gaetanoippolito.model.Pacco;
import com.gaetanoippolito.model.database.MyDeliveryData;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanExpression;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class TrovaPaccoController {
    private Pacco pacco;
    @FXML
    private TextField codicePaccoTextField;

    @FXML
    public Pacco processaTracciamentoPacco(){
        int codiceInserito = Integer.parseInt(this.codicePaccoTextField.getText().trim());

        Pacco paccoDaCercare = MyDeliveryData.getInstance().tracciaPacco(codiceInserito);

        if(paccoDaCercare != null){
            this.pacco = paccoDaCercare;
            return this.pacco;
        }

        return null;
    }

    public Ordine ordineDelPacco(){
        return MyDeliveryData.getInstance().getOrdineDelPacco(this.pacco);
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
