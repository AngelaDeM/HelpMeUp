package com.example.helpmeup.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.time.LocalDate;
import java.util.ArrayList;

@Entity

public class Volontario extends Utente{

    private int punti;
    private String certificazione;

    public Volontario() {
        super();
    }

    public Volontario(Long id, String nome, String cognome, String username, String sesso, String password, LocalDate dataNascita, String email, String indirizzo, String numeroTelefono, int punti, String certificazioni) {
        super(id, nome, cognome, username, sesso, password, dataNascita, email, indirizzo, numeroTelefono);
        this.punti = punti;
        this.certificazione = certificazioni;
    }



    public int getPunti() {
        return punti;
    }

    public void setPunti(int punti) {
        this.punti = punti;
    }

    public String getCertificazione() {
        return certificazione;
    }

    public void setCertificazione(String certificazioni) {
        this.certificazione = certificazioni;
    }


    @Override
    public String toString() {
        return "Volontario{" +
                super.toString() +
                "punti=" + punti +
                ", certificazioni=" + certificazione +
                '}';
    }
}
