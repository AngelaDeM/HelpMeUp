package com.example.helpmeup.repository;

import com.example.helpmeup.model.Richiesta;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

/**
 * Repository per la gestione delle richieste di aiuto.
 * Fornisce metodi per operazioni CRUD e altre funzionalità specifiche.
 *
 * @see Richiesta
 *
 * @author ROBERTO-SCARPA
 * @author fimiani
 * @author Claudio
 */
@Repository
public interface RichiestaRepository extends JpaRepository<Richiesta, Integer> {

    /**
     * Trova una richiesta per ID.
     *
     * @param id l'ID della richiesta
     * @return un oggetto Optional che contiene la richiesta, se trovata
     */
    Optional<Richiesta> findById(Integer id);

    /**
     * Restituisce tutte le richieste disponibili.
     *
     * @return una lista di tutte le richieste
     */
    List<Richiesta> findAll();

    /**
     * Trova tutte le richieste associate a un utente specifico.
     *
     * @param username il nome utente
     * @return una lista di richieste associate all'utente
     */
    @Modifying
    @Query(value = "SELECT r FROM richiesta_utenti r WHERE r.account_id = :username", nativeQuery = true)
    List<Richiesta> findAllByUsername(String username);

    /**
     * Associa un assistito a una richiesta.
     *
     * @param idRichiesta l'ID della richiesta
     * @param UserAssistito l'utente assistito
     * @return il numero di righe modificate
     */
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO richiesta_utenti (account_id, richiesta) VALUES (:UserAssistito, :idRichiesta)", nativeQuery = true)
    int assistitoRichiesta(@Param("idRichiesta") int idRichiesta, @Param("UserAssistito") String UserAssistito);

    /**
     * Accetta una richiesta da parte di un volontario.
     *
     * @param idRichiesta l'ID della richiesta
     * @param idVolontario il nome utente del volontario
     * @return il numero di righe modificate
     */
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO richiesta_utenti (account_id, richiesta) VALUES (:idVolontario, :idRichiesta)", nativeQuery = true)
    int accettaRichiesta(@Param("idRichiesta") int idRichiesta, @Param("idVolontario") String idVolontario);

    /**
     * Aggiorna i dettagli di una richiesta.
     *
     * @param idRichiesta l'ID della richiesta
     * @param titolo il nuovo titolo
     * @param newDescrizione la nuova descrizione
     * @param newDCrea la nuova data di creazione
     * @param newDataInter la nuova data di intervento
     * @param newOraInter il nuovo orario di intervento
     * @param newEmer flag che indica se è un'emergenza
     * @param newPunti i nuovi punti associati
     */
    @Modifying
    @Transactional
    @Query(value = "UPDATE Richiesta SET titolo = :newTitolo, descrizione = :newDescrizione, data_creazione= :newDCrea, " +
            "data_intervento =:newDataInter, orario_intervento =:newOraInter, emergenza =:newEmer, punti =:newPunti " +
            "WHERE id =:idRichiesta", nativeQuery = true)
    void updateRichiesta(@Param("idRichiesta") int idRichiesta, @Param("newTitolo") String titolo,
                         @Param("newDescrizione") String newDescrizione, @Param("newDCrea") LocalDate newDCrea,
                         @Param("newDataInter") LocalDate newDataInter, @Param("newOraInter") LocalTime newOraInter,
                         @Param("newEmer") boolean newEmer, @Param("newPunti") int newPunti);

    /**
     * Restituisce una lista di volontari associati a una richiesta.
     *
     * @param idRichiesta l'ID della richiesta
     * @return una lista di nomi utente dei volontari
     */
    @Modifying
    @Query(value = "SELECT r.account_id FROM richiesta_utenti r WHERE richiesta = :idRichiesta", nativeQuery = true)
    List<String> getVolontari(@Param("idRichiesta") int idRichiesta);

    /**
     * Segna una richiesta come completata.
     *
     * @param idRichiesta l'ID della richiesta
     */
    @Modifying
    @Transactional
    @Query(value = "UPDATE Richiesta SET completato = true WHERE id =:idRichiesta", nativeQuery = true)
    void completa(@Param("idRichiesta") int idRichiesta);

    /**
     * Aggiorna i punti di un utente.
     *
     * @param puntiRichiesta i punti da aggiungere
     * @param username il nome utente
     */
    @Modifying
    @Transactional
    @Query(value = "UPDATE Utente SET punti = punti + :puntiRichiesta WHERE username = :username", nativeQuery = true)
    void aggiornaPunti(@Param("puntiRichiesta") int puntiRichiesta, @Param("username") String username);

    /**
     * Verifica se un volontario è già associato a una richiesta.
     *
     * @param volontario il nome utente del volontario
     * @param richiesta l'ID della richiesta
     * @return `true` se il volontario è associato, altrimenti `false`
     */
    @Query(value = "SELECT CASE WHEN EXISTS (SELECT 1 FROM richiesta_utenti r WHERE account_id = :volontario AND richiesta = :richiesta) THEN TRUE ELSE FALSE END", nativeQuery = true)
    Long esisteVolontario(@Param("volontario") String volontario, @Param("richiesta") int richiesta);

    /**
     * Restituisce tutte le richieste accettate da un volontario specifico.
     *
     * @param username il nome utente del volontario
     * @return una lista di richieste accettate
     */

    @Query(value = "SELECT r.* FROM Richiesta r " +
            "JOIN richiesta_utenti ru ON r.id = ru.richiesta " +
            "WHERE ru.account_id = :username", nativeQuery = true)
    List<Richiesta> getRichieste(@Param("username") String username);

    /**
     * Trova tutte le richieste di emergenza.
     *
     * @return una lista di richieste con emergenza attiva
     */
    @Query(value = "SELECT r FROM Richiesta WHERE r.emergenza = TRUE", nativeQuery = true)
    List<Richiesta> findAllEmergenze();

    /**
     * Deletes entries from the richiesta_utenti table based on the specified richiesta value.
     *
     * @param richiesta the ID of the richiesta whose associated utenti entries should be deleted
     */
    @Modifying
    @Transactional
    @Query(value ="DELETE FROM richiesta_utenti WHERE richiesta = :richiesta", nativeQuery = true)
    void eliminaRichiestaUtenti(@Param("richiesta") int richiesta);

    /**
     * Deletes a richiesta (request) from the database based on the provided richiesta ID.
     *
     * @param richiesta the ID of the richiesta to be deleted
     */
    @Modifying
    @Transactional
    @Query(value ="DELETE FROM richiesta WHERE id = :richiesta", nativeQuery = true)
    void deleteRichiesta(@Param("richiesta") int richiesta);

    /**
     * Retrieves a list of requests (Richieste) that have not been accepted by a specific user.
     * The method executes a native SQL query to fetch requests where the association
     * with the specified user's account does not exist.
     *
     * @param username the username of the user for whom to retrieve non-accepted requests
     * @return a list of Richiesta objects that are not accepted by the specified user
     */
    @Query(value = "SELECT r.* FROM Richiesta r LEFT JOIN richiesta_utenti ru ON r.id = ru.richiesta LEFT JOIN Utente u ON ru.account_id = u.username WHERE u.username = :username AND ru.richiesta IS NULL", nativeQuery = true)
    List<Richiesta> getRichiesteNonAccettate(@Param("username") String username);

}
