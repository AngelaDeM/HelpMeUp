package com.example.helpmeup.service;

import com.example.helpmeup.model.Utente;
import com.example.helpmeup.model.Volontario;
import com.example.helpmeup.repository.UtenteRepository;
import com.example.helpmeup.repository.VolontarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import com.example.helpmeup.model.Volontario;
@Service
public class UtenteService {

    @Autowired
    private UtenteRepository utenteRepository;

    private final VolontarioRepository volontarioRepository;

    @Autowired
    public UtenteService(VolontarioRepository volontarioRepository) {
        this.volontarioRepository = volontarioRepository;
    }

    public void salvaUtente(Volontario utente) {
        volontarioRepository.save(utente);
    }

    // Verifica se l'email esiste nel database
    public boolean verificaEmail(String email) {
        return utenteRepository.existsByEmail(email);
    }


    public boolean verificaUsername(String username) {
        return volontarioRepository.existsByUsername(username);
    }

    public Volontario getVolontarioByUsername(String username) {
        List<Object[]> rawResult = volontarioRepository.findByUsername(username);
        if (rawResult.isEmpty()) {
            // Gestione del caso in cui non viene trovato alcun risultato
            return null;
        }
        // Supponendo che ci sia un solo risultato (username univoco)
        Object[] row = rawResult.get(0);

        // Mappatura manuale dei campi alla classe Volontario
        Volontario volontario = new Volontario();
        volontario.setNome((String) row[0]);
        volontario.setCognome((String) row[1]);
        volontario.setUsername((String) row[2]);
        volontario.setSesso(String.valueOf(row[3])); // Converte il Character in String
        volontario.setPassword((String) row[4]);
        volontario.setDataNascita(((java.sql.Date) row[5]).toLocalDate());
        volontario.setEmail((String) row[6]);
        volontario.setIndirizzo((String) row[7]);
        volontario.setNumeroTelefono((String) row[8]);
        volontario.setPunti((Integer) row[9]);

        System.out.println(volontario);
        return volontario;
    }


    public void updatePuntiVolontario(String username, int pt) {
        volontarioRepository.updatePunti(username,pt);

    }
}
