package com.gaetanoippolito.controller;

import com.gaetanoippolito.model.Admin;
import com.gaetanoippolito.model.database.MyDeliveryData;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Optional;

/**
 * Questa
 */

public class LoginController {
    private final String rootAdminStageFile = "src/com/gaetanoippolito/view/fxml/adminStage.fxml";

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
    private Label loginErrorLabel;

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
     * Questo metodo gestisce i login in base a quale ToggleButton è stato premuto.
     */
    @FXML
    public void gestioneLogin(){
        if(toggleAdmin.isSelected()){
            String username = this.usernameLoginField.getText().trim();
            String password = this.passwordLoginField.getText().trim();

            boolean isAdmin = MyDeliveryData.getInstance().verificaLoginAdmin(username, password);

            if(isAdmin){
                this.loginErrorLabel.setVisible(false);

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
            MenuItem listaMagazzinieri = new MenuItem("listaMagazzinieri");
            MenuItem listaVeicoli = new MenuItem("listaVeicoli");
            MenuItem listaColliDaConsegnare = new MenuItem("listaColliDaConsegnare");

            // Creazione dei MenuItems per il Menu "Magazziniere" che si trova all'interno del Menu "Edit"
            MenuItem assumiMagazziniere = new MenuItem("assumiMagazziniere");
            MenuItem licenziaMagazziniere = new MenuItem("licenziaMagazziniere");

            // Creazione dei MenuItems per il Menu "Azienda" che si trova all'interno del Menu "Edit"
            MenuItem aggiungiAzienda = new MenuItem("aggiungiAzienda");
            MenuItem rimuoviAzienda = new MenuItem("rimuoviAzienda");

            // Creazione del MenuItem per il Menu "Exit"
            MenuItem exit = new MenuItem("Exit..");

            // Dichiaro i Menu
            Menu show = new Menu("Show");

            Menu edit = new Menu("Edit");
            Menu magazziniere = new Menu("Magazziniere");
            Menu azienda = new Menu("Azienda");

            Menu logout = new Menu("Logout");

            show.getItems().addAll(listaAzienda, listaMagazzinieri, listaVeicoli, listaColliDaConsegnare);
            magazziniere.getItems().addAll(assumiMagazziniere, licenziaMagazziniere);
            azienda.getItems().addAll(aggiungiAzienda, rimuoviAzienda);
            edit.getItems().addAll(magazziniere, azienda);
            logout.getItems().add(exit);

            MenuBar menuBarAdmin = new MenuBar();
            menuBarAdmin.getMenus().addAll(show, edit, logout);

            Stage stage = (Stage)loginButton.getScene().getWindow();
            stage.close();

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
