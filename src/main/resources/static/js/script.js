document.addEventListener('DOMContentLoaded', () => {
    const loginForm = document.querySelector('.login-container form');

    loginForm.addEventListener('submit', (event) => {
        event.preventDefault(); // Prevent default form submission

        // script.js
        const usernameInput = document.getElementById('username');
        const passwordInput = document.getElementById('password');

        usernameInput.addEventListener('focus', () => {
            usernameInput.classList.add('focus');
        });

        usernameInput.addEventListener('blur', () => {
            usernameInput.classList.remove('focus');
        });

        passwordInput.addEventListener('focus', () => {
            passwordInput.classList.add('focus');
        });

        passwordInput.addEventListener('blur', () => {
            passwordInput.classList.remove('focus');
        });

        // Basic validation
        if (!username || !password) {
            alert('Please enter both username and password.');
            return;
        }

        // Additional validation (optional)
        if (password.length < 8) {
            alert('Password must be at least 8 characters long.');
            return;
        }
        loginForm.submit();

    });
});