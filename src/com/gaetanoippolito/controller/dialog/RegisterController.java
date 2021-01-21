package com.gaetanoippolito.controller.dialog;

import com.gaetanoippolito.model.Cliente;
import com.gaetanoippolito.model.builderPattern.ConcreteOrdine;
import com.gaetanoippolito.model.database.MyDeliveryData;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanExpression;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

/**
 * Questa classe rappresenta il controller del Dialog per la registrazione di un Cliente.
 */

public class RegisterController {
    /**@see TextField*/
    @FXML
    private TextField nomeTextField;

    /**@see TextField*/
    @FXML
    private TextField cognomeTextField;

    /**@see TextField*/
    @FXML
    private TextField usernameTextField;

    /**@see TextField*/
    @FXML
    private TextField passwordTextField;

    /**@see TextField*/
    @FXML
    private TextField emailTextField;

    /**@see TextField*/
    @FXML
    private TextField indirizzoTextField;

    /**@see TextField*/
    @FXML
    private TextField cfTextField;

    /**@see TextField*/
    @FXML
    private TextField numeroDiTelefonoTextField;

    /**
     * Questo metodo aggiunge un cliente se e solo se il cliente che si vuole aggiungere non sia già presente
     * all'interno di MyDeliveryData.
     * @return Ritorna una Stringa che verrà utilizzata dal Label del login Controller per segnalare se il cliente
     *         è stato aggiunto oppure no.
     */
    public String aggiungiNuovoAccount(){
        String nome = this.nomeTextField.getText().trim();
        String cognome = this.cognomeTextField.getText().trim();
        String username = this.usernameTextField.getText().trim();
        String password = this.passwordTextField.getText().trim();
        String email = this.emailTextField.getText().trim();
        String indirizzo = this.indirizzoTextField.getText().trim();
        String cf = this.cfTextField.getText().trim();
        String numeroDiTelefono = this.numeroDiTelefonoTextField.getText().trim();

        Cliente cliente = new Cliente(username, password, nome, cognome, email, indirizzo, cf, numeroDiTelefono, new ConcreteOrdine());
        if(MyDeliveryData.getInstance().aggiungiCliente(cliente)){
            return "Account Registrato!";
        }
        else{
            return "Account già esistente";
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


