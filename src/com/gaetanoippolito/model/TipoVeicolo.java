package com.gaetanoippolito.model;

import java.util.Random;

/**
 * Enum contenente le varie tipologie di un Veicolo
 */
public enum TipoVeicolo {
    ////////////////////////////////////// CASE //////////////////////////////////////
    CAMION, FURGONE, AUTO;

    ////////////////////////////////////// METODI //////////////////////////////////////

    /**
     * Metodo statico che ritorna un case randomico dell'enum
     * @return Ritorna un case dell'enum
     */
    public static TipoVeicolo getRandomTipoVeicolo(){
        Random random = new Random();
        int randomMax = 2;

        if(random.nextInt(randomMax) == 0){
            return CAMION;
        }
        else if(random.nextInt(randomMax) == 1){
            return FURGONE;
        }
        else{
            return AUTO;
        }
    }
}
