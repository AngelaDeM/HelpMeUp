package com.example.helpmeup.controller;

import com.example.helpmeup.model.Premio;
import com.example.helpmeup.repository.PremioRepository;
import com.example.helpmeup.service.PremioService;
import com.example.helpmeup.service.UtenteService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/api/premio")
@Validated
public class PremioController {

    private final PremioService premioService;
    private final UtenteService utenteService;

    public PremioController(PremioService premioService) {
        this.premioService = premioService;
        utenteService = null;
    }

    @GetMapping("/riscatta")
    public String mostraFormRiscatto() {
        return "Premio/riscatta_premio";
    }

    @PostMapping("/riscatta")
    public ResponseEntity<?> riscattaPremio(@Valid @RequestParam Map<String, String> dati) {
        String id_premio = dati.get("premio");
        String utente = dati.get("utente");
        if (!UtenteService.exist(utente)) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", "Utente non trovato");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }
       premioService.riscattaPremio(id_premio,utente);
        return ResponseEntity.ok("Premio riscattato con successo.");
    }


    @GetMapping("/aggiungi")
    public String mostraFormCreazione() {
        return "Premio/aggiungi_premio";
    }

    @PostMapping("/aggiungi")
    public ResponseEntity<String>   creaPremio(@Valid @RequestParam Map<String, String> dati) {
        String nome = dati.get("nome");
        String descrizione = dati.get("descrizione");
        int punti = Integer.parseInt(dati.get("punti"));
        Premio p = new Premio(nome,descrizione,punti);
        premioService.InsertPremio(p);
        return ResponseEntity.ok("Premio aggiunto con successo.");
    }

    @GetMapping("/getAll")
    public String mostraPremi() {
        return "Premio/visualizza_premi";
    }

    @GetMapping("/visualizza")
    public ResponseEntity<List<Premio>> visualizzaTuttiIPremi() {
        List<Premio> premi = premioService.getAllPremi();
        System.out.println(premi);  // Log per vedere cosa restituisce l'API
        return ResponseEntity.ok(premi);
    }

    @GetMapping("/visualizzaByUtente")
    public String mostraPremiByUser() {
        return "Premio/visualizza_premi_utente";
    }

    @PostMapping("/visualizzaByUtente")
    public ResponseEntity<?> visualizzaByUtente(@Valid @RequestParam Map<String, String> dati) {
        String utente = dati.get("utente");
        if (!UtenteService.exist(utente)) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", "Utente non trovato");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }
        List<Premio> premi = premioService.getAllPremiByUser(utente);
        return ResponseEntity.ok(premi);
    }
}