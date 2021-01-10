package com.gaetanoippolito.controller.dialog;

import com.gaetanoippolito.model.Azienda;
import com.gaetanoippolito.model.database.MyDeliveryData;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanExpression;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

/**
 * Questa classe rappresenta il controller che gestisce la view "aggiungiAziendaDialog.fxml". Quì ci saranno tutti i
 * metodi que gestiscono l'aggiunta al database di un'azienda ed eventuali controlli su ciò che viene aggiunto.
 */
public class AggiungiAziendaController {
    @FXML
    /**@see TextField*/
    private TextField nomeAziendaDialog;
    @FXML
    /**@see TextField*/
    private TextField partitaIVADialog;

    /**
     * Metodo overridato che viene triggerato nel momento in cui viene inizializzata la view
     */
    public void initialize(){}

    /**
     * Questo metodo viene chiamato nel momento in cui, dopo che l'utente ha inserito correttamente il nome dell'azienda
     * e la partita iva, preme il tasto OK presente nel Dialog
     */
    public void processaAggiuntaAzienda(){
        // Salvo il contenuto delle TextField
        String nomeAzienda = this.nomeAziendaDialog.getText().trim();
        String partitaIva = this.partitaIVADialog.getText().trim();

        // Il metodo "controllaEsistenzaAzienda()" restituisce vero se, tramite un controllo all'interno dei file, non
        // esiste già un'azienda con la stessa partita IVA
        if(controllaEsistenzaAzienda(partitaIva)){
            Azienda nuovaAzienda = new Azienda(nomeAzienda, partitaIva);
            // Salvo nel database la nuova azienda
            MyDeliveryData.getInstance().aggiungiAzienda(nuovaAzienda);
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
        return Bindings.createBooleanBinding(() -> this.nomeAziendaDialog.getText().trim().isEmpty() ||
                                                   this.partitaIVADialog.getText().trim().isEmpty(),
                                                   this.nomeAziendaDialog.textProperty(),
                                                   this.partitaIVADialog.textProperty());
    }

    /**
     * Tramite un "for each", questo metodo controlla se la partita IVA digitata dall'utenta non coincida con la partita
     * IVA già esistente in "MyDeliveryData". Nel caso in cui non trova partite IVA uguali restituisce True.
     * @param nuovaPartitaIva rappresenta la partita IVA digitata dall'utente all'interno del TextField del dialog
     * @return ritorna vero se non ha trovato partite IVA uguali, altrimenti, se ne trova una uguale, ritorna falso.
     * @see Azienda
     */
    private boolean controllaEsistenzaAzienda(String nuovaPartitaIva){
        for(Azienda azienda : MyDeliveryData.getInstance().getAziende()){
            if(nuovaPartitaIva.equals(azienda.getPartitaIVA())){
                return false;
            }
        }

        return true;
    }
}
