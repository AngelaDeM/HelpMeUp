

    document.addEventListener('DOMContentLoaded', () => {
    const form = document.getElementById('user-form');
    const editButton = document.getElementById('edit-button');
    const confirmButton = document.getElementById('confirm-button');
    const inputs = form.querySelectorAll('input');


        document.addEventListener('click', function(event) {
            if (event.target.id === 'edit-button') {
                inputs.forEach(input => input.disabled = false);
                editButton.style.display = 'none';
                confirmButton.style.display = 'block';
            }
        });

    // Handle form submission
    form.addEventListener('submit', (event) => {
    event.preventDefault();

    // Raccolta dati modificati
    const updatedData = {};
    inputs.forEach(input => {
    if (!input.disabled && input.value !== input.defaultValue) {
    updatedData[input.name] = input.value;
}
});

    if (Object.keys(updatedData).length === 0) {
    alert('Nessuna modifica effettuata.');
    return;
}

    // Invio dei dati modificati al server
    fetch('/update-user-data', {
    method: 'PUT',
    headers: {
    'Content-Type': 'application/json',
},
    body: JSON.stringify(updatedData),
})
    .then(response => {
    if (response.ok) {
    alert('Dati aggiornati con successo!');
    inputs.forEach(input => {
    input.defaultValue = input.value; // Aggiorna il valore predefinito
    input.disabled = true;
});
    editButton.style.display = 'block';
    confirmButton.style.display = 'none';
} else {
    alert('Errore durante l\'aggiornamento dei dati.');
}
})
    .catch(error => {
    console.error('Errore:', error);
    alert('Si è verificato un errore. Riprova più tardi.');
});
});
});
