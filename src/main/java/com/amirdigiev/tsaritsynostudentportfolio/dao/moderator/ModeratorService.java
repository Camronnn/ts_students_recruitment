package com.amirdigiev.tsaritsynostudentportfolio.dao.moderator;

import com.amirdigiev.tsaritsynostudentportfolio.model.role.Moderator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ModeratorService {

    private final ModeratorRepository moderatorRepository;

    @Autowired
    public ModeratorService(ModeratorRepository moderatorRepository) {
        this.moderatorRepository = moderatorRepository;
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
