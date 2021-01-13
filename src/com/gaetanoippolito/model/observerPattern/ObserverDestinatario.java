package com.gaetanoippolito.model.observerPattern;

import com.gaetanoippolito.model.StatoOrdine;
import java.io.Serializable;

public interface ObserverDestinatario extends Serializable {
    // id del "serialVersionUID"
    long serialVersionUID = 7L;

    void updateStatoOrdine(StatoOrdine statoOrdine);
}
