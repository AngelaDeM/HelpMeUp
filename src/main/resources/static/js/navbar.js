<!-- Script per la gestione del dropdown -->
    const userIcon = document.getElementById('icon-img');
    const dropdownMenu = document.querySelector('.dropdown-menu');

    userIcon.addEventListener('click', () => {
    dropdownMenu.style.display = dropdownMenu.style.display === 'block' ? 'none' : 'block';
});

    // Nascondi il menu cliccando fuori
    window.addEventListener('click', (event) => {
    if (!userIcon.contains(event.target) && !dropdownMenu.contains(event.target)) {
    dropdownMenu.style.display = 'none';
}
});
