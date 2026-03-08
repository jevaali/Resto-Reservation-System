
export function renderRestaurants(restaurants){

    const container = document.getElementById("restaurant-list");

    container.innerHTML = "";

    restaurants.forEach(r => {

        const card = document.createElement("div");
        card.className = "restaurant-card";

        card.innerHTML = `
            <div class="restaurant-name">${r.name}</div>
        `;

        card.onclick = () => {
            window.location.href = `reservation-search.html?restaurantId=${r.id}`;
        };

        container.appendChild(card);

    });
}