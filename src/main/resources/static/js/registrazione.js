const form = document.querySelector('form');
const inputs = document.querySelectorAll('input');

function validateEmail(email) {
    const re = /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    return re.test(String(email).toLowerCase());
}

function validatePassword(password) {
    return password.length >= 5 && // Minimum length
        /[a-z]/.test(password) && // Lowercase letter
        /[A-Z]/.test(password) && // Uppercase letter
        /[0-9]/.test(password); // Number
}

function showError(input, message) {
    const errorElement = document.createElement('span');
    errorElement.classList.add('error');
    errorElement.textContent = message;
    input.parentElement.appendChild(errorElement);
    input.classList.add('invalid');
}

function removeError(input) {
    const errorElement = input.parentElement.querySelector('.error');
    if (errorElement) {
        errorElement.remove();
        input.classList.remove('invalid');
    }
}

inputs.forEach(input => {
    input.addEventListener('blur', (event) => {
        const value = event.target.value;
        removeError(input); // Clear previous errors on blur

        switch (input.type) {
            case 'email':
                if (!value || !validateEmail(value)) {
                    showError(input, 'Please enter a valid email address.');
                }
                break;
            case 'password':
                if (!value || !validatePassword(value)) {
                    showError(input, 'Password must be at least 5 characters and include uppercase, lowercase letters, and numbers.');
                }
                break;
            default:
                if (!value) {
                    showError(input, 'This field is required.');
                }
        }
    });
});

form.addEventListener('submit', (event) => {
    event.preventDefault(); // Prevent default form submission

    let isValid = true;

    const name = document.getElementById('name').value;
    const surname = document.getElementById('cognome').value;
    const email = document.getElementById('email').value;
    const password = document.getElementById('password').value;
    const confirmPassword = document.getElementById('confirm-password').value;
    const privacyCheckbox = document.getElementById('privacy');

    // Remove any previous errors
    inputs.forEach(removeError);

    if (!name || !surname || !email || !password || !confirmPassword || !privacyCheckbox.checked) {
        showError(form, 'Please fill in all required fields and accept the privacy policy.');
        isValid = false;
    }

    if (password !== confirmPassword) {
        showError(form, 'Passwords do not match.');
        isValid = false;
    }

    if (!validateEmail(email)) {
        showError(document.getElementById('email'), 'Please enter a valid email address.');
        isValid = false;
    }

    if (!validatePassword(password)) {
        showError(document.getElementById('password'), 'Password must be at least 5 characters and include uppercase, lowercase letters, and numbers.');
        isValid = false;
    }

    if (isValid) {
        form.submit(); // Submit the form if all validations pass
    }
});