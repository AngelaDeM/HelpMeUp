package com.example.helpmeup;

import com.example.helpmeup.controller.loginController;
import com.example.helpmeup.model.Volontario;
import com.example.helpmeup.repository.UtenteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.validation.support.BindingAwareModelMap;
import org.springframework.web.bind.support.SessionStatus;

import jakarta.servlet.http.HttpSession;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class loginControllerTest {

    @Mock
    private UtenteRepository utenteRepository;

    @Mock
    private HttpSession session;

    @Mock
    private SessionStatus sessionStatus;

    @InjectMocks
    private loginController loginController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testShowLoginForm() {
        String viewName = loginController.showLoginForm();
        assertEquals("login", viewName);
    }

    @Test
    public void testLoginUser_Success() {
        Model model = new BindingAwareModelMap();
        Volontario utente = new Volontario();
        utente.setUsername("johndoe");
        utente.setPassword(new BCryptPasswordEncoder().encode("password"));

        when(utenteRepository.findByUsername("johndoe")).thenReturn(utente);

        String viewName = loginController.loginUser("johndoe", "password", session, model);

        assertEquals("redirect:/", viewName);
        assertTrue(model.containsAttribute("utente"));
        verify(session, times(1)).setAttribute("utente", utente);
    }

    @Test
    public void testLoginUser_UserNotFound() {
        Model model = new BindingAwareModelMap();

        when(utenteRepository.findByUsername("johndoe")).thenReturn(null);

        String viewName = loginController.loginUser("johndoe", "password", session, model);

        assertEquals("redirect:/login?error", viewName);
    }

    @Test
    public void testLoginUser_WrongPassword() {
        Model model = new BindingAwareModelMap();
        Volontario utente = new Volontario();
        utente.setUsername("johndoe");
        utente.setPassword(new BCryptPasswordEncoder().encode("password"));

        when(utenteRepository.findByUsername("johndoe")).thenReturn(utente);

        String viewName = loginController.loginUser("johndoe", "wrongpassword", session, model);

        assertEquals("redirect:/login?error", viewName);
    }

    @Test
    public void testLogoutUser() {
        String viewName = loginController.logoutUser(sessionStatus, session);

        assertEquals("redirect:login", viewName);
        verify(session, times(1)).removeAttribute("utente");
        verify(sessionStatus, times(1)).setComplete();
    }
}