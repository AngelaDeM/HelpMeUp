package com.example.helpmeup.controller;

import com.example.helpmeup.model.Assistito;
import com.example.helpmeup.model.Volontario;
import com.example.helpmeup.repository.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Map;

@RestController
@RequestMapping("/api/registrazione")
public class RegistrazioneController {

    @Autowired
    private UtenteRepository utenteRepository;

    @PostMapping
    public ResponseEntity<String> registraUtente(@RequestBody Map<String, String> dati) {
        String ruolo = dati.get("ruolo");
        String nome = dati.get("nome");
        String cognome = dati.get("cognome");
        String username = dati.get("username");
        String password = dati.get("password");
        String dataNascita = dati.get("dataNascita");
        String email = dati.get("email");
        String indirizzo = dati.get("indirizzo");
        String numeroTelefono = dati.get("numeroTelefono");
        String certificazione = dati.get("certificazione");

        // Controlli di validazione sui campi obbligatori
        if (nome == null || nome.trim().isEmpty()) return ResponseEntity.badRequest().body("Nome obbligatorio.");
        if (cognome == null || cognome.trim().isEmpty()) return ResponseEntity.badRequest().body("Cognome obbligatorio.");
        if (username == null || username.trim().isEmpty()) return ResponseEntity.badRequest().body("Username obbligatorio.");
        if (password == null || password.length() < 6) return ResponseEntity.badRequest().body("Password obbligatoria e deve contenere almeno 6 caratteri.");
        if (email == null || !email.contains("@")) return ResponseEntity.badRequest().body("Email non valida.");
        if (indirizzo == null || indirizzo.trim().isEmpty()) return ResponseEntity.badRequest().body("Indirizzo obbligatorio.");
        if (numeroTelefono == null || !numeroTelefono.matches("\\d{10}")) return ResponseEntity.badRequest().body("Numero di telefono deve contenere 10 cifre.");

        // Validazione data di nascita
        LocalDate dataDiNascita;
        try {
            dataDiNascita = LocalDate.parse(dataNascita);
            if (dataDiNascita.isAfter(LocalDate.now())) {
                return ResponseEntity.badRequest().body("La data di nascita non può essere nel futuro.");
            }
            if (dataDiNascita.isAfter(LocalDate.now().minusYears(18))) {
                return ResponseEntity.badRequest().body("Devi avere almeno 18 anni.");
            }
        } catch (DateTimeParseException e) {
            return ResponseEntity.badRequest().body("Data di nascita non valida. Usa il formato YYYY-MM-DD.");
        }

        // Controlli su unicità di username ed email
        if (utenteRepository.existsByUsername(username)) return ResponseEntity.badRequest().body("Username già in uso.");
        if (utenteRepository.existsByEmail(email)) return ResponseEntity.badRequest().body("Email già in uso.");

        // Creazione oggetto in base al ruolo
        if (ruolo.equalsIgnoreCase("assistito")) {
            Assistito assistito = new Assistito(
                    null, nome, cognome, username, null, password,
                    dataDiNascita, email, indirizzo, numeroTelefono
            );
            utenteRepository.save(assistito);
            return ResponseEntity.ok("Registrazione assistito avvenuta con successo.");
        } else {  // Ruolo volontario
            Volontario volontario = new Volontario(
                    null, nome, cognome, username, null, password,
                    dataDiNascita, email, indirizzo, numeroTelefono,
                    0, certificazione
            );
            utenteRepository.save(volontario);
            return ResponseEntity.ok("Registrazione volontario avvenuta con successo.");
        }
    }
}
