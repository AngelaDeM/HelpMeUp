package com.example.helpmeup.repository;

import com.example.helpmeup.model.Assistito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for managing Assistito data.
 * Provides methods to verify existence of Assistito by email and username.
 *
 * @author Domenico
 *
 * @see Assistito
 */
@Repository
public interface AssistitoRepository extends JpaRepository<Assistito, Long> {

    /**
     * Checks if an Assistito with a specific email exists.
     *
     * @param email the email address to check
     * @return true if an Assistito with the specified email exists, false otherwise
     */
    boolean existsByEmail(String email);

    /**
     * Checks if an Assistito with a specific username exists.
     *
     * @param username the username to check
     * @return true if an Assistito with the specified username exists, false otherwise
     */
    boolean existsByUsername(String username);
}