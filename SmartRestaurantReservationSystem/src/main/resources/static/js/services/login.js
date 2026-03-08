// src/main/resources/static/js/services/login.js

import { loginAdmin } from "../api/adminApi.js";

document.addEventListener("DOMContentLoaded", () => {
    const loginForm = document.getElementById("loginForm");
    const loginError = document.getElementById("loginError");

    if (!loginForm || !loginError) return;

    loginForm.addEventListener("submit", async (e) => {
        e.preventDefault();

        const credentials = {
            email: document.getElementById("email").value,
            password: document.getElementById("password").value
        };

        loginError.style.display = "none"; // hide previous errors

        try {
            const admin = await loginAdmin(credentials);
            localStorage.setItem("admin", JSON.stringify(admin));
            window.location.href = "/admin-dashboard";
        } catch (error) {
            // Display user-friendly error message
            loginError.textContent = error.message || "Login failed. Please try again.";
            loginError.style.display = "block";
        }
    });
});