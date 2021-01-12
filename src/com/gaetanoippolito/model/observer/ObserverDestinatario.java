package com.gaetanoippolito.model.observer;

import com.gaetanoippolito.model.StatoOrdine;

public interface ObserverDestinatario {
    void updateStatoOrdine(StatoOrdine statoOrdine);
}
