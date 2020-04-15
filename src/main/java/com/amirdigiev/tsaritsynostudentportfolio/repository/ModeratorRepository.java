package com.amirdigiev.tsaritsynostudentportfolio.repository;

import com.amirdigiev.tsaritsynostudentportfolio.model.Moderator;
import org.springframework.stereotype.Repository;

@Repository
public interface ModeratorRepository<U extends Moderator> extends CommonRepository<U> {
}
