package com.amirdigiev.tsaritsynostudentportfolio.dao.certificate;

import com.amirdigiev.tsaritsynostudentportfolio.model.Certificate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CertificateService {

    private final CertificateRepository certificateRepository;

    @Autowired
    public CertificateService(CertificateRepository certificateRepository) {
        this.certificateRepository = certificateRepository;
    }

    public List<Certificate> findAll() {
        return (List<Certificate>) certificateRepository.findAll();
    }

    public Optional<Certificate> findById(Long id) {
        return certificateRepository.findById(id);
    }

    public void add(Certificate certificate) {
        certificateRepository.save(certificate);
    }

    public void deleteById(Long id) {
        Optional<Certificate> certificate = certificateRepository.findById(id);
        if(certificate.isPresent()) {
            certificateRepository.deleteById(id);
        } else {
            throw new NullPointerException("Сертификата с id: " + id + " не существует");
        }
    }
}
