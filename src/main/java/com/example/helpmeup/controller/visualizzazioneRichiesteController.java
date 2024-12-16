package com.example.helpmeup.controller;

import com.example.helpmeup.model.Assistito;
import com.example.helpmeup.model.Richiesta;
import com.example.helpmeup.model.Utente;
import com.example.helpmeup.repository.RichiestaRepository;
import com.example.helpmeup.service.RichiestaService;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Controller per la gestione delle richieste, incluse visualizzazione
 * e filtraggio delle richieste secondo criteri specifici. Questo
 * controller contiene endpoint per ottenere dati relativi alle
 * richieste e per gestire le visualizzazioni delle stesse.
 */
@Controller
@RequestMapping("/api")
@SessionAttributes("richiesta")
public class visualizzazioneRichiesteController {

    private static final Logger logger = LoggerFactory.getLogger(visualizzazioneRichiesteController.class);
    private final RichiestaService richiestaService;

    /**
     * Costruttore del controller per la visualizzazione delle richieste.
     *
     * @param richiestaRepository il repository delle richieste
     */
    public visualizzazioneRichiesteController(RichiestaRepository richiestaRepository) {
        this.richiestaService = new RichiestaService(richiestaRepository);
    }

    /**Restituisce tutte le richieste presenti nel database
     *
     * @return List<Richiesta>
     */
    @GetMapping("/findAllRichieste")
    public @ResponseBody List<Richiesta> getAllRichieste(HttpSession session) {
        Utente utente = (Utente) session.getAttribute("utente");
        String username = utente.getUsername();
        List<Richiesta> l1= richiestaService.getAllRichieste();
        List<Richiesta> l2= richiestaService.getRichieste(username);
        l1.removeAll(l2);
        l1.removeIf(Richiesta::isCompletato);
        return l1;
    }

    /**
     * Retrieves the list of emergency requests that have not been completed and are not associated
     * with the current user.
     *
     * @param session the current HTTP session, used to retrieve the current user's information
     * @return a list of emergency requests that are not completed and do not belong to the current user
     */
    @GetMapping("/findAllRichiesteEmergenze")
    public @ResponseBody List<Richiesta> getAllEmergenze(HttpSession session) {
        Utente utente = (Utente) session.getAttribute("utente");
        String username = utente.getUsername();
        List<Richiesta> l1= richiestaService.getAllRichieste();
        List<Richiesta> l2= richiestaService.getRichieste(username);
        l1.removeAll(l2);
        l1.removeIf(Richiesta::isCompletato);
        l1.removeIf(richiesta -> !richiesta.isEmergenza());
        return l1;
    }


    /**
     * Handles the HTTP GET request for the endpoint "/findListRichieste" and maps it
     * to provide the view for the page that lists requests.
     *
     * @return the name of the view that displays the list of requests.
     */
    @GetMapping("/findListRichieste")
    public String getPaginaRichieste() {
        return "/Richiesta/lista_richieste";
    }

    @GetMapping("/findListRichiesteEmergenze")
    public String getPaginaRichiesteEmergenza() {
        return "/Richiesta/visualizza_emergenza";
    }
    /**Restituisce una richiesta specifica
     *
     * @param id l'id della richiesta
     * @return
     */
    @GetMapping("/findRichiestaById")
    public Richiesta getRichiestaById(int id) {
        return richiestaService.getRichiestaById(id);
          }


    /**
     * Handles the GET request for viewing requests based on the user role.
     * Determines the type of user from the session and returns the appropriate view.
     *
     * @param session the HttpSession object to retrieve the current user information
     * @return a string representing the view name for assistito or volontario
     */
    @GetMapping("/visualizzaRichieste")
    public String mostraPremi(HttpSession session) {
        Utente u = (Utente) session.getAttribute("utente");
        if(u instanceof Assistito)
            return "/Richiesta/visualizza_richieste";
        else
            return "/Richiesta/visualizza_richieste_volontario";
    }


    //Restituisce una richiesta specifica



    /**Restituisce tutte le richieste di un determinato volontario
     *
     * @param session
     * @return
     */
    @PostMapping("/findRichiesteByUser")
    public ResponseEntity<List<Richiesta>> getRichieste( HttpSession session){
        Utente utente = (Utente) session.getAttribute("utente");
        List<Richiesta> l= richiestaService.getRichieste(utente.getUsername());
        return ResponseEntity.ok(l);
    }
}
