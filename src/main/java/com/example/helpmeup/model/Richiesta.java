package com.example.helpmeup.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.persistence.GeneratedValue;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Richiesta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String titolo;
    private String descrizione;
    private LocalDateTime data_creazione = LocalDateTime.now();;
    private LocalDate data_intervento;
    private LocalTime orario_intervento;
    private int punti;
    private boolean emergenza;
    //Foreign key for Utente
    @ManyToOne
    @JoinColumn(name = "account_id")
    @JsonIgnore
    private Assistito account_id;

    @ManyToMany
    @JsonIgnore
    private List<Volontario> volontari;



    public Richiesta() {
    }

    public Richiesta(String titolo, String descrizione, LocalDateTime data_creazione, LocalDate data_intervento, LocalTime orario_intervento, boolean emergenza, int punti) {
        this.titolo = titolo;
        this.descrizione = descrizione;
        this.data_creazione = data_creazione;
        this.data_intervento = data_intervento;
        this.orario_intervento = orario_intervento;
        this.punti = punti;
        this.emergenza = emergenza;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @NotBlank(message = "Il titolo non può essere vuoto.")
    @Size(max = 100, message = "Il titolo non può superare i 100 caratteri.")
    public String getTitolo() {
        return titolo;
    }


    @NotBlank(message = "La descrizione non può essere vuota.")
    @Size(max = 500, message = "La descrizione non può superare i 500 caratteri.")
    public String getDescrizione() {
        return descrizione;
    }

    @NotNull(message = "La data di pubblicazione non può essere nulla.")
    public LocalDateTime getDataPubblicazione() {
        return data_creazione;
    }

    @NotNull(message = "L'ora di aiuto è obbligatoria.")
    public LocalTime getOraAiuto() {
        return orario_intervento;
    }

    @NotNull(message = "La data di aiuto è obbligatoria.")
    @Future(message = "La data di aiuto deve essere futura.")
    public LocalDate getDataAiuto() {
        return data_intervento;
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
                ", dataPubblicazione=" + data_creazione +
                ", dataAiuto=" + data_intervento +
                ", oraAiuto=" + orario_intervento +
                ", punti=" + punti +
                ", emergenza=" + emergenza +
                ", account_id=" + account_id +
                '}';
    }
}
