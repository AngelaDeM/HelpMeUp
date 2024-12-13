package com.example.helpmeup.controller;

import com.example.helpmeup.model.Premio;
import com.example.helpmeup.model.Richiesta;
import com.example.helpmeup.model.Utente;
import com.example.helpmeup.service.RichiestaService;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.PostPersist;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Controller per la gestione delle richieste legate all'entità "Richiesta".
 * Fornisce endpoint per creare, modificare, accettare e completare le richieste.
 *
 * @author ROBERTO-SCARPA
 * @author fimiani
 */
@Controller
@RequestMapping("/api/richieste")
@Validated
public class RichiestaController {

    private final RichiestaService richiestaService;

    /**
     * Costruttore per inizializzare il controller con il servizio richiesto.
     *
     * @param richiestaService Il servizio utilizzato per gestire le entità "Richiesta".
     */
    public RichiestaController(RichiestaService richiestaService) {
        this.richiestaService = richiestaService;
    }

    /*
     * @GetMapping("/lista")
     * public String mostraLista() {
     *    return "Richiesta/lista_richieste";
     * }
     */

    /**
     * Endpoint per recuperare tutte le richieste di emergenza.
     *
     * @return ResponseEntity contenente una lista di richieste di emergenza.
     */
    @GetMapping("/mostraEmergenze")
    public ResponseEntity<List<Richiesta>> mostraEmergenze() {
        List<Richiesta> emergenze = richiestaService.getAllEmergency();
        return ResponseEntity.ok(emergenze);
    }

    /**
     * Visualizza il modulo per la pubblicazione di una nuova richiesta.
     *
     * @return Il nome della vista per il modulo di pubblicazione della richiesta.
     */
    @GetMapping("/pubblica")
    public String mostraFormPubblicazione() {
        return "Richiesta/pubblica_richiesta";
    }

    /**
     * Registra una nuova richiesta utilizzando i parametri forniti.
     *
     * @param dati I parametri della richiesta, inclusi utente, titolo, descrizione, data e ora dell'aiuto richiesto.
     * @return ResponseEntity contenente il risultato dell'operazione.
     * @throws Exception Se si verifica un errore durante il processo di registrazione.
     */
    @PostMapping("/pubblica")
    public String registraRichiesta(@Valid @RequestParam Map<String, String> dati, HttpSession session, Model model) {
        try {
            Utente utente = (Utente) session.getAttribute("utente");
            String username = utente.getUsername();
            System.out.println(username);
            String titolo = dati.get("titolo");
            String descrizione = dati.get("descrizione");
            LocalDateTime dataPubblicazione = LocalDateTime.now();
            LocalDate dataAiuto = LocalDate.parse(dati.get("data"));
            LocalTime oraAiuto = LocalTime.parse(dati.get("ora"));
            boolean emergenza = Boolean.parseBoolean(dati.get("emergenza"));

            Richiesta richiesta = new Richiesta(titolo, descrizione, dataAiuto, oraAiuto, emergenza);
            richiestaService.pubblicaRichiesta(richiesta);
            richiestaService.aiutoRichiesta(richiesta.getId(), username);

            // Aggiunge un messaggio di successo al modello
            model.addAttribute("tipo", "Successo");
            model.addAttribute("message", "Registrazione richiesta avvenuta con successo.");
        } catch (Exception e) {
            // Aggiunge un messaggio di errore al modello
            model.addAttribute("tipo", "Errore");
            model.addAttribute("message", "Errore durante la registrazione della richiesta: " + e.getMessage());
        }

        return "Richiesta/pubblica_richiesta"; // Nome della vista (file HTML)
    }

    /**
     * Visualizza il modulo per l'accettazione di una richiesta.
     *
     * @return Il nome della vista per il modulo di accettazione della richiesta.
     */
    @GetMapping("/accetta")
    public String mostraFormAccetta() {
        return "Richiesta/accetta_richiesta";
    }

