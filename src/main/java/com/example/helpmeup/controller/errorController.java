package com.example.helpmeup.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Controller in charge of handling error pages.
 * Implements {@code ErrorController} to customize error handling behavior within the application.
 * Specifically, it intercepts requests to the error endpoint and provides a custom response.
 */
@Controller
public class errorController implements ErrorController {

    // Gestione dell'errore 404 (pagina non trovata)
    @RequestMapping("/error")
    public String handleError() {
        // Qui puoi anche loggare l'errore, se necessario
        return "error/404";  // Restituisce la pagina error/404.html
    }
}
