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
import org.contextmapper.sample.tlas.domain.tla.ThreeLetterAbbreviationRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.contextmapper.sample.tlas.domain.tla.ThreeLetterAbbreviation.TLABuilder;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TlaApplicationServiceTest {

    private static final String TEST_TLA = "QAS";
    private static final String TEST_MEANING = "Quality Attribute Scenario";

    @Mock
    private ThreeLetterAbbreviationRepository repository;

    @InjectMocks
    private TlaApplicationService testee;

    @Test
    void canFindAllTLAs() {
        // given
        when(repository.findAll()).thenReturn(List.of(
                new TLABuilder(TEST_TLA)
                        .withMeaning(TEST_MEANING)
                        .build()
        ));

        // when
        var tlas = testee.findAllTLAs();

        // then
        verify(repository, times(1)).findAll();
        assertThat(tlas)
                .isNotNull()
                .hasSize(1);
    }

    @Test
    void canGetTLAByName() {
        // given
        var shortName = new ShortName(TEST_TLA);
        when(repository.findByName(shortName)).thenReturn(
                Optional.of(new TLABuilder(TEST_TLA)
                        .withMeaning(TEST_MEANING)
                        .build())
        );

        // when
        var tla = testee.getTLAByName(TEST_TLA);

        // then
        verify(repository, times(1)).findByName(shortName);
        assertThat(tla)
                .isNotNull()
                .extracting("name.name", "meaning")
                .containsExactly(TEST_TLA, TEST_MEANING);
    }

    @Test
    void throwExceptionIfTLANameDoesNotExist() {
        // given
        when(repository.findByName(any())).thenReturn(Optional.empty());

        // when, then
        assertThatExceptionOfType(TLAShortNameDoesNotExist.class)
                .isThrownBy(() -> testee.getTLAByName("NOTEXISTING"));
        verify(repository, times(1)).findByName(new ShortName("NOTEXISTING"));
    }

    @Test
    void throwExceptionIfTLANameIsNoValidShortName() {
        // when, then
        assertThatExceptionOfType(TLAShortNameNotValid.class)
                .isThrownBy(() -> testee.getTLAByName("invalid string for TLA name"));
    }

}
