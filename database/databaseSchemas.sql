-- Tabella Account
CREATE TABLE Utente (
                         nome VARCHAR(50) NOT NULL,
                         cognome VARCHAR(50) NOT NULL,
                         username VARCHAR(50) PRIMARY KEY,
                         tipo_account ENUM('volontario', 'assistito') NOT NULL,
                         password VARCHAR(255) NOT NULL,
                         data_nascita DATE NOT NULL,
                         sesso ENUM('M', 'F') NOT NULL,
                         email VARCHAR(100) NOT NULL UNIQUE,
                         indirizzo VARCHAR(255) NOT NULL,
                         numero_telefono VARCHAR(10),
                         punti INT DEFAULT 0
);

-- Tabella Richieste
CREATE TABLE Richiesta (
                           id INT AUTO_INCREMENT PRIMARY KEY,
                           titolo VARCHAR(100) NOT NULL,
                           descrizione TEXT NOT NULL,
                           data_creazione DATE NOT NULL,
                           data_intervento DATE NOT NULL,
                           orario_intervento TIME NOT NULL,
                           emergenza BOOLEAN NOT NULL,
                           account_id VARCHAR(50) NOT NULL,
                           FOREIGN KEY (account_id) REFERENCES Utente(username) ON DELETE CASCADE
);

-- Tabella Forum Messaggi
CREATE TABLE Messaggio (
                           id INT AUTO_INCREMENT PRIMARY KEY,
                           titolo VARCHAR(100),  -- Sarà NULL se è una risposta
                           contenuto TEXT NOT NULL,
                           data_invio TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                           account_id VARCHAR(50) NOT NULL,
                           forum_id INT,  -- NULL se è un messaggio di apertura
                           FOREIGN KEY (account_id) REFERENCES Utente(username) ON DELETE CASCADE,
                           FOREIGN KEY (forum_id) REFERENCES Messaggio(id) ON DELETE CASCADE
);

-- Tabella Premi
CREATE TABLE Premio (
                       nome VARCHAR(100) PRIMARY KEY,
                       descrizione TEXT,
                       punti_richiesti INT NOT NULL
);

-- Tabella Riscatti Premi
CREATE TABLE riscatti_premi (
                                account_id VARCHAR(50) NOT NULL,
                                premio_id VARCHAR(100) NOT NULL,
                                data_riscatto TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                FOREIGN KEY (account_id) REFERENCES Utente(username) ON DELETE CASCADE,
                                FOREIGN KEY (premio_id) REFERENCES Premio(nome) ON DELETE CASCADE
);
