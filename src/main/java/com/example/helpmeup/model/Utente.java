package com.example.helpmeup.model;

import jakarta.persistence.*;
import java.time.LocalDate;



@Entity
@Table(name="utente")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_account", discriminatorType = DiscriminatorType.STRING)
public abstract class Utente {

    private String nome;
    private String cognome;
    //primary key
    @Id
    private String username;
    private String sesso; // "Maschio" o "Femmina" come String
    private String password;
    private LocalDate dataNascita;
    private String email;
    private String indirizzo; // Unico campo per l'indirizzo
    private String numeroTelefono;


    public Utente(String nome, String cognome, String username,  String sesso, String password, LocalDate dataNascita, String email, String indirizzo, String numeroTelefono) {
        this.nome = nome;
        this.cognome = cognome;
        this.username = username;
        this.sesso = sesso;
        this.password = password;
        this.dataNascita = dataNascita;
        this.email = email;
        this.indirizzo = indirizzo;
        this.numeroTelefono = numeroTelefono;
    }

    public Utente() {

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSesso() {
        return sesso;
    }

    public void setSesso(String sesso) {
        this.sesso = sesso;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDate getDataNascita() {
        return dataNascita;
    }

    public void setDataNascita(LocalDate dataNascita) {
        this.dataNascita = dataNascita;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }

    public String getNumeroTelefono() {
        return numeroTelefono;
    }

    public void setNumeroTelefono(String numeroTelefono) {
        this.numeroTelefono = numeroTelefono;
    }

    @Override
    public String toString() {
        return "Utente{" +
                ", nome='" + nome + '\'' +
                ", cognome='" + cognome + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", dataDiNascita=" + dataNascita +
                ", sesso='" + sesso + '\'' +
                ", indirizzo='" + indirizzo + '\'' +
                ", numeroTelefono='" + numeroTelefono + '\'' +
                '}';
    }
}