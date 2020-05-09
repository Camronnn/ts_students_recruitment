package com.amirdigiev.tsaritsynostudentportfolio.dao.manager;


import com.amirdigiev.tsaritsynostudentportfolio.dao.user.UserService;
import com.amirdigiev.tsaritsynostudentportfolio.model.role.HrManager;
import com.amirdigiev.tsaritsynostudentportfolio.model.role.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HrManagerService {

    private final HrManagerRepository hrManagerRepository;
    private final UserService userService;

    @Autowired
    public HrManagerService(HrManagerRepository hrManagerRepository,
                            @Lazy UserService userService)
    {
        this.hrManagerRepository = hrManagerRepository;
        this.userService = userService;
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

    public void update(HrManager newHrManager) {
        User currentUser = userService.getAnAuthorizedUser();

        List<HrManager> managers = findAll();
        for(HrManager hrManager : managers) {
            if (hrManager.getUser().getId().equals(currentUser.getId())) {
                hrManager.setCompany(newHrManager.getCompany());
                hrManagerRepository.save(hrManager);
            }
        }
    }
}
