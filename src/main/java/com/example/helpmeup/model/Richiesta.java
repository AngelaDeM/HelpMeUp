package com.example.helpmeup.model;

import jakarta.persistence.*;
import jakarta.persistence.GeneratedValue;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
public class Richiesta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String titolo;
    private String descrizione;
    private LocalDateTime data_creazione;
    private LocalDate data_intervento;
    private LocalTime orario_intervento;
    private boolean emergenza;


    public Richiesta() {
    }

    public Richiesta(String titolo, String descrizione, LocalDateTime data_creazione, LocalDate data_intervento, LocalTime orario_intervento, boolean emergenza) {
        this.titolo = titolo;
        this.descrizione = descrizione;
        this.data_creazione = data_creazione;
        this.data_intervento = data_intervento;
        this.orario_intervento = orario_intervento;
        this.emergenza = emergenza;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public LocalDateTime getData_creazione() {
        return data_creazione;
    }

    public void setData_creazione(LocalDateTime data_creazione) {
        this.data_creazione = data_creazione;
    }

    public LocalDate getData_intervento() {
        return data_intervento;
    }

    public void setData_intervento(LocalDate data_intervento) {
        this.data_intervento = data_intervento;
    }

    public LocalTime getOrario_intervento() {
        return orario_intervento;
    }

    public void setOrario_intervento(LocalTime orario_intervento) {
        this.orario_intervento = orario_intervento;
    }

    public boolean isEmergenza() {
        return emergenza;
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
                ", data_creazione=" + data_creazione +
                ", data_intervento=" + data_intervento +
                ", orario_intervento=" + orario_intervento +
                ", emergenza=" + emergenza +
                '}';
    }
}


