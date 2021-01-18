package com.gaetanoippolito.model.observerPattern;

import com.gaetanoippolito.model.Persona;
import com.gaetanoippolito.model.StatoPacco;
import java.io.Serial;
import java.io.Serializable;

public class Destinatario extends Persona implements Serializable, ObserverDestinatario {
    ///////////////////////////////// VARIABILI DI ISTANZA /////////////////////////////////
    // id del "serialVersionUID"
    @Serial
    private static final long serialVersionUID = 9L;
    private Stato statoPacco;
    private Ordine ordine;

    public Destinatario(String nome, String cognome, String indirizzo, String numeroDiTelefono, String cf,
                        Stato statoPacco){
        super(nome, cognome, indirizzo, numeroDiTelefono, cf);

        this.statoPacco = statoPacco;
    }

    public Destinatario(String nome, String cognome, String indirizzo, String numeroDiTelefono, String cf){
        this(nome, cognome, indirizzo, numeroDiTelefono, cf, new Stato(StatoPacco.IN_PREPARAZIONE, "Deposito"));
    }

    public Stato getStatoPacco() {
        return statoPacco;
    }

    public void setStatoPacco(Stato statoPacco) {
        this.statoPacco = statoPacco;
    }

    @Override
    public void update(Stato statoAggiornato){
        this.statoPacco = statoAggiornato;
    }

    @Override
    public String toString() {
        return "Persona{" +
                "nome='" + super.getNome() + '\'' +
                ", cognome='" + super.getCognome() + '\'' +
                ", indirizzo='" + super.getIndirizzo() + '\'' +
                ", numeroDiTelefono='" + super.getNumeroDiTelefono() + '\'' +
                ", cf='" + super.getCf() + '\'' +
                ", Stato Ordine='" + this.statoPacco + '\'' +
                '}';
    }
}
