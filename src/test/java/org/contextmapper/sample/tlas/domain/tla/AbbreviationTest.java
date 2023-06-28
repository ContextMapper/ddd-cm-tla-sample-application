package org.contextmapper.sample.tlas.domain.tla;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

class AbbreviationTest {

    @Test
    void cannotCreateAbbreviationFromNull() {
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> {
            new Abbreviation(null);
        });
    }

    @Test
    void cannotCreateAbbreviationFromEmptyString() {
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> {
            new Abbreviation("");
        });
    }

    @Test
    void cannotCreateAbbreviationFromMultipleWords() {
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> {
            new Abbreviation("hello world");
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
        var abbreviation = new Abbreviation(value);

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
        var firstAbbr = new Abbreviation(tlaString);
        var secondAbbr = new Abbreviation(tlaString);

        // then
        assertThat(firstAbbr).isEqualTo(secondAbbr);
        assertThat(firstAbbr).hasSameHashCodeAs(secondAbbr);
    }

}