    /**
     * Accetta una richiesta per un volontario specifico.
     *
     * @param dati I parametri della richiesta, inclusi l'ID della richiesta e l'ID del volontario.
     * @return ResponseEntity contenente il risultato dell'operazione.
     * @throws Exception Se si verifica un errore durante il processo di accettazione.
     */
    @PostMapping("/accetta")
    public ResponseEntity<String> accettaRichiesta(@Valid @RequestParam Map<String, String> dati) {
        try {
            int id_richiesta = Integer.parseInt(dati.get("richiesta"));
            String id_volontario = dati.get("volontario");
            Richiesta r = richiestaService.getRichiestaById(id_richiesta);
            boolean giaAccettato = richiestaService.statoAccettoVolontario(id_richiesta, id_volontario);
            if (!r.isCompletato() && !giaAccettato) {
                richiestaService.accettaRichiesta(id_richiesta, id_volontario);
                return ResponseEntity.ok("Richiesta accettata con successo.");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Richiesta già accettata dall'utente.");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Errore durante l'accettazione della richiesta: " + e.getMessage());
        }
    }

    /**
     * Visualizza il modulo per la modifica di una richiesta.
     *
     * @return Il nome della vista per il modulo di modifica della richiesta.
     */
    @GetMapping("/modifica")
    public String mostraFormModifica() {
        return "Richiesta/modifica_richiesta";
    }

    /**
     * Modifica una richiesta esistente.
     *
     * @param dati I parametri della richiesta, inclusi ID della richiesta, titolo, descrizione, data e ora.
     * @return ResponseEntity contenente il risultato dell'operazione.
     * @throws Exception Se si verifica un errore durante il processo di modifica.
     */
    @PostMapping("/modifica")
    public String modificaRichiesta(@Valid @RequestParam Map<String, String> dati, Model model) {
        try {

            int id = Integer.parseInt(dati.get("richiesta"));
            String titolo = dati.get("titolo");
            String descrizione = dati.get("descrizione");
            LocalDate dataAiuto = LocalDate.parse(dati.get("data"));
            LocalTime oraAiuto = LocalTime.parse(dati.get("ora"));
            boolean emergenza = Boolean.parseBoolean(dati.get("emergenza"));
            // Trova la richiesta esistente nel database
            Optional<Richiesta> richiestaOptional = Optional.ofNullable(richiestaService.getRichiestaById(id));
            if (richiestaOptional.isPresent()) {
                Richiesta richiesta = richiestaOptional.get();
                if (!richiesta.isCompletato()) {
                    richiesta.setTitolo(titolo);
                    richiesta.setDescrizione(descrizione);
                    richiesta.setDataAiuto(dataAiuto);
                    richiesta.setOraAiuto(oraAiuto);
                    richiesta.setEmergenza(emergenza);
                    richiestaService.updateRichiesta(richiesta);


                    model.addAttribute("tipo", "Successo");
                    model.addAttribute("message", "Registrazione richiesta avvenuta con successo.");
                } else {
                    model.addAttribute("tipo", "Error");
                    model.addAttribute("message", "Registrazione già completa.");
                }
            } else {
                model.addAttribute("tipo", "Error");
                model.addAttribute("message", "Richiesta non trovata.");

            }
        } catch (Exception e) {
            model.addAttribute("tipo", "Error");
            model.addAttribute("message", "Errore durante la modifica della richiesta.");
        }
        return "Richiesta/visualizza_richieste";
    }


    /**
     * Elimina una richiesta dal sistema.
     *
     * @param dati I parametri della richiesta, inclusi l'ID della richiesta da eliminare.
     * @return ResponseEntity contenente il risultato dell'operazione.
     * @throws Exception Se si verifica un errore durante il processo di eliminazione.
     */
    @GetMapping("/elimina")
    public String deleteRichiesta(@Valid @RequestParam Map<String, String> dati, Model model) {
        try {
            int id_richiesta = Integer.parseInt(dati.get("id"));
            System.out.println("elimino richiesta"+id_richiesta);

            Richiesta richiesta = richiestaService.getRichiestaById(id_richiesta);
            if (!richiesta.isCompletato()) {
                richiestaService.eliminaRichiesta(richiesta);
                model.addAttribute("tipo", "Successo");
                model.addAttribute("message", "Richiesta eliminata con successo.");

            } else {
                model.addAttribute("tipo", "Error");
                model.addAttribute("message", "Richiesta già completata.");
            }
        } catch (NumberFormatException e) {
            model.addAttribute("tipo", "Error");
            model.addAttribute("message", "Richiesta non valida.");
        } catch (RuntimeException e) {
            model.addAttribute("tipo", "Error");
            model.addAttribute("message", "Richiesta non trovata.");
        } catch (Exception e) {
            model.addAttribute("tipo", "Error");
            model.addAttribute("message", "Errore durante l'eliminazione della richiesta.");
        }
        return "Richiesta/visualizza_richieste";
    }

    /**
     * Visualizza il modulo per il completamento di una richiesta.
     *
     * @return Il nome della vista per il modulo di completamento della richiesta.
     */
    @GetMapping("/completa")
    public String mostraFormCompletarichiesta() {
        return "Richiesta/completa_richiesta";
    }

    /**
     * Completa una richiesta e la segna come terminata.
     *
     * @param dati I parametri della richiesta, inclusi l'ID della richiesta.
     * @return ResponseEntity contenente il risultato dell'operazione.
     * @throws Exception Se si verifica un errore durante il processo di completamento.
     */
    @PostMapping("/completa")
    public ResponseEntity<String> completaRichiesta(@Valid @RequestParam Map<String, String> dati) {
        try {
            int id_richiesta = Integer.parseInt(dati.get("richiesta"));
            List<String> volontari = richiestaService.getVolontari(id_richiesta);
            Richiesta r = richiestaService.getRichiestaById(id_richiesta);
            richiestaService.completaRichiesta(id_richiesta, volontari, r.getPunti());
            richiestaService.getRichiestaById(id_richiesta).setCompletato(true);
            return ResponseEntity.ok("Richiesta completata con successo.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Errore durante il completamento della richiesta: " + e.getMessage());
        }
    }

    /**
     * Gestisce le eccezioni di validazione durante l'elaborazione della richiesta.
     *
     * @param e L'eccezione che si è verificata durante la validazione.
     * @return ResponseEntity contenente il messaggio di errore.
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<String> handleValidationExceptions(ConstraintViolationException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Errore di validazione: " + e.getMessage());
    }
}
