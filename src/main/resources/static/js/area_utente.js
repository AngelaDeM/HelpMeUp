

    // Non mostrare nessuna sezione all'inizio
    document.querySelectorAll('.section').forEach(section => {
    section.classList.remove('active');
});

    // Mostra la sezione "Dati Anagrafici" quando si clicca sul link
    document.querySelector('#dati').classList.add('active');



    // Aggiungi un event listener a ciascun link nel menu
    document.querySelectorAll('.nav li a').forEach(link => {
    link.addEventListener('click', function(e) {
        e.preventDefault();  // Impedisci il comportamento di default del link

        // Rimuovi la classe 'active' da tutte le sezioni
        document.querySelectorAll('.section').forEach(section => {
            section.classList.remove('active');
        });

        // Mostra la sezione corrispondente
        const targetSection = document.querySelector(this.getAttribute('href'));
        if (targetSection) {
            targetSection.classList.add('active');  // Mostra solo la sezione cliccata
        }
    });
});








    document.getElementById('hamburger').addEventListener('click', function() {
    console.log('Hamburger clicked');
    const sidebar = document.getElementById('sidebar');
    sidebar.classList.toggle('active');
});




