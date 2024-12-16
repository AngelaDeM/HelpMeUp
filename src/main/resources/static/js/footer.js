// Get the social icons container
const socialIconsContainer = document.querySelector('.social-icons');

// Array of social media links and icons
const socialMedia = [
    {
        link: 'https://www.facebook.com/your-facebook-page',
        icon: 'fa-facebook'
    },
    {
        link: 'https://twitter.com/your-twitter-handle',
        icon: 'fa-twitter'
    },
    {
        link: 'https://www.instagram.com/your-instagram-handle',
        icon: 'fa-instagram'
    },
    // Add more social media links and icons as needed
];

// Create and append social media icons
socialMedia.forEach(social => {
    const iconLink = document.createElement('a');
    iconLink.href = social.link;
    iconLink.target = '_blank'; // Open links in a new tab

    const icon = document.createElement('i');
    icon.classList.add('fa', social.icon);

    iconLink.appendChild(icon);
    socialIconsContainer.appendChild(iconLink);
});