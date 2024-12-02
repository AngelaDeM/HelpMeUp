package com.example.helpmeup.repository;

import com.example.helpmeup.model.Volontario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VolontarioRepository extends JpaRepository<Volontario, Long> {

    boolean existsByEmail(String email);
    boolean existsByUsername(String username);
}


