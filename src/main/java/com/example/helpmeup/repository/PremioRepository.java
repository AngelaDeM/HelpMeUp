package com.example.helpmeup.repository;

import com.example.helpmeup.model.Premio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PremioRepository extends JpaRepository<Premio, Integer> {
    @Modifying
    @Query(value = "INSERT INTO riscatta_premi (account_id, premio_id) VALUES (:utente, :premio)", nativeQuery = true)
    void riscattaPremio(String premio, String utente);

    @Modifying
    @Query(value = "SELECT p FROM riscatta_premi WHERE account_id =:utente", nativeQuery = true)
    List<Premio> getPremioByVolontari(String utente);
}
