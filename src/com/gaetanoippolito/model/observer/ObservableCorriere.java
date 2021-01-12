package com.gaetanoippolito.model.observer;

import com.gaetanoippolito.model.StatoOrdine;

public interface ObservableCorriere {
    void aggiungiDestinatario(Destinatario destinatario);
    void rimuoviDestinatario(Destinatario destinatario);
    void notificaDestintari(StatoOrdine statoOrdine);
}
