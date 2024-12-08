package com.example.helpmeup.repository;

import com.example.helpmeup.model.Volontario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VolontarioRepository extends JpaRepository<Volontario, String> {
    boolean existsByEmail(String email);
    boolean existsByUsername(String username);
}