package com.gaetanoippolito.controller.dialog;

import com.gaetanoippolito.model.*;
import com.gaetanoippolito.model.database.MyDeliveryData;
import com.gaetanoippolito.model.observerPattern.Destinatario;
import com.gaetanoippolito.model.observerPattern.Ordine;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanExpression;
import javafx.fxml.FXML;
import javafx.scene.control.*;

/**
 * Questa classe rappresenta il Controller per la creazione degli Ordini da parte di un Cliente
 */

public class CreaOrdineController {
    ///////////////////////////////// VARIABILI DI ISTANZA /////////////////////////////////
    /**@see Cliente*/
    private Cliente mittente;

    /**@see ChoiceBox*/
    @FXML
    private ChoiceBox<Azienda> aziendeChoiceBox;

    /**@see TextField*/
    @FXML
    private TextField nomeTextField;

    /**@see TextField*/
    @FXML
    private TextField cognomeTextField;

    /**@see TextField*/
    @FXML
    private TextField indirizzoTextField;

    /**@see TextField*/
    @FXML
    private TextField cfTextField;

    /**@see TextField*/
    @FXML
    private TextField numeroDiTelefonoTextField;

    ////////////////////////////////////// METODI //////////////////////////////////////
    /**
     * Metodo overridato che viene triggerato nel momento in cui viene inizializzata la view. Il suo scopo è
     * quello di riempire la ChoiceBox di aziende prese da MyDeliveryData
     */
    @FXML
    public void initialize(){
        this.aziendeChoiceBox.setItems(MyDeliveryData.getInstance().getAziende());
    }

    /**
     * Metodo che setta il Mittente (Cliente) di un ordine.
     * @param mittente Rappresenta il Cliente che genera un ordine.
     * @see Cliente
     */
    public void setMittente(Cliente mittente){
        this.mittente = mittente;
    }

    /**
     * Metodo che ritorna il Mittente (Cliente) di un ordine.
     * @return Ritorna il mittente di un ordine.
     * @see Cliente
     */
    public Cliente getMittente(){
        return this.mittente;
    }

    /**
     * Questo metodo fa in modo che l'ordine venga creato. Nel caso in cui l'azienda presso cui si vuole creare
     * un ordine non ha corrieri e/o veicoli disponibili, ritorna false. Altrimenti, tramite il valore di ritorno
     * dei vari TextFlied all'interno del Dialog, si crea un nuovo ordine con le informazioni inseriti dall'utente.
     * Queste informazioni andranno a creare l'istanza del destinatario, l'ordine e il pacco. In particolare,
     * l'ordine viene creato sfruttando il metodo "creaOrdine" del pattern "Builder".
     * @return Ritorna true se l'ordine è stato creato, altrimenti ritorna false se i veicoli e/o i corrieri non
     *         sono disponibili.
     */
    public boolean processaCreazioneOrdine(){
        if(MyDeliveryData.getInstance().getCorrieriDisponibili(this.aziendeChoiceBox.getValue()) == null ||
           MyDeliveryData.getInstance().getVeicoloAziendaNotBusy(this.aziendeChoiceBox.getValue()) == null){
            return false;
        }
        else{
            String nomeDestinatario = this.nomeTextField.getText().trim();
            String cognomeTextField = this.cognomeTextField.getText().trim();
            String indirizzoTextField = this.indirizzoTextField.getText().trim();
            String cfTextField = this.cfTextField.getText().trim();
            String numeroDiTelefonoTextField = this.numeroDiTelefonoTextField.getText().trim();

            Destinatario destinatario = new Destinatario(nomeDestinatario, cognomeTextField, indirizzoTextField,
                    cfTextField, numeroDiTelefonoTextField);

            Ordine ordine = this.mittente.creaOrdine(this.mittente, destinatario, this.aziendeChoiceBox.getValue());
            ordine.setPacco(new Pacco(mittente, destinatario, ordine.generaPeso(), ordine.generaFragile()));
            Pacco pacco = ordine.getPacco();

            MyDeliveryData.getInstance().aggiungiPacco(pacco);
            MyDeliveryData.getInstance().aggiungiOrdine(ordine);

            return true;
        }

    }

    /**
     * Questo metodo serve a disabilitare l'OK button se i textField sono vuoti, in maniera dinamica
     * @return ritorna una espressione booleana osservabile
     */
    public BooleanExpression disabilitaOkButton() {
        // "createBooleanBinding" accetta una funzione lamba che rappresenta la nostra espressione booleana
        // e le proprietà osservabili.
        // Ritorna una espressione booleana ad ogni cambiamento del testo dei vari TextField
        // "textProperty" ritorna il testo corrente
        /**@see Bindings*/
        return Bindings.createBooleanBinding(() -> this.aziendeChoiceBox.getValue() == null ||
                                                   this.nomeTextField.getText().trim().isEmpty() ||
                                                   this.cognomeTextField.getText().trim().isEmpty() ||
                                                   this.indirizzoTextField.getText().trim().isEmpty() ||
                                                   this.cfTextField.getText().trim().isEmpty() ||
                                                   this.numeroDiTelefonoTextField.getText().trim().isEmpty(),
                                                   this.aziendeChoiceBox.valueProperty(),
                                                   this.nomeTextField.textProperty(),
                                                   this.cognomeTextField.textProperty(),
                                                   this.indirizzoTextField.textProperty(),
                                                   this.cfTextField.textProperty(),
                                                   this.numeroDiTelefonoTextField.textProperty());
    }
}
