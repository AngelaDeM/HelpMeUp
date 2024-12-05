
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


    document.addEventListener('DOMContentLoaded', () => {
        fetch('/findAllRichieste')
            .then(response => response.json())
            .then(data => {
                const requestsList = document.querySelector('.requests-list');
                requestsList.innerHTML = ''; // Svuota la lista esistente

                data.forEach(request => {
                    const requestItem = `
                    <label for="request${request.id}" class="request-item">
                        <div>
                            <h3>${request.titolo}</h3>
                            <p><strong>Data:</strong> ${request.data_intervento}</p>
                            <p><strong>Ora:</strong> ${request.orario_intervento}</p>
                            <p><strong>Luogo:</strong> ${request.utente.indirizzo}</p>
                            <p>${request.descrizione}</p>
                        </div>
                    </label>
                    <input type="checkbox" id="request${request.id}" class="modal-trigger">
                    <div class="modal" id="modal${request.id}">
                        <div class="modal-content">
                            <label for="request${request.id}" class="close-btn">×</label>
                            <h3>${request.titolo}</h3>
                            <p><strong>Data:</strong> ${request.data_intervento}</p>
                            <p><strong>Ora:</strong> ${request.orario_intervento}</p>
                            <p><strong>Luogo:</strong> ${request.utente.indirizzo}</p>
                            <p>${request.descrizione}</p>
                            <div class="action-buttons">
                                <button class="accept-btn">Accetta</button>
                                <button class="reject-btn">Rifiuta</button>
                            </div>
                        </div>
                    </div>
                `;
                    requestsList.insertAdjacentHTML('beforeend', requestItem);
                });
            })
            .catch(error => console.error('Errore nel caricamento delle richieste:', error));
    });
