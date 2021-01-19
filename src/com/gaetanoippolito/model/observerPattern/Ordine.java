package com.gaetanoippolito.model.observerPattern;

import com.gaetanoippolito.model.*;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

/**
 * Questa classe rappresenta l'ordine che viene generato da un cliente. Esso è un Concrete Observer per il pattern
 * "Observer". Mantiene lo stato del soggetto osservato e notifica gli osservatori in caso di un cambio di stato.
 */
public class Ordine implements Serializable, ObservableOrdine {
    ///////////////////////////////// VARIABILI DI ISTANZA /////////////////////////////////
    // id del "serialVersionUID"
    /**@see Serializable*/
    @Serial
    private static final long serialVersionUID = 10L;

    /**@see Pacco*/
    private Pacco pacco;
    /**@see Cliente*/
    private Cliente mittente;
    /**@see LocalDate*/
    private LocalDate dataDiConsegna;
    /**@see Azienda*/
    private Azienda ordineDaAzienda;
    /**@see Corriere*/
    private Corriere ordineDelCorriere;
    /**@see Veicolo*/
    private Veicolo ordineDelVeicolo;
    private boolean presoInCarico;
    /**@see ObserverDestinatario*/
    private List<ObserverDestinatario> destinatari = new ArrayList<>();
    /**@see Stato*/
    private Stato statoPacco;

    //////////////////////////////////// COSTRUTTORE ////////////////////////////////////
    /**
     * Costruttore che non accetta parametri siccome quest'ordine viene buildato grazie al pattern Builder
     */
    public Ordine(){
        this.presoInCarico = false;
    }

    ///////////////////////////////////// GETTER /////////////////////////////////////
    /**
     * Questo metodo restituisce il Pacco dell'ordine
     * @return Ritorna il Pacco dell'ordine
     * @see Pacco
     */
    public Pacco getPacco() {
        return this.pacco;
    }

    /**
     * Questo metodo restituisce il LocalDate dell'ordine
     * @return Ritorna il LocalDate dell'ordine
     * @see LocalDate
     */
    public LocalDate getDataDiConsegna() {
        return this.dataDiConsegna;
    }

    /**
     * Questo metodo restituisce il Cliente dell'ordine
     * @return Ritorna il Cliente dell'ordine
     * @see Cliente
     */
    public Cliente getMittente(){
        return this.mittente;
    }

    /**
     * Questo metodo restituisce l'azienda dell'ordine
     * @return Ritorna l'azienda dell'ordine
     * @see Azienda
     */
    public Azienda getAziendaDaOrdine() {
        return this.ordineDaAzienda;
    }

    /**
     * Questo metodo restituisce il Veicolo dell'ordine
     * @return Ritorna il Veicolo dell'ordine
     * @see Veicolo
     */
    public Veicolo getOrdineDelVeicolo() {
        return this.ordineDelVeicolo;
    }

    /**
     * Questo metodo restituisce il Corriere dell'ordine
     * @return Ritorna il Corriere dell'ordine
     * @see Corriere
     */
    public Corriere getCorriereFromOrdine(){
        return this.ordineDelCorriere;
    }

    /**
     * Questo metodo restituisce vero se l'ordine è stato preso in carico, altrimenti ritorna false
     * @return Ritorna vero se l'ordine è stato preso in carico, altrimenti ritorna false
     */
    public boolean getPresoInCarico(){
        return this.presoInCarico;
    }

    /**
     * Questo metodo restituisce lo Stato dell'ordine
     * @return Ritorna lo Stato dell'ordine
     * @see Stato
     */
    public Stato getStatoPacco(){
        return this.statoPacco;
    }

    /**
     * Questo metodo restituisce la lista dei destinatari dell'ordine
     * @return Ritorna la lista dei destinatari dell'ordine
     * @see Destinatario
     */
    public List<ObserverDestinatario> getDestinatari() {
        return destinatari;
    }

    ///////////////////////////////////// SETTER /////////////////////////////////////
    /**
     * Questo metodo setta il Pacco dell'ordine
     * @param pacco Rappresenta il pacco dell'ordine
     * @see Pacco
     */
    public void setPacco(Pacco pacco) {
        this.pacco = pacco;
    }

    /**
     * Questo metodo setta la data di consegna dell'ordine
     * @param dataDiConsegna Rappresenta la data di consegna dell'ordine
     * @see LocalDate
     */
    public void setDataDiConsegna(LocalDate dataDiConsegna) {
        this.dataDiConsegna = dataDiConsegna;
    }

    /**
     * Questo metodo setta il Mittente dell'ordine
     * @param mittente Rappresenta il Mittente dell'ordine
     * @see Cliente
     */
    public void setMittente(Cliente mittente){
        this.mittente = mittente;
    }

    /**
     * Questo metodo setta l'azienda dell'ordine
     * @param ordineDaAzienda Rappresenta l'azienda dell'ordine
     * @see Azienda
     */
    public void setAzienda(Azienda ordineDaAzienda) {
        this.ordineDaAzienda = ordineDaAzienda;
    }

