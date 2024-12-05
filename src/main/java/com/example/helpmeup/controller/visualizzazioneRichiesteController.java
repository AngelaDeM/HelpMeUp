package com.example.helpmeup.controller;

import com.example.helpmeup.model.Richiesta;
import com.example.helpmeup.repository.RichiestaRepository;
import com.example.helpmeup.service.RichiestaService;
import jakarta.persistence.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.List;

@Controller
@SessionAttributes("richiesta")
//TODO: Gestire il controllo degli accessi, in modo che solo gli utenti volontari possano visualizzare le richieste
public class visualizzazioneRichiesteController {
    private static final Logger logger = LoggerFactory.getLogger(visualizzazioneRichiesteController.class);
    private final RichiestaService richiestaService;
    public visualizzazioneRichiesteController(RichiestaRepository richiestaRepository) {
        this.richiestaService = new RichiestaService(richiestaRepository);
    }

    //Restituisce tutte le richieste presenti nel database
    @GetMapping("/findAllRichieste")
    public @ResponseBody List<Richiesta> getAllRichieste() {
        return richiestaService.getAllRichieste();
    }
    //Restituisce una richiesta specifica
    @GetMapping("/findRichiestaById")
    public Richiesta getRichiestaById(int id){
        return richiestaService.getRichiestaById(id);
    }
}
