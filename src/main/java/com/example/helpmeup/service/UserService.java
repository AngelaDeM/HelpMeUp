package com.example.helpmeup.service;

import com.example.helpmeup.model.Volontario;
import com.example.helpmeup.model.Assistito;
import com.example.helpmeup.repository.VolontarioRepository;
import com.example.helpmeup.repository.AssistitoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private VolontarioRepository volontarioRepository;

    @Autowired
    private AssistitoRepository assistitoRepository;

    // Salva un Volontario nel database
    public void saveVolontario(Volontario volontario) {
        volontarioRepository.save(volontario);
    }

    // Salva un Assistito nel database
    public void saveAssistito(Assistito assistito) {
        assistitoRepository.save(assistito);
    }

    // Verifica se un'email esiste già nel database (sia per Volontario che per Assistito)
    public boolean verificaEmail(String email) {
        return volontarioRepository.existsByEmail(email) || assistitoRepository.existsByEmail(email);
    }

    // Verifica se uno username esiste già nel database (sia per Volontario che per Assistito)
    public boolean verificaUsername(String username) {
        return volontarioRepository.existsByUsername(username) || assistitoRepository.existsByUsername(username);
    }
}
