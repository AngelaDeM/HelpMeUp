package com.example.helpmeup.repository;

import com.example.helpmeup.model.Utente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository per la gestione delle operazioni relative agli utenti.
 * Estende {@link JpaRepository} per fornire i metodi di accesso ai dati relativi agli utenti.
 *
 * @author ROBERTO-SCARPA
 * @author fimiani
 */
@Repository
public interface UtenteRepository extends JpaRepository<Utente, String> {

    /**
     * Verifica se esiste un utente con l'email fornita.
     *
     * @param email L'email da verificare.
     * @return true se esiste un utente con l'email specificata, false altrimenti.
     */
    boolean existsByEmail(String email);

    /**
     * Verifica se esiste un utente con il nome utente fornito.
     *
     * @param username Il nome utente da verificare.
     * @return true se esiste un utente con il nome utente specificato, false altrimenti.
     */
    boolean existsByUsername(String username);

    /**
     * Trova un utente in base al nome utente fornito.
     *
     * @param username Il nome utente dell'utente da cercare.
     * @return L'oggetto {@link Utente} corrispondente al nome utente, o null se non trovato.
     */
    Utente findByUsername(String username);
}
