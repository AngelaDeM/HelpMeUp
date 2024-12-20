package com.example.helpmeup.controller;

import com.example.helpmeup.model.*;
import com.example.helpmeup.service.UtenteService;
import com.example.helpmeup.service.VolontarioService;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Controller for managing the personal area of users.
 *
 * @author Domenico
 * @author ROBERTO-SCARPA
 * @author fimiani
 *
 */
@Controller
public class PersonalAreaController {
private final VolontarioService volontarioService;


    public PersonalAreaController(VolontarioService volontarioService) {
        this.volontarioService = volontarioService;
    }

    /**
     * Displays the personal area page for the user based on their type.
     * If the user is not logged in, redirects to the login page.
     * For users of type {@code Volontario}, additional information such as
     * points and rewards is displayed. For users of type {@code Assistito},
     * a specific view is returned.
     *
     * @param session the current HTTP session, used to retrieve the logged-in user.
     * @param model the model object used to bind attributes for the view.
     * @return the name of the view to be rendered, either the personal area for
     *         "volontario" or "assistito", or a redirect to the login page if no user is logged in.
     */
    @GetMapping("/area_utente")
    public String visualizzaAreaPersonale(HttpSession session, Model model) {
        // Recupero dell'utente dalla sessione (simulazione)
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
            return "AreaUtente/area_utente";  // Nome del template da visualizzare
        }
        else if (utente instanceof Assistito) {
            model.addAttribute("tipo", "assistito");
            // Any other information for the assistito
        }
        return "AreaUtente/AreaAssistito";  // Nome del template da visualizzare

    }

    /**
     * Retrieves the user's current points from the session and returns them in a map.
     *
     * @param session the current HTTP session, used to fetch the user's points.
     * @return a ResponseEntity containing a map with the key "punti" and the user's points as the value.
     */
    @GetMapping("/get_punti")
    public ResponseEntity<Map<String, Integer>> getPunti(HttpSession session) {
        int punti = (int) session.getAttribute("punti");  // Sostituisci con la logica per ottenere i punti
        Map<String, Integer> response = new HashMap<>();
        response.put("punti", punti);
        return ResponseEntity.ok(response);
    }

}