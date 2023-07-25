package org.contextmapper.sample.tlas.application;

import org.contextmapper.sample.tlas.application.exception.TLAShortNameDoesNotExist;
import org.contextmapper.sample.tlas.application.exception.TLAShortNameNotValid;
import org.contextmapper.sample.tlas.domain.tla.Abbreviation;
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

    public ThreeLetterAbbreviation getTLAByName(final String name) {
        try {
            var shortName = new Abbreviation(name);
            var tla = repository.findByName(shortName);
            if (tla.isPresent()) {
                return tla.get();
            } else {
                throw new TLAShortNameDoesNotExist(name);
            }
        } catch (IllegalArgumentException e) {
            throw new TLAShortNameNotValid(name);
        }
    }

}
