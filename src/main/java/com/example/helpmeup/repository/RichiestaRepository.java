package com.example.helpmeup.repository;

import com.example.helpmeup.model.Richiesta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RichiestaRepository extends JpaRepository<Richiesta, Integer> {

    Optional<Richiesta> findById(Integer id);
    List<Richiesta> findAll();

    @Modifying
    @Query(value = "SELECT r FROM richiesta r WHERE r.account_id= :username", nativeQuery = true)
    List<Richiesta> findAllByUsername(String username);

    @Modifying
    @Query(value = "SELECT r FROM Richiesta r JOIN richiesta_utenti ru ON r.id = ru.richiesta WHERE ru.account_id = username", nativeQuery = true)
    List<Richiesta> getRichiesteByVolontario(String username);
    @Modifying
    @Query(value = "INSERT INTO richiesta (account_id, richiesta) VALUES (:idVolontario, :idRichiesta)", nativeQuery = true)
    int accettaRichiesta(@Param("idRichiesta") int idRichiesta, @Param("idVolontario") String idVolontario);

}
