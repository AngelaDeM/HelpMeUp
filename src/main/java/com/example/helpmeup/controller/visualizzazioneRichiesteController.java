package com.example.helpmeup.controller;

import com.example.helpmeup.model.Richiesta;
import com.example.helpmeup.repository.RichiestaRepository;
import jakarta.persistence.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.List;

@Controller
@SessionAttributes("richiesta")
public class visualizzazioneRichiesteController {
    private final RichiestaRepository richiestaRepository;
    public visualizzazioneRichiesteController(RichiestaRepository richiestaRepository) {
        this.richiestaRepository = richiestaRepository;
    }

    //Restituisce tutte le richieste presenti nel database
    @GetMapping("/findAllRichieste")
    public List<Richiesta> getAllRichieste() {
        return richiestaRepository.findAll();
    }

    //Restituisce una richiesta specifica
    @GetMapping("/findRichiestaById")
    public Richiesta getRichiestaById(int id){
        return richiestaRepository.findById(id).get();
    }
}
