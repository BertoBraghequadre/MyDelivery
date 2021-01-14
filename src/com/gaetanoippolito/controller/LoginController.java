package com.gaetanoippolito.controller;

import com.gaetanoippolito.controller.dialog.RegisterController;
import com.gaetanoippolito.controller.dialog.TrovaPaccoController;
import com.gaetanoippolito.model.Admin;
import com.gaetanoippolito.model.Cliente;
import com.gaetanoippolito.model.Ordine;
import com.gaetanoippolito.model.Pacco;
import com.gaetanoippolito.model.database.MyDeliveryData;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Optional;

/**
 * Questo Controller gestisce il login da parte dell'utente selezionando le apposite view, se le codizioni sono state
 * accettate, e cambia gli stati delle classi del model
 */
public class LoginController {
    ///////////////////////////////// VARIABILI DI ISTANZA /////////////////////////////////
    private final String rootAdminStageFile = "src/com/gaetanoippolito/view/fxml/adminStage.fxml";
    private final String rootRegisterDialogFile = "src/com/gaetanoippolito/view/fxml/dialog/registerDialog.fxml";
    private final String rootClienteStageFile = "src/com/gaetanoippolito/view/fxml/clienteStage.fxml";
    private final String rootTrovaPaccoDialogFile = "src/com/gaetanoippolito/view/fxml/dialog/trovaPaccoDialog.fxml";
    private Cliente cliente;

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
    @FXML
    private TextField cognomeLoginField;

    /**@see Label*/
    @FXML
    private Label loginErrorLabel;

    /**@see TextArea*/
    @FXML
    private TextArea informazioniPaccoTextArea;


    ////////////////////////////////////// METODI //////////////////////////////////////
    /**
     * Metodo overridato che viene triggerato nel momento in cui viene inizializzata la view
     */
    @FXML
    public void initialize(){}

    public Cliente getCliente(){
        return this.cliente;
    }

    /**
     * Questo metodo serve ad abilitare il bottone "login" e il bottone "registra" quando i vari ToggleButton sono
     * selezionati e, nel caso in cui il ToggleButton corriere fosse selezionato, permette di mostrare il TextField
     * per selezionare il cognome e cambia il PromptText del Username a Nome
     */
    @FXML
    public void attivaBottoneLogin(){
        if(this.toggleAdmin.isSelected()){
            this.loginButton.setDisable(false);
            this.registerButton.setDisable(true);
            this.cognomeLoginField.setVisible(false);
            this.usernameLoginField.setPromptText("Username");
        }
        else if(this.toggleCliente.isSelected()){
            this.loginButton.setDisable(false);
            this.registerButton.setDisable(false);
            this.cognomeLoginField.setVisible(false);
            this.usernameLoginField.setPromptText("Username");
        }
        else if(this.toggleCorriere.isSelected()){
            this.loginButton.setDisable(false);
            this.registerButton.setDisable(true);
            this.cognomeLoginField.setVisible(true);
            this.usernameLoginField.setPromptText("Nome");
        }
        else{
            this.loginButton.setDisable(true);
            this.registerButton.setDisable(true);
        }
    }

    /**
     * Questo metodo gestisce i login in base a quale ToggleButton è stato premuto.
     */
    @FXML
    public void gestioneLogin(){
        if(this.toggleAdmin.isSelected()){
            // Salvo il valore in input digitato dall'utente nei vari TextField
            String username = this.usernameLoginField.getText().trim();
            String password = this.passwordLoginField.getText().trim();

            /**@see MyDeliveryData*/
            if(MyDeliveryData.getInstance().verificaLoginAdmin(username, password)){
                this.loginErrorLabel.setVisible(false);

                // Questo è il metodo che ci permette di cambiare Stage
                vaiAdInterfacciaAdmin();
            }
            else{
                this.loginErrorLabel.setVisible(true);
                this.loginErrorLabel.setText("Username o Password Errati");
            }
        }

        if(this.toggleCliente.isSelected()){
            // Salvo il valore in input digitato dall'utente nei vari TextField
            String username = this.usernameLoginField.getText().trim();
            String password = this.passwordLoginField.getText().trim();

            try{
                this.cliente = MyDeliveryData.getInstance().loginCliente(new Cliente(username, password));

                vaiAdInterfacciaCliente();
            } catch (Exception e){
                this.loginErrorLabel.setVisible(true);
                this.loginErrorLabel.setText("Username o Password Errati");
            }
        }

        if(this.toggleCorriere.isSelected()){
            // Salvo il valore in input digitato dall'utente nei vari TextField
            String nome = this.usernameLoginField.getText().trim();
            String cognome = this.cognomeLoginField.getText().trim();
            String password = this.passwordLoginField.getText().trim();

            try{
                this.cliente = MyDeliveryData.getInstance().loginCliente(new Cliente(nome, password));

                vaiAdInterfacciaCliente();
            } catch (Exception e){
                this.loginErrorLabel.setVisible(true);
                this.loginErrorLabel.setText("Corriere non riconosciuto");
            }
        }
    }

    /**
     * Questo metodo apre una nuova finestra che permette all'utente di registrarsi sulla piattaforma.
     */
    @FXML
    public void gestisciRegistrazione(){
        RegisterController registerController;
        Dialog<ButtonType> registerDialog = new Dialog<>();
        FXMLLoader loader = new FXMLLoader();

        // Impostiamo il proprietario del Dialog che si deve aprire e il titolo del Dialog
        registerDialog.initOwner(this.loginRoot.getScene().getWindow());
        registerDialog.setTitle("Registra un Account");

        // Carichiamo il file fxml che rappresenterà la finestra con cui registrarsi
        try{
            Parent root = loader.load(new FileInputStream(rootRegisterDialogFile));
            registerDialog.getDialogPane().setContent(root);
        } catch (IOException e){
            System.out.println("File not found");
            e.printStackTrace();
        }
        registerController = loader.getController();

        // Aggiungiamo i Bottoni nel dialog
        registerDialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        registerDialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);

