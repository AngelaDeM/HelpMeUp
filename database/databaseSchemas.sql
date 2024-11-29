-- Tabella Account
CREATE TABLE account (
                         id INT AUTO_INCREMENT PRIMARY KEY,
                         nome VARCHAR(50) NOT NULL,
                         cognome VARCHAR(50) NOT NULL,
                         username VARCHAR(50) UNIQUE NOT NULL,
                         tipo_account ENUM('volontario', 'assistito') NOT NULL,
                         password VARCHAR(255) NOT NULL,
                         data_nascita DATE NOT NULL,
                         sesso ENUM('M', 'F') NOT NULL,
                         email VARCHAR(100) NOT NULL UNIQUE,
                         indirizzo VARCHAR(255) NOT NULL,
                         numero_telefono VARCHAR(10),
                         punti INT DEFAULT 0,
                         certificazioni VARCHAR(1000)
);

CREATE TABLE certificazione(
                        id INT AUTO_INCREMENT PRIMARY KEY,
                        titolo VARCHAR(50) NOT NULL,
                        account_id INT NOT NULL,
                        FOREIGN KEY (account_id) REFERENCES account(id) ON DELETE CASCADE
);


-- Tabella Richieste
CREATE TABLE richiesta (
                           id INT AUTO_INCREMENT PRIMARY KEY,
                           titolo VARCHAR(100) NOT NULL,
                           descrizione TEXT NOT NULL,
                           data DATE NOT NULL,
                           orario TIME NOT NULL,
                           stato ENUM('emergenza', 'no_emergenza') NOT NULL,
                           account_id INT NOT NULL,
                           FOREIGN KEY (account_id) REFERENCES account(id) ON DELETE CASCADE
);

-- Tabella Forum Messaggi
CREATE TABLE messaggio (
                           id INT AUTO_INCREMENT PRIMARY KEY,
                           titolo VARCHAR(100),  -- Sarà NULL se è una risposta
                           contenuto TEXT NOT NULL,
                           data_invio TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                           account_id INT NOT NULL,
                           forum_id INT,  -- NULL se è un messaggio di apertura
                           FOREIGN KEY (account_id) REFERENCES account(id) ON DELETE CASCADE,
                           FOREIGN KEY (forum_id) REFERENCES messaggio(id) ON DELETE CASCADE
);

-- Tabella Calendario (per gestire gli eventi o appuntamenti legati a richieste)
CREATE TABLE calendario (
                            id INT AUTO_INCREMENT PRIMARY KEY,
                            richiesta_id INT NOT NULL,
                            evento_data DATE NOT NULL,
                            evento_orario TIME NOT NULL,
                            descrizione TEXT,
                            FOREIGN KEY (richiesta_id) REFERENCES richiesta(id) ON DELETE CASCADE
);

-- Tabella Premi
CREATE TABLE premio (
                       id INT AUTO_INCREMENT PRIMARY KEY,
                       nome VARCHAR(100) NOT NULL,
                       descrizione TEXT,
                       punti_richiesti INT NOT NULL,
                       disponibilita INT NOT NULL  -- Numero di premi disponibili
);

-- Tabella Riscatti Premi
CREATE TABLE riscatti_premi (
                                id INT AUTO_INCREMENT PRIMARY KEY,
                                account_id INT NOT NULL,
                                premio_id INT NOT NULL,
                                data_riscatto TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                FOREIGN KEY (account_id) REFERENCES account(id) ON DELETE CASCADE,
                                FOREIGN KEY (premio_id) REFERENCES premio(id) ON DELETE CASCADE
);