package com.example.helpmeup.service;

import com.example.helpmeup.model.Utente;
import com.example.helpmeup.repository.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UtenteService {

    @Autowired
    private UtenteRepository utenteRepository;

    @Transactional
    public void salvaUtente(Utente utente) {
        utenteRepository.save(utente);
    }

    public boolean verificaEmail(String email) {
        return utenteRepository.existsByEmail(email);
    }

    public boolean verificaUsername(String username) {
        return utenteRepository.existsByUsername(username);
    }
}