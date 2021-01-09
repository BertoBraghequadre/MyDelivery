package com.gaetanoippolito.model;

import java.util.Random;

public enum TipoVeicolo {
    CAMION, FURGONE, AUTO;

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
