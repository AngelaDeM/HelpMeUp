package com.example.helpmeup.controller;

import com.example.helpmeup.model.Utente;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.support.SessionStatus;
import com.example.helpmeup.repository.UtenteRepository;

/**
 * Controller per la gestione del login.
 *
 * @author Claudio
 */
@Controller
@SessionAttributes("utente")
public class loginController {
    private final UtenteRepository utenteRepository;

    /**
     * Costruttore del controller per il login.
     *
     * @param utenteRepository il repository degli utenti
     */
    public loginController(UtenteRepository utenteRepository) {
        this.utenteRepository = utenteRepository;
    }

    /**
     * Mostra il form di login.
     *
     * @return String
     */
    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

    /**
     * Gestisce il login dell'utente.
     *
     * @param username l'username dell'utente
     * @param password la password dell'utente
     * @param model il modello
     * @return String
     */
    @PostMapping("/login")
    public String loginUser(@RequestParam String username, @RequestParam String password, HttpSession session, Model model) {
        Utente utente = utenteRepository.findByUsername(username);


        if (utente == null) {
            // Handle user not found case
            return "redirect:/login?error";
        }

        if (!new BCryptPasswordEncoder().matches(password, utente.getPassword())) {
            // Handle wrong password case
            return "redirect:/login?error";
        }

        // Set the user in the session
        model.addAttribute("utente", utente);


        return "redirect:/"; // Reindirizza alla homepage
    }

    /**
     * Gestisce il logout dell'utente.
     *
     * @param sessionStatus lo stato della sessione
     * @return String
     */
    @GetMapping("/logout")
    public String logoutUser(SessionStatus sessionStatus) {
        sessionStatus.setComplete(); // Completa la sessione
        return "redirect:login"; // Reindirizza alla pagina di login
    }
}