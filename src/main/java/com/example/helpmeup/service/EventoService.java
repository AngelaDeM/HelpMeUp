package com.example.helpmeup.service;

import com.example.helpmeup.model.Evento;
import com.example.helpmeup.repository.EventoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
/**
 * Service per la gestione degli eventi.
 *
 * @author Claudio
 */
@Service
public class EventoService {
    /**
     * Repository degli eventi.
     *
     * @see EventoRepository
     */
    private final EventoRepository eventoRepository;

    /**
     * Costruttore del service per gli eventi.
     *
     * @param eventoRepository il repository degli eventi
     */
    public EventoService(EventoRepository eventoRepository) {
        this.eventoRepository = eventoRepository;
    }

    /**
     * Inserisce un nuovo evento.
     *
     * @param nome il nome dell'evento
     * @param data la data dell'evento
     * @param ora l'ora dell'evento
     */
    public void insertEvento(String nome, LocalDate data, LocalTime ora) {
        eventoRepository.insertEvento(nome, data, ora);
    }

    /**
     * Elimina l'evento con l'id specificato.
     *
     * @param id l'id dell'evento
     */
    public void deleteEvento(int id) {
        eventoRepository.deleteEvento(id);
    }

    /**
     * Aggiorna l'evento con l'id specificato.
     *
     * @param id l'id dell'evento
     * @param nome il nome dell'evento
     * @param data la data dell'evento
     * @param ora l'ora dell'evento
     */
    public void updateEvento(int id, String nome, LocalDate data, LocalTime ora) {
        eventoRepository.updateEvento(id, nome, data, ora);
    }

    /**
     * Restituisce tutti gli eventi.
     *
     * @return la lista di tutti gli eventi
     */
    public List<Evento> findAll() {
        return eventoRepository.findAll();
    }

    /**
     * Restituisce l'evento con l'id specificato.
     *
     * @param id l'id dell'evento
     * @return l'evento con l'id specificato
     */
    public Evento findById(int id) {
        return eventoRepository.findById(id);
    }
}
