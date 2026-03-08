package com.example.smartrestaurantreservationsystem.services;

import com.example.smartrestaurantreservationsystem.DTO.AdminDTO;
import com.example.smartrestaurantreservationsystem.model.Admin;
import com.example.smartrestaurantreservationsystem.repositories.AdminRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;

    public AdminService(AdminRepository adminRepository, PasswordEncoder passwordEncoder) {
        this.adminRepository = adminRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public AdminDTO register(Admin admin) {
        if (adminRepository.findByEmail(admin.getEmail()).isPresent()) {
            return null;
        }

        admin.setPassword(passwordEncoder.encode(admin.getPassword()));
        admin.setRole("ADMIN");
        Admin saved = adminRepository.save(admin);
        return mapToDTO(saved);
    }

    public AdminDTO login(String email, String password) {
        return adminRepository.findByEmail(email)
                .filter(admin -> passwordEncoder.matches(password, admin.getPassword()))
                .map(this::mapToDTO)
                .orElse(null);
    }

    public Admin findByEmail(String email) {
        return adminRepository.findByEmail(email).orElse(null);
    }

    private AdminDTO mapToDTO(Admin admin){
        AdminDTO dto = new AdminDTO();
        dto.setId(admin.getId());
        dto.setName(admin.getName());
        dto.setEmail(admin.getEmail());
        dto.setPhone(admin.getPhone());
        dto.setRole(admin.getRole());
        return dto;
    }

    public Admin save(Admin admin) {
        return adminRepository.save(admin);
    }
}