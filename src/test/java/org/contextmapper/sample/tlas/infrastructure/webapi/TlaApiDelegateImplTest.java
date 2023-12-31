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

package org.contextmapper.sample.tlas.infrastructure.webapi;

import org.contextmapper.sample.tlas.application.TlaApplicationService;
import org.contextmapper.sample.tlas.domain.tla.ThreeLetterAbbreviation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TlaApiDelegateImplTest {

    private static final String TEST_TLA = "TLA";
    private static final String TEST_MEANING = "Three Letter Abbreviation";
    private static final String TEST_ALTERNATIVE_MEANING = "Three Letter Acronym";

    @Mock
    private TlaApplicationService applicationService;

    @InjectMocks
    private TlaApiDelegateImpl testee;

    @Test
    void canListAllTLAs() {
        // given
        when(applicationService.findAllTLAs()).thenReturn(List.of(
                new ThreeLetterAbbreviation.TLABuilder(TEST_TLA)
                        .withMeaning(TEST_MEANING)
                        .withAlternativeMeaning(TEST_ALTERNATIVE_MEANING)
                        .build()
        ));

        // when
        var tlas = testee.listTLAs();

        // then
        verify(applicationService, times(1)).findAllTLAs();
        assertThat(tlas).isNotNull();
        assertThat(tlas.getBody()).hasSize(1);
        assertThat(tlas.getBody().get(0))
                .extracting("name", "meaning")
                .containsExactly(TEST_TLA, TEST_MEANING);
        assertThat(tlas.getBody().get(0).getAlternativeMeanings())
                .contains(TEST_ALTERNATIVE_MEANING);
    }

    @Test
    void canGetTLAByName() {
        // given
        when(applicationService.getTLAByName(TEST_TLA)).thenReturn(
                new ThreeLetterAbbreviation.TLABuilder(TEST_TLA)
                        .withMeaning(TEST_MEANING)
                        .withAlternativeMeaning(TEST_ALTERNATIVE_MEANING)
                        .build()
        );

        // when
        var tla = testee.getTLAByName(TEST_TLA);

        // then
        verify(applicationService, times(1)).getTLAByName(TEST_TLA);
        assertThat(tla.getBody())
                .isNotNull()
                .extracting("name", "meaning")
                .containsExactly(TEST_TLA, TEST_MEANING);
        assertThat(tla.getBody().getAlternativeMeanings())
                .contains(TEST_ALTERNATIVE_MEANING);
    }

}
