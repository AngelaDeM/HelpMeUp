package com.example.helpmeup;

import com.example.helpmeup.controller.visualizzazioneRichiesteController;
import com.example.helpmeup.model.Richiesta;
import com.example.helpmeup.repository.RichiestaRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@Transactional
@Rollback
class VisualizzazioneRichiesteControllerTest {

    @Autowired
    private RichiestaRepository richiestaRepository;

    @Autowired
    private visualizzazioneRichiesteController controller;

/*    @BeforeEach
    void setUp() {
        // Populate the database with test data if necessary
        Richiesta richiesta1 = new Richiesta();
        richiesta1.setTitolo("Test Data 1"); // Replace with actual fields
        Richiesta richiesta2 = new Richiesta();
        richiesta2.setTitolo("Test Data 2"); // Replace with actual fields

        richiestaRepository.save(richiesta1);
        richiestaRepository.save(richiesta2);
    }*/

    @Test
    void testGetAllRichieste() {
        // Retrieve data using the controller
       // List<Richiesta> richieste = controller.getAllRichieste();

        // Assertions to verify database interaction
       // assertEquals(1, richieste.size());
       // assertTrue(richieste.stream().anyMatch(r -> "Prova".equals(r.getTitolo())));
    }
}
