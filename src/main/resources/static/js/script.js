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

        // Simulate sending data to the server (replace with actual server-side logic)
        fetch('/login', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({ username, password })
        })
            .then(response => {
                if (response.ok) {
                    // Successful login
                    alert('Login successful!');
                    // Redirect to the desired page
                    window.location.href = 'dashboard.html'; // Replace with your desired URL
                } else {
                    // Handle login failure
                    alert('Login failed. Please check your credentials.');
                }
            })
            .catch(error => {
                console.error('Error:', error);
                alert('An error occurred. Please try again later.');
            });
    });
});