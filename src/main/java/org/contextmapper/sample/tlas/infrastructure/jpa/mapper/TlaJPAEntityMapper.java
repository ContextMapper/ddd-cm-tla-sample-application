package org.contextmapper.sample.tlas.infrastructure.jpa.mapper;

import org.contextmapper.sample.tlas.domain.tla.ThreeLetterAbbreviation;
import org.contextmapper.sample.tlas.infrastructure.jpa.model.ThreeLetterAbbreviationJPAEntity;

import static org.contextmapper.sample.tlas.domain.tla.TLAStatus.valueOf;
import static org.contextmapper.sample.tlas.domain.tla.ThreeLetterAbbreviation.TLABuilder;

public class TlaJPAEntityMapper {

    public static ThreeLetterAbbreviation toDomainEntity(final ThreeLetterAbbreviationJPAEntity jpaEntity) {
        return new TLABuilder(jpaEntity.getName())
                .withMeaning(jpaEntity.getMeaning())
                .withAlternativeMeanings(jpaEntity.getAlternativeMeanings())
                .withLink(jpaEntity.getUrl())
                .withStatus(valueOf(jpaEntity.getStatus()))
                .build();
    }

}
