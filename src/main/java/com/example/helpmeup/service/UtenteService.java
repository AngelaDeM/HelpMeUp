package com.example.helpmeup.service;

import com.example.helpmeup.model.Utente;
import com.example.helpmeup.repository.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UtenteService {

    @Autowired
    private UtenteRepository utenteRepository;

    // Verifica se l'email esiste nel database
    public boolean verificaEmail(String email) {
        return utenteRepository.existsByEmail(email);
    }

    // Verifica se lo username esiste nel database
    public boolean verificaUsername(String username) {
        return utenteRepository.existsByUsername(username);
    }

    // Salva un nuovo utente nel database
    public void salvaUtente(Utente utente) {
        utenteRepository.save(utente);
    }
}
