package com.gaetanoippolito.model.observerPattern;

/**
 * Questa classe rappresenta l'interfaccia dell'observer. Espone l’interfaccia che consente di aggiornare gli
 * osservatori in caso di cambio di stato del soggetto osservato.
 */
public interface ObserverDestinatario {
    /**
     * Questo è un metodo sfruttato delle classi observable in cui si aggiorna lo stato di tutti gli observer
     * che osservano.
     * @param statoAggiornato Rappresenta lo stato aggiornato.
     */
    void update(Stato statoAggiornato);
}
