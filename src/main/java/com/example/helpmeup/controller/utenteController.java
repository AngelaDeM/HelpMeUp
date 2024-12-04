package com.example.helpmeup.controller;

import com.example.helpmeup.model.Utente;
import com.example.helpmeup.service.UtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api")
public class utenteController {

    @Autowired
    private UtenteService utenteService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody Utente utente) {

        // Verifica che la data di nascita non sia nel futuro
        if (utente.getDataNascita().isAfter(LocalDate.now())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Errore: la data di nascita non può essere nel futuro!");
        }

        // Verifica se l'email è già presente nel database
        if (utenteService.verificaEmail(utente.getEmail())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Errore: l'email è già registrata!");
        }

        // Verifica se lo username è già presente nel database
        if (utenteService.verificaUsername(utente.getUsername())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Errore: lo username è già registrato!");
        }

        // Se tutte le verifiche passano, salva l'utente nel database
        utenteService.salvaUtente(utente);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Utente registrato con successo!");
    }
}