    /**
     * Questo metodo setta il Veicolo dell'ordine
     * @param ordineDelVeicolo Rappresenta il Veicolo dell'ordine
     * @see Veicolo
     */
    public void setVeicoloDiOrdine(Veicolo ordineDelVeicolo) {
        this.ordineDelVeicolo = ordineDelVeicolo;
    }

    /**
     * Questo metodo setta il Corriere dell'ordine
     * @param corriere Rappresenta il pacco dell'ordine
     * @see Corriere
     */
    public void setCorriereDiOrdine(Corriere corriere){
        this.ordineDelCorriere = corriere;
    }

    /**
     * Questo metodo fa in modo che il metodo venga preso in carico (true) o no (false)
     * @param presoInCarico Rappresenta se l'ordine deve essere preso in carico oppure no
     */
    public void setPresoInCarico(boolean presoInCarico){
        this.presoInCarico = presoInCarico;
    }

    /**
     * Questo metodo setta lo stato dell'ordine
     * @param statoPacco Rappresenta lo stato dell'ordine
     * @see Stato
     */
    public void setStatoPacco(Stato statoPacco){
        this.statoPacco = statoPacco;
        notifyObserver(statoPacco);
    }

    /**
     * Questo metodo genera un peso per il pacco che viene creato durante la creazione dell'ordine
     * @return Ritorna il peso del pacco.
     */
    public double generaPeso(){
        Random random = new Random();

        int maxSize = 80;
        return ((double) random.nextInt(maxSize)) + 0.1d;
    }

    /**
     * Questo metodo genera un boolean casuale che rappresenta se il Pacco è fragile o no
     * @return Ritorna un boolean rappresentante la fragilità del Pacco (true se è fragile; false se non è fragile)
     */
    public boolean generaFragile(){
        Random random = new Random();

        if((random.nextInt(10) + 1) <= 5){
            return true;
        }
        else{
            return false;
        }
    }

    /**
     * Metodo overridato dall'interfaccia "ObservableOrdine". Questo metodo permette ad un destinatario di osservare
     * l'ordine
     * @param destinatario Rappresenta il destinatario che deve essere notificato dei cambiamenti dell'ordine
     * @see Destinatario
     */
    @Override
    public void attach(ObserverDestinatario destinatario) {
        this.destinatari.add(destinatario);
    }

    /**
     * Metodo overridato dall'interfaccia "ObservableOrdine". Questo metodo permette ad un destinatario di rimuoversi
     * dall'osservazione dell'ordine.
     * @param destinatario Rappresenta il destinatario che deve essere rimosso dagli aggiornamenti dell'ordine
     * @see Destinatario
     */
    @Override
    public void detach(ObserverDestinatario destinatario) {
        this.destinatari.remove(destinatario);
    }

    /**
     * Questo metodo notifica tutti gli observer dei cambiamenti di stato dell'ordine
     * @param statoAggiornato Rappresenta il cambiamento di stato da notificare a tutti gli observer
     * @see Destinatario
     * @see Stato
     */
    @Override
    public void notifyObserver(Stato statoAggiornato) {
        for(ObserverDestinatario destinatario : this.destinatari){
            destinatario.update(statoAggiornato);
        }
    }

    /**
     * Questo è un metodo overridato dalla classe Object che ritorna una stringa rappresentante l'istanza della
     * classe da cui viene chiamato.
     * @return Ritorna una stringa con le informazioni dell'istanza della classe Destinatario
     * @see Object
     */
    @Override
    public String toString() {
        return "Ordine{" +
                "pacco=" + pacco +
                ", mittente=" + mittente +
                ", dataDiConsegna=" + dataDiConsegna +
                ", ordineDaAzienda=" + ordineDaAzienda +
                ", ordineDelCorriere=" + ordineDelCorriere +
                ", ordineDelVeicolo=" + ordineDelVeicolo +
                '}';
    }

    /**
     * Metodo overridato dalla classe object che ritorna vero se l'istanza che chiama il metodo è uguale all'istanza
     * della classe all'interno del parametro di input di equals.
     * @see Object
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Ordine)) return false;
        Ordine ordine = (Ordine) o;
        return presoInCarico == ordine.presoInCarico && Objects.equals(pacco, ordine.pacco) && Objects.equals(mittente, ordine.mittente) && Objects.equals(dataDiConsegna, ordine.dataDiConsegna) && Objects.equals(ordineDaAzienda, ordine.ordineDaAzienda) && Objects.equals(ordineDelCorriere, ordine.ordineDelCorriere) && Objects.equals(ordineDelVeicolo, ordine.ordineDelVeicolo) && Objects.equals(destinatari, ordine.destinatari) && Objects.equals(statoPacco, ordine.statoPacco);
    }

    /**
     * Questo metodo overridato della classe Object ritorna l'hashcode della classe
     * @return Rappresenta il codice della classe
     * @see Object
     */
    @Override
    public int hashCode() {
        return Objects.hash(pacco, mittente, dataDiConsegna, ordineDaAzienda, ordineDelCorriere, ordineDelVeicolo, presoInCarico, destinatari, statoPacco);
    }
}
