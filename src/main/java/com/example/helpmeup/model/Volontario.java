package com.example.helpmeup.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;

@Entity
@DiscriminatorValue("Volontario")
public class Volontario extends Utente{

    private int punti;
    @ManyToMany
    @JoinTable(
    name = "riscatti_premi",
    joinColumns = @JoinColumn(name = "account_id"),
    inverseJoinColumns = @JoinColumn(name = "premio_id")
    )
    private List<Premio> premi;

    @ManyToMany
    @JoinTable(
    name = "richieste_volontari",
    joinColumns = @JoinColumn(name = "account_id"),
    inverseJoinColumns = @JoinColumn(name = "richiesta_id")
    )
    List<Richiesta> richieste;

    public Volontario() {
    }

    public Volontario(String nome, String cognome, String username, String sesso, String password, LocalDate dataNascita, String email, String indirizzo, String numeroTelefono, int punti, List<Premio> premi) {
        super(nome, cognome, username, sesso, password, dataNascita, email, indirizzo, numeroTelefono);
        this.punti = punti;
        this.premi = premi;
    }
    public Volontario(Utente utente, int punti, List<Premio> premi) {
        super(utente.getNome(), utente.getCognome(), utente.getUsername(), utente.getSesso(), utente.getPassword(), utente.getDataNascita(), utente.getEmail(), utente.getIndirizzo(), utente.getNumeroTelefono());
        this.punti = punti;
        this.premi = premi;
    }

    public int getPunti() {
        return punti;
    }

    public void setPunti(int punti) {
        this.punti = punti;
    }

    public void addPunti(int punti) {
        this.punti += punti;
    }
    public void removePunti(int punti) {
        this.punti -= punti;
    }
    public List<Premio> getPremi() {
        return premi;
    }

    public void setPremi(ArrayList<Premio> premi) {
        this.premi = premi;
    }

    public void addPremi(Premio premio) {
        premi.add(premio);
    }

    public void removePremio(Premio premio) {
        premi.remove(premio);
    }

    @Override
    public String toString() {
        return "Volontario{" +
                super.toString() +
                "punti=" + punti +
                ", premi=" + premi +
                '}';
    }
}
