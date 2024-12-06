package com.example.helpmeup.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Volontario extends Utente{

    private int punti;
    @ManyToMany
    @JoinTable(
            name = "riscatti_premi",
            joinColumns = @JoinColumn(name = "account_id"),
            inverseJoinColumns = @JoinColumn(name = "premio_id")
    )

    private List<Premio> premi;


    public Volontario() {
    }

    public Volontario(String nome, String cognome, String username, String sesso, String password, LocalDate dataNascita, String email, String indirizzo, String numeroTelefono, int punti, List<Premio> premi, String tipo_account) {
        super(nome, cognome, username, sesso, password, dataNascita, email, indirizzo, numeroTelefono, tipo_account);
        this.punti = punti;
        this.premi = premi;
    }
    public Volontario(Utente utente, int punti, ArrayList<Premio> premi) {
        super(utente.getNome(), utente.getCognome(), utente.getUsername(), utente.getSesso(), utente.getPassword(), utente.getDataNascita(), utente.getEmail(), utente.getIndirizzo(), utente.getNumeroTelefono(), utente.getTipo_account());
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
    public List<Premio> getPremi() {
        return premi;
    }

    public void setPremi(List<Premio> premi) {
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