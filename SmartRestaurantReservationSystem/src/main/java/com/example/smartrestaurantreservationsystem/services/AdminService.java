package com.example.smartrestaurantreservationsystem.services;

import com.example.smartrestaurantreservationsystem.DTO.AdminDTO;
import com.example.smartrestaurantreservationsystem.model.Admin;
import com.example.smartrestaurantreservationsystem.repositories.AdminRepository;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    private final AdminRepository adminRepository;

    public AdminService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    public AdminDTO register(Admin admin) {
        if (adminRepository.findByEmail(admin.getEmail()).isPresent()) {
            return null;
        }

        Admin saved = adminRepository.save(admin);
        return mapToDTO(saved);
    }

    public AdminDTO login(String email, String password) {
        return adminRepository.findByEmail(email)
                .filter(admin -> admin.getPassword().equals(password))
                .map(this::mapToDTO)
                .orElse(null);
    }

    private AdminDTO mapToDTO(Admin admin){
        AdminDTO dto = new AdminDTO();
        dto.setId(admin.getId());
        dto.setName(admin.getName());
        dto.setEmail(admin.getEmail());
        dto.setPhone(admin.getPhone());
        return dto;
    }
}