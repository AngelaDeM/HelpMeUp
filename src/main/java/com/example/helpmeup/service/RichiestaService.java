package com.example.helpmeup.service;

import com.example.helpmeup.model.Richiesta;
import com.example.helpmeup.repository.RichiestaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Servizio per la gestione delle richieste.
 * Questo servizio interagisce con il repository per eseguire operazioni sulle richieste.
 *
 * @author ROBERTO-SCARPA
 * @author fimiani
 */
@Service
public class RichiestaService {

    private final RichiestaRepository richiestaRepository;

    /**
     * Costruttore per inizializzare il servizio con il repository delle richieste.
     *
     * @param richiestaRepository Il repository per l'interazione con la base dati delle richieste.
     */
    public RichiestaService(RichiestaRepository richiestaRepository) {
        this.richiestaRepository = richiestaRepository;
    }

    /**
     * Pubblica una nuova richiesta nel sistema.
     *
     * @param richiesta L'oggetto Richiesta da pubblicare.
     * @return La richiesta appena pubblicata.
     * @throws RuntimeException Se si verifica un errore durante la pubblicazione della richiesta.
     */
    public Richiesta pubblicaRichiesta(Richiesta richiesta) {
        return richiestaRepository.save(richiesta);
    }

    /**
     * Aggiorna i dettagli di una richiesta esistente.
     *
     * @param richiesta L'oggetto Richiesta con i dati aggiornati.
     * @throws RuntimeException Se si verifica un errore durante l'aggiornamento della richiesta.
     */
    public void updateRichiesta(Richiesta richiesta) {
        richiestaRepository.updateRichiesta(richiesta.getId(), richiesta.getTitolo(),
                richiesta.getDescrizione(), richiesta.getDataPubblicazione(),
                richiesta.getDataAiuto(), richiesta.getOraAiuto(),
                richiesta.isEmergenza(), richiesta.getPunti());
    }

    /**
     * Elimina una richiesta dal sistema.
     *
     * @param richiesta L'oggetto Richiesta da eliminare.
     * @throws RuntimeException Se si verifica un errore durante l'eliminazione della richiesta.
     */
    public void eliminaRichiesta(Richiesta richiesta) {
        richiestaRepository.eliminaRichiestaUtenti(richiesta.getId());
        richiestaRepository.deleteRichiesta(richiesta.getId());
    }

    /**
     * Recupera tutte le richieste nel sistema.
     *
     * @return Una lista di oggetti Richiesta che rappresentano tutte le richieste.
     */
    public List<Richiesta> getAllRichieste() {
        return richiestaRepository.findAll();
    }

    /**
     * Recupera una richiesta in base al suo ID.
     *
     * @param id L'ID della richiesta da recuperare.
     * @return L'oggetto Richiesta corrispondente all'ID specificato.
     * @throws RuntimeException Se la richiesta non viene trovata.
     */
    public Richiesta getRichiestaById(int id) {
        return richiestaRepository.findById(id).get();
    }

    /**
     * Accetta una richiesta da un volontario.
     *
     * @param idRichiesta L'ID della richiesta da accettare.
     * @param idVolontario L'ID del volontario che accetta la richiesta.
     * @return Un intero che rappresenta il numero di righe aggiornate.
     * @throws RuntimeException Se si verifica un errore durante l'accettazione della richiesta.
     */
    public int accettaRichiesta(int idRichiesta, String idVolontario) {
        return richiestaRepository.accettaRichiesta(idRichiesta, idVolontario);
    }

    /**
     * Associa un utente come assistito a una richiesta.
     *
     * @param idRichiesta L'ID della richiesta da aggiornare.
     * @param userAssistito Il nome dell'utente assistito.
     * @return Un intero che rappresenta il numero di righe aggiornate.
     * @throws RuntimeException Se si verifica un errore durante l'associazione.
     */
    public int aiutoRichiesta(int idRichiesta, String userAssistito) {
        return richiestaRepository.assistitoRichiesta(idRichiesta, userAssistito);
    }

    /**
     * Recupera i volontari associati a una richiesta.
     *
     * @param idRichiesta L'ID della richiesta.
     * @return Una lista di stringhe che rappresentano gli ID dei volontari associati alla richiesta.
     */
    public List<String> getVolontari(int idRichiesta) {
        return richiestaRepository.getVolontari(idRichiesta);
    }

    /**
     * Completa una richiesta e aggiorna i punti per ogni volontario.
     *
     * @param idRichiesta L'ID della richiesta da completare.
     * @param volontari La lista di volontari che hanno partecipato alla richiesta.
     * @param punti Il numero di punti da assegnare ai volontari.
     * @throws RuntimeException Se si verifica un errore durante il completamento della richiesta.
     */
    public void completaRichiesta(int idRichiesta, List<String> volontari, int punti) {
        richiestaRepository.completa(idRichiesta);
        for (String v : volontari)
            richiestaRepository.aggiornaPunti(punti, v);
    }

    /**
     * Verifica se un volontario ha già accettato una richiesta.
     *
     * @param idRichiesta L'ID della richiesta da verificare.
     * @param volontario Il nome del volontario da verificare.
     * @return true se il volontario ha accettato la richiesta, false altrimenti.
     */
    public boolean statoAccettoVolontario(int idRichiesta, String volontario) {
        return richiestaRepository.esisteVolontario(volontario, idRichiesta) == 1;
    }

    /**
     * Recupera tutte le richieste associate a un volontario specifico.
     *
     * @param username Il nome del volontario di cui si desidera ottenere le richieste.
     * @return Una lista di oggetti Richiesta associati al volontario.
     */
    public List<Richiesta> getRichieste(String username) {
        return richiestaRepository.getRichieste(username);
    }

    /**
     * Recupera tutte le richieste di emergenza.
     *
     * @return Una lista di oggetti Richiesta che rappresentano le richieste di emergenza.
     */
    public List<Richiesta> getAllEmergency() {
        return richiestaRepository.findAllEmergenze();
    }
/*
    public List<Richiesta> getRichiesteByAssistito(String username) {
        return richiestaRepository.getRichiesteByAssistito(username);
    }

 */
}
