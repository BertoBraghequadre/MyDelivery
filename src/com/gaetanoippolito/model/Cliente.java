package com.gaetanoippolito.model;

import com.gaetanoippolito.model.builderPattern.BuilderOrdine;
import com.gaetanoippolito.model.observerPattern.Destinatario;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Random;

public class Cliente extends Utente implements Serializable {
    @Serial
    private static final long serialVersionUID = 3L;

    private String idCliente;
    private BuilderOrdine builderOrdine;

    public Cliente(String username, String password, String nome, String cognome, String email, String indirizzo,
                   String cf, String numeroDiTelefono){

        super(username, password, nome, cognome, email, indirizzo, cf, numeroDiTelefono);
    }

    public BuilderOrdine getBuilderOrdine(){
        return this.builderOrdine;
    }

    public String getIdCliente(){
        return this.idCliente;
    }

    public void setIdCliente(String idCliente){
        this.idCliente = idCliente;
    }

    public void setBuilderOrdine(BuilderOrdine builderOrdine){
        this.builderOrdine = builderOrdine;
    }

    public void creaOrdine(Destinatario destinatario, Azienda ordineDiAzienda, Veicolo veicoloDiOrdine){
        Random random = new Random();
        int maxDays = 25;
        long randomDays = random.nextInt(maxDays);

        this.builderOrdine.creaOrdine();
        this.builderOrdine.buildMittente(this);
        this.builderOrdine.buildDestinatario(destinatario);
        this.builderOrdine.buildStatoOrdine(StatoOrdine.IN_PREPARAZIONE);
        this.builderOrdine.buildDataDiConsegna(LocalDate.now().plusDays(randomDays));
        this.builderOrdine.buildAzienda(ordineDiAzienda);
        this.builderOrdine.buildVeicolo(veicoloDiOrdine);
        //this.builderOrdine.buildCorriere(Corriere corriere);
    }

    /*
    public String riceviNotificaStatoOrdine(StatoOrdine stato){
        // setterà lo stato di un ordine già creato
    }
    */

    @Override
    public String toString(){
        return String.format("Username: %s\n" +
                        "Password: %s\n" +
                        "Nome: %s\n" +
                        "Cognome: %s\n" +
                        "Email: %s\n" +
                        "ID: %s",
                super.getUsername(), super.getPassword(), super.getNome(), super.getCognome(), super.getEmail(), this.idCliente);
    }
}
