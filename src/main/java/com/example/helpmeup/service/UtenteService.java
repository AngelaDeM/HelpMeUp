package com.example.helpmeup.service;

import com.example.helpmeup.model.Volontario;
import com.example.helpmeup.repository.VolontarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UtenteService {

    private final VolontarioRepository volontarioRepository;
    static private final VolontarioRepository vRepository = null;
    @Autowired
    public UtenteService(VolontarioRepository volontarioRepository) {
        this.volontarioRepository = volontarioRepository;
    }

    public void salvaUtente(Volontario utente) {
        volontarioRepository.save(utente);
    }

    public static boolean exist(String utente) {
        return vRepository.existsByUsername(utente);
    }

    public boolean verificaEmail(String email) {
        return volontarioRepository.existsByEmail(email);
    }

    public boolean verificaUsername(String username) {
        return volontarioRepository.existsByUsername(username);
    }
}