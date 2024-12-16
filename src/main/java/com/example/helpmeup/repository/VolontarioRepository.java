package com.example.helpmeup.repository;

import com.example.helpmeup.model.Volontario;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * Repository per la gestione dei dati relativi ai volontari.
 * Fornisce metodi per verificare l'esistenza, aggiornare i punti
 * e recuperare informazioni sui volontari.
 *
 * @author ROBERTO-SCARPA
 * @author fimiani
 * @author Domenico
 *
 * @see Volontario
 */
@Repository
public interface VolontarioRepository extends JpaRepository<Volontario, String> {

    @Query(value = "SELECT u.punti FROM Utente u where username= :username", nativeQuery = true)
     int getPuntiVolontario(@Param("username") String username);

    /**
     * Verifica se esiste un volontario con una determinata email.
     *
     * @param email l'indirizzo email da verificare
     * @return true se esiste un volontario con l'email specificata, altrimenti false
     */
    boolean existsByEmail(String email);

    /**
     * Verifica se esiste un volontario con un determinato username.
     *
     * @param username il nome utente da verificare
     * @return true se esiste un volontario con l'username specificato, altrimenti false
     */
    boolean existsByUsername(String username);

    /**
     * Recupera le informazioni di un volontario specifico in base all'username.
     *
     * @param username il nome utente del volontario
     * @return una lista di oggetti che rappresentano le informazioni del volontario
     *         (nome, cognome, username, sesso, password, data di nascita, email, indirizzo,
     *         numero di telefono, punti)
     */
    @Query(value = "SELECT nome, cognome, username, sesso, password, data_nascita, email, indirizzo, numero_telefono, punti " +
            "FROM Utente " +
            "WHERE username = :username AND tipo_account = 'Volontario'", nativeQuery = true)
    List<Object[]> findByUsername(String username);

    /**
     * Aggiorna i punti di un volontario specifico.
     *
     * @param username il nome utente del volontario
     * @param nuoviPunti il nuovo valore dei punti da assegnare
     */
    @Modifying
    @Transactional
    @Query(value = "UPDATE Utente SET punti = :nuoviPunti WHERE username = :username", nativeQuery = true)
    void updatePunti(@Param("username") String username, @Param("nuoviPunti") int nuoviPunti);

}
