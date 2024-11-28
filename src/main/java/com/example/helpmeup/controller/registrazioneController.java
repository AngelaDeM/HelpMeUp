package com.example.helpmeup.controller;

import com.example.helpmeup.model.Utente;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class registrazioneController {

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new Utente());  // Aggiungi un oggetto User vuoto al modello
        return "registrazione";  // Restituisce la vista 'registrazione.html'
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute Utente Utente, String confirmPassword, Model model) {
        // Validazione della registrazione (puoi aggiungere la logica di validazione qui)
        if (!Utente.getPassword().equals(confirmPassword)) {
            model.addAttribute("error", "Le password non corrispondono!");
            return "registrazione";  // Ritorna alla pagina di registrazione con il messaggio di errore
        }

        // Se la validazione è andata a buon fine, salva l'utente e fai il redirect
        // userService.save(user);  // Salva l'utente nel database
        return "redirect:/success";  // Redirect alla pagina di successo
    }
}
