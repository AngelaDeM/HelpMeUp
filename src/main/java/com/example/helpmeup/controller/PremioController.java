package com.example.helpmeup.controller;

import com.example.helpmeup.repository.PremioRepository;
import com.example.helpmeup.service.PremioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping("/api/premio")
@Validated
public class PremioController {

    private final PremioService premioService;

    public PremioController(PremioService premioService) {
        this.premioService = premioService;
    }

    @GetMapping("/riscatta")
    public String mostraFormPubblicazione() {
        return "Premio/riscatta_premio";
    }

    @PostMapping("/riscatta")
    public ResponseEntity<String>   riscattaPremio(@RequestBody Map<String, String> dati) {
        String id_premio = dati.get("premio");
        String utente = dati.get("utente");
        premioService.riscattaPremio(id_premio,utente);
        return ResponseEntity.ok("Premio riscattato con successo.");
    }



}