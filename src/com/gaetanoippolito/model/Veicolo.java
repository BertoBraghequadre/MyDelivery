package com.gaetanoippolito.model;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Classe che astrae il concetto di Veicolo.
 */

public class Veicolo {
    /**@see TipoVeicolo*/
    private TipoVeicolo tipoVeicolo;
    private double capienzaContainer;
    private String codice;
    /**@see Pacco*/
    private ArrayList<Pacco> pacchiDepositati;

    // Costruttore
    public Veicolo(TipoVeicolo tipoVeicolo, double capienzaContainer, String codice){
        this.tipoVeicolo = tipoVeicolo;
        this.capienzaContainer = capienzaContainer;
        this.codice = codice;
    }

    // Getter e Setter
    public TipoVeicolo getTipoVeicolo() {
        return tipoVeicolo;
    }

    public double getCapienzaContainer() {
        return capienzaContainer;
    }

    public String getCodice() {
        return codice;
    }

    public ArrayList<Pacco> getPacchiDepositati() {
        return pacchiDepositati;
    }

    public void setPacchiDepositati(ArrayList<Pacco> pacchiDepositati) {
        this.pacchiDepositati = pacchiDepositati;
    }

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
        return String.format("Tipo veicolo: %s\n" +
                             "Capienza del container: %s\n" +
                             "Codice veicolo: %s\n" +
                             "Pacchi depositati: %s", this.tipoVeicolo, this.capienzaContainer,
                                                      this.codice, this.pacchiDepositati.size());
    }
}
