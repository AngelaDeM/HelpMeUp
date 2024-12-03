package com.example.helpmeup.controller;

import com.example.helpmeup.model.Richiesta;
import com.example.helpmeup.repository.RichiestaRepository;
import com.example.helpmeup.service.RichiestaService;
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
    public ResponseEntity<String> registraRichiesta(@RequestParam Map<String, String> dati) {
        try{
            String titolo = dati.get("titolo");
            String descrizione = dati.get("descrizione");
            LocalDateTime dataPubblicazione = LocalDateTime.now();
            LocalDate dataAiuto = LocalDate.parse(dati.get("data"));
            LocalTime oraAiuto = LocalTime.parse(dati.get("ora"));
            boolean emergenza = Boolean.parseBoolean(dati.get("emergenza"));

            Richiesta richiesta = new Richiesta(titolo, descrizione, dataAiuto, oraAiuto, emergenza);
            richiestaService.pubblicaRichiesta(richiesta);
            return ResponseEntity.ok("Registrazione richiesta avvenuta con successo.");
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Errore durante la registrazione della richiesta: " + e.getMessage());
        }

    }

    @PostMapping("/accetta")
    public ResponseEntity<String>   accettaRichiesta(@RequestBody Map<String, String> dati) {
        int id_richiesta = Integer.parseInt(dati.get("richiesta"));
        String id_volontario = dati.get("volontario");
        richiestaService.accettaRichiesta(id_richiesta,id_volontario);
        return ResponseEntity.ok("Richiesta accettata con successo.");
    }

    @PostMapping("/modifica")
    public ResponseEntity<String> modificaRichiesta(@RequestBody Map<String, String> dati) {
        try {
            // Estrai i dati dal corpo della richiesta
            int id = Integer.parseInt(dati.get("id"));
            String titolo = dati.get("titolo");
            String descrizione = dati.get("descrizione");
            LocalDate dataAiuto = LocalDate.parse(dati.get("data"));
            LocalTime oraAiuto = LocalTime.parse(dati.get("ora"));
            boolean emergenza = Boolean.parseBoolean(dati.get("emergenza"));

            // Trova la richiesta esistente nel database
            Optional<Richiesta> richiestaOptional = Optional.ofNullable(richiestaService.getRichiestaById(id));
            if (richiestaOptional.isPresent()) {
                Richiesta richiesta = richiestaOptional.get();
                richiesta.setTitolo(titolo);
                richiesta.setDescrizione(descrizione);
                richiesta.setDataAiuto(dataAiuto);
                richiesta.setOraAiuto(oraAiuto);
                richiesta.setEmergenza(emergenza);
                 // Settiamo la data di pubblicazione come attuale

                // modifica la richiesta aggiornata nel database
                richiestaService.updateRichiesta(richiesta);
                return ResponseEntity.ok("Richiesta aggiornata con successo.");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Richiesta non trovata.");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Errore durante la modifica della richiesta: " + e.getMessage());
        }
    }

    @PostMapping("/elimina")
    public ResponseEntity<String> deleteRichiesta(@RequestBody Map<String, String> dati) {
        try {
            int id_richiesta = Integer.parseInt(dati.get("richiesta"));
            Richiesta richiesta = richiestaService.getRichiestaById(id_richiesta);
            richiestaService.eliminaRichiesta(richiesta);
            return ResponseEntity.ok("Richiesta eliminata con successo.");
        } catch (NumberFormatException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("ID richiesta non valido: " + e.getMessage());
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Richiesta non trovata: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Errore durante l'eliminazione della richiesta: " + e.getMessage());
        }
    }

}