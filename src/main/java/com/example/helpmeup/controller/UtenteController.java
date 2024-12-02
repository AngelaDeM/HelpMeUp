package com.example.helpmeup.controller;

import com.example.helpmeup.model.Volontario;
import com.example.helpmeup.model.Assistito;
import com.example.helpmeup.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api")
public class UtenteController {

    @Autowired
    private UserService utenteService;

    // Registrazione del Volontario
    @PostMapping("/register/volontario")
    public ResponseEntity<String> registerVolontario(@RequestBody Volontario volontario) {

        // Verifica che la data di nascita non sia nel futuro
        if (volontario.getDataNascita().isAfter(LocalDate.now())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Errore: la data di nascita non può essere nel futuro!");
        }

        // Verifica se l'email è già presente nel database
        if (utenteService.verificaEmail(volontario.getEmail())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Errore: l'email è già registrata!");
        }

        // Verifica se lo username è già presente nel database
        if (utenteService.verificaUsername(volontario.getUsername())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Errore: lo username è già registrato!");
        }

        // Logiche specifiche per il Volontario (ad esempio, i punti)
        // Aggiungi eventuali personalizzazioni o logiche di gestione per il Volontario

        // Se tutte le verifiche passano, salva il Volontario nel database
        utenteService.saveVolontario(volontario);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Volontario registrato con successo!");
    }

    // Registrazione dell'Assistito
    @PostMapping("/register/assistito")
    public ResponseEntity<String> registerAssistito(@RequestBody Assistito assistito) {

        // Verifica che la data di nascita non sia nel futuro
        if (assistito.getDataNascita().isAfter(LocalDate.now())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Errore: la data di nascita non può essere nel futuro!");
        }

        // Verifica se l'email è già presente nel database
        if (utenteService.verificaEmail(assistito.getEmail())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Errore: l'email è già registrata!");
        }

        // Verifica se lo username è già presente nel database
        if (utenteService.verificaUsername(assistito.getUsername())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Errore: lo username è già registrato!");
        }

        // Logiche specifiche per l'Assistito
        // Aggiungi eventuali personalizzazioni o logiche di gestione per l'Assistito

        // Se tutte le verifiche passano, salva l'Assistito nel database
        utenteService.saveAssistito(assistito);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Assistito registrato con successo!");
    }
}
