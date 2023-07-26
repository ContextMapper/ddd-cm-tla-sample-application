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

package org.contextmapper.sample.tlas.domain.tla;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

class ShortNameTest {

    @Test
    void cannotCreateAbbreviationFromNull() {
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> {
            new ShortName(null);
        });
    }

    @Test
    void cannotCreateAbbreviationFromEmptyString() {
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> {
            new ShortName("");
        });
    }

    @Test
    void cannotCreateAbbreviationFromMultipleWords() {
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> {
            new ShortName("hello world");
        });
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "TLA",
            "ADR",
            "DI",
            "IoC",
            "SMART"
    })
    void canCreateAbbreviation(final String value) {
        // when
        var abbreviation = new ShortName(value);

        // then
        assertThat(abbreviation)
                .isNotNull()
                .hasToString(value)
                .extracting("name").as(value);
    }

    @Test
    void sameStringIsSameAbbreviation() {
        // given
        var tlaString = "TLA";

        // when
        var firstAbbr = new ShortName(tlaString);
        var secondAbbr = new ShortName(tlaString);

        // then
        assertThat(firstAbbr).isEqualTo(secondAbbr);
        assertThat(firstAbbr).hasSameHashCodeAs(secondAbbr);
    }

}
