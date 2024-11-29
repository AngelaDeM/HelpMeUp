package com.example.helpmeup.model;

import java.time.LocalDateTime;

public class Messaggio {
    private static int count=0;
    private String descrizione;
    private LocalDateTime data;
    private final int id=count++;

    public Messaggio(String descrizione, LocalDateTime data) {
        this.descrizione = descrizione;
        this.data = data;
    }

    public Messaggio() {
    }

    public static int getCount() {
        return count;
    }

    public static void setCount(int count) {
        Messaggio.count = count;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Messaggio{" +
                "descrizione='" + descrizione + '\'' +
                ", data=" + data +
                ", id=" + id +
                '}';
    }
}
