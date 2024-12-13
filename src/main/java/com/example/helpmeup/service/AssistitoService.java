package com.example.helpmeup.service;

import com.example.helpmeup.model.Assistito;
import com.example.helpmeup.repository.AssistitoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service class for managing Assistito entities.
 * Provides methods for saving and verifying Assistito information.
 *
 * @author Domenico
 */
@Service
public class AssistitoService {

    private final AssistitoRepository assistitoRepository;

    /**
     * Constructor for AssistitoService.
     *
     * @param assistitoRepository the repository for managing Assistito entities
     */
    @Autowired
    public AssistitoService(AssistitoRepository assistitoRepository) {
        this.assistitoRepository = assistitoRepository;
    }

    /**
     * Saves an Assistito entity.
     *
     * @param assistito the Assistito entity to save
     */
    public void salvaAssistito(Assistito assistito) {
        assistitoRepository.save(assistito);
    }

    /**
     * Checks if an email is already in use by an Assistito.
     *
     * @param email the email to check
     * @return true if the email is in use, false otherwise
     */
    public boolean verificaEmail(String email) {
        return assistitoRepository.existsByEmail(email);
    }

    /**
     * Checks if a username is already in use by an Assistito.
     *
     * @param username the username to check
     * @return true if the username is in use, false otherwise
     */
    public boolean verificaUsername(String username) {
        return assistitoRepository.existsByUsername(username);
    }
}