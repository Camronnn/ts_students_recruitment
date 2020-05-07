package com.amirdigiev.tsaritsynostudentportfolio.dao.admin;


import com.amirdigiev.tsaritsynostudentportfolio.model.role.Admin;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdminRepository extends CrudRepository<Admin, Long> {
    List<Admin> findAll();
}
