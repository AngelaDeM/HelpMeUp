package com.example.helpmeup;

import com.example.helpmeup.model.Premio;
import com.example.helpmeup.repository.PremioRepository;
import com.example.helpmeup.service.PremioService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class PremioServiceTest {

    @Mock
    private PremioRepository premioRepository;

    @InjectMocks
    private PremioService premioService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testRiscattaPremio() {
        doNothing().when(premioRepository).riscattaPremio("Premio1", "utente1");

        premioService.riscattaPremio("Premio1", "utente1");

        verify(premioRepository, times(1)).riscattaPremio("Premio1", "utente1");
    }

    @Test
    public void testGetAllPremi() {
        List<Premio> premi = Collections.singletonList(new Premio("Premio1", "Descrizione1", 100));
        doReturn(premi).when(premioRepository).findAll();

        List<Premio> result = premioService.getAllPremi();

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(premioRepository, times(1)).findAll();
    }

    @Test
    public void testInsertPremio() {
        Premio premio = new Premio("Premio1", "Descrizione1", 100);
        doReturn(premio).when(premioRepository).save(premio);

        premioService.InsertPremio(premio);

        verify(premioRepository, times(1)).save(premio);
    }

    @Test
    public void testGetAllPremiByUser() {
        List<Object[]> premi = Collections.singletonList(new Object[]{"Premio1", "Descrizione1", 100});
        doReturn(premi).when(premioRepository).getByVolontario("utente1");

        List<Object[]> result = premioService.getAllPremiByUser("utente1");

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(premioRepository, times(1)).getByVolontario("utente1");
    }

    @Test
    public void testGetPremioByNome() {
        Premio premio = new Premio("Premio1", "Descrizione1", 100);
        doReturn(premio).when(premioRepository).getByNome("Premio1");

        Premio result = premioService.getPremioByNome("Premio1");

        assertNotNull(result);
        assertEquals("Premio1", result.getNome());
        verify(premioRepository, times(1)).getByNome("Premio1");
    }
}