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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/api/premio")
@Validated
public class PremioController {

    private final PremioService premioService;
    private final UtenteService utenteService;

    public PremioController(PremioService premioService, UtenteService utenteService) {
        this.premioService = premioService;

        this.utenteService = utenteService;
    }

    @GetMapping("/riscatta")
    public String mostraFormRiscatto() {
        return "Premio/riscatta_premio";
    }

    @PostMapping("/riscatta")
    public ResponseEntity<?> riscattaPremio(@Valid @RequestParam Map<String, String> dati) {
        try{
            String id_premio = dati.get("premio");
            String utente = dati.get("utente");
            Volontario v =  utenteService.getVolontarioByUsername(utente);
            Premio p = premioService.getPremioByNome(id_premio);
            int pt= v.getPunti();

            int pr=p.getPuntiRichiesti();

            if(pt>=pr){
                premioService.riscattaPremio(id_premio,utente);
                v.removePunti(pr);
                pt=pt-pr;
                utenteService.updatePuntiVolontario(v.getUsername(),pt);
                return ResponseEntity.ok("Premio riscattato con successo.");
            }else{
                String messaggio = "Non hai abbastanza punti per riscattare questo premio. "
                        + "Punti richiesti: " + p.getPuntiRichiesti() + ", punti disponibili: " + v.getPunti();
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body(messaggio);
            }
        }
        catch(Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Errore durante il riscatto del premio: " + e.getMessage());
        }

    }


    @GetMapping("/aggiungi")
    public String mostraFormCreazione() {
        return "Premio/aggiungi_premio";
    }

    @PostMapping("/aggiungi")
    public ResponseEntity<String>   creaPremio(@Valid @RequestParam Map<String, String> dati) {
        String nome = dati.get("nome");
        String descrizione = dati.get("descrizione");
        int punti = Integer.parseInt(dati.get("punti"));
        Premio p = new Premio(nome,descrizione,punti);
        premioService.InsertPremio(p);
        return ResponseEntity.ok("Premio aggiunto con successo.");
    }

    @GetMapping("/getAll")
    public String mostraPremi() {
        return "Premio/visualizza_premi";
    }

    @GetMapping("/visualizza")
    public ResponseEntity<List<Premio>> visualizzaTuttiIPremi() {
        List<Premio> premi = premioService.getAllPremi();
        System.out.println(premi);  // Log per vedere cosa restituisce l'API
        return ResponseEntity.ok(premi);
    }

    @GetMapping("/visualizzaByUtente")
    public String mostraPremiByUser() {
        return "Premio/visualizza_premi_utente";
    }



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