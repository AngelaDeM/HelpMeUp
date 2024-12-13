package com.example.helpmeup.controller;

import com.example.helpmeup.model.Assistito;
import com.example.helpmeup.model.Volontario;
import com.example.helpmeup.model.Utente;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PersonalAreaController {

    @GetMapping("/area_utente")
    public String visualizzaAreaPersonale(HttpSession session, Model model) {
        // Recupero dell'utente dalla sessione (simulazione)
        Utente utente = (Utente) session.getAttribute("utente");

        if (utente == null) {
            return "redirect:/login";  // Reindirizza se non c'è l'utente in sessione
        }

        model.addAttribute("utente", utente);

        // Determina se l'utente è un Volontario o Assistito
        if (utente instanceof Volontario) {
            model.addAttribute("tipo", "volontario");
            Volontario volontario = (Volontario) utente;
            model.addAttribute("punti", volontario.getPunti());
            model.addAttribute("premi", volontario.getPremi());
            return "AreaUtente/area_utente";  // Nome del template da visualizzare
        }
        else if (utente instanceof Assistito) {
            model.addAttribute("tipo", "assistito");

            // Eventuali altre informazioni per l'assistito
        }
        return "AreaUtente/AreaAssistito";  // Nome del template da visualizzare

    }
}
