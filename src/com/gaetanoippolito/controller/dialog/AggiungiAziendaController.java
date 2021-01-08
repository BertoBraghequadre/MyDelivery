package com.gaetanoippolito.controller.dialog;

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
        System.out.println("CIAO");
    }
}
