

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

    async function fetchRequests() {
    try {
    const response = await fetch('/api/requests');
    if (!response.ok) throw new Error('Errore nel recupero delle richieste');
    const requests = await response.json();

    // Filtra le richieste con emergenza: true
    const emergencyRequests = requests.filter(request => request.emergenza);

    // Popola la lista
    const requestsList = document.querySelector('.requests-list');
    requestsList.innerHTML = ''; // Svuota la lista precedente
    emergencyRequests.forEach(request => {
    requestsList.innerHTML += `
                 <label for="request${request.id}" class="request-item">
                        <div>
                            <h3>${request.titolo}</h3>
                            <p><strong>Data:</strong> ${request.data_intervento}</p>
                            <p><strong>Ora:</strong> ${request.orario_intervento}</p>
                            <p><strong>Luogo:</strong> ${request.utente.indirizzo}</p>
                            <p>${request.descrizione}</p>
                        </div>
            `;
});
} catch (error) {
    console.error('Errore:', error);
}
}

    // Esegui la funzione all'avvio
    fetchRequests();


