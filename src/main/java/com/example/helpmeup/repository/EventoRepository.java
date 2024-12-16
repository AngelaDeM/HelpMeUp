package com.example.helpmeup.repository;


import com.example.helpmeup.model.Evento;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.time.LocalDate;
import java.time.LocalTime;
/**
 * Repository per la gestione delle operazioni relative agli eventi.
 * Estende {@link JpaRepository} per fornire i metodi di accesso ai dati relativi agli eventi.
 *
 * @see Evento
 *
 * @author Claudio
 */
@Repository
public interface EventoRepository extends JpaRepository<Evento, Integer> {

    /**
     * Restituisce l'evento con l'id specificato.
     *
     * @param id l'id dell'evento
     * @return l'evento con l'id specificato
     */
    @Query(value = "SELECT * FROM evento WHERE id = :id", nativeQuery = true)
    Evento findById(int id);

    /**
     * Restituisce tutti gli eventi.
     *
     * @return la lista di tutti gli eventi
     */
    List<Evento> findAll();

    /**
     * Restituisce tutti gli eventi di un utente.
     *
     * @param username l'username dell'utente di cui si vogliono ottenere gli eventi
     * @return la lista di tutti gli eventi dell'utente
     */
    @Query(value = "SELECT * FROM Evento WHERE utente = :username   ", nativeQuery = true)
    List<Evento> findByUtente(String username);

    /**
     * Inserisce un nuovo evento.
     *
     * @param nome il nome dell'evento
     * @param data la data dell'evento
     * @param ora l'ora dell'evento
     */
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO Evento (nome, data_evento, ora, utente) VALUES (:nome, :data, :ora, :utente)", nativeQuery = true)
    void insertEvento(String nome, LocalDate data, LocalTime ora, String utente);

    /**
     * Elimina l'evento con l'id specificato.
     *
     * @param id l'id dell'evento
     */
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM Evento WHERE id = :id", nativeQuery = true)
    void deleteEvento(int id);

    /**
     * Aggiorna l'evento con l'id specificato.
     *
     * @param id l'id dell'evento
     * @param nome il nome dell'evento
     * @param data la data dell'evento
     * @param ora l'ora dell'evento
     */
    @Modifying
    @Transactional
    @Query(value = "UPDATE Evento SET nome = :nome, data_evento = :data, ora = :ora WHERE id = :id", nativeQuery = true)
    void updateEvento(int id, String nome, LocalDate data, LocalTime ora);
}
