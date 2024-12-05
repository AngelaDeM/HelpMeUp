package com.example.helpmeup.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class homeController {

    @GetMapping("/")
    public String homepage() {
        return "index"; // Nome del file HTML senza estensione
    }
}
