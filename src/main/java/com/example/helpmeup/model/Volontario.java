package com.example.helpmeup.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;

@Entity
public class Volontario extends Utente{

    private int punti;
    @ManyToMany
    @JoinTable(
    name = "volontario_premio",
    joinColumns = @JoinColumn(name = "volontario_id"),
    inverseJoinColumns = @JoinColumn(name = "premio_id")
    )
    private ArrayList<Premio> premi;


    public Volontario() {
    }

    public Volontario(String nome, String cognome, String username, String sesso, String password, LocalDate dataNascita, String email, String indirizzo, String numeroTelefono, int punti, ArrayList<Premio> premi) {
        super(nome, cognome, username, sesso, password, dataNascita, email, indirizzo, numeroTelefono);
        this.punti = punti;
        this.premi = premi;
    }
    public Volontario(Utente utente, int punti, ArrayList<Premio> premi) {
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
    public ArrayList<Premio> getPremi() {
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
