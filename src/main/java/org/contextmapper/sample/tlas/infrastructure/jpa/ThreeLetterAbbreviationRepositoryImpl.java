package org.contextmapper.sample.tlas.infrastructure.jpa;

import org.apache.commons.lang3.NotImplementedException;
import org.contextmapper.sample.tlas.domain.tla.Abbreviation;
import org.contextmapper.sample.tlas.domain.tla.ThreeLetterAbbreviation;
import org.contextmapper.sample.tlas.domain.tla.ThreeLetterAbbreviationRepository;
import org.contextmapper.sample.tlas.infrastructure.jpa.internal_repos.JpaThreeLetterAbbreviationRepository;
import org.contextmapper.sample.tlas.infrastructure.jpa.mapper.TlaJPAEntityMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static java.util.Optional.empty;
import static java.util.Optional.of;
import static java.util.stream.Collectors.toList;
import static org.contextmapper.sample.tlas.infrastructure.jpa.mapper.TlaJPAEntityMapper.toDomainEntity;

@Repository
public class ThreeLetterAbbreviationRepositoryImpl implements ThreeLetterAbbreviationRepository {

    private JpaThreeLetterAbbreviationRepository jpaInternalRepo;

    public ThreeLetterAbbreviationRepositoryImpl(final JpaThreeLetterAbbreviationRepository jpaInternalRepo) {
        this.jpaInternalRepo = jpaInternalRepo;
    }

    @Override
    public ThreeLetterAbbreviation save(final ThreeLetterAbbreviation tla) {
        throw new NotImplementedException();
    }

    @Override
    public Optional<ThreeLetterAbbreviation> findByName(final Abbreviation name) {
        var optionalTLA = jpaInternalRepo.findById(name.toString());
        if (optionalTLA.isPresent())
            return of(toDomainEntity(optionalTLA.get()));
        return empty();
    }

    @Override
    public List<ThreeLetterAbbreviation> findAll() {
        return jpaInternalRepo.findAll().stream()
                .map(TlaJPAEntityMapper::toDomainEntity)
                .collect(toList());
    }
}
