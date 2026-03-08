export async function registerAdmin(admin) {
    const response = await fetch("/api/admin/register", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(admin)
    });

    const text = await response.text();
    try {
        const data = JSON.parse(text);
        if (!response.ok) throw new Error(data.message || `Registration failed (${response.status})`);
        return data;
    } catch {
        throw new Error(`Unexpected response from server: ${text.substring(0, 100)}`);
    }
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