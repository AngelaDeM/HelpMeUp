package com.example.helpmeup.model;

public class Premio {

    private static int countID=100;
    private final int id=countID++;
    private  String nome;
    private  String descrizione;
    private int puntiRiscossione;

    public Premio() {
    }

    public Premio( String nome, String descrizione, int puntiRiscossione) {
        this.nome = nome;
        this.descrizione = descrizione;
        this.puntiRiscossione = puntiRiscossione;
    }

    public int getId() {
        return id;
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

    public int getPuntiRiscossione() {
        return puntiRiscossione;
    }

    public void setPuntiRiscossione(int puntiRiscossione) {
        this.puntiRiscossione = puntiRiscossione;
    }
}
