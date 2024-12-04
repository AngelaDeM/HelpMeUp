package com.example.helpmeup.controller;

import com.example.helpmeup.model.Assistito;
import com.example.helpmeup.model.Utente;
import com.example.helpmeup.model.Volontario;
import com.example.helpmeup.service.UtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
public class registrazioneController {

    private final UtenteService utenteService;
    @Autowired
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public registrazioneController(UtenteService utenteService, PasswordEncoder passwordEncoder) {
        this.utenteService = utenteService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new Volontario()); // Aggiungi un oggetto Utente vuoto al modello
        return "register"; // Restituisce la vista 'registrazione.html'
    }

    @PostMapping("/register")
    public String registerUser(
            @RequestParam("user_type") String userType,
            @RequestParam("confirm-password") String confirmPassword,
            @RequestParam("province") String provincia,
            @RequestParam("city") String citta,
            @RequestParam("address") String via,
            @RequestParam("number") String numeroCivico,
            @RequestParam("nome") String nome,
            @RequestParam("cognome") String cognome,
            @RequestParam("gender") String sesso,
            @RequestParam("phone") String numeroTelefono,
            @RequestParam("email") String email,
            @RequestParam("password") String password,
            @RequestParam("birthdate") LocalDate dataNascita,
            @RequestParam("username") String username,
            Model model) {

        // Validazione del nome
        if (!nome.matches("^[a-zA-Z]{1,50}$")) {
            model.addAttribute("error", "Errore: il nome deve contenere solo lettere e avere un massimo di 50 caratteri!");
            return "register"; // Torna alla pagina di registrazione
        }

        // Validazione del cognome
        if (!cognome.matches("^[a-zA-Z]{1,50}$")) {
            model.addAttribute("error", "Errore: il cognome deve contenere solo lettere e avere un massimo di 50 caratteri!");
            return "register"; // Torna alla pagina di registrazione
        }

        // Validazione dello username
        if (!username.matches("^[a-zA-Z0-9]{1,20}$")) {
            model.addAttribute("error", "Errore: lo username deve contenere solo lettere e numeri e avere un massimo di 20 caratteri!");
            return "register"; // Torna alla pagina di registrazione
        }

        // Validazione del numero di telefono
        if (!numeroTelefono.matches("^\\d{10}$")) {
            model.addAttribute("error", "Errore: il numero di telefono deve contenere solo numeri e avere esattamente 10 cifre!");
            return "register"; // Torna alla pagina di registrazione
        }

        // Validazione dell'email
        if (!email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
            model.addAttribute("error", "Errore: l'email deve rispettare il formato classico (esempio: ciao@gmail.com)!");
            return "register"; // Torna alla pagina di registrazione
        }

        // Validazione della password
        if (!password.matches("^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{5,}$")) {
            model.addAttribute("error", "Errore: la password deve contenere almeno 5 caratteri, includere lettere, numeri e simboli!");
            return "register"; // Torna alla pagina di registrazione
        }

        // Validazione della conferma della password
        if (!password.equals(confirmPassword)) {
            model.addAttribute("error", "Errore: le password non corrispondono!");
            return "register"; // Torna alla pagina di registrazione
        }

        // Controllo se la data di nascita è nel futuro
        if (dataNascita.isAfter(LocalDate.now())) {
            model.addAttribute("error", "Errore: la data di nascita non può essere nel futuro!");
            return "register"; // Torna alla pagina di registrazione
        }

        // Componi l'indirizzo
        String indirizzo = provincia + ", " + citta + ", " + via + ", " + numeroCivico;

        // Controlla il formato dell'indirizzo
        String indirizzoPattern = "^[a-zA-Z]+, [a-zA-Z]+, [a-zA-Z0-9\\s]+, \\d+[A-Za-z0-9]*$";
        Pattern pattern = Pattern.compile(indirizzoPattern);
        Matcher matcher = pattern.matcher(indirizzo);

        if (!matcher.matches()) {
            model.addAttribute("error", "L'indirizzo non è nel formato corretto (Provincia, Città, via, numerocivico)!");
            return "register"; // Torna alla pagina di registrazione
        }

        // Crea un'istanza concreta di Utente
        Utente utente;
        if ("Assistito".equals(userType)) {
            utente = new Assistito();
        } else {
            utente = new Volontario();
        }

        // Imposta i campi dell'utente
        utente.setNome(nome);
        utente.setCognome(cognome);
        utente.setUsername(username);
        utente.setSesso(sesso);
        utente.setNumeroTelefono(numeroTelefono);
        utente.setEmail(email);
        utente.setPassword(passwordEncoder.encode(password));
        utente.setDataNascita(dataNascita);
        utente.setIndirizzo(indirizzo);

        // Salva l'utente nel database
        utenteService.salvaUtente(utente);

        // Reindirizza alla pagina di successo
        return "redirect:/success";  // Reindirizza a /success dopo il salvataggio
    }
}
