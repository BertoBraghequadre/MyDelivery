package com.gaetanoippolito.controller;

import com.gaetanoippolito.model.Admin;
import com.gaetanoippolito.model.database.MyDeliveryData;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Optional;

/**
 * Questo Controller gestisce il login da parte dell'utente selezionando le apposite view, se le codizioni sono state
 * accettate, e cambia gli stati delle classi del model
 */

public class LoginController {
    // Attributi
    private final String rootAdminStageFile = "src/com/gaetanoippolito/view/fxml/adminStage.fxml";

    // Variabili di istanza che rappresentano il layout del Login
    /**@see GridPane*/
    @FXML
    private GridPane loginRoot;

    /**@see ToggleButton*/
    @FXML
    private ToggleButton toggleAdmin;
    /**@see ToggleButton*/
    @FXML
    private ToggleButton toggleCorriere;
    /**@see ToggleButton*/
    @FXML
    private ToggleButton toggleCliente;

    /**@see Button*/
    @FXML
    private Button loginButton;
    /**@see Button*/
    @FXML
    private Button registerButton;

    /**@see TextField*/
    @FXML
    private TextField usernameLoginField;
    /**@see PasswordField*/
    @FXML
    private PasswordField passwordLoginField;

    /**@see Label*/
    @FXML
    private Label loginErrorLabel;

    /**
     * Metodo overridato che viene triggerato nel momento in cui viene inizializzata la view
     */
    @FXML
    public void initialize(){

    }

    /**
     * Questo metodo serve ad abilitare il bottone "login" quando i vari ToggleButton sono selezionati.
     */
    @FXML
    public void attivaBottoneLogin(){
        if(toggleAdmin.isSelected() || toggleCorriere.isSelected()){
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
     * Questo metodo gestisce i login in base a quale ToggleButton è stato premuto.
     */
    @FXML
    public void gestioneLogin(){
        if(toggleAdmin.isSelected()){
            // Salvo il valore in input digitato dall'utente nei vari TextField
            String username = this.usernameLoginField.getText().trim();
            String password = this.passwordLoginField.getText().trim();

            /**@see MyDeliveryData*/
            boolean isAdmin = MyDeliveryData.getInstance().verificaLoginAdmin(username, password);

            if(isAdmin){
                this.loginErrorLabel.setVisible(false);

                // Questo è il metodo che ci permette di cambiare Stage
                vaiAdInterfacciaAdmin();
            }
            else{
                this.loginErrorLabel.setVisible(true);
                this.loginErrorLabel.setText("Username o Password Errati");
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

    /**
     * Questo metodo viene utilizzato per aprire una nuova Finestra in cui vi è l'interfaccia grafica per l'Admin
     */
    private void vaiAdInterfacciaAdmin(){
        try{
            // Creazione dei MenuItems per il Menu "show"
            MenuItem listaAzienda = new MenuItem("Lista Azienda");
            MenuItem listaVeicoli = new MenuItem("Lista Veicoli");
            MenuItem listaColliDaConsegnare = new MenuItem("Lista Colli Da Consegnare");

            // Creazione dei MenuItems per il Menu "Azienda" che si trova all'interno del Menu "Edit"
            MenuItem aggiungiAzienda = new MenuItem("Aggiungi Azienda");
            MenuItem rimuoviAzienda = new MenuItem("Rimuovi Azienda");

            // Creazione del MenuItem per il Menu "Exit"
            MenuItem exit = new MenuItem("Exit..");

            // Dichiaro i Menu
            Menu show = new Menu("Show");

            Menu edit = new Menu("Edit");
            Menu azienda = new Menu("Azienda");

            Menu logout = new Menu("Logout");

            // Associo i MenuItems ai Menu
            // Show -> Lista Azienda, Lista Veicoli, Lista Colli Da Consegnare
            show.getItems().addAll(listaAzienda, listaVeicoli, listaColliDaConsegnare);
            // Azienda -> Aggiungi Azienda, Rimuovi Azienda
            azienda.getItems().addAll(aggiungiAzienda, rimuoviAzienda);
            // Edit -> Azienda
            edit.getItems().addAll(azienda);
            // Logout -> Exit..
            logout.getItems().add(exit);

            // Aggiungo al MenuBar tutti i Menu con i MenuItems associati
            MenuBar menuBarAdmin = new MenuBar();
            menuBarAdmin.getMenus().addAll(show, edit, logout);

            // Chiude la finestra del Login
            Stage stage = (Stage)loginButton.getScene().getWindow();
            stage.close();

            // Creazione di una nuova finestra
            Stage adminStage = new Stage();

            VBox adminVBox = new VBox();
            adminVBox.getChildren().add(menuBarAdmin);

            FXMLLoader loader = new FXMLLoader();
            Parent root = loader.load(new FileInputStream(rootAdminStageFile));
            adminVBox.getChildren().add(root);

            adminStage.setTitle(Admin.getInstance().getNome() + " " + Admin.getInstance().getCognome());
            adminStage.setScene(new Scene(adminVBox, 800, 600));
            adminStage.show();
        } catch (IOException e){
            System.out.println("Errore nel caricamento del File fxml");
        }
    }
}
