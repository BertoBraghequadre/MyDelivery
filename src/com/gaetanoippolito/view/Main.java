package com.gaetanoippolito.view;

import com.gaetanoippolito.model.database.MyDeliveryData;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

/**
 * Questa classe rappresenta l'Entry point dell'applicazione. Quì vengono gestite le scene e gli stage della finistra
 * dell'applicazione.
 * @author Gaetano Ippolito (0124001867)
 * @version 0.5.1
 * */

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("fxml/login.fxml"));

        setUserAgentStylesheet(STYLESHEET_CASPIAN);

        primaryStage.setTitle("My Delivery");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }

    @Override
    public void init() throws Exception{
        try{
            MyDeliveryData.getInstance().loadAdmins();
        } catch (IOException e) {
            System.out.println("Caricamento file \"listaAdmin.txt\" fallito");
        }

        MyDeliveryData.getInstance().loadAziende();
    }

    @Override
    public void stop() throws Exception{
        MyDeliveryData.getInstance().storeAziende();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
