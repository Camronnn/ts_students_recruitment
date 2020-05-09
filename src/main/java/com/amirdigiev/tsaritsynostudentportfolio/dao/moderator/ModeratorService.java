package com.amirdigiev.tsaritsynostudentportfolio.dao.moderator;

import com.amirdigiev.tsaritsynostudentportfolio.dao.manager.HrManagerRepository;
import com.amirdigiev.tsaritsynostudentportfolio.dao.user.UserService;
import com.amirdigiev.tsaritsynostudentportfolio.model.role.HrManager;
import com.amirdigiev.tsaritsynostudentportfolio.model.role.Moderator;
import com.amirdigiev.tsaritsynostudentportfolio.model.role.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ModeratorService {

    private final ModeratorRepository moderatorRepository;
    private final UserService userService;

    @Autowired
    public ModeratorService(ModeratorRepository moderatorRepository,
                            @Lazy UserService userService)
    {
        this.moderatorRepository = moderatorRepository;
        this.userService = userService;
    }

    public List<Moderator> findAll() {
        return moderatorRepository.findAll();
    }

    public Optional<Moderator> findById(Long id) {
        return moderatorRepository.findById(id);
    }

    public void add(Moderator moderator) {
        moderatorRepository.save(moderator);
    }
}
