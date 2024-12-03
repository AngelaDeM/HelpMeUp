package com.example.helpmeup.model;

import jakarta.persistence.*;

import java.util.ArrayList;

@Entity
public class Premio{

    @Id
    private  String nome;
    private  String descrizione;
    private int puntiRichiesti;
    @ManyToMany(mappedBy = "premi")
    private ArrayList<Volontario> volontari;

    public Premio() {
    }

    public Premio( String nome, String descrizione, int puntiRiscossione) {
        this.nome = nome;
        this.descrizione = descrizione;
        this.puntiRichiesti = puntiRichiesti;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public int getPuntiRichiesti() {
        return puntiRichiesti;
    }

    public void setPuntiRichiesti(int puntiRichiesti) {
        this.puntiRichiesti = puntiRichiesti;
    }
}
