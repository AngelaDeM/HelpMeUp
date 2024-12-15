package com.example.helpmeup.service;

import com.example.helpmeup.model.Premio;
import com.example.helpmeup.repository.PremioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Servizio per la gestione dei premi.
 * Questo servizio interagisce con il repository per eseguire operazioni sui premi.
 *
 * @author ROBERTO-SCARPA
 * @author fimiani
 */
@Service
public class PremioService {

    private final PremioRepository premioRepository;

    /**
     * Costruttore per inizializzare il servizio con il repository dei premi.
     *
     * @param premioRepository Il repository per l'interazione con la base dati dei premi.
     */
    public PremioService(PremioRepository premioRepository) {
        this.premioRepository = premioRepository;
    }

    /**
     * Riscatta un premio per un determinato utente.
     *
     * @param premio Il nome del premio che si desidera riscattare.
     * @param utente Il nome dell'utente che sta riscattando il premio.
     * @throws RuntimeException Se si verifica un errore durante il riscatto del premio.
     */
    public void riscattaPremio(String premio, String utente) {
        premioRepository.riscattaPremio(premio, utente);
    }

    /**
     * Recupera tutti i premi disponibili.
     *
     * @return Una lista di oggetti Premio che rappresentano tutti i premi disponibili.
     */
    public List<Premio> getAllPremi() {
        return premioRepository.findAll();
    }

    /**
     * Inserisce un nuovo premio nel sistema.
     *
     * @param premio Il premio da inserire nel sistema.
     * @throws RuntimeException Se si verifica un errore durante l'inserimento del premio.
     */
    public void InsertPremio(Premio premio) {
        premioRepository.save(premio);
    }

    /**
     * Recupera tutti i premi riscattati da un determinato utente.
     *
     * @param utente Il nome dell'utente per il quale si desidera ottenere i premi riscattati.
     * @return Una lista di oggetti che rappresentano i premi riscattati dall'utente.
     */
    public List<Object[]> getAllPremiByUser(String utente) {
        return premioRepository.getByVolontario(utente);
    }

    /**
     * Recupera un premio in base al suo nome.
     *
     * @param id Il nome del premio che si desidera recuperare.
     * @return L'oggetto Premio corrispondente al nome specificato, oppure null se non trovato.
     * @throws RuntimeException Se si verifica un errore durante il recupero del premio.
     */
    public Premio getPremioByNome(String id) {
        return premioRepository.getByNome(id);
    }

    public List<Premio> getAllByUser(String utente) {
        return premioRepository.getByVolontario2(utente);
    }
}
