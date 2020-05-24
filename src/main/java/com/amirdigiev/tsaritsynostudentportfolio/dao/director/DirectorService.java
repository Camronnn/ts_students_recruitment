package com.amirdigiev.tsaritsynostudentportfolio.dao.director;

import com.amirdigiev.tsaritsynostudentportfolio.dao.user.UserService;
import com.amirdigiev.tsaritsynostudentportfolio.model.role.Director;
import com.amirdigiev.tsaritsynostudentportfolio.model.role.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service("directorService")
@SuppressWarnings("Duplicates")
public class DirectorService {

    private final DirectorRepository directorRepository;
    private final UserService userService;

    @Autowired
    public DirectorService(DirectorRepository directorRepository,
                           @Lazy UserService userService)
    {
        this.directorRepository = directorRepository;
        this.userService = userService;
    }

    public List<Director> findAll() {
        return directorRepository.findAll();
    }

    public Optional<Director> findById(Long id) {
        return directorRepository.findById(id);
    }

    public void add(Director director) {
        directorRepository.save(director);
    }

    public void update(Director newDirector) {
        User currentUser = userService.getAnAuthorizedUser();

        List<Director> directors = findAll();
        for(Director director : directors) {
            if (director.getUser().getId().equals(currentUser.getId())) {
                director.setDepartment(newDirector.getDepartment());
                directorRepository.save(director);
            }
        }
    }

    public void deleteById(Long id) {
        directorRepository.deleteById(id);
    }
}
