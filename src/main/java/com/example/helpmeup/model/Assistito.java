package com.example.helpmeup.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@DiscriminatorValue("assistito")
public class Assistito extends Utente{
    @OneToMany
    private List<Richiesta> richieste;

    public Assistito(String nome, String cognome, String username, String sesso, String password, LocalDate dataNascita, String email, String indirizzo, String numeroTelefono) {
        super(nome, cognome, username, sesso, password, dataNascita, email, indirizzo, numeroTelefono);
    }

    public Assistito(Utente utente){
        super(utente.getNome(), utente.getCognome(), utente.getUsername(), utente.getSesso(), utente.getPassword(), utente.getDataNascita(), utente.getEmail(), utente.getIndirizzo(), utente.getNumeroTelefono());
    }

    public Assistito() {
    }

    public List<Richiesta> getRichieste() {
        return richieste;
    }

    public void setRichieste(List<Richiesta> richieste) {
        this.richieste = richieste;
    }
    @Override
    public String toString() {
        return "Assistito{}: "+super.toString();
    }
}
