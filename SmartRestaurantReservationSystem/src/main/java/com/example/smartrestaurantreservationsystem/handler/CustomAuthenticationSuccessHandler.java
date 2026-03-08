package com.example.smartrestaurantreservationsystem.handler;

import com.example.smartrestaurantreservationsystem.model.Admin;
import com.example.smartrestaurantreservationsystem.services.AdminService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private final AdminService adminService;

    public CustomAuthenticationSuccessHandler(AdminService adminService) {
        this.adminService = adminService;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        String email = ((UserDetails) authentication.getPrincipal()).getUsername();

        Admin admin = adminService.findByEmail(email);

        HttpSession session = request.getSession();
        session.setAttribute("adminId", admin.getId());
        session.setAttribute("adminName", admin.getName());
        session.setAttribute("adminEmail", admin.getEmail());
        session.setAttribute("adminRole", admin.getRole());

        response.sendRedirect("admin/admin-dashboard");
    }
}
