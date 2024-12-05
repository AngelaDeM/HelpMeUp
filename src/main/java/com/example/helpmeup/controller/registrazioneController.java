package com.example.helpmeup.controller;

import com.example.helpmeup.model.Volontario;
import com.example.helpmeup.service.UtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
public class registrazioneController {

    private final UtenteService utenteService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public registrazioneController(UtenteService utenteService, PasswordEncoder passwordEncoder) {
        this.utenteService = utenteService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new Volontario()); // Puoi decidere un valore predefinito
        return "register"; // Restituisce la vista 'register.html'
    }

    @PostMapping("/register")
    public String registerUser(@RequestParam("user_type") String userType,
                               @RequestParam("confirm-password") String confirmPassword,
                               @RequestParam("province") String provincia,
                               @RequestParam("city") String citta,
                               @RequestParam("address") String via,
                               @RequestParam("number") String numeroCivico,
                               @ModelAttribute("user") Volontario utente,
                               Model model) {

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
            return "register";
        }

        // Validazione del cognome
        if (!utente.getCognome().matches("^[a-zA-Z]{1,50}$")) {
            model.addAttribute("error", "Errore: il cognome deve contenere solo lettere e avere un massimo di 50 caratteri!");
            model.addAttribute("user", utente);
            return "register";
        }

        // Validazione dello username
        if (!utente.getUsername().matches("^[a-zA-Z0-9]{1,20}$")) {
            model.addAttribute("error", "Errore: lo username deve contenere solo lettere e numeri e avere un massimo di 20 caratteri!");
            model.addAttribute("user", utente);
            return "register";
        }

        // Validazione del numero di telefono
        if (!utente.getNumeroTelefono().matches("^\\d{10}$")) {
            model.addAttribute("error", "Errore: il numero di telefono deve contenere solo numeri e avere esattamente 10 cifre!");
            model.addAttribute("user", utente);
            return "register";
        }

        // Validazione dell'email
        if (!utente.getEmail().matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
            model.addAttribute("error", "Errore: l'email deve rispettare il formato classico (esempio: ciao@gmail.com)!");
            model.addAttribute("user", utente);
            return "register";
        }

        // Validazione della password
        if (!utente.getPassword().matches("^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{5,}$")) {
            model.addAttribute("error", "Errore: la password deve contenere almeno 5 caratteri, includere lettere, numeri e simboli!");
            model.addAttribute("user", utente);
            return "register";
        }

        // Validazione della conferma della password
        if (!utente.getPassword().equals(confirmPassword)) {
            model.addAttribute("error", "Errore: le password non corrispondono!");
            model.addAttribute("user", utente);
            return "register";
        }

        // Controllo se la data di nascita è nel futuro
        if (utente.getDataNascita().isAfter(LocalDate.now())) {
            model.addAttribute("error", "Errore: la data di nascita non può essere nel futuro!");
            model.addAttribute("user", utente);
            return "register";
        }

        // Controlla il formato dell'indirizzo
        String indirizzoPattern = "^[a-zA-Z]+, [a-zA-Z]+, [a-zA-Z0-9\\s]+, \\d+[A-Za-z0-9]*$";
        Pattern pattern = Pattern.compile(indirizzoPattern);
        Matcher matcher = pattern.matcher(indirizzo);

        if (!matcher.matches()) {
            model.addAttribute("error", "L'indirizzo non è nel formato corretto (Provincia, Città, via, numerocivico)!");
            model.addAttribute("user", utente);
            return "register";
        }

        // Imposta la password codificata
        utente.setPassword(passwordEncoder.encode(utente.getPassword()));

        // Salva l'utente nel database
        utenteService.salvaUtente(utente);

        // Reindirizza alla pagina di successo
        return "redirect:/success";  // Reindirizza a /success dopo il salvataggio
    }
}
