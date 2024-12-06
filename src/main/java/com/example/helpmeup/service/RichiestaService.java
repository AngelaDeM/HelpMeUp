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

    public void updateRichiesta(Richiesta richiesta) {
                richiestaRepository.updateRichiesta(richiesta.getId(),richiesta.getTitolo(),
                richiesta.getDescrizione(),richiesta.getDataPubblicazione(),
                richiesta.getDataAiuto(),richiesta.getOraAiuto(),
                richiesta.isEmergenza(),richiesta.getPunti());
    }

    public void eliminaRichiesta(Richiesta richiesta) {
        richiestaRepository.delete(richiesta);
    }

    public List<Richiesta> getAllRichieste() {
        return richiestaRepository.findAll();
    }

    public Richiesta getRichiestaById(int id){
        return richiestaRepository.findById(id).get();
    }

    public int accettaRichiesta(int idRichiesta, String idVolontario) {
        return richiestaRepository.accettaRichiesta(idRichiesta,idVolontario);
    }
}
