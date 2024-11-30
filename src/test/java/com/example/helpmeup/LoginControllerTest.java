package com.example.helpmeup;

import com.example.helpmeup.controller.loginController;
import com.example.helpmeup.model.Utente;
import com.example.helpmeup.repository.UtenteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.support.SessionStatus;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class LoginControllerTest {

    @Mock
    private Model model;

    @Mock
    private SessionStatus sessionStatus;

    @InjectMocks
    private loginController loginController;

    @Mock
    private UtenteRepository utenteRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testLoginUser() {
        Utente utente = new Utente();
        utente.setUsername("test");
        utente.setPassword(new BCryptPasswordEncoder().encode("password"));

        when(utenteRepository.findByUsername("test")).thenReturn(utente);

        String result = loginController.loginUser("test", "password", model);

        assertEquals("redirect:/", result);
    }

    @Test
    public void testLogoutUser() {
        String result = loginController.logoutUser(sessionStatus);

        verify(sessionStatus, times(1)).setComplete();
        assertEquals("redirect:/login", result);
    }
}
