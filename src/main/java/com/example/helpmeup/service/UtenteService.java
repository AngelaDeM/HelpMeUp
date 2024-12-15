package com.example.helpmeup.service;

import com.example.helpmeup.model.Premio;
import com.example.helpmeup.model.Utente;
import com.example.helpmeup.model.Volontario;
import com.example.helpmeup.repository.UtenteRepository;
import com.example.helpmeup.repository.VolontarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Servizio per la gestione degli utenti e dei volontari.
 * Fornisce metodi per la verifica, salvataggio e aggiornamento delle informazioni relative agli utenti e volontari.
 *
 * @author ROBERTO-SCARPA
 * @author fimiani
 * @author Claudio
 * @author Domenico
 */
@Service
public class UtenteService {

    @Autowired
    private UtenteRepository utenteRepository;

    private final VolontarioRepository volontarioRepository;

    /**
     * Costruttore per l'iniezione delle dipendenze necessarie al servizio.
     *
     * @param volontarioRepository il repository per gestire i volontari
     */
    @Autowired
    public UtenteService(VolontarioRepository volontarioRepository) {
        this.volontarioRepository = volontarioRepository;
    }

    /**
     * Salva un oggetto Volontario nel database.
     *
     * @param utente il volontario da salvare
     */
    public void salvaUtente(Volontario utente) {
        volontarioRepository.save(utente);
    }

    /**
     * Verifica se un'email esiste nel database.
     *
     * @param email l'email da verificare
     * @return true se l'email esiste, altrimenti false
     */
    public boolean verificaEmail(String email) {
        return utenteRepository.existsByEmail(email);
    }

    /**
     * Verifica se uno username esiste nel database.
     *
     * @param username lo username da verificare
     * @return true se lo username esiste, altrimenti false
     */
    public boolean verificaUsername(String username) {
        return volontarioRepository.existsByUsername(username);
    }

    /**
     * Recupera un volontario in base al suo username.
     *
     * @param username lo username del volontario
     * @return il volontario corrispondente o null se non trovato
     */
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

    /**
     * Aggiorna i punti di un volontario.
     *
     * @param username lo username del volontario
     * @param pt il nuovo valore dei punti da assegnare
     */
    public void updatePuntiVolontario(String username, int pt) {
        volontarioRepository.updatePunti(username, pt);
    }



}
