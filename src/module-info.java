/**
 * Questo modulo rappresenta il Modulo principale del progetto MyDelivery
 */
module MyDelivery {
    requires javafx.fxml;
    requires javafx.controls;

    opens com.gaetanoippolito.model;
    opens com.gaetanoippolito.model.database;
    opens com.gaetanoippolito.model.builderPattern;
    opens com.gaetanoippolito.model.observerPattern;

    opens com.gaetanoippolito.view;
    opens com.gaetanoippolito.view.fxml;
    opens com.gaetanoippolito.view.fxml.dialog;
    
    opens com.gaetanoippolito.controller;
    opens com.gaetanoippolito.controller.dialog;
}