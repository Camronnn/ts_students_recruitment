package com.amirdigiev.tsaritsynostudentportfolio.dao.manager;


import com.amirdigiev.tsaritsynostudentportfolio.model.role.HrManager;
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
