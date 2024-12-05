package com.example.helpmeup.repository;

import com.example.helpmeup.model.Utente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface UtenteRepository extends JpaRepository<Utente, Long> {
    boolean existsByEmail(String email);
    boolean existsByUsername(String username);
}