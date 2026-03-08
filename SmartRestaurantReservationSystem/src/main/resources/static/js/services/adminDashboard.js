// Admin Dashboard Service
// Handles admin dashboard functionality

export function initAdminDashboard() {
    console.log('initAdminDashboard called');
    // Get admin data from localStorage and display welcome message
    document.addEventListener('DOMContentLoaded', function() {
        console.log('DOM content loaded for admin dashboard');
        const adminData = localStorage.getItem('admin');
        console.log('Admin data from localStorage:', adminData);
        
        if (adminData) {
            try {
                const admin = JSON.parse(adminData);
                console.log('Parsed admin data:', admin);
                
                const welcomeMessage = document.getElementById('welcomeMessage');
                console.log('Welcome message element:', welcomeMessage);
                
                if (welcomeMessage && admin.name) {
                    welcomeMessage.textContent = `Welcome, ${admin.name}! Here you can manage your restaurant's reservations and settings.`;
                    console.log('Welcome message updated');
                } else {
                    if (!welcomeMessage) {
                        console.error('Welcome message element not found');
                    }
                    if (!admin.name) {
                        console.error('Admin name not found in data');
                    }
                }
            } catch (parseError) {
                console.error('Error parsing admin data from localStorage:', parseError);
            }
        } else {
            console.warn('No admin data found in localStorage');
        }
    });
}
