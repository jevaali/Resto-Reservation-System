package com.example.smartrestaurantreservationsystem.handler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CustomLogoutSuccessHandler implements LogoutSuccessHandler {

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response,
                                Authentication authentication) throws IOException, ServletException {
        
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.removeAttribute("adminId");
            session.removeAttribute("adminName");
            session.removeAttribute("adminEmail");
            session.removeAttribute("adminRole");
        }
        
        response.sendRedirect("/");
    }
}
