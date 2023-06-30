package org.contextmapper.sample.tlas.application;

import org.contextmapper.sample.tlas.domain.tla.ThreeLetterAbbreviation;
import org.contextmapper.sample.tlas.domain.tla.ThreeLetterAbbreviationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TlaApplicationService {

    private ThreeLetterAbbreviationRepository repository;

    public TlaApplicationService(final ThreeLetterAbbreviationRepository repository) {
        this.repository = repository;
    }

    public List<ThreeLetterAbbreviation> findAllTLAs() {
        return repository.findAll();
    }

}
