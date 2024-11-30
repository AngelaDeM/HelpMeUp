package com.example.helpmeup.controller;

import com.example.helpmeup.model.Utente;
import com.example.helpmeup.service.UtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
public class registrazioneController {

    private final UtenteService utenteService;

    @Autowired
    public registrazioneController(UtenteService utenteService) {
        this.utenteService = utenteService;
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new Utente()); // Aggiungi un oggetto Utente vuoto al modello
        return "registrazione"; // Restituisce la vista 'registrazione.html'
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute Utente utente, String confirmPassword, Model model) {
        // Validazione della registrazione
        if (!utente.getPassword().equals(confirmPassword)) {
            model.addAttribute("error", "Le password non corrispondono!");
            return "registrazione"; // Torna alla pagina di registrazione
        }

        // Controllo se la data di nascita è nel futuro
        if (utente.getDataNascita().isAfter(LocalDate.now())) {
            model.addAttribute("error", "Errore: la data di nascita non può essere nel futuro!");
            return "registrazione"; // Torna alla pagina di registrazione
        }

        // Verifica se l'email è già presente nel database
        if (utenteService.verificaEmail(utente.getEmail())) {
            model.addAttribute("error", "Errore: l'email è già registrata!");
            return "registrazione"; // Torna alla pagina di registrazione
        }

        // Verifica se lo username è già presente nel database
        if (utenteService.verificaUsername(utente.getUsername())) {
            model.addAttribute("error", "Errore: lo username è già registrato!");
            return "registrazione"; // Torna alla pagina di registrazione
        }

        // Verifica se l'indirizzo è nel formato corretto
        String indirizzo = utente.getIndirizzo();
        String indirizzoPattern = "^[a-zA-Z]+, [a-zA-Z]+, [a-zA-Z0-9\\s]+, \\d+[A-Za-z0-9]*$";
        Pattern pattern = Pattern.compile(indirizzoPattern);
        Matcher matcher = pattern.matcher(indirizzo);

        if (!matcher.matches()) {
            model.addAttribute("error", "L'indirizzo non è nel formato corretto (Provincia, Città, via, numerocivico)!");
            return "registrazione"; // Torna alla pagina di registrazione
        }

        //Cifratura della password
        utente.setPassword(new BCryptPasswordEncoder().encode(utente.getPassword()));

        // Salva l'utente nel database
        utenteService.salvaUtente(utente);

        // Reindirizza alla pagina di successo
        return "redirect:/success";  // Reindirizza a /success dopo il salvataggio
    }

    // Aggiungi un metodo per gestire la pagina di successo
    @GetMapping("/success")
    public String showSuccessPage() {
        return "success";  // Restituisce la vista success.html
    }
}
