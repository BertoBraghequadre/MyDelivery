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

/**
 * Questa classe rappresenta il controller del Dialog per la registrazione di un corriere
 */

public class RegisterCorriereController {
    ///////////////////////////////// VARIABILI DI ISTANZA /////////////////////////////////
    /**@see TextField*/
    @FXML
    private TextField nomeTextField;

    /**@see TextField*/
    @FXML
    private TextField cognomeTextField;

    /**@see TextField*/
    @FXML
    private TextField idTextField;

    /**@see ChoiceBox*/
    @FXML
    private ChoiceBox<Azienda> aziendeChoiceBox;

    ////////////////////////////////////// METODI //////////////////////////////////////
    /**
     * Metodo overridato che viene triggerato nel momento in cui viene inizializzata la view. Inoltre setta gli
     * item della ChoiceBox di Aziende prese da MyDeliveryData.
     */
    @FXML
    public void initialize(){
        this.aziendeChoiceBox.setItems(MyDeliveryData.getInstance().getAziende());
    }

    /**
     * Questo metodo permette l'aggiunta di un nuovo corriere all'interno della piattaforma. Se il corriere che si
     * vuole aggiungere non esiste, esso verrà aggiunto ritornando true, altrimenti non verrà aggiunto e il metodo
     * ritonerà false.
     * @return Ritorna true se il nuovo corriere è stato aggiunto, altrimenti ritorna false.
     */
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

    /**
     * Questo metodo serve a disabilitare l'OK button se i textField sono vuoti, in maniera dinamica
     * @return ritorna una espressione booleana osservabile
     */
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
