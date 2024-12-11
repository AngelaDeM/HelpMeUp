package com.example.helpmeup.controller;

import com.example.helpmeup.model.Assistito;
import com.example.helpmeup.model.Volontario;
import com.example.helpmeup.model.Utente;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Controller for managing the personal area of users.
 *
 * @author Domenico
 *
 */
@Controller
public class PersonalAreaController {

    /**
     * Displays the personal area of the user.
     *
     * @param session the HTTP session to retrieve the user
     * @param model the model to pass data to the view
     * @return the name of the view to display
     */
    @GetMapping("/area_utente")
    public String visualizzaAreaPersonale(HttpSession session, Model model) {
        // Retrieve the user from the session (simulation)
        Utente utente = (Utente) session.getAttribute("utente");

        if (utente == null) {
            return "redirect:/login";  // Redirect if there is no user in session
        }

        model.addAttribute("utente", utente);

        // Determine if the user is a Volontario or Assistito
        if (utente instanceof Volontario) {
            model.addAttribute("tipo", "volontario");
            Volontario volontario = (Volontario) utente;
            model.addAttribute("punti", volontario.getPunti());
            model.addAttribute("premi", volontario.getPremi());
        } else if (utente instanceof Assistito) {
            model.addAttribute("tipo", "assistito");
            // Any other information for the assistito
        }

        return "AreaUtente/area_utente";  // Name of the template to display
    }
}