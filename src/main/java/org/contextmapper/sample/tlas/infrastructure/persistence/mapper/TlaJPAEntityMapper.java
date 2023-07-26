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

package org.contextmapper.sample.tlas.infrastructure.persistence.mapper;

import org.contextmapper.sample.tlas.domain.tla.ThreeLetterAbbreviation;
import org.contextmapper.sample.tlas.infrastructure.persistence.model.ThreeLetterAbbreviationJPAEntity;

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
