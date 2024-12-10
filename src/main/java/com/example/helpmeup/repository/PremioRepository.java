package com.example.helpmeup.repository;

import com.example.helpmeup.model.Premio;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository per la gestione dei dati relativi ai premi.
 * Fornisce metodi per interagire con il database riguardanti premi e riscatti.
 *
 * @author ROBERTO-SCARPA
 * @author fimiani
 */
@Repository
public interface PremioRepository extends JpaRepository<Premio, Integer> {

    /**
     * Registra il riscatto di un premio da parte di un volontario.
     * Inserisce una nuova entry nella tabella di associazione `riscatti_premi`.
     *
     * @param premio_id  l'ID del premio riscattato
     * @param account_id l'ID dell'account del volontario
     * @throws jakarta.transaction.TransactionRequiredException se la transazione non è disponibile
     */
    @Modifying
    @Query(value = "INSERT INTO riscatti_premi (account_id, premio_id) VALUES (:account_id, :premio_id)", nativeQuery = true)
    @Transactional
    void riscattaPremio(@Param("premio_id") String premio_id, @Param("account_id") String account_id);

    /**
     * Restituisce una lista di premi riscattati da un volontario specifico, inclusa la data del riscatto.
     *
     * @param username il nome utente del volontario
     * @return una lista di array di oggetti che include il nome del premio, la descrizione e la data del riscatto
     * @throws IllegalArgumentException se l'argomento fornito non è valido
     */
    @Query(value = "SELECT p.nome, p.descrizione, rp.data_riscatto " +
            "FROM Premio p " +
            "JOIN riscatti_premi rp ON p.nome = rp.premio_id " +
            "WHERE rp.account_id = :username", nativeQuery = true)
    List<Object[]> getByVolontario(@Param("username") String username);

    /**
     * Restituisce un premio specifico basandosi sul nome fornito.
     *
     * @param id il nome del premio da cercare
     * @return il premio corrispondente al nome fornito
     * @throws IllegalArgumentException se l'ID fornito non è valido
     */
    @Query(value = "SELECT * FROM Premio WHERE nome = :id", nativeQuery = true)
    Premio getByNome(@Param("id") String id);
}
