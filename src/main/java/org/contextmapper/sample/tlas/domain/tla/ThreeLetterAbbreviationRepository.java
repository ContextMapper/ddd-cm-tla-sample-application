package org.contextmapper.sample.tlas.domain.tla;

import org.jmolecules.ddd.annotation.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ThreeLetterAbbreviationRepository {

    ThreeLetterAbbreviation save(final ThreeLetterAbbreviation tla);

    Optional<ThreeLetterAbbreviation> findByName(final Abbreviation name);

    List<ThreeLetterAbbreviation> findAll();

}
