package com.gaetanoippolito.controller;

import com.gaetanoippolito.model.Admin;
import com.gaetanoippolito.model.database.MyDeliveryData;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

/**
 *
 */

public class LoginController {
    @FXML
    private GridPane loginRoot;

    @FXML
    private ToggleButton toggleAdmin;
    @FXML
    private ToggleButton toggleMagazziniere;
    @FXML
    private ToggleButton toggleCorriere;
    @FXML
    private ToggleButton toggleCliente;

    @FXML
    private Button loginButton;
    @FXML
    private Button registerButton;

    @FXML
    private TextField usernameLoginField;
    @FXML
    private PasswordField passwordLoginField;

    @FXML
    public void initialize(){

    }

    /**
     * Questo metodo serve ad abilitare il bottone "login" quando i vari ToggleButton sono selezionati.
     */
    @FXML
    public void attivaBottoneLogin(){
        if(toggleAdmin.isSelected() || toggleMagazziniere.isSelected() || toggleCorriere.isSelected()){
            loginButton.setDisable(false);
            registerButton.setDisable(true);
        }
        else if(toggleCliente.isSelected()){
            loginButton.setDisable(false);
            registerButton.setDisable(false);
        }
        else{
            loginButton.setDisable(true);
            registerButton.setDisable(true);
        }
    }

    /**
     *
     */
    @FXML
    public void gestioneLogin(){
        if(toggleAdmin.isSelected()){
            String username = this.usernameLoginField.getText().trim();
            String password = this.passwordLoginField.getText().trim();

            ObservableList<Admin> admin = MyDeliveryData.getInstance().getListaAdmin();

            for (Admin admins : admin) {
                if(username.equals(admins.getUsername()) && password.equals(admins.getPassword())){
                    System.out.println("CIAOOOO");
                }
            }
        }
    }

    /**
     * Questo metodo apre una nuova finestra per permettere all'utente di registrarsi all'applicazione.
     */
    @FXML
    public void gestisciRegistrazione(){
        RegisterController registerController;
        Dialog<ButtonType> registerDialog = new Dialog<>();
        FXMLLoader loader = new FXMLLoader();

        registerDialog.initOwner(this.loginRoot.getScene().getWindow());
        registerDialog.setTitle("Registra un Account");

        try{
            Parent root = loader.load(new FileInputStream("src/com/gaetanoippolito/view/registerDialog.fxml"));
            registerDialog.getDialogPane().setContent(root);
        } catch (IOException e){
            System.out.println("File not found");
            e.printStackTrace();
        }

        // Aggiungiamo i Bottoni nel dialog
        registerDialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        registerDialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);

        // Carichiamo il controller del registerDialog
        registerController = loader.getController();

        /*
        // Settiamo il bottone a "non cliccabile" in base al ritorno dinamico del testo dei vari TextField
        registerDialog.getDialogPane().lookupButton(ButtonType.OK).disableProperty().bind(registerController.invalidInputProperty());
         */

        // Aspettiamo l'input dell'utente
        Optional<ButtonType> result = registerDialog.showAndWait();
    }
}
