package com.amirdigiev.tsaritsynostudentportfolio.dao.moderator;


import com.amirdigiev.tsaritsynostudentportfolio.model.role.Moderator;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ModeratorRepository extends CrudRepository<Moderator, Long> {
    List<Moderator> findAll();
}
