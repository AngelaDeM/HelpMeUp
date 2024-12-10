package com.example.helpmeup.controller;

import com.example.helpmeup.model.Premio;
import com.example.helpmeup.model.Utente;
import com.example.helpmeup.model.Volontario;
import com.example.helpmeup.service.PremioService;
import com.example.helpmeup.service.UtenteService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
     * Mostra il form per riscattare un premio.
     *
     * @return il nome della pagina HTML del form di riscatto
     */
    @GetMapping("/riscatta")
    public String mostraFormRiscatto() {
        return "Premio/riscatta_premio";
    }

    /**
     * Gestisce il riscatto di un premio da parte di un volontario.
     *
     * @param dati una mappa contenente i dati del premio e dell'utente
     * @return una risposta HTTP che indica il risultato dell'operazione
     * @throws IllegalArgumentException se si verifica un errore durante il riscatto
     */
    @PostMapping("/riscatta")
    public ResponseEntity<?> riscattaPremio(@Valid @RequestParam Map<String, String> dati) {
        try {
            String id_premio = dati.get("premio");
            String utente = dati.get("utente");
            Volontario v = utenteService.getVolontarioByUsername(utente);
            Premio p = premioService.getPremioByNome(id_premio);
            int pt = v.getPunti();
            int pr = p.getPuntiRichiesti();

            if (pt >= pr) {
                premioService.riscattaPremio(id_premio, utente);
                v.removePunti(pr);
                pt = pt - pr;
                utenteService.updatePuntiVolontario(v.getUsername(), pt);
                return ResponseEntity.ok("Premio riscattato con successo.");
            } else {
                String messaggio = "Non hai abbastanza punti per riscattare questo premio. "
                        + "Punti richiesti: " + p.getPuntiRichiesti() + ", punti disponibili: " + v.getPunti();
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body(messaggio);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Errore durante il riscatto del premio: " + e.getMessage());
        }
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
    public String mostraPremi() {
        return "Premio/visualizza_premi";
    }

    /**
     * Restituisce una lista di tutti i premi disponibili.
     *
     * @return una risposta HTTP contenente la lista dei premi
     */
    @GetMapping("/visualizza")
    public ResponseEntity<List<Premio>> visualizzaTuttiIPremi() {
        List<Premio> premi = premioService.getAllPremi();
        return ResponseEntity.ok(premi);
    }

    /**
     * Mostra la pagina HTML per visualizzare i premi riscattati da un utente.
     *
     * @return il nome della pagina HTML
     */
    @GetMapping("/visualizzaByUtente")
    public String mostraPremiByUser() {
        return "Premio/visualizza_premi_utente";
    }

    /**
     * Restituisce una lista di premi riscattati da un utente specifico.
     *
     * @param dati una mappa contenente il nome dell'utente
     * @return una risposta HTTP contenente la lista di premi riscattati
     */
    @PostMapping("/visualizzaByUtente")
    public ResponseEntity<List<Object[]>> visualizzaPremiByUtente(@Valid @RequestBody Map<String, String> dati) {
        String utente = dati.get("utente");
        System.out.println(utente);
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
