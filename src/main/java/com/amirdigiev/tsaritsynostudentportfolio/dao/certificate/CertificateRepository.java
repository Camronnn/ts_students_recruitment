package com.amirdigiev.tsaritsynostudentportfolio.dao.certificate;

import com.amirdigiev.tsaritsynostudentportfolio.model.Certificate;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CertificateRepository extends CrudRepository<Certificate, Long> {
}
