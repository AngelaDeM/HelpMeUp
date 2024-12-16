package com.example.helpmeup.service;

import com.example.helpmeup.model.Utente;
import com.example.helpmeup.model.Volontario;
import com.example.helpmeup.repository.UtenteRepository;
import com.example.helpmeup.repository.VolontarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service class for managing Volontario entities.
 *
 * @author Domenico
 */
@Service
public class VolontarioService {

    private final VolontarioRepository volontarioRepository;

    /**
     * Constructor for VolontarioService.
     *
     * @param volontarioRepository the repository for managing Volontario entities
     */
    @Autowired
    public VolontarioService(VolontarioRepository volontarioRepository) {
        this.volontarioRepository = volontarioRepository;
    }

    /**
     * Saves a Volontario entity.
     *
     * @param utente the Volontario entity to save
     */
    public void salvaUtente(Volontario utente) {
        volontarioRepository.save(utente);
    }

    /**
     * Checks if an email is already in use by a Volontario.
     *
     * @param email the email to check
     * @return true if the email is in use, false otherwise
     */
    public boolean verificaEmail(String email) {
        return volontarioRepository.existsByEmail(email);
    }

    /**
     * Checks if a username is already in use by a Volontario.
     *
     * @param username the username to check
     * @return true if the username is in use, false otherwise
     */
    public boolean verificaUsername(String username) {
        return volontarioRepository.existsByUsername(username);
    }

    /**
     * Retrieves the points associated with a Volontario identified by their username.
     *
     * @param username the username of the Volontario whose points are to be retrieved
     * @return the points of the specified Volontario
     */
    public int getPuntiVolontario(String username) {
        return volontarioRepository.getPuntiVolontario(username);
    }
}