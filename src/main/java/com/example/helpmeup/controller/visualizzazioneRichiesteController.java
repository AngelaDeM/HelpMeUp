package com.example.helpmeup.controller;

import com.example.helpmeup.model.Richiesta;
import com.example.helpmeup.repository.RichiestaRepository;
import com.example.helpmeup.service.RichiestaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api")
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


    //Restituisce tutte le richieste di un determinato volontario
    @GetMapping("/findRichiesteByVolontario")
    public List<Richiesta> getRichiesteByVolontario(String username){
        System.out.println("Richieste by volontario");
        return richiestaService.getRichiesteByVolontario(username);
    }
}
