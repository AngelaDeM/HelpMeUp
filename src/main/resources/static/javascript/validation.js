// src/main/resources/static/js/validation.js

function validateForm() {
    // Ottieni tutti i valori del form
    var nome = document.getElementById("nome").value;
    var cognome = document.getElementById("cognome").value;
    var username = document.getElementById("username").value;
    var data_nascita = document.getElementById("data_nascita").value;
    var email = document.getElementById("email").value;
    var numero_telefono = document.getElementById("numero_telefono").value;
    var sesso = document.getElementById("sesso").value;
    var indirizzo = document.getElementById("indirizzo").value;
    var password = document.getElementById("password").value;
    var confirmPassword = document.getElementById("confirmPassword").value;

    // Verifica che tutti i campi obbligatori siano compilati
    if (!nome || !cognome || !username || !data_nascita || !email || !numero_telefono || !sesso || !indirizzo || !password || !confirmPassword) {
        alert("Tutti i campi devono essere compilati.");
        return false;
    }

    // Verifica che la password e la conferma password siano uguali
    if (password !== confirmPassword) {
        alert("Le password non corrispondono.");
        return false;
    }

    // Verifica che il numero di telefono contenga solo 10 cifre
    var phoneRegex = /^\d{10}$/;
    if (!phoneRegex.test(numero_telefono)) {
        alert("Il numero di telefono deve contenere esattamente 10 cifre.");
        return false;
    }

    // Se tutto è valido, invia il form
    return true;
}
