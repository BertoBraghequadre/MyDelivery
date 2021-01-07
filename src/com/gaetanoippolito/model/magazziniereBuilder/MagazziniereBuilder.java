package com.gaetanoippolito.model.magazziniereBuilder;

import com.gaetanoippolito.model.Genere;
import com.gaetanoippolito.model.magazziniereBuilder.Magazziniere;

import java.time.LocalDate;

public abstract class MagazziniereBuilder {
    // Attributi
    protected Magazziniere magazziniere;

    // Metodi astratti
    public abstract void buildNome(String nome);
    public abstract void buildCognome(String cognome);
    public abstract void buildUsername(String username);
    public abstract void buildPassword(String password);
    public abstract void buildEmail(String email);
    public abstract void buildDataDiAssunzione(LocalDate dataDiAssunzione);
    public abstract void buildEta(int eta);
    public abstract void buildSesso(Genere sesso);
    public abstract void buildCF(String cf);

    public void creaMagazziniere(){
        this.magazziniere = new Magazziniere();
    }

    public final Magazziniere getMagazziniere(){
        return this.magazziniere;
    }
}
