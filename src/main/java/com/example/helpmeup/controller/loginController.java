package com.example.helpmeup.controller;

import com.example.helpmeup.model.Utente;
import com.example.helpmeup.model.Volontario;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.support.SessionStatus;
import com.example.helpmeup.repository.UtenteRepository;
@Controller
@SessionAttributes("utente")
public class loginController {
    private final UtenteRepository utenteRepository;

    public loginController(UtenteRepository utenteRepository) {
        this.utenteRepository = utenteRepository;
    }

    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

    @PostMapping("/login")
    public String loginUser(@RequestParam String username, @RequestParam String password, Model model) {
        Utente utente = utenteRepository.findByUsername(username);

        //stampa di prova
        System.out.println("User: " + utente.getNome());

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

        return "redirect:/success"; // Reindirizza alla homepage
    }
    @GetMapping("/logout")
    public String logoutUser(SessionStatus sessionStatus) {
        sessionStatus.setComplete(); // Completa la sessione
        return "redirect:/login"; // Reindirizza alla pagina di login
    }
}