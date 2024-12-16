package com.example.helpmeup.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * The homeController class is responsible for handling requests to the homepage of the application.
 * It maps the root URL ("/") to the main page.
 */
@Controller
public class homeController {

    /**
     * Handles requests to the homepage of the application.
     * Maps the root URL ("/") to the main page.
     *
     * @return the name of the HTML file (without extension) to be rendered as the homepage
     */
    @GetMapping("/")
    public String homepage() {
        return "index"; // Nome del file HTML senza estensione
    }
}
