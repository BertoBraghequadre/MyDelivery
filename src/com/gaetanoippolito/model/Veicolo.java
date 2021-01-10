package com.gaetanoippolito.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

/**
 * Classe che astrae il concetto di Veicolo.
 */

public class Veicolo implements Serializable {
    ///////////////////////////////// VARIABILI DI ISTANZA /////////////////////////////////

    // id del "serialVersionUID"
    private static final long serialVersionUID = 2L;

    /**@see TipoVeicolo*/
    private TipoVeicolo tipoVeicolo;
    private double capienzaContainer;
    private int codice;
    private String aziendaAssociata;
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
    }

    /**
     * Costruttore di Veicolo associato ad un'azienda.
     * @param tipoVeicolo Rappresenta la tipologia del veicolo
     * @param capienzaContainer Rappresenta la capienza del container del veicolo
     * @param codice Rappresenta il codice del veicolo
     * @param aziendaAssociata Rappresenta l'azienda associata al veicolo
     * @see Azienda
     */
    public Veicolo(TipoVeicolo tipoVeicolo, double capienzaContainer, int codice, String aziendaAssociata){
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
    public String getAziendaAssociata(){
        return this.aziendaAssociata;
    }

    ///////////////////////////////////// SETTER /////////////////////////////////////

    /**
     * Metodo che setta la ArrayList di pacchi con uno dato in input
     * @param pacchiDepositati Rappresenta un ArrayList di pacchi
     */
    public void setPacchiDepositati(ArrayList<Pacco> pacchiDepositati) {
        this.pacchiDepositati = pacchiDepositati;
    }

    ////////////////////////////////////// METODI //////////////////////////////////////

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
        return Objects.equals(codice, veicolo.codice);
    }

    /**
     * Restituisce un valore hash per un oggetto.
     * @return Rappresenta il valore hash per l'oggetto.
     */
    @Override
    public int hashCode() {
        return Objects.hash(codice);
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
                             "Pacchi depositati: %s\n\t\t\t\t\t\t\t\t\t\t\t\t\t",
                             this.tipoVeicolo, this.capienzaContainer,
                             this.codice, this.aziendaAssociata, this.pacchiDepositati);
    }
}
