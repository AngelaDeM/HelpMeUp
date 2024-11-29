package com.example.helpmeup.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "account")
public class Utente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String cognome;
    private String username;

    private String tipoAccount; // "volontario" o "assistito" come String
    private String sesso; // "Maschio" o "Femmina" come String

    private String password;
    private LocalDate dataNascita;
    private String email;

    private String indirizzo;

    private String numeroTelefono;
    private Integer punti = 0;
    //esempio

    @ElementCollection
    @CollectionTable(name = "certificazioni", joinColumns = @JoinColumn(name = "utente_id"))
    @Column(name = "certificazione")
    private List<String> certificazioni = new ArrayList<>();

    public List<String> getCertificazioni() {
        return certificazioni;
    }

    public void setCertificazioni(List<String> certificazioni) {
        this.certificazioni = certificazioni;
    }

    // Costruttore di default
    public Utente() {
    }

    // Costruttore parametrizzato
    public Utente(String nome, String cognome, String username, String tipoAccount, String password,
                  LocalDate dataNascita, String sesso, String indirizzo, String numeroTelefono, Integer punti) {
        this.nome = nome;
        this.cognome = cognome;
        this.username = username;
        this.tipoAccount = tipoAccount;
        this.email = email;
        this.password = password;
        this.dataNascita = dataNascita;
        this.sesso = sesso;
        this.indirizzo = indirizzo;
        this.numeroTelefono = numeroTelefono;
        this.punti = punti;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getTipoAccount() {
        return tipoAccount;
    }

    public void setTipoAccount(String tipoAccount) {
        this.tipoAccount = tipoAccount;
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

    public Integer getPunti() {
        return punti;
    }

    public void setPunti(Integer punti) {
        this.punti = punti;
    }

    @Override
    public String toString() {
        return "Utente{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", cognome='" + cognome + '\'' +
                ", username='" + username + '\'' +
                ", tipoAccount='" + tipoAccount + '\'' +
                ", sesso='" + sesso + '\'' +
                ", dataNascita=" + dataNascita +
                ", email='" + email + '\'' +
                ", indirizzo='" + indirizzo + '\'' +
                ", numeroTelefono='" + numeroTelefono + '\'' +
                ", punti=" + punti +
                ", certificazioni=" + certificazioni +
                '}';
    }
}