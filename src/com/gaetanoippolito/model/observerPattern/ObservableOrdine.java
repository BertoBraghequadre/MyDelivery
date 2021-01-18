package com.gaetanoippolito.model.observerPattern;

public interface ObservableOrdine {
    void attach(ObserverDestinatario destinatario);
    void detach(ObserverDestinatario destinatario);
    void notifyObserver(Stato statoAggiornato);
}
