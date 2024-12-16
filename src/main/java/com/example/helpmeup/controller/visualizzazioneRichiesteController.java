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
 * Controller per la visualizzazione delle richieste.
 *
 * @author Claudio
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
