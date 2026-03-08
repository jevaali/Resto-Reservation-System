document.addEventListener('DOMContentLoaded', function() {
    // Initialize date input with today's date
    const today = new Date().toISOString().split('T')[0];
    const dateInput = document.getElementById('reservationDate');
    dateInput.value = today;
    
    // Filter button event listener
    const filterBtn = document.getElementById('filterBtn');
    filterBtn.addEventListener('click', filterReservations);
    
    // Edit reservation buttons
    const editButtons = document.querySelectorAll('.btn-edit');
    editButtons.forEach(button => {
        button.addEventListener('click', function() {
            const reservationCard = this.closest('.reservation-card');
            showEditReservationForm(reservationCard);
        });
    });
    
    // Delete reservation buttons
    const deleteButtons = document.querySelectorAll('.btn-delete');
    deleteButtons.forEach(button => {
        button.addEventListener('click', function() {
            const reservationCard = this.closest('.reservation-card');
            deleteReservation(reservationCard);
        });
    });
});

function filterReservations() {
    const selectedDate = document.getElementById('reservationDate').value;
    const restaurantId = document.querySelector('meta[name="restaurant-id"]')?.content;
    
    // Simulate API call to fetch reservations for the selected date
    console.log(`Filtering reservations for date: ${selectedDate}, restaurant: ${restaurantId}`);
    
    // In real implementation, this would fetch data from the server
    // and update the reservations list
    alert(`Filtering reservations for ${selectedDate} - This is a placeholder for real API call`);
}

function showEditReservationForm(reservationCard) {
    // Create modal or form to edit reservation
    const customerName = reservationCard.querySelector('h3').textContent;
    alert(`Editing reservation for ${customerName} - This is a placeholder`);
}

function deleteReservation(reservationCard) {
    const customerName = reservationCard.querySelector('h3').textContent;
    
    if (confirm(`Are you sure you want to delete the reservation for ${customerName}?`)) {
        // Simulate API call to delete reservation
        console.log('Deleting reservation');
        
        // In real implementation, this would send a DELETE request to the server
        reservationCard.style.opacity = '0.5';
        setTimeout(() => {
            reservationCard.remove();
        }, 300);
    }
}
