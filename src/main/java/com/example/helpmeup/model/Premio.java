package com.example.helpmeup.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "premio")  // Assicurati che il nome corrisponda alla tabella nel database
public class Premio {

    @Id
    @Column(name = "nome", nullable = false, unique = true)  // Definisce la chiave primaria
    @NotBlank(message = "Il nome del premio non può essere vuoto.")
    private String nome;

    @Column(name = "descrizione", length = 500)  // Limite di lunghezza opzionale
    @NotBlank(message = "La descrizione del premio non può essere vuota.")
    private String descrizione;

    @Column(name = "punti_richiesti", nullable = false)
    @Positive(message = "I punti richiesti devono essere un valore positivo.")
    private int puntiRichiesti;

    @ManyToMany
    @JoinTable(
            name = "riscatti_premi",
            joinColumns = @JoinColumn(name = "premio_id"),
            inverseJoinColumns = @JoinColumn(name = "account_id")
    )
    @JsonIgnore
    private List<Volontario> volontari = new ArrayList<>();


    // Costruttore vuoto necessario per JPA
    public Premio() {
    }

    public Premio(String nome, String descrizione, int puntiRichiesti) {
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

    public List<Volontario> getVolontari() {
        return volontari;
    }

    public void setVolontari(List<Volontario> volontari) {
        this.volontari = volontari;
    }

    @Override
    public String toString() {
        return "Premio{" +
                "nome='" + nome + '\'' +
                ", descrizione='" + descrizione + '\'' +
                ", puntiRichiesti=" + puntiRichiesti +

                '}';
    }
}
