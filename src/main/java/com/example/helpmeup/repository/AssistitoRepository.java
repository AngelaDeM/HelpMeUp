package com.example.helpmeup.repository;

import com.example.helpmeup.model.Assistito;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AssistitoRepository extends JpaRepository<Assistito, Long> {

    boolean existsByEmail(String email);
    boolean existsByUsername(String username);
}