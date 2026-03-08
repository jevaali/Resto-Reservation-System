import { createRestaurant } from '../api/restaurantApi.js';

document.addEventListener('DOMContentLoaded', function() {
    const restaurantForm = document.getElementById('restaurantForm');
    const cancelBtn = document.getElementById('cancelBtn');

    // Handle form submission
    restaurantForm.addEventListener('submit', async function(event) {
        event.preventDefault();
        
        const formData = new FormData(restaurantForm);
        const restaurantData = {
            name: formData.get('name'),
            location: formData.get('location')
        };

        try {
            // Create restaurant using API
            const createdRestaurant = await createRestaurant(restaurantData);
            
            // Show success message
            showNotification('Restaurant created successfully!', 'success');
            
            // Redirect to admin dashboard after a short delay
            setTimeout(() => {
                window.location.href = '/admin/admin-dashboard';
            }, 1500);
        } catch (error) {
            console.error('Error creating restaurant:', error);
            console.error('Error details:', {
                message: error.message,
                stack: error.stack,
                response: error.response
            });
            
            // Show more detailed error message
            let errorMsg = 'Failed to create restaurant. Please try again.';
            if (error.message.includes('403')) {
                errorMsg = 'Access denied. Please log in as an admin.';
            } else if (error.message.includes('401')) {
                errorMsg = 'Please log in first.';
            } else if (error.message.includes('Network')) {
                errorMsg = 'Network error. Please check your connection.';
            }
            
            showNotification(errorMsg, 'error');
        }
    });

    // Handle cancel button
    cancelBtn.addEventListener('click', function() {
        window.location.href = '/admin/admin-dashboard';
    });

    // Function to show notification
    function showNotification(message, type) {
        // Remove existing notifications
        const existingNotification = document.querySelector('.notification');
        if (existingNotification) {
            existingNotification.remove();
        }

        // Create new notification
        const notification = document.createElement('div');
        notification.className = `notification notification-${type}`;
        notification.textContent = message;
        
        // Add notification to page
        const formContainer = document.querySelector('.form-container');
        formContainer.insertBefore(notification, formContainer.firstChild);

        // Auto remove notification after 3 seconds
        setTimeout(() => {
            if (notification.parentNode) {
                notification.remove();
            }
        }, 3000);
    }
});
