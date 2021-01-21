package com.gaetanoippolito.model;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

/**
 * Classe che astrae il concetto di Veicolo.
 */

public class Veicolo implements Serializable {
    ///////////////////////////////// VARIABILI DI ISTANZA /////////////////////////////////
    // id del "serialVersionUID"
    /**@see Serializable*/
    @Serial
    private static final long serialVersionUID = 2L;

    /**@see TipoVeicolo*/
    private TipoVeicolo tipoVeicolo;
    private double capienzaContainer;
    private int codice;
    private boolean isBusy;
    /**@see Azienda*/
    private Azienda aziendaAssociata;
    /**@see Pacco*/
    private ArrayList<Pacco> pacchiDepositati;

    //////////////////////////////////// COSTRUTTORE ////////////////////////////////////
    /**
     * Costruttore di Veicolo
     * @param tipoVeicolo Rappresenta la tipologia del veicolo
     * @param capienzaContainer Rappresenta la capienza del container del veicolo
     * @param codice Rappresenta il codice del veicolo
     */
    public Veicolo(TipoVeicolo tipoVeicolo, double capienzaContainer, int codice){
        this.tipoVeicolo = tipoVeicolo;
        this.capienzaContainer = capienzaContainer;
        this.codice = codice;
        this.isBusy = false;
    }

    /**
     * Costruttore di Veicolo associato ad un'azienda.
     * @param tipoVeicolo Rappresenta la tipologia del veicolo
     * @param capienzaContainer Rappresenta la capienza del container del veicolo
     * @param codice Rappresenta il codice del veicolo
     * @param aziendaAssociata Rappresenta l'azienda associata al veicolo
     * @see Azienda
     */
    public Veicolo(TipoVeicolo tipoVeicolo, double capienzaContainer, int codice, Azienda aziendaAssociata){
        this(tipoVeicolo, capienzaContainer, codice);
        this.aziendaAssociata = aziendaAssociata;
        this.pacchiDepositati = new ArrayList<>();
    }

    ///////////////////////////////////// GETTER /////////////////////////////////////
    /**
     * Metodo che restituisce la tipologia del veicolo
     * @return Ritorna la tipologia del veicolo
     */
    public TipoVeicolo getTipoVeicolo() {
        return this.tipoVeicolo;
    }

    /**
     * Metodo che restituisce la capienza del container del veicolo
     * @return Ritorna la capienza del container del veicolo
     */
    public double getCapienzaContainer() {
        return this.capienzaContainer;
    }

    /**
     * Metodo che restituisce il codice del veicolo
     * @return Ritorna il codice del veicolo
     */
    public int getCodice() {
        return this.codice;
    }

    /**
     * Metodo che restituisce la lista di pacchi contenuti nel veicolo
     * @return Ritorna il codice del veicolo
     */
    public ArrayList<Pacco> getPacchiDepositati() {
        return this.pacchiDepositati;
    }

    /**
     * Metodo che restituisce il nome dell'azienda associata al veicolo
     * @return Ritorna il nome dell'azienda associata al veicolo
     */
    public Azienda getAziendaAssociata(){
        return this.aziendaAssociata;
    }

    /**
     * Metodo che restituisce true se il veicolo è impegnato in un ordine, altrimenti restituisce false
     * @return Ritorna true se il veicolo è impegnato in un ordine
     */
    public boolean getIsBusy(){
        return this.isBusy;
    }

    ///////////////////////////////////// SETTER /////////////////////////////////////

    /**
     * Metodo che setta la ArrayList di pacchi con uno dato in input
     * @param pacchiDepositati Rappresenta un ArrayList di pacchi
     */
    public void setPacchiDepositati(ArrayList<Pacco> pacchiDepositati) {
        this.pacchiDepositati = pacchiDepositati;
    }

    /**
     * Metodo che setta un veicolo a impegnato
     * @param isBusy Rappresenta se il veicolo è impegnato in un ordine
     */
    public void setIsBusy(boolean isBusy){
        this.isBusy = isBusy;
    }

    /**
     * Metodo che setta la capienza massima del container dei veicoli
     * @param capienzaContainer Rappresenta la capienza massima dei container dei veicoli
     */
    public void setCapienzaContainer(double capienzaContainer) {
        this.capienzaContainer = capienzaContainer;
    }

    ////////////////////////////////////// METODI //////////////////////////////////////
    /**
     * Questo metodo restituisce il peso di un container in base a quanti pacchi sono stati depositati
     * @return Ritorna il peso dei pacchi all'interno del container del veicolo
     */
    public double getPesoInContainer(){
        double pesoDepositato = 0.0d;

        for(Pacco pacco : this.pacchiDepositati){
            pesoDepositato += pacco.getPesoPacco();
        }

        return pesoDepositato;
    }

    /**
     * Questo metodo deposita un pacco all'interno del container
     * @param pacco Rappresenta il pacco da depositare
     */
    public void depositaPacco(Pacco pacco){
        this.pacchiDepositati.add(pacco);
    }

    /**
     * Il metodo equals() che viene ereditato dalla classe Object. Serve per confrontare due oggetti, dove
     * restituisce true solo se si tratta di due riferimenti allo stesso oggetto.
     * @param o Rappresenta l'oggetto da paragonare.
     * @return Ritorna true se i due oggetti sono uguali
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Veicolo)) return false;
        Veicolo veicolo = (Veicolo) o;
        return codice == veicolo.codice && Objects.equals(aziendaAssociata, veicolo.aziendaAssociata);
    }


    /**
     * Restituisce un valore hash per un oggetto.
     * @return Rappresenta il valore hash per l'oggetto.
     */
    @Override
    public int hashCode() {
        return Objects.hash(codice, aziendaAssociata);
    }

    /**
     * Restituisce una stringa con tutte le informazioni di un'istanza dell'Azienda.
     * @return Ritorna una stringa personalizzata con le informazioni di un'istanza dell'Azienda.
     */
    @Override
    public String toString() {
        return String.format("Tipo veicolo: %s - " +
                             "Capienza del container: %s - " +
                             "Codice veicolo: %s - " +
                             "Azienda associata: %s - " +
                             "isBusy: %s - " +
                             "Pacchi depositati: %s\n",
                             this.tipoVeicolo, this.capienzaContainer,
                             this.codice, this.aziendaAssociata, this.isBusy, this.pacchiDepositati);
    }
}
