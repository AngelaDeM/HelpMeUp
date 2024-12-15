package com.example.helpmeup;

import com.example.helpmeup.controller.registrazioneController;
import com.example.helpmeup.model.Assistito;
import com.example.helpmeup.model.Volontario;
import com.example.helpmeup.service.AssistitoService;
import com.example.helpmeup.service.VolontarioService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.validation.support.BindingAwareModelMap;

import jakarta.servlet.http.HttpSession;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class registrazioneControllerTest {

    @Mock
    private VolontarioService volontarioService;

    @Mock
    private AssistitoService assistitoService;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private HttpSession session;

    @InjectMocks
    private registrazioneController registrazioneController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testShowRegistrationForm() {
        Model model = new BindingAwareModelMap();
        String viewName = registrazioneController.showRegistrationForm(model);

        assertEquals("registrazione", viewName);
        assertTrue(model.containsAttribute("user"));
    }

    @Test
    public void testRegisterUser_Volontario() {
        Model model = new BindingAwareModelMap();
        Volontario utente = new Volontario();
        utente.setNome("John");
        utente.setCognome("Doe");
        utente.setSesso("M");
        utente.setDataNascita(LocalDate.of(1990, 1, 1));
        utente.setNumeroTelefono("1234567890");
        utente.setUsername("johndoe");
        utente.setEmail("john.doe@example.com");
        utente.setPassword("password");

        when(passwordEncoder.encode(anyString())).thenReturn("encodedPassword");
        when(volontarioService.verificaEmail(anyString())).thenReturn(false);
        when(assistitoService.verificaEmail(anyString())).thenReturn(false);
        when(volontarioService.verificaUsername(anyString())).thenReturn(false);
        when(assistitoService.verificaUsername(anyString())).thenReturn(false);

        String viewName = registrazioneController.registerUser("volontario", "password", "Province", "City", "Street", "123", utente, model, session);

        assertEquals("success", viewName);
        verify(volontarioService, times(1)).salvaUtente(any(Volontario.class));
        verify(session, times(1)).setAttribute(eq("utente"), any(Volontario.class));
    }

    @Test
    public void testRegisterUser_Assistito() {
        Model model = new BindingAwareModelMap();
        Volontario utente = new Volontario();
        utente.setNome("Jane");
        utente.setCognome("Doe");
        utente.setSesso("F");
        utente.setDataNascita(LocalDate.of(1992, 2, 2));
        utente.setNumeroTelefono("0987654321");
        utente.setUsername("janedoe");
        utente.setEmail("jane.doe@example.com");
        utente.setPassword("password");

        when(passwordEncoder.encode(anyString())).thenReturn("encodedPassword");
        when(volontarioService.verificaEmail(anyString())).thenReturn(false);
        when(assistitoService.verificaEmail(anyString())).thenReturn(false);
        when(volontarioService.verificaUsername(anyString())).thenReturn(false);
        when(assistitoService.verificaUsername(anyString())).thenReturn(false);

        String viewName = registrazioneController.registerUser("assistito", "password", "Province", "City", "Street", "123", utente, model, session);

        assertEquals("success", viewName);
        verify(assistitoService, times(1)).salvaAssistito(any(Assistito.class));
        verify(session, times(1)).setAttribute(eq("utente"), any(Volontario.class));
    }

    @Test
    public void testShowSuccessPage() {
        String viewName = registrazioneController.showSuccessPage();

        assertEquals("success", viewName);
    }
}