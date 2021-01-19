package com.gaetanoippolito.model.observerPattern;

/**
 * Questa classe espone l’interfaccia che consente agli osservatori di iscriversi e cancellarsi.
 */
public interface ObservableOrdine {
    /**
     * Questo è il metodo che permette di attaccare un destinatario da notificare
     * @param destinatario Rappresenta il destinatario da aggiungere all'observer
     */
    void attach(ObserverDestinatario destinatario);

    /**
     * Questo è il metodo che permette di rimuovere un destinatario da notificare
     * @param destinatario Rappresenta il destinatario da rimuovere all'observer
     */
    void detach(ObserverDestinatario destinatario);

    /**
     * Questo è il metodo che permette di notificare i destinatari da notificare
     * @param statoAggiornato Rappresenta lo stato nuovo.
     */
    void notifyObserver(Stato statoAggiornato);
}
