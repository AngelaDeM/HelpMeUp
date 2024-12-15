package com.example.helpmeup;

import com.example.helpmeup.controller.PersonalAreaController;
import com.example.helpmeup.model.Assistito;
import com.example.helpmeup.model.Premio;
import com.example.helpmeup.model.Volontario;
import com.example.helpmeup.service.UtenteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.support.BindingAwareModelMap;

import jakarta.servlet.http.HttpSession;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class PersonalAreaControllerTest {

    @Mock
    private HttpSession session;

    @Mock
    private UtenteService utenteService;

    @InjectMocks
    private PersonalAreaController personalAreaController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testVisualizzaAreaPersonale_Volontario() {
        Model model = new BindingAwareModelMap();
        Volontario volontario = new Volontario();
        volontario.setPunti(100);
        volontario.setPremi(new ArrayList<Premio>());

        when(session.getAttribute("utente")).thenReturn(volontario);

        String viewName = personalAreaController.visualizzaAreaPersonale(session, model);

        assertEquals("AreaUtente/area_utente", viewName);
        assertEquals(volontario, model.getAttribute("utente"));
        assertEquals("volontario", model.getAttribute("tipo"));
        assertEquals(100, model.getAttribute("punti"));
        assertEquals(new ArrayList<Premio>(), model.getAttribute("premi"));
    }

    @Test
    public void testVisualizzaAreaPersonale_Assistito() {
        Model model = new BindingAwareModelMap();
        Assistito assistito = new Assistito();

        when(session.getAttribute("utente")).thenReturn(assistito);

        String viewName = personalAreaController.visualizzaAreaPersonale(session, model);

        assertEquals("AreaUtente/AreaAssistito", viewName);
        assertEquals(assistito, model.getAttribute("utente"));
        assertEquals("assistito", model.getAttribute("tipo"));
    }

    @Test
    public void testVisualizzaAreaPersonale_NoUser() {
        Model model = new BindingAwareModelMap();

        when(session.getAttribute("utente")).thenReturn(null);

        String viewName = personalAreaController.visualizzaAreaPersonale(session, model);

        assertEquals("redirect:/login", viewName);
    }

    @Test
    public void testVisualizzaTuttiIPremi() {
        Volontario volontario = new Volontario();
        List<Premio> premi = Collections.emptyList();

        when(session.getAttribute("utente")).thenReturn(volontario);
        when(utenteService.getAllPunti(volontario)).thenReturn(premi);

        ResponseEntity<List<Premio>> response = personalAreaController.visualizzaTuttiIPremi(session);

        assertEquals(ResponseEntity.ok(premi), response);
    }
}