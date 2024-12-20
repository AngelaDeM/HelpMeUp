package com.example.helpmeup.controller;

import com.example.helpmeup.model.Evento;
import com.example.helpmeup.model.Utente;
import com.example.helpmeup.model.Volontario;
import com.example.helpmeup.service.EventoService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

/**
 * Controller del calendario.
 *
 * @author Claudio
 */
@Controller
@SessionAttributes("utente")
public class CalendarioController {
    private final EventoService eventoService;

    /**
     * Costruttore del controller per il calendario.
     *
     * @param eventoService il service degli eventi
     */
    public CalendarioController(EventoService eventoService) {
        this.eventoService = eventoService;
    }

    /**
     * Mostra il calendario.
     *
     * @return String
     */
    @RequestMapping("/calendario")
    public String showCalendario() {
        return "calendario";
    }

    /**
     * Inserisce un nuovo evento.
     *
     * @param nome il nome dell'evento
     * @param data la data dell'evento
     * @param ora l'ora dell'evento
     */
    @PostMapping("/insertEvento")
    public String insertEvento(HttpSession session, @RequestParam String nome, @RequestParam String data, @RequestParam String ora, @RequestParam String utente) {
        // Converti la stringa della data e ora in oggetti Java
        LocalDate eventoData = LocalDate.parse(data);
        LocalTime eventoOra = LocalTime.parse(ora);

        eventoService.insertEvento(nome, eventoData, eventoOra, utente);

        Utente user = (Utente) session.getAttribute("utente");
        if(user instanceof Volontario) {
            return "AreaUtente/area_utente";
        }
        else {
            return "AreaUtente/AreaAssistito";
        }
    }


    /**
     * Elimina l'evento con l'id specificato.
     *
     * @param id l'id dell'evento
     */

    @PostMapping("/deleteEvento")
    public void deleteEvento(int id) {
        eventoService.deleteEvento(id);
    }

    /**
     * Aggiorna l'evento con l'id specificato.
     *
     * @param id l'id dell'evento
     * @param nome il nome dell'evento
     * @param data la data dell'evento
     * @param ora l'ora dell'evento
     */
    @PostMapping("/updateEvento")
    public void updateEvento(int id, String nome, LocalDate data, LocalTime ora) {
        eventoService.updateEvento(id, nome, data, ora);
    }

    /**
     * Restituisce tutti gli eventi.
     *
     * @return List<Evento>
     */

    @GetMapping("/findAllEventi")
    public @ResponseBody List<Evento> findAll() {
        return eventoService.findAll();
    }

    /**
     * Restituisce tutti gli eventi di un utente.
     *
     * @param username l'username dell'utente di cui si vogliono ottenere gli eventi
     * @return List<Evento>
     */
    @GetMapping("/eventiByUtente")
    public @ResponseBody List<Evento> findByUtente(String username) {
        return eventoService.findByUtente(username);
    }
}
