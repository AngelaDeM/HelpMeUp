package com.example.helpmeup.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Assistito extends Utente{

    public Assistito(String nome, String cognome, String username, String sesso, String password, LocalDate dataNascita, String email, String indirizzo, String numeroTelefono, String tipo_account) {
        super(nome, cognome, username, sesso, password, dataNascita, email, indirizzo, numeroTelefono, tipo_account);
    }

    public Assistito(Utente utente){
        super(utente.getNome(), utente.getCognome(), utente.getUsername(), utente.getSesso(), utente.getPassword(), utente.getDataNascita(), utente.getEmail(), utente.getIndirizzo(), utente.getNumeroTelefono(), utente.getTipo_account());
    }

    public Assistito() {
    }

    @Override
    public String toString() {
        return "Assistito{}: "+super.toString();
    }
}