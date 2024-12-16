package com.example.helpmeup.controller;

import com.example.helpmeup.model.Utente;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * The PagineController class is a Spring MVC Controller that handles HTTP GET requests
 * and maps them to corresponding view templates. It provides methods for rendering
 * various pages of the application, and in some cases, populates the model with user-specific data.
 */
@Controller

public class PagineController {

    public PagineController() {
    }

    /**
     * Handles the HTTP GET request to the "/index" endpoint.
     *
     * @return A string representing the path to the index view.
     */
    @GetMapping("/index")
    public String mostraIndex() {
        return "/index";
    }

    /**
     * Handles GET requests for the "Chi Siamo" page.
     *
     * @return The name of the view representing the "Chi Siamo" page.
     */
    @GetMapping("/chi_siamo")
    public String mostraChiSiammo() {
        return "Varie/chi_siamo";
    }

    /**
     * Handles GET requests to the "/learn_more" endpoint and returns the name of the
     * view corresponding to the "learn_more" page.
     *
     * @return the name of the view to render for the "learn_more" page.
     */
    @GetMapping("/learn_more")
    public String mostraLearn_more() {
        return "Varie/learn_more";
    }

    /**
     * Handles the request for the forum page.
     *
     * @return the name of the view for the forum page, "Varie/forum".
     */
    @GetMapping("/forum")
    public String mostraForum() {
        return "Varie/forum";
    }

    /**
     * Handles the HTTP GET request for the "/punti" endpoint.
     * This method returns the name of the view for displaying points.
     *
     * @return the name of the view "Punti/punti".
     */
    @GetMapping("/punti")
    public String mostraPunti() {
        return "Punti/punti";
    }

    /**
     * Manages the display of the request list view.
     *
     * @return The name of the Thymeleaf template for rendering the request list page.
     */
    //Visualizza lista richieste
    @GetMapping("/lista_richieste")
    public String mostraRichieste() {
        return "Richiesta/lista_richieste";
    }

    /**
     * Handles HTTP GET requests to display the calendar for the user.
     * Retrieves the authenticated user from the session and adds it to the model for rendering.
     *
     * @param session the current HTTP session, used to retrieve the logged-in user
     * @param model the model object used to pass attributes to the view
     * @return the name of the view to be rendered, "AreaUtente/calendario"
     */
    //Visualizza calendario
    @GetMapping("/calendario")
    public String mostraCalendario(HttpSession session, Model model) {
        // Recupera l'utente dalla sessione
        Utente user = (Utente) session.getAttribute("utente");

        // Aggiungi l'oggetto user al modello per Thymeleaf
        model.addAttribute("utente", user);
        return "AreaUtente/calendario";
    }

    /**
     * Handles the GET request to display personal data.
     *
     * This method retrieves the user object from the HttpSession, adds it
     * to the Model for rendering purposes, and returns the view name associated
     * with the user's personal data page.
     *
     * @param session the HttpSession object used to retrieve the logged-in user's data
     * @param model the Model object for adding attributes that will be passed to the view
     * @return a String representing the name of the view for the user's personal data page
     */
    //Visualizza dati anagrafici
    @GetMapping("/dati_anagrafici")
    public String mostraDatiAnagrafici(HttpSession session, Model model) {
        // Recupera l'utente dalla sessione
        Utente user = (Utente) session.getAttribute("utente");

        // Aggiungi l'oggetto user al modello per Thymeleaf
        model.addAttribute("utente", user);
        return "AreaUtente/dati_anagrafici";
    }

    /**
     * Handles HTTP GET requests to the "/lista_emergenze" endpoint
     * and returns the name of the view to display the list of emergencies.
     *
     * @return the name of the view template for the emergencies list page
     */
    @GetMapping("/lista_emergenze")
    public String mostraEmergenze() {
        return "AreaUtente/lista_emergenze";
    }


}
