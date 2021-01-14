package com.gaetanoippolito.model.observerPattern;

import com.gaetanoippolito.model.StatoPacco;
import java.io.Serializable;

public interface ObservableCorriere extends Serializable {
    // id del "serialVersionUID"
    long serialVersionUID = 7L;

    void aggiungiDestinatario(Destinatario destinatario);
    void rimuoviDestinatario(Destinatario destinatario);
    void notificaDestintari(StatoPacco statoPacco);
}
