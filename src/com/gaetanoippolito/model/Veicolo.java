package com.gaetanoippolito.model;

import com.gaetanoippolito.model.database.MyDeliveryData;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

/**
 * Classe che astrae il concetto di Veicolo.
 */

public class Veicolo implements Serializable {
    // id del "serialVersionUID"
    private static final long serialVersionUID = 2L;

    // Attributi
    /**@see TipoVeicolo*/
    private TipoVeicolo tipoVeicolo;
    private double capienzaContainer;
    private int codice;
    private String aziendaAssociata;
    /**@see Pacco*/
    private ArrayList<Pacco> pacchiDepositati;

    // Costruttore
    public Veicolo(TipoVeicolo tipoVeicolo, double capienzaContainer, int codice){
        this.tipoVeicolo = tipoVeicolo;
        this.capienzaContainer = capienzaContainer;
        this.codice = codice;
    }

    public Veicolo(TipoVeicolo tipoVeicolo, double capienzaContainer, int codice, String aziendaAssociata){
        this(tipoVeicolo, capienzaContainer, codice);
        this.aziendaAssociata = aziendaAssociata;
        this.pacchiDepositati = new ArrayList<>();
    }

    // Getter e Setter
    public TipoVeicolo getTipoVeicolo() {
        return this.tipoVeicolo;
    }

    public double getCapienzaContainer() {
        return this.capienzaContainer;
    }

    public int getCodice() {
        return this.codice;
    }

    public ArrayList<Pacco> getPacchiDepositati() {
        return this.pacchiDepositati;
    }

    public void setPacchiDepositati(ArrayList<Pacco> pacchiDepositati) {
        this.pacchiDepositati = pacchiDepositati;
    }

    public String getAziendaAssociata(){
        return this.aziendaAssociata;
    }

    // Metodi
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Veicolo)) return false;
        Veicolo veicolo = (Veicolo) o;
        return Objects.equals(codice, veicolo.codice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codice);
    }

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
