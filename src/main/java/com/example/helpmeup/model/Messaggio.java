package com.example.helpmeup.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Messaggio {
    @Id
    private int id;
    private String titolo;
    private String contenuto;
    private LocalDateTime data_invio;
    private int account_id;
    @ManyToOne
    @JoinColumn(name = "forum_id")
    private Messaggio forum;
    private int forum_id;

    public Messaggio(int id, String titolo, String contenuto, LocalDateTime data_invio, int account_id, Messaggio forum, int forum_id) {
        this.id = id;
        this.titolo = titolo;
        this.contenuto = contenuto;
        this.data_invio = data_invio;
        this.account_id = account_id;
        this.forum = forum;
        this.forum_id = forum_id;
    }

    public Messaggio() {
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public String getContenuto() {
        return contenuto;
    }

    public void setContenuto(String contenuto) {
        this.contenuto = contenuto;
    }

    public LocalDateTime getData_invio() {
        return data_invio;
    }

    public void setData_invio(LocalDateTime data_invio) {
        this.data_invio = data_invio;
    }

    public int getAccount_id() {
        return account_id;
    }

    public void setAccount_id(int account_id) {
        this.account_id = account_id;
    }

    public Messaggio getForum() {
        return forum;
    }

    public void setForum(Messaggio forum) {
        this.forum = forum;
    }

    public int getForum_id() {
        return forum_id;
    }

    public void setForum_id(int forum_id) {
        this.forum_id = forum_id;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Messaggio{" +
                "id=" + id +
                ", titolo='" + titolo + '\'' +
                ", contenuto='" + contenuto + '\'' +
                ", data_invio=" + data_invio +
                ", account_id=" + account_id +
                ", forum=" + forum +
                ", forum_id=" + forum_id +
                '}';
    }
}
