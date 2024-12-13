package com.example.helpmeup.controller;

import com.example.helpmeup.model.Utente;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller

public class PagineController {

    public PagineController() {
    }

    @GetMapping("/index")
    public String mostraIndex() {
        return "/index";
    }

    @GetMapping("/chi_siamo")
    public String mostraChiSiammo() {
        return "Varie/chi_siamo";
    }

    @GetMapping("/learn_more")
    public String mostraLearn_more() {
        return "Varie/learn_more";
    }

    @GetMapping("/forum")
    public String mostraForum() {
        return "Varie/forum";
    }

    @GetMapping("/punti")
    public String mostraPunti() {
        return "Punti/punti";
    }

    //Visualizza lista richieste
    @GetMapping("/lista_richieste")
    public String mostraRichieste() {
        return "Richiesta/lista_richieste";
    }

    //Visualizza calendario
    @GetMapping("/calendario")
    public String mostraCalendario() {
        return "AreaUtente/calendario";
    }

    //Visualizza dati anagrafici
    @GetMapping("/dati_anagrafici")
    public String mostraDatiAnagrafici(HttpSession session, Model model) {
        // Recupera l'utente dalla sessione
        Utente user = (Utente) session.getAttribute("utente");

        // Aggiungi l'oggetto user al modello per Thymeleaf
        model.addAttribute("utente", user);
        return "AreaUtente/dati_anagrafici";
    }

    @GetMapping("/lista_emergenze")
    public String mostraEmergenze() {
        return "AreaUtente/lista_emergenze";
    }
}
