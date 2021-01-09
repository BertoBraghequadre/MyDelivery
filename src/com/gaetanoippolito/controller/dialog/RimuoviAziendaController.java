package com.gaetanoippolito.controller.dialog;

import com.gaetanoippolito.model.database.MyDeliveryData;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class RimuoviAziendaController {
    @FXML
    private TextField partitaIVADialog;

    @FXML
    public void initialize(){}

    public boolean processaRimozioneAzienda(){
        String partitaIVA = this.partitaIVADialog.getText().trim();

        return MyDeliveryData.getInstance().cercaAzienda(partitaIVA);
    }
}
