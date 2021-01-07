package com.gaetanoippolito.model.magazziniereBuilder;

import com.gaetanoippolito.model.Genere;
import com.gaetanoippolito.model.Utente;

import java.time.LocalDate;

/**
 * Classe creata per astrarre il concetto di Magazziniere. Esso rappresenta il Product del pattern Builder
 */

public class Magazziniere extends Utente {
    // Attributi
    private int eta;
    /**@see Genere*/
    private Genere sesso;
    private String cf;
    /**@see LocalDate*/
    private LocalDate dataDiAssunzione;

    // Costruttore vuoto siccome abbiamo un pattern Builder
    public Magazziniere(){}

    // Setter e Getters
    public void setEta(int eta) {
        this.eta = eta;
    }

    public void setSesso(Genere sesso) {
        this.sesso = sesso;
    }

    public void setCF(String cf) {
        this.cf = cf;
    }

    public void setDataDiAssunzione(LocalDate dataDiAssunzione) {
        this.dataDiAssunzione = dataDiAssunzione;
    }

    public int getEta() {
        return this.eta;
    }

    public Genere getSesso() {
        return this.sesso;
    }

    public String getCF() {
        return this.cf;
    }

    public LocalDate getDATA_DI_ASSUNZIONE() {
        return this.dataDiAssunzione;
    }
}
