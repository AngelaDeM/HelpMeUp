package com.example.helpmeup.repository;

import com.example.helpmeup.model.Utente;
import com.example.helpmeup.model.Volontario;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VolontarioRepository extends JpaRepository<Volontario, Long> {
    boolean existsByEmail(String email);
    boolean existsByUsername(String username);


    @Query(value = "SELECT nome, cognome, username, sesso, password, data_nascita, email, indirizzo, numero_telefono, punti " +
            "FROM Utente " +
            "WHERE username = :username AND tipo_account = 'Volontario'", nativeQuery = true)
    List<Object[]> findByUsername(String username);


    @Modifying
    @Transactional
    @Query(value = "UPDATE Utente SET punti = :nuoviPunti WHERE username = :username ", nativeQuery = true)
    void updatePunti(@Param("username") String username, @Param("nuoviPunti") int nuoviPunti);


}