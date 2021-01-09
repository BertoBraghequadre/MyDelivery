package com.gaetanoippolito.controller.dialog;

import com.gaetanoippolito.model.Azienda;
import com.gaetanoippolito.model.database.MyDeliveryData;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanExpression;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class AggiungiAziendaController {
    @FXML
    private TextField nomeAziendaDialog;
    @FXML
    private TextField partitaIVADialog;

    public void initialize(){

    }

    public void processaAggiuntaAzienda(){
        String nomeAzienda = this.nomeAziendaDialog.getText().trim();
        String partitaIva = this.partitaIVADialog.getText().trim();

        if(controllaEsistenzaAzienda(partitaIva)){
            Azienda nuovaAzienda = new Azienda(nomeAzienda, partitaIva);
            MyDeliveryData.getInstance().aggiungiAzienda(nuovaAzienda);
        }
    }

    /**
     * Questo metodo serve a disabilitare l'OK button se i textField sono vuoti
     * @return ritorna una espressione booleana osservabile
     */
    public BooleanExpression disabilitaOkButton() {
        // "createBooleanBinding" accetta una funzione lamba che rappresenta la nostra espressione booleana
        // e le proprietà osservabili.
        // Ritorna una espressione booleana ad ogni cambiamento del testo dei vari TextField
        // "textProperty" ritorna il testo corrente
        /**@see Bindings*/
        return Bindings.createBooleanBinding(() -> this.nomeAziendaDialog.getText().trim().isEmpty() ||
                                                   this.partitaIVADialog.getText().trim().isEmpty(),
                                                   this.nomeAziendaDialog.textProperty(),
                                                   this.partitaIVADialog.textProperty());
    }

    public boolean controllaEsistenzaAzienda(String nuovaPartitaIva){
        for(Azienda azienda : MyDeliveryData.getInstance().getAziende()){
            if(nuovaPartitaIva.equals(azienda.getPartitaIVA())){
                return false;
            }
        }

        return true;
    }
}
