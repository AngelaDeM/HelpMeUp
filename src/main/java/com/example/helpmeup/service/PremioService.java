package com.example.helpmeup.service;

import com.example.helpmeup.model.Premio;
import com.example.helpmeup.repository.PremioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PremioService {

    private final PremioRepository premioRepository;

    public PremioService(PremioRepository premioRepository) {
        this.premioRepository = premioRepository;
    }

    public void riscattaPremio(String premio,String utente) {
        premioRepository.riscattaPremio(premio,utente);
    }

    public  List<Premio> getAllPremi() {
        return premioRepository.findAll();
    }

    public void InsertPremio(Premio premio) { premioRepository.save(premio);};

    public List<Object[]> getAllPremiByUser(String utente){
        return premioRepository.getByVolontario(utente);
    }

    public Premio getPremioByNome(String id) {
        return premioRepository.getByNome(id);
    }

}