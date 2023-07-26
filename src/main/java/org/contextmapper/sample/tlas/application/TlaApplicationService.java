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

package org.contextmapper.sample.tlas.application;

import org.contextmapper.sample.tlas.application.exception.TLAShortNameDoesNotExist;
import org.contextmapper.sample.tlas.application.exception.TLAShortNameNotValid;
import org.contextmapper.sample.tlas.domain.tla.ShortName;
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
            var shortName = new ShortName(name);
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
