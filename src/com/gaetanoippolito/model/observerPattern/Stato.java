package com.gaetanoippolito.model.observerPattern;

import com.gaetanoippolito.model.StatoPacco;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

public class Stato implements Serializable {
    // id del "serialVersionUID"
    @Serial
    private static final long serialVersionUID = 14L;
    private StatoPacco statoOrdine;
    private String posizione;

    public Stato(StatoPacco statoOrdine, String posizione) {
        this.statoOrdine = statoOrdine;
        this.posizione = posizione;
    }

    public StatoPacco getStatoOrdine() {
        return statoOrdine;
    }

    public void setStatoOrdine(StatoPacco statoOrdine) {
        this.statoOrdine = statoOrdine;
    }

    public String getPosizione() {
        return posizione;
    }

    public void setPosizione(String posizione) {
        this.posizione = posizione;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Stato)) return false;
        Stato stato = (Stato) o;
        return statoOrdine == stato.statoOrdine && Objects.equals(posizione, stato.posizione);
    }

    @Override
    public int hashCode() {
        return Objects.hash(statoOrdine, posizione);
    }

    @Override
    public String toString() {
        return "Stato{" +
                "statoOrdine=" + statoOrdine +
                ", posizione='" + posizione + '\'' +
                '}';
    }
}
