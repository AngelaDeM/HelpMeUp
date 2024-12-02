package com.example.helpmeup.model;

import jakarta.persistence.Entity;

import java.time.LocalDate;

@Entity
public class Assistito extends Utente{

    public Assistito(Long id, String nome, String cognome, String username, String sesso, String password, LocalDate dataNascita, String email, String indirizzo, String numeroTelefono) {
        super(id, nome, cognome, username, sesso, password, dataNascita, email, indirizzo, numeroTelefono);
    }

    public Assistito(Utente utente) {
        super(utente.getId(), utente.getNome(), utente.getCognome(), utente.getUsername(), utente.getSesso(), utente.getPassword(), utente.getDataNascita(), utente.getEmail(), utente.getIndirizzo(), utente.getNumeroTelefono());
    }

    public Assistito() {
        super();
    }

    @Override
    public String toString() {
        return "Assistito{}: "+super.toString();
    }
}
