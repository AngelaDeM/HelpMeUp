function aggiornaDataOra() {
    const now = new Date();
    const options = { year: 'numeric', month: 'numeric', day: 'numeric', hour: 'numeric', minute: 'numeric', second: 'numeric' };
    const formattedDateTime = now.toLocaleDateString('it-IT', options);
    document.getElementById('data-ora').textContent = formattedDateTime;
}

// Esegui la funzione all'onload e ogni secondo (puoi regolare l'intervallo)
window.onload = aggiornaDataOra;
setInterval(aggiornaDataOra, 1000);

const form = document.querySelector('form');

form.addEventListener('submit', async (event) => {
    event.preventDefault();

    const titolo = document.getElementById('titolo').value;
    const descrizione = document.getElementById('descrizione').value;
    const emergenza = document.querySelector('input[name="emergenza"]:checked').value;

    const formData = {
        titolo,
        descrizione,
        emergenza
    };

    try {
        // Mostra un indicatore di attività (opzionale)
        document.getElementById('submit-button').disabled = true;
        document.getElementById('loading-indicator').style.display = 'block';

        const response = await fetch('your-server-endpoint', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(formData)
        });

        if (!response.ok) {
            throw new Error('Network response was not ok');
        }

        const data = await response.json();
        // Handle the server response here
        console.log(data);
        alert('Richiesta inviata con successo!');
        form.reset();
    } catch (error) {
        console.error('Error:', error);
        alert('Errore nell\'invio della richiesta. Riprova più tardi.');
    } finally {
        // Nasconde l'indicatore di attività (opzionale)
        document.getElementById('submit-button').disabled = false;
        document.getElementById('loading-indicator').style.display = 'none';
    }
});