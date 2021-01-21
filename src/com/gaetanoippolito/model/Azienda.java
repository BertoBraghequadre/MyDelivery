package com.gaetanoippolito.model;

import com.gaetanoippolito.model.database.MyDeliveryData;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

/**
 * Questa Classe rappresenta l'astrazione dell'azienda con cui il cliente interagisce al momento della creazione
 * di un ordine.
 */
public class Azienda implements Serializable {
    ///////////////////////////////// VARIABILI DI ISTANZA /////////////////////////////////
    // id del "serialVersionUID" di default
    @Serial
    private static final long serialVersionUID = 1L;

    private String nomeAzienda;
    private String partitaIVA;
    /**@see Veicolo*/
    private ArrayList<Veicolo> veicoli;
    /**@see Corriere*/
    private ArrayList<Corriere> corrieri;

    //////////////////////////////////// COSTRUTTORE ////////////////////////////////////
    /**
     * Costruttore completo che setta le variabili di istanza.
     * @param nomeAzienda Rappresenta il nome dell'azienda
     * @param partitaIVA Rappresenta la partita IVA dell'azienda
     * @param veicoli Rappresenta la lista di veicoli presenti nell'azienda
     */
    public Azienda(String nomeAzienda, String partitaIVA, ArrayList<Veicolo> veicoli, ArrayList<Corriere> corrieri){
        this.nomeAzienda = nomeAzienda;
        this.partitaIVA = partitaIVA;
        this.corrieri = corrieri;

        if(veicoli.size() == 0){
            this.veicoli = associaVeicoli(this);
        }
        else{
            this.veicoli = veicoli;
        }

        this.corrieri = corrieri;
    }

    /**
     * Costruttore da utilizzare nel caso in cui non si ha una lista di Veicoli o Corrieri da passare
     * @param nomeAzienda Rappresenta il nome dell'azienda
     * @param partitaIVA Rappresenta la partita IVA dell'azienda
     */
    public Azienda(String nomeAzienda, String partitaIVA){
        this(nomeAzienda, partitaIVA, new ArrayList<>(), new ArrayList<>(MyDeliveryData.getInstance().getCorrieri()));
    }

    ///////////////////////////////////// GETTER /////////////////////////////////////
    /**
     * Metodo che restituisce il nome dell'azienda
     * @return Ritorna il nome dell'azienda
     */
    public String getNome() {
        return this.nomeAzienda;
    }

    /**
     * Metodo che restituisce la partita IVA dell'azienda
     * @return Ritorna la partita IVA dell'azienda
     */
    public String getPartitaIVA() {
        return this.partitaIVA;
    }

    /**
     * Metodo che restituisce la lista dei veicoli dell'azienda
     * @return Ritorna la lista dei veicoli dell'azienda
     * @see Veicolo
     */
    public ArrayList<Veicolo> getVeicoli() {
        return this.veicoli;
    }

    public ArrayList<Corriere> getCorrieri(){
        return this.corrieri;
    }
    ///////////////////////////////////// SETTER /////////////////////////////////////
    public void setCorrieri(Corriere corriere){
        this.corrieri.add(corriere);
    }

    ////////////////////////////////////// METODI //////////////////////////////////////
    /**
     * Metodo privato per creare dei veicoli da associare all'azienda nel momento della creazione di un'azienda.
     * @param azienda Rappresenta l'azienda da passare al costruttore del Veicolo
     * @return Ritorna un ArrayList di veicoli generati randomicamente.
     */
    private ArrayList<Veicolo> associaVeicoli(Azienda azienda){
        /**@see Random*/
        Random random = new Random();
        /**@see Veicolo*/
        Veicolo veicoloAssociato;
        ArrayList<Veicolo> veicoliDiAzienda = new ArrayList<>();
        TipoVeicolo tipoVeicolo;
        double capienzaContainer;
        int codice;
        int maxRandom = 10;

        // Salvo un numero randomico che va da 5 a 10
        int randomNumber = (random.nextInt(maxRandom) + 5);

        // Eseguo un ciclo for le cui interazioni dipendono dal numero randomico
        for(int i = 0; i < randomNumber; i++){
            tipoVeicolo = TipoVeicolo.getRandomTipoVeicolo();
            if(tipoVeicolo == TipoVeicolo.CAMION){
                capienzaContainer = 150d;
                codice = i;
            }
            else if(tipoVeicolo == TipoVeicolo.FURGONE){
                capienzaContainer = 100d;
                codice = i;
            }
            else{
                capienzaContainer = 50d;
                codice = i;
            }

            veicoloAssociato = new Veicolo(tipoVeicolo, capienzaContainer, codice, azienda);
            MyDeliveryData.getInstance().aggiungiVeicoli(veicoloAssociato);
            veicoliDiAzienda.add(veicoloAssociato);
        }

        return veicoliDiAzienda;
    }

    /**
     * Il metodo equals() che viene ereditato dalla classe Object. Serve per confrontare due oggetti, dove
     * restituisce true solo se si tratta di due riferimenti allo stesso oggetto.
     * @param o Rappresenta l'oggetto da paragonare.
     * @return Ritorna true se i due oggetti sono uguali
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Azienda)) return false;
        Azienda azienda = (Azienda) o;
        return Objects.equals(partitaIVA, azienda.partitaIVA);
    }

    /**
     * Restituisce un valore hash per un oggetto.
     * @return Rappresenta il valore hash per l'oggetto.
     */
    @Override
    public int hashCode() {
        return Objects.hash(partitaIVA);
    }

    /**
     * Restituisce una stringa con tutte le informazioni di un'istanza dell'Azienda.
     * @return Ritorna una stringa personalizzata con le informazioni di un'istanza dell'Azienda.
     */
    @Override
    public String toString() {
        return String.format("%s - Partita IVA: %s",
                              this.nomeAzienda, this.partitaIVA);
    }
}
