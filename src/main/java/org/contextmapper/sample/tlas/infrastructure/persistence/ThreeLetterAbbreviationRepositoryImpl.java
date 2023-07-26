/*
 * Copyright 2023 The Context Mapper project team.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.contextmapper.sample.tlas.infrastructure.persistence;

import org.apache.commons.lang3.NotImplementedException;
import org.contextmapper.sample.tlas.domain.tla.Abbreviation;
import org.contextmapper.sample.tlas.domain.tla.ThreeLetterAbbreviation;
import org.contextmapper.sample.tlas.domain.tla.ThreeLetterAbbreviationRepository;
import org.contextmapper.sample.tlas.infrastructure.persistence.internal_repos.JpaThreeLetterAbbreviationRepository;
import org.contextmapper.sample.tlas.infrastructure.persistence.mapper.TlaJPAEntityMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static java.util.Optional.empty;
import static java.util.Optional.of;
import static java.util.stream.Collectors.toList;
import static org.contextmapper.sample.tlas.infrastructure.persistence.mapper.TlaJPAEntityMapper.toDomainEntity;

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
