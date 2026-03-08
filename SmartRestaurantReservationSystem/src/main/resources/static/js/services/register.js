// src/main/resources/static/js/services/register.js
import { registerAdmin } from "../api/adminApi.js";

document.addEventListener("DOMContentLoaded", () => {
    const registerForm = document.getElementById("registerForm");
    const registerError = document.getElementById("registerError");
    const registerSuccess = document.getElementById("registerSuccess");

    if (!registerForm) return;

    registerForm.addEventListener("submit", async (e) => {
        e.preventDefault();

        registerError.style.display = "none";
        registerSuccess.style.display = "none";

        const admin = {
            name: document.getElementById("name").value,
            email: document.getElementById("email").value,
            phone: document.getElementById("phone").value,
            password: document.getElementById("password").value
        };

        try {
            const result = await registerAdmin(admin);
            localStorage.setItem("admin", JSON.stringify(result));

            registerSuccess.textContent = "Registration successful! Redirecting to dashboard…";
            registerSuccess.style.display = "block";

            setTimeout(() => {
                window.location.href = "/admin-dashboard";
            }, 1000);

        } catch (error) {
            registerError.textContent = error.message || "Registration failed. Please try again.";
            registerError.style.display = "block";
        }
    });
});