package com.amirdigiev.tsaritsynostudentportfolio.dao.event;

import com.amirdigiev.tsaritsynostudentportfolio.model.Event;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepository extends CrudRepository<Event, Long> {
    List<Event> findAll();
}
