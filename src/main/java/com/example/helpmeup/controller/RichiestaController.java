package com.example.helpmeup.controller;

import com.example.helpmeup.model.Richiesta;
import com.example.helpmeup.model.Volontario;
import com.example.helpmeup.repository.RichiestaRepository;
import com.example.helpmeup.service.RichiestaService;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.time.LocalDate;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Optional;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/api/richieste")
@Validated
public class RichiestaController {

    private final RichiestaService richiestaService;

    public RichiestaController(RichiestaService richiestaService) {
        this.richiestaService = richiestaService;
    }

    @GetMapping("/pubblica")
    public String mostraFormPubblicazione() {
        return "Richiesta/pubblica_richiesta";
    }

    @PostMapping("/pubblica")
    public ResponseEntity<String> registraRichiesta(@Valid @RequestParam Map<String, String> dati) {
        try{
            String username = dati.get("assistito");
            String titolo = dati.get("titolo");
            String descrizione = dati.get("descrizione");
            LocalDateTime dataPubblicazione = LocalDateTime.now();
            LocalDate dataAiuto = LocalDate.parse(dati.get("data"));
            LocalTime oraAiuto = LocalTime.parse(dati.get("ora"));
            boolean emergenza = Boolean.parseBoolean(dati.get("emergenza"));

            Richiesta richiesta = new Richiesta(titolo, descrizione, dataAiuto, oraAiuto, emergenza);
            richiestaService.pubblicaRichiesta(richiesta);
            richiestaService.aiutoRichiesta(richiesta.getId(),username);
            return ResponseEntity.ok("Registrazione richiesta avvenuta con successo.");
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Errore durante la registrazione della richiesta: " + e.getMessage());
        }

    }

    @GetMapping("/accetta")
    public String mostraFormAccetta() {
        return "Richiesta/accetta_richiesta";
    }

    @PostMapping("/accetta")
    public ResponseEntity<String>   accettaRichiesta(@Valid @RequestParam Map<String, String> dati) {
        try{
            int id_richiesta = Integer.parseInt(dati.get("richiesta"));
            String id_volontario = dati.get("volontario");
            Richiesta r = richiestaService.getRichiestaById(id_richiesta);
            boolean giaAccettato= richiestaService.statoAccettoVolontario(id_richiesta,id_volontario);
            if(!r.isCompletato() && !giaAccettato){
                richiestaService.accettaRichiesta(id_richiesta,id_volontario);
                return ResponseEntity.ok("Richiesta accettata con successo.");
            }
            else{
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Richiesta già accettata dall'utente.");
            }

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Errore durante l'accettazione della richiesta: " + e.getMessage());
        }
    }

    @GetMapping("/modifica")
    public String mostraFormModifica() {
        return "Richiesta/modifica_richiesta";
    }

    @PostMapping("/modifica")
    public ResponseEntity<String> modificaRichiesta(@Valid @RequestParam Map<String, String> dati) {
        try {
            // Estrai i dati dal corpo della richiesta
            int id = Integer.parseInt(dati.get("richiesta"));
            String titolo = dati.get("titolo");
            String descrizione = dati.get("descrizione");
            LocalDate dataAiuto = LocalDate.parse(dati.get("data"));
            LocalTime oraAiuto = LocalTime.parse(dati.get("ora"));
            boolean emergenza = Boolean.parseBoolean(dati.get("emergenza"));
            System.out.println("sono qui");
            // Trova la richiesta esistente nel database
            Optional<Richiesta> richiestaOptional = Optional.ofNullable(richiestaService.getRichiestaById(id));
            if (richiestaOptional.isPresent()) {
                Richiesta richiesta = richiestaOptional.get();
                if(richiesta.isCompletato()){
                    richiesta.setTitolo(titolo);
                    richiesta.setDescrizione(descrizione);
                    richiesta.setDataAiuto(dataAiuto);
                    richiesta.setOraAiuto(oraAiuto);
                    richiesta.setEmergenza(emergenza);
                    // Settiamo la data di pubblicazione come attuale

                    // modifica la richiesta aggiornata nel database
                    richiestaService.updateRichiesta(richiesta);
                    return ResponseEntity.ok("Richiesta aggiornata con successo.");
                }
                else return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Richiesta già completata.");

            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Richiesta non trovata.");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Errore durante la modifica della richiesta: " + e.getMessage());
        }
    }

    @GetMapping("/elimina")
    public String mostraFormEliminazione() {
        return "Richiesta/elimina_richiesta";
    }

    @PostMapping("/elimina")
    public ResponseEntity<String> deleteRichiesta(@Valid @RequestParam Map<String, String> dati) {
        try {
            int id_richiesta = Integer.parseInt(dati.get("richiesta"));
            Richiesta richiesta = richiestaService.getRichiestaById(id_richiesta);
            if(!richiesta.isCompletato()){
                richiestaService.eliminaRichiesta(richiesta);
                return ResponseEntity.ok("Richiesta eliminata con successo.");
            }
            else{
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Richiesta già completata.");
            }
        } catch (NumberFormatException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("ID richiesta non valido: " + e.getMessage());
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Richiesta non trovata: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Errore durante l'eliminazione della richiesta: " + e.getMessage());
        }
    }

    @GetMapping("/completa")
    public String mostraFormCompletarichiesta() {
        return "Richiesta/completa_richiesta";
    }

    @PostMapping("/completa")
    public ResponseEntity<String> completaRichiesta(@Valid @RequestParam Map<String, String> dati) {
        try{
            int id_richiesta = Integer.parseInt(dati.get("richiesta"));
            List<String> volontari=richiestaService.getVolontari(id_richiesta);
            Richiesta r = richiestaService.getRichiestaById(id_richiesta);
            richiestaService.completaRichiesta(id_richiesta,volontari,r.getPunti());
            richiestaService.getRichiestaById(id_richiesta).setCompletato(true);
            return ResponseEntity.ok("Richiesta completata con successo.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Errore durante il completamento della richiesta: " + e.getMessage());
        }
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<String> handleValidationExceptions(ConstraintViolationException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Errore di validazione: " + e.getMessage());
    }

}