package com.gaetanoippolito.model.observerPattern;

import com.gaetanoippolito.model.StatoOrdine;

public interface ObserverDestinatario {
    void updateStatoOrdine(StatoOrdine statoOrdine);
}
