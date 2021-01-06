package com.gaetanoippolito.controller;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanExpression;

public class RegisterController {

    /*
    // Una BooleanExpression ritorna una espressione booleana osservabile
    public BooleanExpression invalidInputProperty(){
        // "createBooleanBinding" accetta una funzione lamba che rappresenta la nostra espressione booleana
        // e le proprietà osservabili.
        // Semplificando: Ritorna una espressione booleana ad ogni cambiamento del testo dei vari TextField
        // "textProperty" ritorna il testo corrente
        return Bindings.createBooleanBinding(() -> this.newFirstName.getText().trim().isEmpty() ||
                                                   this.newLastName.getText().trim().isEmpty() ||
                                                   this.newPhoneNumber.getText().trim().isEmpty(),
                                                   this.newFirstName.textProperty(),
                                                   this.newLastName.textProperty(),
                                                   this.newPhoneNumber.textProperty());
    }
    */
}


