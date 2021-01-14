package com.gaetanoippolito.model;

import com.gaetanoippolito.model.database.MyDeliveryData;
import com.gaetanoippolito.model.observerPattern.Destinatario;

import java.io.Serial;
import java.io.Serializable;
import java.util.Random;

public class Pacco implements Serializable {
    // id del "serialVersionUID"
    @Serial
    private static final long serialVersionUID = 12L;
    private int codice;
    private Cliente mittente;
    private Destinatario destinatario;
    private double pesoPacco;
    private boolean isFragile;
    private StatoPacco statoPacco;

    public Pacco(Cliente mittente, Destinatario destinatario, double pesoPacco, boolean isFragile){
        this.mittente = mittente;
        this.destinatario = destinatario;
        this.pesoPacco = pesoPacco;
        this.isFragile = isFragile;
        this.statoPacco = StatoPacco.IN_PREPARAZIONE;

        Random random = new Random();
        int maxRandomNumber = 56000;
        this.codice = (MyDeliveryData.getInstance().getPacchi().size() + 1) + random.nextInt(maxRandomNumber);
    }

    public int getCodice(){
        return this.codice;
    }

    public Cliente getMittente(){
        return this.mittente;
    }

    public Destinatario getDestinatario() {
        return this.destinatario;
    }

    public double getPesoPacco() {
        return this.pesoPacco;
    }

    public boolean isFragile() {
        return this.isFragile;
    }

    public StatoPacco getStatoPacco(){
        return this.statoPacco;
    }

    public void setMittente(Cliente mittente) {
        this.mittente = mittente;
    }

    public void setDestinatario(Destinatario destinatario) {
        this.destinatario = destinatario;
    }

    public void setPesoPacco(double pesoPacco) {
        this.pesoPacco = pesoPacco;
    }

    public void setFragile(boolean fragile) {
        isFragile = fragile;
    }

    public void setStatoPacco(StatoPacco statoPacco){
        this.statoPacco = statoPacco;
    }

    @Override
    public String toString() {
        return "Pacco{" +
                "codice='" + codice + '\'' +
                ", mittente=" + mittente +
                ", destinatario=" + destinatario +
                ", pesoPacco=" + pesoPacco +
                ", isFragile=" + isFragile +
                '}';
    }
}
