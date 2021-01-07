/**
 * Questo modulo rappresenta il Modulo principale del progetto MyDelivery
 */
module MyDelivery {
    requires javafx.fxml;
    requires javafx.controls;

    opens com.gaetanoippolito.model;
    opens com.gaetanoippolito.model.database;
    opens com.gaetanoippolito.model.magazziniereBuilder;
    opens com.gaetanoippolito.view;
    opens com.gaetanoippolito.controller;
}