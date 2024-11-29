package com.example.helpmeup.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Richiesta {

    private static int countID=1000;

    private final int id=countID++;
    private String titolo;
    private String descrizione;
    private LocalDateTime dataPubblicazione;
    private LocalDate dataAiuto;
    private LocalTime oraAiuto;
    private final int punti;
    private boolean emergenza;


    public Richiesta() {
        this.punti = 50;
    }

    public Richiesta(String titolo, String descrizione, LocalDateTime dataPubblicazione, LocalDate dataAiuto, LocalTime oraAiuto, boolean emergenza) {
        this.titolo = titolo;
        this.descrizione = descrizione;
        this.dataPubblicazione = dataPubblicazione;
        this.dataAiuto = dataAiuto;
        this.oraAiuto = oraAiuto;

        this.emergenza = emergenza;
        if(this.emergenza){
            this.punti = 100;
        }else {
            this.punti = 50;
        }
    }

    public int getId() {
        return id;
    }

    public String getTitolo() {
        return titolo;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public LocalDateTime getDataPubblicazione() {
        return dataPubblicazione;
    }

    public LocalTime getOraAiuto() {
        return oraAiuto;
    }

    public LocalDate getDataAiuto() {
        return dataAiuto;
    }

    public int getPunti() {
        return punti;
    }

    public boolean isEmergenza() {
        return emergenza;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public void setDataPubblicazione(LocalDateTime dataPubblicazione) {
        this.dataPubblicazione = dataPubblicazione;
    }

    public void setDataAiuto(LocalDate dataAiuto) {
        this.dataAiuto = dataAiuto;
    }

    public void setOraAiuto(LocalTime oraAiuto) {
        this.oraAiuto = oraAiuto;
    }


    public void setEmergenza(boolean emergenza) {
        this.emergenza = emergenza;
    }

    @Override
    public String toString() {
        return "Richiesta{" +
                "id=" + id +
                ", titolo='" + titolo + '\'' +
                ", descrizione='" + descrizione + '\'' +
                ", dataPubblicazione=" + dataPubblicazione +
                ", dataAiuto=" + dataAiuto +
                ", oraAiuto=" + oraAiuto +
                ", punti=" + punti +
                ", emergenza=" + emergenza +
                '}';
    }
}


