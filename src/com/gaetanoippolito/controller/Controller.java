package com.gaetanoippolito.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;

public class Controller {
    @FXML
    private ToggleButton toggleAdmin;

    @FXML
    private Button loginButton;

    @FXML
    public void initialize(){

    }

    @FXML
    public void attivaBottoneLogin(){
        if(toggleAdmin.isSelected()){
            loginButton.setDisable(false);
        }
        else{
            loginButton.setDisable(true);
        }
    }
}
