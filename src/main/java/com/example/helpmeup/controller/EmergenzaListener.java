package com.example.helpmeup.controller;

import com.example.helpmeup.model.Richiesta;
import jakarta.persistence.PostPersist;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Listener che si attiva dopo il persistente di una richiesta.
 * Se la richiesta è di tipo emergenza, viene gestita di conseguenza.
 *
 * @author ROBERTO-SCARPA
 * @author fimiani
 */
public class EmergenzaListener {

    private static final Logger logger = LoggerFactory.getLogger(EmergenzaListener.class);

    /**
     * Metodo che viene invocato dopo che una richiesta è stata persistita nel database.
     * Se la richiesta è di tipo emergenza, il metodo gestisce l'emergenza.
     *
     * @param r La richiesta appena persistita.
     * @throws NullPointerException Se la richiesta è null.
     */
    @PostPersist
    public void onPostPersist(Richiesta r) {
        if (r == null) {
            logger.error("La richiesta passata al listener è null");
            throw new NullPointerException("La richiesta non può essere null");
        }
        if (r.isEmergenza()) {
            // Chiamata al metodo che gestisce l'emergenza
            handleEmergenza(r);
        }
    }

    /**
     * Metodo che gestisce l'emergenza, ad esempio inviando una notifica o loggando l'emergenza.
     *
     * @param r La richiesta che rappresenta l'emergenza.
     * @throws IllegalArgumentException Se la richiesta non è valida.
     * @return void
     */
    private void handleEmergenza(Richiesta r) {
        if (r == null) {
            throw new IllegalArgumentException("La richiesta non può essere null");
        }

        // Esegui l'azione, ad esempio inviare una notifica o loggare l'emergenza
        System.out.println("Emergenza attivata per " + r);
        // Log per monitoraggio
        logger.info("Emergenza attivata per la richiesta: {}", r);
    }
}
