package com.gaetanoippolito.controller.dialog;

import com.gaetanoippolito.model.Azienda;
import com.gaetanoippolito.model.database.MyDeliveryData;
import com.gaetanoippolito.model.Corriere;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanExpression;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

import java.io.IOException;

public class RegisterCorriereController {
    @FXML
    private TextField nomeTextField;
    @FXML
    private TextField cognomeTextField;
    @FXML
    private TextField idTextField;
    @FXML
    private ChoiceBox<Azienda> aziendeChoiceBox;

    @FXML
    public void initialize(){
        this.aziendeChoiceBox.setItems(MyDeliveryData.getInstance().getAziende());
    }

    public boolean aggiungiNuovoAccount(){
        String nome = this.nomeTextField.getText().trim();
        String cognome = this.cognomeTextField.getText().trim();
        String ID = this.idTextField.getText().trim();

        Corriere corriere = new Corriere(nome, cognome, ID);

        if(MyDeliveryData.getInstance().aggiungiCorrieri(corriere)){
            this.aziendeChoiceBox.getValue().setCorrieri(corriere);
            try{
                MyDeliveryData.getInstance().storeAziende();
            } catch (IOException e){
                System.out.println("Errore nel salvataggio dell'azienda");
                e.printStackTrace();
            }

            return true;
        }
        else{
            return false;
        }
    }

    // Una BooleanExpression ritorna una espressione booleana osservabile
    public BooleanExpression disabilitaTastoOK(){
        // "createBooleanBinding" accetta una funzione lamba che rappresenta la nostra espressione booleana
        // e le proprietà osservabili.
        // Semplificando: Ritorna una espressione booleana ad ogni cambiamento del testo dei vari TextField
        // "textProperty" ritorna il testo corrente
        return Bindings.createBooleanBinding(() ->
                        this.aziendeChoiceBox.getValue() == null ||
                        this.nomeTextField.getText().trim().isEmpty() ||
                        this.cognomeTextField.getText().trim().isEmpty() ||
                        this.idTextField.getText().trim().isEmpty(),
                        this.nomeTextField.textProperty(),
                        this.cognomeTextField.textProperty(),
                        this.idTextField.textProperty(),
                        this.aziendeChoiceBox.valueProperty());
    }
}
