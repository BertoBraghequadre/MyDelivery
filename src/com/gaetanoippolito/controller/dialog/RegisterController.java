package com.gaetanoippolito.controller.dialog;

import com.gaetanoippolito.model.Cliente;
import com.gaetanoippolito.model.database.MyDeliveryData;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanExpression;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class RegisterController {
    @FXML
    private TextField nomeTextField;
    @FXML
    private TextField cognomeTextField;
    @FXML
    private TextField usernameTextField;
    @FXML
    private TextField passwordTextField;
    @FXML
    private TextField emailTextField;
    @FXML
    private TextField indirizzoTextField;
    @FXML
    private TextField cfTextField;
    @FXML
    private TextField numeroDiTelefonoTextField;


    public String aggiungiNuovoAccount(){
        String nome = this.nomeTextField.getText().trim();
        String cognome = this.cognomeTextField.getText().trim();
        String username = this.usernameTextField.getText().trim();
        String password = this.passwordTextField.getText().trim();
        String email = this.emailTextField.getText().trim();
        String indirizzo = this.indirizzoTextField.getText().trim();
        String cf = this.cfTextField.getText().trim();
        String numeroDiTelefono = this.numeroDiTelefonoTextField.getText().trim();

        Cliente cliente = new Cliente(username, password, nome, cognome, email, indirizzo, cf, numeroDiTelefono);
        if(MyDeliveryData.getInstance().aggiungiCliente(cliente)){
            return "Account Registrato!";
        }
        else{
            return "Account già esistente";
        }
    }

    // Una BooleanExpression ritorna una espressione booleana osservabile
    public BooleanExpression disabilitaTastoOK(){
        // "createBooleanBinding" accetta una funzione lamba che rappresenta la nostra espressione booleana
        // e le proprietà osservabili.
        // Semplificando: Ritorna una espressione booleana ad ogni cambiamento del testo dei vari TextField
        // "textProperty" ritorna il testo corrente
        return Bindings.createBooleanBinding(() -> this.nomeTextField.getText().trim().isEmpty() ||
                                                   this.cognomeTextField.getText().trim().isEmpty() ||
                                                   this.usernameTextField.getText().trim().isEmpty() ||
                                                   this.passwordTextField.getText().trim().isEmpty() ||
                                                   this.emailTextField.getText().trim().isEmpty() ||
                                                   this.indirizzoTextField.getText().trim().isEmpty() ||
                                                   this.cfTextField.getText().trim().isEmpty() ||
                                                   this.numeroDiTelefonoTextField.getText().trim().isEmpty(),
                                                   this.nomeTextField.textProperty(),
                                                   this.cognomeTextField.textProperty(),
                                                   this.usernameTextField.textProperty(),
                                                   this.passwordTextField.textProperty(),
                                                   this.emailTextField.textProperty(),
                                                   this.indirizzoTextField.textProperty(),
                                                   this.cfTextField.textProperty(),
                                                   this.numeroDiTelefonoTextField.textProperty());
    }
}


