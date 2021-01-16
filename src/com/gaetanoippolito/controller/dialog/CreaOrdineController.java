package com.gaetanoippolito.controller.dialog;

import com.gaetanoippolito.model.*;
import com.gaetanoippolito.model.database.MyDeliveryData;
import com.gaetanoippolito.model.observerPattern.Destinatario;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanExpression;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class CreaOrdineController {
    private Cliente mittente;

    @FXML
    private ChoiceBox<Azienda> aziendeChoiceBox;

    @FXML
    private TextField nomeTextField;
    @FXML
    private TextField cognomeTextField;
    @FXML
    private TextField indirizzoTextField;
    @FXML
    private TextField cfTextField;
    @FXML
    private TextField numeroDiTelefonoTextField;

    @FXML
    public void initialize(){
        this.aziendeChoiceBox.setItems(MyDeliveryData.getInstance().getAziende());
    }

    public void setMittente(Cliente mittente){
        this.mittente = mittente;
    }

    public Cliente getMittente(){
        return this.mittente;
    }

    public boolean processaCreazioneOrdine(){
        if(MyDeliveryData.getInstance().getCorrieriDisponibili(this.aziendeChoiceBox.getValue()) == null ||
           MyDeliveryData.getInstance().getVeicoloAziendaNotBusy(this.aziendeChoiceBox.getValue()) == null){
            return false;
        }
        else{
            String nomeDestinatario = this.nomeTextField.getText().trim();
            String cognomeTextField = this.cognomeTextField.getText().trim();
            String indirizzoTextField = this.indirizzoTextField.getText().trim();
            String cfTextField = this.cfTextField.getText().trim();
            String numeroDiTelefonoTextField = this.numeroDiTelefonoTextField.getText().trim();

            Destinatario destinatario = new Destinatario(nomeDestinatario, cognomeTextField, indirizzoTextField,
                    cfTextField, numeroDiTelefonoTextField);

            Ordine ordine = this.mittente.creaOrdine(this.mittente, destinatario, this.aziendeChoiceBox.getValue());
            ordine.setPacco(new Pacco(mittente, destinatario, ordine.generaPeso(), ordine.generaFragile()));
            Pacco pacco = ordine.getPacco();

            MyDeliveryData.getInstance().aggiungiPacco(pacco);
            MyDeliveryData.getInstance().aggiungiOrdine(ordine);

            return true;
        }

    }

    /**
     * Questo metodo serve a disabilitare l'OK button se i textField sono vuoti, in maniera dinamica
     * @return ritorna una espressione booleana osservabile
     */
    public BooleanExpression disabilitaOkButton() {
        // "createBooleanBinding" accetta una funzione lamba che rappresenta la nostra espressione booleana
        // e le proprietà osservabili.
        // Ritorna una espressione booleana ad ogni cambiamento del testo dei vari TextField
        // "textProperty" ritorna il testo corrente
        /**@see Bindings*/
        return Bindings.createBooleanBinding(() -> this.aziendeChoiceBox.getValue() == null ||
                                                   this.nomeTextField.getText().trim().isEmpty() ||
                                                   this.cognomeTextField.getText().trim().isEmpty() ||
                                                   this.indirizzoTextField.getText().trim().isEmpty() ||
                                                   this.cfTextField.getText().trim().isEmpty() ||
                                                   this.numeroDiTelefonoTextField.getText().trim().isEmpty(),
                                                   this.aziendeChoiceBox.valueProperty(),
                                                   this.nomeTextField.textProperty(),
                                                   this.cognomeTextField.textProperty(),
                                                   this.indirizzoTextField.textProperty(),
                                                   this.cfTextField.textProperty(),
                                                   this.numeroDiTelefonoTextField.textProperty());
    }
}
