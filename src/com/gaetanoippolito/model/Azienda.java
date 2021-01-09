package com.gaetanoippolito.model;

import com.gaetanoippolito.model.database.MyDeliveryData;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

public class Azienda implements Serializable {
    // id del "serialVersionUID" di default
    private static final long serialVersionUID = 1L;

    // Attributi
    private String nomeAzienda;
    private String partitaIVA;
    private ArrayList<Veicolo> veicoli;
    // TODO: Aggiungere i Corrieri

    // Costruttori
    public Azienda(String nomeAzienda, String partitaIVA, ArrayList<Veicolo> veicoli){
        this.nomeAzienda = nomeAzienda;
        this.partitaIVA = partitaIVA;
        this.veicoli = associaVeicoli(nomeAzienda);
    }

    public Azienda(String nome, String partitaIVA){
        this(nome, partitaIVA, new ArrayList<>());
    }

    // Setter e Getter
    public String getNome() {
        return this.nomeAzienda;
    }

    public void setNome(String nome) {
        this.nomeAzienda = nome;
    }

    public String getPartitaIVA() {
        return this.partitaIVA;
    }

    public void setPartitaIVA(String partitaIVA) {
        this.partitaIVA = partitaIVA;
    }

    public ArrayList<Veicolo> getVeicoli() {
        return this.veicoli;
    }

    public void setVeicoli(ArrayList<Veicolo> veicoli) {
        this.veicoli = veicoli;
    }

    // Metodi
    private ArrayList<Veicolo> associaVeicoli(String nomeAzienda){
        Random random = new Random();
        Veicolo veicoloAssociato;
        ArrayList<Veicolo> veicoliDiAzienda = new ArrayList<>();
        TipoVeicolo tipoVeicolo;
        double capienzaContainer;
        int codice;
        int maxRandom = 4;

        // Salvo un numero randomico che va da 1 a 5
        int randomNumber = (random.nextInt(maxRandom) + 1);

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

            veicoloAssociato = new Veicolo(tipoVeicolo, capienzaContainer, codice, nomeAzienda);
            MyDeliveryData.getInstance().aggiungiVeicoli(veicoloAssociato);
            veicoliDiAzienda.add(veicoloAssociato);
        }

        return veicoliDiAzienda;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Azienda)) return false;
        Azienda azienda = (Azienda) o;
        return Objects.equals(partitaIVA, azienda.partitaIVA);
    }

    @Override
    public int hashCode() {
        return Objects.hash(partitaIVA);
    }

    @Override
    public String toString() {
        return String.format("Nome azienda: %s - Partita IVA: %s - Veicoli: %s",
                              this.nomeAzienda, this.partitaIVA, this.veicoli);
    }
}
