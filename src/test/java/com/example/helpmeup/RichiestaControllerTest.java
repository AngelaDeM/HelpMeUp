package com.example.helpmeup;

import com.example.helpmeup.controller.RichiestaController;
import com.example.helpmeup.model.Richiesta;
import com.example.helpmeup.model.Utente;
import com.example.helpmeup.model.Volontario;
import com.example.helpmeup.service.RichiestaService;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.support.BindingAwareModelMap;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class RichiestaControllerTest {

    @Mock
    private RichiestaService richiestaService;

    @Mock
    private HttpSession session;

    @InjectMocks
    private RichiestaController richiestaController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testMostraEmergenze() {
        List<Richiesta> emergenze = List.of(new Richiesta());
        when(richiestaService.getAllEmergency()).thenReturn(emergenze);

        ResponseEntity<List<Richiesta>> response = richiestaController.mostraEmergenze();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(emergenze, response.getBody());
    }

    @Test
    public void testMostraFormPubblicazione() {
        String viewName = richiestaController.mostraFormPubblicazione();
        assertEquals("Richiesta/pubblica_richiesta", viewName);
    }

    @Test
    public void testRegistraRichiesta() {
        Map<String, String> dati = new HashMap<>();
        dati.put("titolo", "Test Titolo");
        dati.put("descrizione", "Test Descrizione");
        dati.put("data", "2023-12-25");
        dati.put("ora", "10:00");
        dati.put("emergenza", "true");

        Volontario utente = new Volontario();
        utente.setUsername("testUser");
        when(session.getAttribute("utente")).thenReturn(utente);

        Model model = new BindingAwareModelMap();

        String viewName = richiestaController.registraRichiesta(dati, session, model);

        assertEquals("Richiesta/pubblica_richiesta", viewName);
        assertEquals("Successo", model.getAttribute("tipo"));
        assertEquals("Registrazione richiesta avvenuta con successo.", model.getAttribute("message"));
    }

    @Test
    public void testMostraFormAccetta() {
        String viewName = richiestaController.mostraFormAccetta();
        assertEquals("Richiesta/accetta_richiesta", viewName);
    }

    @Test
    public void testAccettaRichiesta() {
        Map<String, String> dati = new HashMap<>();
        dati.put("richiesta", "1");
        dati.put("volontario", "testVolontario");

        Richiesta richiesta = new Richiesta();
        richiesta.setCompletato(false);
        when(richiestaService.getRichiestaById(1)).thenReturn(richiesta);
        when(richiestaService.statoAccettoVolontario(1, "testVolontario")).thenReturn(false);

        ResponseEntity<String> response = richiestaController.accettaRichiesta(dati);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Richiesta accettata con successo.", response.getBody());
    }

    @Test
    public void testMostraFormModifica() {
        String viewName = richiestaController.mostraFormModifica();
        assertEquals("Richiesta/modifica_richiesta", viewName);
    }

    @Test
    public void testModificaRichiesta() {
        Map<String, String> dati = new HashMap<>();
        dati.put("richiesta", "1");
        dati.put("titolo", "Titolo Modificato");
        dati.put("descrizione", "Descrizione Modificata");
        dati.put("data", "2023-12-25");
        dati.put("ora", "10:00");
        dati.put("emergenza", "true");

        Richiesta richiesta = new Richiesta();
        richiesta.setCompletato(false);
        when(richiestaService.getRichiestaById(1)).thenReturn(richiesta);

        Model model = new BindingAwareModelMap();

        String viewName = richiestaController.modificaRichiesta(dati, model);

        assertEquals("Richiesta/visualizza_richieste", viewName);
        assertEquals("Successo", model.getAttribute("tipo"));
        assertEquals("Registrazione richiesta avvenuta con successo.", model.getAttribute("message"));
    }

    @Test
    public void testDeleteRichiesta() {
        Map<String, String> dati = new HashMap<>();
        dati.put("id", "1");

        Richiesta richiesta = new Richiesta();
        richiesta.setCompletato(false);
        when(richiestaService.getRichiestaById(1)).thenReturn(richiesta);

        Model model = new BindingAwareModelMap();

        String viewName = richiestaController.deleteRichiesta(dati, model);

        assertEquals("Richiesta/visualizza_richieste", viewName);
        assertEquals("Successo", model.getAttribute("tipo"));
        assertEquals("Richiesta eliminata con successo.", model.getAttribute("message"));
    }

    @Test
    public void testMostraFormCompletarichiesta() {
        String viewName = richiestaController.mostraFormCompletarichiesta();
        assertEquals("Richiesta/completa_richiesta", viewName);
    }

    @Test
    public void testCompletaRichiesta() {
        Map<String, String> dati = new HashMap<>();
        dati.put("richiesta", "1");

        Richiesta richiesta = new Richiesta();
        when(richiestaService.getRichiestaById(1)).thenReturn(richiesta);
        when(richiestaService.getVolontari(1)).thenReturn(List.of("volontario1"));

        ResponseEntity<String> response = richiestaController.completaRichiesta(dati);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Richiesta completata con successo.", response.getBody());
    }
}