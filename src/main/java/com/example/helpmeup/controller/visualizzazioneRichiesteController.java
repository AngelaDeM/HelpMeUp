package com.example.helpmeup.controller;

import com.example.helpmeup.model.Richiesta;
import com.example.helpmeup.repository.RichiestaRepository;
import com.example.helpmeup.service.RichiestaService;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public @ResponseBody List<Richiesta> getAllRichieste() {
        return richiestaService.getAllRichieste();
    }

    /**Restituisce una richiesta specifica
     *
     * @param id l'id della richiesta
     * @return
     */
    @GetMapping("/findRichiestaById")
    public Richiesta getRichiestaById(int id){
        return richiestaService.getRichiestaById(id);
    }


    /**Restituisce tutte le richieste di un determinato volontario
     *
     * @param session
     * @return
     */
    @GetMapping("/findRichiesteByVolontario")
    public List<Richiesta> getRichiesteByVolontario(HttpSession session) {
        String username = (String) session.getAttribute("username");
        return richiestaService.getRichiesteByVolontario(username);
    }

    /**Restituisce tutte le richieste di un determinato assistito
     *
     * @param session
     * @return
     */
    @GetMapping("/findRichiesteByAssistito")
    public @ResponseBody List<Richiesta> getRichiesteByAssistito(HttpSession session) {
        String username = (String) session.getAttribute("username");
        return richiestaService.getRichiesteByAssistito(username);
    }
}
