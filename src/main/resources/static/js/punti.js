// Inizializzazione dei punti
    // Inizializzazione dei punti
    let points = 0;
    const maxPoints = 300; // Limite massimo di punti
    const progressBar = document.getElementById('progress-bar');
    const pointsText = document.getElementById('points-text');

    // Premi con i relativi punti necessari
    const rewards = [
    { id: 20, name: "Certificato di Volontariato" },
    { id: 50, name: "Badge di Volontario Esperto" },
    { id: 100, name: "Premio Speciale" },
    { id: 150, name: "Medagli d'Oro" },
    { id: 200, name: "Trofeo di Volontariato" },
    { id: 300, name: "Volontario dell'Anno" },
    ];

    // Funzione per aggiornare la barra dei punti
    function updatePoints() {
        const percentage = (points / maxPoints) * 100;
        progressBar.style.width = `${percentage}%`;
        pointsText.textContent = `${points} Punti`;

        // Abilita o disabilita i premi in base al punteggio
        rewards.forEach(reward => {
        const rewardButton = document.getElementById(`reward-${reward.id}`);
        if (points >= reward.id) {
        rewardButton.classList.remove('disabled');

    } else {
        rewardButton.classList.add('disabled');

    }
    });
    }

    // Funzione per completare una richiesta
    function completeRequest(requestPoints) {
        if (points < maxPoints) {
        const newPoints = points + requestPoints;
        if (newPoints > maxPoints) {
        points = maxPoints; // Limita i punti al massimo consentito
        showPopup('Hai raggiunto il massimo dei punti!');
    } else {
        points = newPoints;
    }
        updatePoints();
    } else {
        showPopup('Hai già raggiunto il massimo dei punti!');
    }
    }

    // Funzione per mostrare il popup
    function showPopup(message) {
        const popup = document.getElementById('popup');
        const popupMessage = document.getElementById('popup-message');
        popupMessage.textContent = message;
        popup.classList.remove('hidden');
    }

    // Funzione per chiudere il popup
    function closePopup() {
        const popup = document.getElementById('popup');
        popup.classList.add('hidden');
    }

    // Funzione per aggiungere un premio riscattato alla cronologia
    function addToRedeemedHistory(rewardName, rewardPoints) {
        const redeemedList = document.getElementById('redeemed-list');
        const listItem = document.createElement('li');
        listItem.textContent = `Premio: ${rewardName} - ${rewardPoints} Punti`;
        redeemedList.appendChild(listItem);
    }

    // Funzione per riscattare un premio
    function redeemReward(requiredPoints) {
        const reward = rewards.find(r => r.id === requiredPoints);
        if (points >= requiredPoints) {
        points -= requiredPoints;
        updatePoints();
        addToRedeemedHistory(reward.name, requiredPoints);
        showPopup(`Complimenti! Hai riscattato il premio "${reward.name}".`, true);
    } else {
        showPopup('Non hai abbastanza punti per riscattare questo premio.', true);
    }
    }

