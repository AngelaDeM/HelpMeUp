package com.example.helpmeup.service;

import com.example.helpmeup.model.Assistito;
import com.example.helpmeup.repository.AssistitoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AssistitoService {

    private final AssistitoRepository assistitoRepository;

    @Autowired
    public AssistitoService(AssistitoRepository assistitoRepository) {
        this.assistitoRepository = assistitoRepository;
    }

    public void salvaAssistito(Assistito assistito) {
        assistitoRepository.save(assistito);
    }

    public boolean verificaEmail(String email) {
        return assistitoRepository.existsByEmail(email);
    }

    public boolean verificaUsername(String username) {
        return assistitoRepository.existsByUsername(username);
    }
}