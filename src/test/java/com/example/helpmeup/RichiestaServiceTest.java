package com.example.helpmeup;

import com.example.helpmeup.model.Richiesta;
import com.example.helpmeup.repository.RichiestaRepository;
import com.example.helpmeup.service.RichiestaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class RichiestaServiceTest {

    @Mock
    private RichiestaRepository richiestaRepository;

    @InjectMocks
    private RichiestaService richiestaService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testPubblicaRichiesta() {
        Richiesta richiesta = new Richiesta();
        doReturn(richiesta).when(richiestaRepository).save(richiesta);

        Richiesta result = richiestaService.pubblicaRichiesta(richiesta);

        assertNotNull(result);
        verify(richiestaRepository, times(1)).save(richiesta);
    }

    @Test
    public void testUpdateRichiesta() {
        Richiesta richiesta = new Richiesta();
        richiesta.setTitolo("Titolo aggiornato");

        doNothing().when(richiestaRepository).updateRichiesta(anyInt(), anyString(), anyString(), any(), any(), any(), anyBoolean(), anyInt());

        richiestaService.updateRichiesta(richiesta);

        verify(richiestaRepository, times(1)).updateRichiesta(anyInt(), anyString(), anyString(), any(), any(), any(), anyBoolean(), anyInt());
    }

    @Test
    public void testEliminaRichiesta() {
        Richiesta richiesta = new Richiesta();
        doNothing().when(richiestaRepository).deleteRichiesta(richiesta.getId());
        doNothing().when(richiestaRepository).eliminaRichiestaUtenti(richiesta.getId());

        richiestaService.eliminaRichiesta(richiesta);

        verify(richiestaRepository, times(1)).deleteRichiesta(richiesta.getId());
        verify(richiestaRepository, times(1)).eliminaRichiestaUtenti(richiesta.getId());
    }

    @Test
    public void testGetAllRichieste() {
        List<Richiesta> richieste = Collections.singletonList(new Richiesta());
        doReturn(richieste).when(richiestaRepository).findAll();

        List<Richiesta> result = richiestaService.getAllRichieste();

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(richiestaRepository, times(1)).findAll();
    }

    @Test
    public void testGetRichiestaById() {
        Richiesta richiesta = new Richiesta();
        doReturn(Optional.of(richiesta)).when(richiestaRepository).findById(1);

        Richiesta result = richiestaService.getRichiestaById(1);

        assertNotNull(result);
        verify(richiestaRepository, times(1)).findById(1);
    }

    @Test
    public void testAccettaRichiesta() {
        doReturn(1).when(richiestaRepository).accettaRichiesta(1, "volontario1");

        int result = richiestaService.accettaRichiesta(1, "volontario1");

        assertEquals(1, result);
        verify(richiestaRepository, times(1)).accettaRichiesta(1, "volontario1");
    }

    @Test
    public void testAiutoRichiesta() {
        doReturn(1).when(richiestaRepository).assistitoRichiesta(1, "assistito1");

        int result = richiestaService.aiutoRichiesta(1, "assistito1");

        assertEquals(1, result);
        verify(richiestaRepository, times(1)).assistitoRichiesta(1, "assistito1");
    }

    @Test
    public void testGetVolontari() {
        List<String> volontari = Collections.singletonList("volontario1");
        doReturn(volontari).when(richiestaRepository).getVolontari(1);

        List<String> result = richiestaService.getVolontari(1);

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(richiestaRepository, times(1)).getVolontari(1);
    }

    @Test
    public void testCompletaRichiesta() {
        doNothing().when(richiestaRepository).completa(1);
        doNothing().when(richiestaRepository).aggiornaPunti(100, "volontario1");

        richiestaService.completaRichiesta(1, Collections.singletonList("volontario1"), 100);

        verify(richiestaRepository, times(1)).completa(1);
        verify(richiestaRepository, times(1)).aggiornaPunti(100, "volontario1");
    }

    @Test
    public void testStatoAccettoVolontario() {
        doReturn(1).when(richiestaRepository).esisteVolontario("volontario1", 1);

        boolean result = richiestaService.statoAccettoVolontario(1, "volontario1");

        assertTrue(result);
        verify(richiestaRepository, times(1)).esisteVolontario("volontario1", 1);
    }

    @Test
    public void testGetRichieste() {
        List<Richiesta> richieste = Collections.singletonList(new Richiesta());
        doReturn(richieste).when(richiestaRepository).getRichieste("volontario1");

        List<Richiesta> result = richiestaService.getRichieste("volontario1");

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(richiestaRepository, times(1)).getRichieste("volontario1");
    }

    @Test
    public void testGetAllEmergency() {
        List<Richiesta> richieste = Collections.singletonList(new Richiesta());
        doReturn(richieste).when(richiestaRepository).findAllEmergenze();

        List<Richiesta> result = richiestaService.getAllEmergency();

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(richiestaRepository, times(1)).findAllEmergenze();
    }
}