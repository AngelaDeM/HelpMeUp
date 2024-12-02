package com.example.helpmeup.service;

import com.example.helpmeup.model.Assistito;
import com.example.helpmeup.model.Utente;
import com.example.helpmeup.model.Volontario;
import com.example.helpmeup.repository.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UtenteRepository utenteRepository;

    public String validaERegistraUtente(
            String ruolo, String nome, String cognome, String username, String password,
            String dataNascita, String email, String indirizzo, String numeroTelefono, String certificazione) {

        // Validazione dei campi comuni
        String errore = validaCampiComuni(nome, cognome, username, password, dataNascita, email, indirizzo, numeroTelefono);
        if (errore != null) return errore;

        // Controllo unicità username ed email
        if (utenteRepository.existsByUsername(username)) return "Username già in uso.";
        if (utenteRepository.existsByEmail(email)) return "Email già in uso.";

        // Creazione oggetto in base al ruolo
        if (ruolo.equalsIgnoreCase("assistito")) {
            Assistito assistito = new Assistito(
                    null, nome, cognome, username, null, password, LocalDate.parse(dataNascita),
                    email, indirizzo, numeroTelefono
            );
            utenteRepository.save(assistito);
        } else if (ruolo.equalsIgnoreCase("volontario")) {
            Volontario volontario = new Volontario(
                    null, nome, cognome, username, null, password, LocalDate.parse(dataNascita),
                    email, indirizzo, numeroTelefono, 0, certificazione
            );
            utenteRepository.save(volontario);
        } else {
            return "Ruolo non valido.";
        }

        return "Registrazione avvenuta con successo.";
    }

    private String validaCampiComuni(
            String nome, String cognome, String username, String password, String dataNascita,
            String email, String indirizzo, String numeroTelefono) {

        if (nome == null || nome.trim().isEmpty()) return "Nome obbligatorio.";
        if (cognome == null || cognome.trim().isEmpty()) return "Cognome obbligatorio.";
        if (username == null || username.trim().isEmpty()) return "Username obbligatorio.";
        if (password == null || password.length() < 6) return "Password obbligatoria e deve contenere almeno 6 caratteri.";
        if (email == null || !email.contains("@")) return "Email non valida.";
        if (indirizzo == null || indirizzo.trim().isEmpty()) return "Indirizzo obbligatorio.";
        if (numeroTelefono == null || !numeroTelefono.matches("\\d{10}")) return "Numero di telefono deve contenere 10 cifre.";

        try {
            LocalDate dataDiNascita = LocalDate.parse(dataNascita);
            if (dataDiNascita.isAfter(LocalDate.now())) return "La data di nascita non può essere nel futuro.";
            if (dataDiNascita.isAfter(LocalDate.now().minusYears(18))) return "Devi avere almeno 18 anni.";
        } catch (DateTimeParseException e) {
            return "Data di nascita non valida. Usa il formato YYYY-MM-DD.";
        }

        return null; // Nessun errore
    }
}
