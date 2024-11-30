package com.example.helpmeup.repository;

import com.example.helpmeup.model.Utente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UtenteRepository extends JpaRepository<Utente, Long> {

    // Verifica se l'email esiste nel database
    boolean existsByEmail(String email);

    // Verifica se lo username esiste nel database
    boolean existsByUsername(String username);

    // Trova un utente per username
    Utente findByUsername(String username); // Trova un utente per username
}
