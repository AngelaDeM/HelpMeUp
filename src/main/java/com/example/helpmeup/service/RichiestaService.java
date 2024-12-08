package com.example.helpmeup.service;

import com.example.helpmeup.model.Richiesta;
import com.example.helpmeup.repository.RichiestaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RichiestaService {
    private final RichiestaRepository richiestaRepository;

    public RichiestaService(RichiestaRepository richiestaRepository) {
        this.richiestaRepository = richiestaRepository;
    }

    public Richiesta pubblicaRichiesta(Richiesta richiesta) {
        return richiestaRepository.save(richiesta);
    }

    public Richiesta updateRichiesta(Richiesta richiesta) {
        richiestaRepository.delete(richiesta);
        return richiestaRepository.save(richiesta);
    }

    public void eliminaRichiesta(Richiesta richiesta) {
        richiestaRepository.delete(richiesta);
    }

    public List<Richiesta> getAllRichieste() {
        return richiestaRepository.findAll();
    }

    //get richieste by volontario
    public List<Richiesta> getRichiesteByVolontario(String username){
        return richiestaRepository.getRichiesteByVolontario(username);
    }

    public Richiesta getRichiestaById(int id){
        return richiestaRepository.findById(id).get();
    }

    public int accettaRichiesta(int idRichiesta, String idVolontario) {
        return richiestaRepository.accettaRichiesta(idRichiesta,idVolontario);
    }
}
