package com.example.helpmeup.model;

import jakarta.persistence.Entity;

import java.time.LocalDate;
import java.util.ArrayList;

@Entity
public class Volontario extends Utente{

    private int punti;
    private ArrayList<String> certificazioni;

    public Volontario() {
        super();
        this.punti = 0;
        this.certificazioni = new ArrayList<>();
    }

    public Volontario(Utente utente) {
        super(utente.getId(), utente.getNome(), utente.getCognome(), utente.getUsername(), utente.getSesso(), utente.getPassword(), utente.getDataNascita(), utente.getEmail(), utente.getIndirizzo(), utente.getNumeroTelefono());
        this.punti = 0;
        this.certificazioni = new ArrayList<>();
    }
    public Volontario(Long id, String nome, String cognome, String username, String sesso, String password, LocalDate dataNascita, String email, String indirizzo, String numeroTelefono, int punti, ArrayList<String> certificazioni) {
        super(id, nome, cognome, username, sesso, password, dataNascita, email, indirizzo, numeroTelefono);
        this.punti = punti;
        this.certificazioni = certificazioni;
    }

    public int getPunti() {
        return punti;
    }

    public void setPunti(int punti) {
        this.punti = punti;
    }

    public ArrayList<String> getCertificazioni() {
        return certificazioni;
    }

    public void setCertificazioni(ArrayList<String> certificazioni) {
        this.certificazioni = certificazioni;
    }

    public void addCertificazione(String certificazione) {
        certificazioni.add(certificazione);
    }

    public void removeCertificazione(String certificazione) {
        certificazioni.remove(certificazione);
    }

    @Override
    public String toString() {
        return "Volontario{" +
                super.toString() +
                "punti=" + punti +
                ", certificazioni=" + certificazioni +
                '}';
    }
}
