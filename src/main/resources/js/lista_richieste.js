
    // Funzione per filtrare le richieste
    document.getElementById('searchInput').addEventListener('input', function() {
    const searchTerm = this.value.toLowerCase(); // Ottieni il termine di ricerca
    const requestItems = document.querySelectorAll('.request-item'); // Seleziona tutte le richieste

    // Loop su tutte le richieste
    requestItems.forEach(function(requestItem) {
    const title = requestItem.querySelector('h3').textContent.toLowerCase(); // Ottieni il titolo della richiesta
    const description = requestItem.querySelector('p').textContent.toLowerCase(); // Ottieni la descrizione

    // Mostra o nascondi le richieste in base al termine di ricerca
    if (title.includes(searchTerm) || description.includes(searchTerm)) {
    requestItem.style.display = ''; // Mostra l'elemento
} else {
    requestItem.style.display = 'none'; // Nascondi l'elemento
}
});
});
