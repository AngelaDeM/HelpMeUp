package com.example.helpmeup.repository;

import com.example.helpmeup.model.Richiesta;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface RichiestaRepository extends JpaRepository<Richiesta, Integer> {

    Optional<Richiesta> findById(Integer id);
    List<Richiesta> findAll();

    @Modifying
    @Query(value = "SELECT r FROM richiesta_utenti r WHERE r.account_id= :username", nativeQuery = true)
    List<Richiesta> findAllByUsername(String username);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO richiesta_utenti (account_id, richiesta) VALUES (:idVolontario, :idRichiesta)", nativeQuery = true)
    int accettaRichiesta(@Param("idRichiesta") int idRichiesta, @Param("idVolontario") String idVolontario);


    @Modifying
    @Transactional
    @Query(value = "UPDATE Richiesta SET titolo = :newTitolo, descrizione = :newDescrizione, data_creazione= :newDCrea, " +
            "data_intervento =:newDataInter, orario_intervento =:newOraInter, emergenza =:newEmer, punti =:newPunti " +
                "WHERE id =:idRichiesta",nativeQuery = true)
    void updateRichiesta(@Param("idRichiesta") int idRichiesta, @Param("newTitolo") String titolo,
                         @Param("newDescrizione")String newDescrizione,@Param("newDCrea")LocalDate newDCrea,
                         @Param("newDataInter") LocalDate newDataInter,@Param("newOraInter") LocalTime newOraInter,
                         @Param("newEmer") boolean newEmer,@Param("newPunti") int newPunti);
}
