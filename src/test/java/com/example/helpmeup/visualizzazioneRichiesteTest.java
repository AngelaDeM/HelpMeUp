package com.example.helpmeup;

import com.example.helpmeup.controller.visualizzazioneRichiesteController;
import com.example.helpmeup.model.Richiesta;
import com.example.helpmeup.repository.RichiestaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class VisualizzazioneRichiesteControllerTest {

    @Mock
    private RichiestaRepository richiestaRepository;

    @InjectMocks
    private visualizzazioneRichiesteController controller;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllRichieste() {
        // Arrange
        Richiesta richiesta1 = new Richiesta();
        richiesta1.setId(1);
        Richiesta richiesta2 = new Richiesta();
        richiesta2.setId(2);
        List<Richiesta> richieste = Arrays.asList(richiesta1, richiesta2);

        when(richiestaRepository.findAll()).thenReturn(richieste);

        // Act
        List<Richiesta> result = controller.getAllRichieste();

        // Assert
        assertEquals(2, result.size());
        assertEquals(richiesta1, result.get(0));
        assertEquals(richiesta2, result.get(1));
        verify(richiestaRepository, times(1)).findAll();
    }

    @Test
    void testGetRichiestaById() {
        // Arrange
        int id = 1;
        Richiesta richiesta = new Richiesta();
        richiesta.setId(id);

        when(richiestaRepository.findById(id)).thenReturn(Optional.of(richiesta));

        // Act
        Richiesta result = controller.getRichiestaById(id);

        // Assert
        assertNotNull(result);
        assertEquals(id, result.getId());
        verify(richiestaRepository, times(1)).findById(id);
    }

    @Test
    void testGetRichiestaByIdNotFound() {
        // Arrange
        int id = 1;
        when(richiestaRepository.findById(id)).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(NoSuchElementException.class, () -> controller.getRichiestaById(id));
        verify(richiestaRepository, times(1)).findById(id);
    }
}
