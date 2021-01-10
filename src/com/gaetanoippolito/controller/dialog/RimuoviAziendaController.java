package com.gaetanoippolito.controller.dialog;

import com.gaetanoippolito.model.database.MyDeliveryData;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

/**
 * Questa classe rappresenta il controller che gestisce la view "rimuoviAziendaDialog.fxml". Quì ci saranno tutti i
 * metodi que gestiscono la rimozione di un'azienda ed eventuali controlli su ciò che viene rimosso.
 */
public class RimuoviAziendaController {
    @FXML
    /**@see TextField*/
    private TextField partitaIVADialog;

    @FXML
    /**
     * Metodo overridato che viene triggerato nel momento in cui viene inizializzata la view
     */
    public void initialize(){}

    /**
     * Questo metodo viene chiamato quando l'utente preme sul tasto OK del "rimuoviAziendaDialog.fxml".
     * Dopo che viene salvata la partita IVA presente nel TextField del "rimuoviAziendaDialog.fxml", viene interrogato
     * "MyDeliveryData" per la rimozione dell'azienda. Il metodo ritorna true se l'azienda è stata rimossa, altrimenti
     * ritorna false.
     * @return Ritorna true se l'azienda è stata rimossa, altrimenti ritorna falso.
     */
    public boolean processaRimozioneAzienda(){
        String partitaIVA = this.partitaIVADialog.getText().trim();

        /**@see MyDeliveryData*/
        return MyDeliveryData.getInstance().cercaAzienda(partitaIVA);
    }
}
