package com.example.helpmeup.repository;

import com.example.helpmeup.model.Premio;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PremioRepository extends JpaRepository<Premio, Integer> {
    @Modifying


    @Query(value = "INSERT INTO riscatti_premi (account_id, premio_id) VALUES (:account_id, :premio_id)", nativeQuery = true)
    @Transactional
    void riscattaPremio(@Param("premio_id") String premio_id, @Param("account_id") String account_id);

    @Modifying
    @Query(value = "SELECT p.nome, p.descrizione, rp.data_riscatto " +
            "FROM Premio p " +
            "JOIN riscatti_premi rp ON p.nome = rp.premio_id " +
            "WHERE rp.account_id = :username", nativeQuery = true)
    List<Object[]> getPremioByVolontari(@Param("username") String username);

}
