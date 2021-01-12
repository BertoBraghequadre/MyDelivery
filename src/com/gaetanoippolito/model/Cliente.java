package com.gaetanoippolito.model;

import com.gaetanoippolito.model.builderOrdine.BuilderOrdine;
import java.time.LocalDate;
import java.util.Random;

public class Cliente extends Utente{
    private BuilderOrdine builderOrdine;

    public Cliente(String username, String password, String nome, String cognome, String email, String indirizzo,
                   String cf, String numeroDiTelefono){

        super(username, password, nome, cognome, email, indirizzo, cf, numeroDiTelefono);
    }

    public BuilderOrdine getBuilderOrdine(){
        return this.builderOrdine;
    }

    public void setBuilderOrdine(BuilderOrdine builderOrdine){
        this.builderOrdine = builderOrdine;
    }

    public void creaOrdine(Persona destinatario, Azienda ordineDiAzienda, Veicolo veicoloDiOrdine){
        Random random = new Random();
        int maxDays = 25;
        long randomDays = random.nextInt(maxDays);

        builderOrdine.creaOrdine();
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
}
