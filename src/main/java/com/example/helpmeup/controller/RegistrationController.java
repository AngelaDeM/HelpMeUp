package com.example.helpmeup.controller;

import com.example.helpmeup.model.Volontario;
import com.example.helpmeup.model.Assistito;
import com.example.helpmeup.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class RegistrationController {

    @Autowired
    private UserService userService;

    @GetMapping("/register")
    public String showRegistrationPage() {
        return "register";
    }

    @PostMapping("/register-choice")
    public String handleRegistrationChoice(@RequestParam("role") String role) {
        if (role.equals("volontario")) {
            return "register";
        } else {
            return "register-assistito";
        }
    }

    @PostMapping("/register-volontario")
    public String registerVolontario(Volontario volontario) {
        userService.saveVolontario(volontario);
        return "redirect:/login";
    }

    @PostMapping("/register-assistito")
    public String registerAssistito(Assistito assistito) {
        userService.saveAssistito(assistito);
        return "redirect:/login";
    }
}
