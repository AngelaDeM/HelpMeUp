
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
        // Funzione per ottenere tutte le richieste
        fetch('/api/findAllRichieste')
            .then(response => {
                console.log('Risposta ricevuta:', response); // Visualizza l'oggetto Response
                return response.json();
            })
            .then(data => {
                // Popola la lista delle richieste nella pagina
                const requestsList = document.querySelector('.requests-list');
                requestsList.innerHTML = ''; // Pulisce eventuali contenuti preesistenti

                data.forEach(richiesta => {
                    const requestItem = document.createElement('div');
                    requestItem.classList.add('request-item');

                    requestItem.innerHTML = `
                    <h2>${richiesta.titolo}</h2>
                    <p><strong>Descrizione:</strong> ${richiesta.descrizione}</p>
                    <p><strong>Data di Creazione:</strong> ${new Date(richiesta.data_creazione).toLocaleDateString()}</p>
                    <p><strong>Data Intervento:</strong> ${new Date(richiesta.data_intervento).toLocaleDateString()}</p>
                    <p><strong>Orario Intervento:</strong> ${richiesta.orario_intervento}</p>
                    <p><strong>Emergenza:</strong> ${richiesta.emergenza ? 'Sì' : 'No'}</p>
                `;

                    requestsList.appendChild(requestItem);
                });
            })
            .catch(error => {
                console.error('Errore nel recupero delle richieste:', error);
            });

        // Funzione per filtrare le richieste in base all'input di ricerca
        const searchInput = document.getElementById('searchInput');
        searchInput.addEventListener('input', () => {
            const query = searchInput.value.toLowerCase();
            const requestItems = document.querySelectorAll('.request-item');

            requestItems.forEach(item => {
                const title = item.querySelector('h2').textContent.toLowerCase();
                const description = item.querySelector('p').textContent.toLowerCase();
                if (title.includes(query) || description.includes(query)) {
                    item.style.display = 'block';
                } else {
                    item.style.display = 'none';
                }
            });
        });
    });
