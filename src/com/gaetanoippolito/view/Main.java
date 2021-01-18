package com.gaetanoippolito.view;

import com.gaetanoippolito.model.database.MyDeliveryData;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Questa classe rappresenta l'Entry point dell'applicazione. Quì vengono gestite le scene e gli stage della finistra
 * dell'applicazione.
 * @author Gaetano Ippolito (0124001867)
 * @version 0.19.6
 * */

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("fxml/login.fxml"));

        setUserAgentStylesheet(STYLESHEET_CASPIAN);

        primaryStage.setTitle("My Delivery");
        primaryStage.setScene(new Scene(root, 550, 450));
        primaryStage.show();
    }

    @Override
    public void init() throws Exception{
        MyDeliveryData.getInstance().loadAdmins();
        MyDeliveryData.getInstance().loadAziende();
        MyDeliveryData.getInstance().loadVeicoli();
        MyDeliveryData.getInstance().loadClienti();
        MyDeliveryData.getInstance().loadCorrieri();
        MyDeliveryData.getInstance().loadOrdini();
        MyDeliveryData.getInstance().loadPacchi();
        MyDeliveryData.getInstance().loadCentroDiSmistamento();
    }

    @Override
    public void stop() throws Exception{
        MyDeliveryData.getInstance().storeAziende();
        MyDeliveryData.getInstance().storeVeicoli();
        MyDeliveryData.getInstance().storeClienti();
        MyDeliveryData.getInstance().storeCorrieri();
        MyDeliveryData.getInstance().storeOrdini();
        MyDeliveryData.getInstance().storePacchi();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
