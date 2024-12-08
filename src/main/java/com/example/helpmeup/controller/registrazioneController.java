package com.example.helpmeup.controller;

import com.example.helpmeup.model.Assistito;
import com.example.helpmeup.model.Volontario;
import com.example.helpmeup.repository.AssistitoRepository;
import com.example.helpmeup.service.AssistitoService;
import com.example.helpmeup.service.VolontarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
public class registrazioneController {

    private final VolontarioService volontarioService;
    private final AssistitoService assistitoService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public registrazioneController(VolontarioService volontarioService, AssistitoService assistitoService, PasswordEncoder passwordEncoder, AssistitoRepository assistitoRepository) {
        this.volontarioService = volontarioService;
        this.assistitoService = assistitoService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/registrazione")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new Volontario());
        return "registrazione";
    }

    @PostMapping("/registrazione")
    public String registerUser(@RequestParam("user_type") String userType,
                               @RequestParam("confirm-password") String confirmPassword,
                               @RequestParam("province") String provincia,
                               @RequestParam("city") String citta,
                               @RequestParam("address") String via,
                               @RequestParam("number") String numeroCivico,
                               @ModelAttribute("user") Volontario utente,
                               Model model) {
        //stampa di prova
        System.out.println("User type: " + userType);
        // Mappa i campi dal form all'oggetto utente
        utente.setNome(utente.getNome());
        utente.setCognome(utente.getCognome());
        utente.setSesso(utente.getSesso());
        utente.setDataNascita(utente.getDataNascita());
        String indirizzo = provincia + ", " + citta + ", " + via + ", " + numeroCivico;
        utente.setIndirizzo(indirizzo);
        utente.setNumeroTelefono(utente.getNumeroTelefono());
        utente.setUsername(utente.getUsername());
        utente.setEmail(utente.getEmail());
        utente.setPassword(utente.getPassword());

        // Validazione del nome
        if (!utente.getNome().matches("^[a-zA-Z]{1,50}$")) {
            model.addAttribute("error", "Errore: il nome deve contenere solo lettere e avere un massimo di 50 caratteri!");
            model.addAttribute("user", utente);
            return "registrazione";
        }

        // Validazione del cognome
        if (!utente.getCognome().matches("^[a-zA-Z]{1,50}$")) {
            model.addAttribute("error", "Errore: il cognome deve contenere solo lettere e avere un massimo di 50 caratteri!");
            model.addAttribute("user", utente);
            return "registrazione";
        }

        // Validazione dello username
        if (!utente.getUsername().matches("^[a-zA-Z0-9]{1,20}$")) {
            model.addAttribute("error", "Errore: lo username deve contenere solo lettere e numeri e avere un massimo di 20 caratteri!");
            model.addAttribute("user", utente);
            return "registrazione";
        }

        // Validazione del numero di telefono
        if (!utente.getNumeroTelefono().matches("^\\d{10}$")) {
            model.addAttribute("error", "Errore: il numero di telefono deve contenere solo numeri e avere esattamente 10 cifre!");
            model.addAttribute("user", utente);
            return "registrazione";
        }

        // Validazione dell'email
        if (!utente.getEmail().matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
            model.addAttribute("error", "Errore: l'email deve rispettare il formato classico (esempio: ciao@gmail.com)!");
            model.addAttribute("user", utente);
            return "registrazione";
        }

        // Validazione della password
        if (utente.getPassword().length() < 5) {
            model.addAttribute("error", "Errore: la password deve contenere almeno 5 caratteri!");
            model.addAttribute("user", utente);
            return "registrazione";
        }

        // Validazione della conferma della password
        if (!utente.getPassword().equals(confirmPassword)) {
            model.addAttribute("error", "Errore: le password non corrispondono!");
            model.addAttribute("user", utente);
            return "registrazione";
        }

        // Controllo se la data di nascita è nel futuro
        if (utente.getDataNascita().isAfter(LocalDate.now())) {
            model.addAttribute("error", "Errore: la data di nascita non può essere nel futuro!");
            model.addAttribute("user", utente);
            return "registrazione";
        }

        // Controlla il formato dell'indirizzo
        String indirizzoPattern = "^[a-zA-Z\\s]+, [a-zA-Z\\s]+, [a-zA-Z\\s]+, \\d+[0-9]*$";
        Pattern pattern = Pattern.compile(indirizzoPattern);
        Matcher matcher = pattern.matcher(indirizzo);

        if (!matcher.matches()) {
            model.addAttribute("error", "L'indirizzo non è nel formato corretto (Provincia, Città, via, numerocivico)!");
            model.addAttribute("user", utente);
            return "registrazione";
        }

        // Imposta la password codificata
        utente.setPassword(passwordEncoder.encode(utente.getPassword()));

            // Check if email or username already exists
            if (volontarioService.verificaEmail(utente.getEmail()) || assistitoService.verificaEmail(utente.getEmail())) {
                model.addAttribute("error", "Errore: l'email è già in uso!");
                model.addAttribute("user", utente);
                return "registrazione";
            }

            if (volontarioService.verificaUsername(utente.getUsername()) || assistitoService.verificaUsername(utente.getUsername())) {
                model.addAttribute("error", "Errore: lo username è già in uso!");
                model.addAttribute("user", utente);
                return "registrazione";
            }

        // Create the appropriate user type
        if ("volontario".equalsIgnoreCase(userType)) {
            Volontario volontario = new Volontario(utente, 0, new ArrayList<>());
            volontarioService.salvaUtente(volontario);
        } else if ("assistito".equalsIgnoreCase(userType)) {
            Assistito assistito = new Assistito(utente);
            assistitoService.salvaAssistito(assistito);
        } else {
            model.addAttribute("error", "Errore: tipo di utente non valido!");
            model.addAttribute("user", utente);
            return "registrazione";
        }

        // Reindirizza alla pagina di successo
        return "redirect:/success"; // Reindirizza a /success dopo il salvataggio
    }
    @GetMapping("/success")
    public String showSuccessPage() {
        return "success"; // Reindirizza a /success dopo il salvataggio
    }
}
