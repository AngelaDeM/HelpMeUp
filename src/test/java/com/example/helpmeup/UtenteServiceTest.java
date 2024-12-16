package com.example.helpmeup;

import com.example.helpmeup.model.Volontario;
import com.example.helpmeup.repository.UtenteRepository;
import com.example.helpmeup.repository.VolontarioRepository;
import com.example.helpmeup.service.UtenteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UtenteServiceTest {

    @Mock
    private UtenteRepository utenteRepository;

    @Mock
    private VolontarioRepository volontarioRepository;

    @InjectMocks
    private UtenteService utenteService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSalvaUtente() {
        Volontario volontario = new Volontario();
        volontario.setUsername("testUser");

        // Esegui il metodo
        utenteService.salvaUtente(volontario);

        // Verifica che il repository sia stato invocato con l'oggetto corretto
        verify(volontarioRepository, times(1)).save(volontario);
    }

    @Test
    public void testVerificaEmail() {
        String email = "test@example.com";

        doReturn(true).when(utenteRepository).existsByEmail(email);

        boolean result = utenteService.verificaEmail(email);

        assertTrue(result);
        verify(utenteRepository, times(1)).existsByEmail(email);
    }

    @Test
    public void testVerificaUsername() {
        String username = "testUser";

        doReturn(true).when(volontarioRepository).existsByUsername(username);

        boolean result = utenteService.verificaUsername(username);

        assertTrue(result);
        verify(volontarioRepository, times(1)).existsByUsername(username);
    }

    @Test
    public void testGetVolontarioByUsername() {
        String username = "testUser";
        Object[] row = {
                "John", "Doe", username, 'M', "password123",
                java.sql.Date.valueOf("1990-01-01"), "john@example.com",
                "Test Address", "1234567890", 100
        };

        doReturn(List.of(row)).when(volontarioRepository).findByUsername(username);

        Volontario result = utenteService.getVolontarioByUsername(username);

        assertNotNull(result);
        assertEquals("John", result.getNome());
        assertEquals("Doe", result.getCognome());
        assertEquals(username, result.getUsername());
        assertEquals("M", result.getSesso());
        assertEquals("password123", result.getPassword());
        assertEquals("1990-01-01", result.getDataNascita().toString());
        assertEquals("john@example.com", result.getEmail());
        assertEquals("Test Address", result.getIndirizzo());
        assertEquals("1234567890", result.getNumeroTelefono());
        assertEquals(100, result.getPunti());

        verify(volontarioRepository, times(1)).findByUsername(username);
    }

    @Test
    public void testGetVolontarioByUsername_NotFound() {
        String username = "nonexistentUser";

        doReturn(Collections.emptyList()).when(volontarioRepository).findByUsername(username);

        Volontario result = utenteService.getVolontarioByUsername(username);

        assertNull(result);
        verify(volontarioRepository, times(1)).findByUsername(username);
    }

    @Test
    public void testUpdatePuntiVolontario() {
        String username = "testUser";
        int newPoints = 150;

        // Esegui il metodo
        utenteService.updatePuntiVolontario(username, newPoints);

        // Verifica che il repository sia stato invocato con i parametri corretti
        verify(volontarioRepository, times(1)).updatePunti(username, newPoints);
    }

}