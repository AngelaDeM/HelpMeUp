package com.example.helpmeup.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller

public class PagineController {

    public PagineController(){}

    @GetMapping("/index")
    public String mostraIndex() {
        return "/index";
    }

    @GetMapping("/chi_siamo")
    public String mostraChiSiammo() {
        return "Varie/chi_siamo";
    }

    @GetMapping("/learn_more")
    public String mostraLearn_more() {
        return "Varie/learn_more";
    }

    @GetMapping("/forum")
    public String mostraForum() {
        return "Varie/forum";
    }


@GetMapping("/punti")
public String mostraPunti() {
    return "Punti/punti";
}
}
