package com.amirdigiev.tsaritsynostudentportfolio.service;


import com.amirdigiev.tsaritsynostudentportfolio.model.HrManager;
import com.amirdigiev.tsaritsynostudentportfolio.repository.HrManagerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HrManagerService {

    private final HrManagerRepository hrManagerRepository;

    @Autowired
    public HrManagerService(HrManagerRepository hrManagerRepository) {
        this.hrManagerRepository = hrManagerRepository;
    }

    public List<HrManager> findAll() {
        return hrManagerRepository.findAll();
    }

    public Optional<HrManager> findById(Long id) {
        return hrManagerRepository.findById(id);
    }

    public void add(HrManager hrManager) {
        hrManagerRepository.save(hrManager);
    }
}
