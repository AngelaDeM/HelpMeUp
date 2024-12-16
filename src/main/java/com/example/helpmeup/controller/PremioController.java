package com.example.helpmeup.controller;

import com.example.helpmeup.model.Premio;
import com.example.helpmeup.model.Utente;
import com.example.helpmeup.model.Volontario;
import com.example.helpmeup.service.PremioService;
import com.example.helpmeup.service.UtenteService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import java.sql.Timestamp;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Controller per la gestione dei premi nella piattaforma HelpMeUp.
 * Consente la creazione, visualizzazione e riscatto dei premi.
 *
 * @author ROBERTO-SCARPA
 * @author fimiani
 */
@Controller
@RequestMapping("/api/premio")
@Validated
public class PremioController {

    private final PremioService premioService;
    private final UtenteService utenteService;

    /**
     * Costruttore del controller.
     *
     * @param premioService il servizio per la gestione dei premi
     * @param utenteService il servizio per la gestione degli utenti
     */
    public PremioController(PremioService premioService, UtenteService utenteService) {
        this.premioService = premioService;
        this.utenteService = utenteService;
    }


    /**
     * Gestisce il riscatto di un premio da parte di un volontario.
     *
     * @param dati una mappa contenente i dati del premio e dell'utente
     * @return una risposta HTTP che indica il risultato dell'operazione
     * @throws IllegalArgumentException se si verifica un errore durante il riscatto
     */
    @GetMapping("/riscatta")
    public String riscattaPremio(@Valid @RequestParam Map<String, String> dati, HttpSession session,Model model) {
        try {
            String id_premio = dati.get("premio");
            Utente utente = (Utente) session.getAttribute("utente");
            String user = utente.getUsername();
            Volontario v = utenteService.getVolontarioByUsername(user);
            Premio p = premioService.getPremioByNome(id_premio);
            int pt = v.getPunti();
            int pr = p.getPuntiRichiesti();

            if (pt >= pr) {
                premioService.riscattaPremio(id_premio, user);
                v.removePunti(pr);
                pt = pt - pr;
                int nuovipunti= (int) session.getAttribute("punti");
                nuovipunti = nuovipunti-pr;
                session.setAttribute("punti",nuovipunti);
                utenteService.updatePuntiVolontario(v.getUsername(), pt);
                model.addAttribute("tipo","Success.");
                model.addAttribute("message","Premio riscattato con successo.");
            } else {
                model.addAttribute("tipo", "Error");
                model.addAttribute("message", "Non hai abbastanza punti per riscattare il premio.");
            }
        } catch (Exception e) {
            model.addAttribute("tipo", "Error");
            model.addAttribute("message","Errore durante il riscatto del premio");
        }
        return "/Premio/visualizza_premi";
    }

    /**
     * Mostra il form per aggiungere un nuovo premio.
     *
     * @return il nome della pagina HTML del form di creazione
     */
    @GetMapping("/aggiungi")
    public String mostraFormCreazione() {
        return "Premio/aggiungi_premio";
    }

    /**
     * Crea un nuovo premio e lo salva nel database.
     *
     * @param dati una mappa contenente i dati del premio
     * @return una risposta HTTP che indica il risultato dell'operazione
     */
    @PostMapping("/aggiungi")
    public ResponseEntity<String> creaPremio(@Valid @RequestParam Map<String, String> dati) {
        String nome = dati.get("nome");
        String descrizione = dati.get("descrizione");
        int punti = Integer.parseInt(dati.get("punti"));
        Premio p = new Premio(nome, descrizione, punti);
        premioService.InsertPremio(p);
        return ResponseEntity.ok("Premio aggiunto con successo.");
    }

    /**
     * Mostra la pagina HTML per visualizzare tutti i premi.
     *
     * @return il nome della pagina HTML
     */
    @GetMapping("/getAll")
    public String mostraPremi(HttpSession session, Model model) {
        Volontario u = (Volontario) session.getAttribute("utente");
        model.addAttribute("utente", u);
        return "Premio/visualizza_premi";
    }

    /**
     * Restituisce una lista di tutti i premi disponibili.
     *
     * @return una risposta HTTP contenente la lista dei premi
     */
    @GetMapping("/visualizza")
    public ResponseEntity<List<Premio>> visualizzaTuttiIPremi(HttpSession session) {
        List<Premio> premi = premioService.getAllPremi();
        Utente u = (Utente) session.getAttribute("utente");
        List<Premio> premiMinus = premioService.getAllByUser(u.getUsername());
        premi.removeAll(premiMinus);
        return ResponseEntity.ok(premi);
    }

    /**
     * Restituisce una lista di premi riscattati da un utente specifico.
     *
     * @param dati una mappa contenente il nome dell'utente
     * @return una risposta HTTP contenente la lista di premi riscattati
     */
    @GetMapping("/visualizzaByUtente")
    public ResponseEntity<List<Object[]>> visualizzaPremiByUtente(@Valid @RequestBody Map<String, String> dati,HttpSession sessione) {
        Utente u = (Utente) sessione.getAttribute("utente");
        String utente = u.getUsername();
        List<Object[]> premi = premioService.getAllPremiByUser(utente);

        // Formatter per ottenere solo "yyyy-MM-dd HH:mm:ss"
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        List<Object[]> premiFormattati = new ArrayList<>();

        for (Object[] premio : premi) {
            String nome = (String) premio[0];
            String descrizione = (String) premio[1];
            Timestamp dataRiscattoObj = (Timestamp) premio[2];

            // Formatta la data e ora senza fuso orario
            String dataRiscattoFormatted = dataRiscattoObj.toLocalDateTime().format(formatter);

            // Crea un array di oggetti formattati
            Object[] premioFormattato = {nome, descrizione, dataRiscattoFormatted};

            premiFormattati.add(premioFormattato);
        }

        return ResponseEntity.ok(premiFormattati);
    }
}
