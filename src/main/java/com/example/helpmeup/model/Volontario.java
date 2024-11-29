package com.example.helpmeup.model;

import java.util.ArrayList;

public class Volontario {

    private int punti;
    private ArrayList<String> certificazioni;

    public Volontario(int punti, ArrayList<String> certificazioni) {
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
