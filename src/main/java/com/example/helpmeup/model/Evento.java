package com.example.helpmeup.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
public class Evento {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @ManyToOne
    @JoinColumn(name = "utente")
    private Utente utente;
    private String nome;
    @Column(name = "data_evento")
    private LocalDate data;
    private LocalTime ora;

    public Evento() {
    }

    public Evento(String nome, LocalDate data, LocalTime ora, Utente utente) {
        this.nome = nome;
        this.data = data;
        this.ora = ora;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public LocalTime getOra() {
        return ora;
    }

    public void setOra(LocalTime ora) {
        this.ora = ora;
    }

    public Utente getUtente() {
        return utente;
    }

    public void setUtente(Utente utente) {
        this.utente = utente;
    }

    @Override
    public String toString() {
        return "Evento{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", data=" + data +
                ", ora=" + ora +
                '}';
    }
}
