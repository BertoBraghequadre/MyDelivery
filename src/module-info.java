module MyDelivery {
    requires javafx.fxml;
    requires javafx.controls;

    opens com.gaetanoippolito.model;
    opens com.gaetanoippolito.model.database;
    opens com.gaetanoippolito.view;
    opens com.gaetanoippolito.controller;
}