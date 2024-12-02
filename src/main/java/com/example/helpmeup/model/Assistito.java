package com.example.helpmeup.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.time.LocalDate;

@Entity


public class Assistito extends Utente{

    public Assistito(Long id, String nome, String cognome, String username, String sesso, String password, LocalDate dataNascita, String email, String indirizzo, String numeroTelefono) {
        super(id, nome, cognome, username, sesso, password, dataNascita, email, indirizzo, numeroTelefono);
    }

    public Assistito() {
    }

    @Override
    public String toString() {
        return "Assistito{}: "+super.toString();
    }
}