        // Settiamo il bottone a "non cliccabile" in base al ritorno dinamico del testo dei vari TextField
        registerDialog.getDialogPane().lookupButton(ButtonType.OK).disableProperty().bind(registerController.disabilitaTastoOK());

        // Aspettiamo l'input dell'utente
        Optional<ButtonType> result = registerDialog.showAndWait();

        if(result.isPresent() && result.get() == ButtonType.OK){
            this.loginErrorLabel.setVisible(true);
            this.loginErrorLabel.setText(registerController.aggiungiNuovoAccount());
        }
        else if(result.isPresent() && result.get() == ButtonType.CANCEL){
            this.loginErrorLabel.setVisible(true);
            this.loginErrorLabel.setText("Operazione annullata");
        }
        else{
            this.loginErrorLabel.setVisible(true);
            this.loginErrorLabel.setText("Account esistente");
        }
    }

    @FXML
    public void gestisciTracciamentoPacco(){
        TrovaPaccoController trovaPaccoController;
        Dialog<ButtonType> trovaPaccoDialog = new Dialog<>();
        FXMLLoader loader = new FXMLLoader();

        // Impostiamo il proprietario del Dialog che si deve aprire e il titolo del Dialog
        trovaPaccoDialog.initOwner(this.loginRoot.getScene().getWindow());
        trovaPaccoDialog.setTitle("Traccia un Pacco");

        // Carichiamo il file fxml che rappresenterà la finestra con cui registrarsi
        try{
            Parent root = loader.load(new FileInputStream(rootTrovaPaccoDialogFile));
            trovaPaccoDialog.getDialogPane().setContent(root);
        } catch (IOException e){
            System.out.println("File not found");
            e.printStackTrace();
        }
        trovaPaccoController = loader.getController();

        // Aggiungiamo i Bottoni nel dialog
        trovaPaccoDialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        trovaPaccoDialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);

        // Settiamo il bottone a "non cliccabile" in base al ritorno dinamico del testo dei vari TextField
        trovaPaccoDialog.getDialogPane().lookupButton(ButtonType.OK).disableProperty().bind(trovaPaccoController.disabilitaOkButton());

        // Aspettiamo l'input dell'utente
        Optional<ButtonType> result = trovaPaccoDialog.showAndWait();

        if(result.isPresent() && result.get() == ButtonType.OK){
            Pacco paccoDaMostrare = trovaPaccoController.processaTracciamentoPacco();
            Ordine ordineDaMostrare = trovaPaccoController.ordineDelPacco();

            if(paccoDaMostrare != null && ordineDaMostrare != null){
                this.loginErrorLabel.setVisible(false);
                this.informazioniPaccoTextArea.setVisible(true);
                this.informazioniPaccoTextArea.setText(String.format(
                                "Mittente: %s %s\n" +
                                "Destinatario: %s %s\n" +
                                "Data di consegna: %s\n" +
                                "Stato pacco: %s\n" +
                                "Codice pacco: %s",
                        paccoDaMostrare.getMittente().getNome(), paccoDaMostrare.getMittente().getCognome(),
                        paccoDaMostrare.getDestinatario().getNome(), paccoDaMostrare.getDestinatario().getCognome(),
                        ordineDaMostrare.getDataDiConsegna(),
                        paccoDaMostrare.getStatoPacco(), paccoDaMostrare.getCodice()));
            }
            else{
                this.informazioniPaccoTextArea.setVisible(false);
                this.loginErrorLabel.setVisible(true);
                this.loginErrorLabel.setText("Questo pacco non esiste");
            }
        }
        else{
            this.loginErrorLabel.setVisible(true);
            this.loginErrorLabel.setText("Operazione annullata");
        }
    }

    /**
     * Questo metodo viene utilizzato per aprire una nuova Finestra in cui vi è l'interfaccia grafica per l'Admin
     */
    private void vaiAdInterfacciaAdmin(){
        // Chiude la finestra del Login
        Stage stage = (Stage)loginButton.getScene().getWindow();
        stage.close();

        // Creazione di una nuova finestra
        Stage adminStage = new Stage();

        try{
            FXMLLoader loader = new FXMLLoader();
            Parent root = loader.load(new FileInputStream(rootAdminStageFile));

            adminStage.setTitle(Admin.getInstance().getNome() + " " + Admin.getInstance().getCognome());
            adminStage.setScene(new Scene(root, 800, 400));
            adminStage.show();


        } catch (IOException e){
            e.printStackTrace();
            System.out.println("Errore nel caricamento del file");
        }
    }

    private void vaiAdInterfacciaCliente(){
        // Chiude la finestra del Login
        Stage stage = (Stage)loginButton.getScene().getWindow();
        stage.close();

        // Creazione di una nuova finestra
        Stage clienteStage = new Stage();

        try{
            FXMLLoader loader = new FXMLLoader();
            ClienteStageController clienteStageController;

            Parent root = loader.load(new FileInputStream(rootClienteStageFile));

            clienteStage.setTitle(this.cliente.getNome() + " " + this.cliente.getCognome());
            clienteStage.setScene(new Scene(root, 800, 400));

            clienteStageController = loader.getController();
            clienteStageController.setCliente(this.cliente);
            clienteStageController.setOrdini(MyDeliveryData.getInstance().getMittenteOrdini(this.cliente));

            clienteStage.show();

        } catch (IOException e){
            e.printStackTrace();
            System.out.println("Errore nel caricamento del file");
        }
    }
}
