package com.example.helpmeup.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "richiesta")
public class Richiesta {

    private static int countID=1000;

    @Id
    private final int id=countID++;
    private String titolo;
    private String descrizione;
    private final LocalDateTime data_creazione = LocalDateTime.now();;
    private LocalDate data_intervento;
    private LocalTime orario_intervento;
    private final int punti;
    private boolean emergenza;




    public Richiesta() {
        this.punti = 50;
    }

    public Richiesta(String titolo, String descrizione, LocalDate data_intervento, LocalTime orario_intervento, boolean emergenza) {
        this.titolo = titolo;
        this.descrizione = descrizione;
        this.data_intervento = data_intervento;
        this.orario_intervento = orario_intervento;

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

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }


    public void setDataAiuto(LocalDate dataAiuto) {
        this.data_intervento = dataAiuto;
    }

    public void setOraAiuto(LocalTime oraAiuto) {
        this.orario_intervento = oraAiuto;
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
                '}';
    }
}