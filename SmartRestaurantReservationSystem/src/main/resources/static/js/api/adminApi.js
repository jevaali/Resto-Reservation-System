export async function registerAdmin(admin) {
    const response = await fetch("/api/admin/register", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(admin)
    });

    if (!response.ok) {
        const errorData = await response.json().catch(() => ({ message: "Registration failed" }));
        throw new Error(errorData.message || `Registration failed (${response.status})`);
    }

    return response.json();
}

// src/main/resources/static/js/api/adminApi.js
export async function loginAdmin(credentials) {
    const response = await fetch("/api/admin/login", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(credentials)
    });

    if (!response.ok) {
        const errorData = await response.json().catch(() => ({ message: "Login failed" }));
        throw new Error(errorData.message || `Login failed (${response.status})`);
    }

    return response.json();
}