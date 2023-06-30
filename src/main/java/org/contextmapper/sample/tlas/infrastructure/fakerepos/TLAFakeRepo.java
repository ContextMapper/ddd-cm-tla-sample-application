package org.contextmapper.sample.tlas.infrastructure.fakerepos;

import org.contextmapper.sample.tlas.domain.tla.Abbreviation;
import org.contextmapper.sample.tlas.domain.tla.ThreeLetterAbbreviation;
import org.contextmapper.sample.tlas.domain.tla.ThreeLetterAbbreviationRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class TLAFakeRepo implements ThreeLetterAbbreviationRepository {
    @Override
    public ThreeLetterAbbreviation save(ThreeLetterAbbreviation tla) {
        return null;
    }

    @Override
    public Optional<ThreeLetterAbbreviation> findByName(Abbreviation name) {
        return Optional.empty();
    }

    @Override
    public List<ThreeLetterAbbreviation> findAll() {
        return List.of(
                new ThreeLetterAbbreviation.TLABuilder("TLA")
                        .withMeaning("Three Letter Abbreviation")
                        .build()
        );
    }
}
