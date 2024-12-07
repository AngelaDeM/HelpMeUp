package com.example.helpmeup.repository;

import com.example.helpmeup.model.Utente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UtenteRepository extends JpaRepository<Utente, String> {
    boolean existsByEmail(String email);
    boolean existsByUsername(String username);
    Utente findByUsername(String username);
}