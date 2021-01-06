package com.gaetanoippolito.view;

import com.gaetanoippolito.model.database.MyDeliveryData;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));

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
            System.out.println("Caricamento file fallito");
        }
    }

    @Override
    public void stop() throws Exception{
        try{
            MyDeliveryData.getInstance().storeAdmins();
        } catch(IOException e){
            System.out.println("Fallimento nel salvataggio");
        }
    }


    public static void main(String[] args) {
        launch(args);
    }
}
