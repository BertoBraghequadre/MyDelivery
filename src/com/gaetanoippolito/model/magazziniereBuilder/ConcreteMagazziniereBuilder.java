package com.gaetanoippolito.model.magazziniereBuilder;

import com.gaetanoippolito.model.Genere;
import java.time.LocalDate;

/**
 * Questa classe costruisce il Product in base ai metodi definiti nel Builder
 */

public class ConcreteMagazziniereBuilder extends MagazziniereBuilder {
    /**
     * Questo metodo setta il nome del Magazziniere
     * @param nome rappresenta il nome in input dato dal utente al magazziniere
     */
    @Override
    public void buildNome(String nome){
        magazziniere.setNome(nome);
    }

    /**
     * Questo metodo setta il cognome del Magazziniere
     * @param cognome rappresenta il cognome in input dato dal utente al magazziniere
     */
    @Override
    public void buildCognome(String cognome){
        magazziniere.setCognome(cognome);
    }

    /**
     * Questo metodo setta l'username del Magazziniere
     * @param username rappresenta l'username in input dato dal utente al magazziniere
     */
    @Override
    public void buildUsername(String username){
        magazziniere.setUsername(username);
    }

    /**
     * Questo metodo setta la password del Magazziniere
     * @param password rappresenta la password in input data dal utente al magazziniere
     */
    @Override
    public void buildPassword(String password){
        magazziniere.setPassword(password);
    }

    /**
     * Questo metodo setta la email del Magazziniere
     * @param email rappresenta la email in input data dal utente al magazziniere
     */
    @Override
    public void buildEmail(String email){
        magazziniere.setEmail(email);
    }

    /**
     * Questo metodo setta la dataDiAssunzione del Magazziniere
     * @param dataDiAssunzione rappresenta la dataDiAssunzione in input dato dal utente al magazziniere
     * @see LocalDate
     */
    @Override
    public void buildDataDiAssunzione(LocalDate dataDiAssunzione){
        magazziniere.setDataDiAssunzione(dataDiAssunzione);
    }

    /**
     * Questo metodo setta l'età del Magazziniere
     * @param eta rappresenta l'età in input dato dal utente al magazziniere
     */
    @Override
    public void buildEta(int eta){
        magazziniere.setEta(eta);
    }

    /**
     * Questo metodo setta il genere del Magazziniere
     * @param sesso rappresenta il genere in input dato dal utente al magazziniereù
     * @see Genere
     */
    @Override
    public void buildSesso(Genere sesso){
        magazziniere.setSesso(sesso);
    }

    /**
     * Questo metodo setta il codice fiscale del Magazziniere
     * @param cf rappresenta il codice fiscale in input dato dal utente al magazziniere
     */
    @Override
    public void buildCF(String cf){
        magazziniere.setCF(cf);
    }
}
