package com.gaetanoippolito.controller.dialog;

import com.gaetanoippolito.model.database.MyDeliveryData;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class RimuoviAziendaController {
    @FXML
    private TextField partitaIVADialog;

    @FXML
    public void initialize(){}

    public void processaRimozioneAzienda(){
        String partitaIVA = this.partitaIVADialog.getText().trim();

        MyDeliveryData.getInstance().cercaAzienda(partitaIVA);
    }
}
