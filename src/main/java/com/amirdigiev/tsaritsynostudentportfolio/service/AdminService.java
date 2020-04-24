package com.amirdigiev.tsaritsynostudentportfolio.service;

import com.amirdigiev.tsaritsynostudentportfolio.model.Admin;
import com.amirdigiev.tsaritsynostudentportfolio.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminService {

    private final AdminRepository adminRepository;

    @Autowired
    public AdminService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    public List<Admin> findAll() {
        return adminRepository.findAll();
    }

    public Optional<Admin> findById(Long id) {
        return adminRepository.findById(id);
    }

    public void add(Admin admin) {
        adminRepository.save(admin);
    }
}
