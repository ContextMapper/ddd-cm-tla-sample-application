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

import org.contextmapper.sample.tlas.domain.tla.exception.InvalidTLAStateTransitionException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.ValueSource;

import static nl.jqno.equalsverifier.EqualsVerifier.simple;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.contextmapper.sample.tlas.domain.tla.TLAStatus.*;
import static org.contextmapper.sample.tlas.domain.tla.ThreeLetterAbbreviation.TLABuilder;

class ThreeLetterAbbreviationTest {

    private static final String TEST_TLA = "QAS";
    private static final String TEST_MEANING = "Quality Attribute Scenario";
    private static final String TEST_ALTERNATIVE_MEANING_1 = "Quite a show";
    private static final String TEST_ALTERNATIVE_MEANING_2 = "Quality Assurance Specialist";
    private static final TLAStatus TEST_STATUS = ACCEPTED;
    private static final String TEST_LINK = "https://www.acronymfinder.com/QAS.html";


    @Test
    void canCreateTLAWithRequiredParams() {
        // given
        var tlaBuilder = new TLABuilder(TEST_TLA)
                .withMeaning(TEST_MEANING);

        // when
        var tla = tlaBuilder.build();

        // then
        assertThat(tla)
                .isNotNull()
                .extracting("name", "meaning", "status")
                .containsExactly(new ShortName((TEST_TLA)), TEST_MEANING, PROPOSED);
    }

    @Test
    void canCreateTLAWithAllParams() {
        // given
        var tlaBuilder = new TLABuilder(TEST_TLA)
                .withMeaning(TEST_MEANING)
                .withAlternativeMeaning(TEST_ALTERNATIVE_MEANING_1)
                .withLink(TEST_LINK)
                .withStatus(TEST_STATUS);

        // when
        var tla = tlaBuilder.build();

        //then
        assertThat(tla)
                .isNotNull()
                .extracting("name", "meaning", "status", "link")
                .containsExactly(new ShortName(TEST_TLA), TEST_MEANING, TEST_STATUS, TEST_LINK);
        assertThat(tla.getAlternativeMeanings()).contains(TEST_ALTERNATIVE_MEANING_1);
    }

    @Test
    void canCreateTLAWithMultipleAlternativeMeanings() {
        // given
        var tlaBuilder = new TLABuilder(TEST_TLA)
                .withMeaning(TEST_MEANING)
                .withAlternativeMeaning(TEST_ALTERNATIVE_MEANING_1)
                .withAlternativeMeaning(TEST_ALTERNATIVE_MEANING_2)
                .withLink(TEST_LINK)
                .withStatus(TEST_STATUS);

        // when
        var tla = tlaBuilder.build();

        // then
        assertThat(tla)
                .isNotNull()
                .extracting("name", "meaning", "status", "link")
                .containsExactly(new ShortName(TEST_TLA), TEST_MEANING, TEST_STATUS, TEST_LINK);
        assertThat(tla.getAlternativeMeanings()).contains(TEST_ALTERNATIVE_MEANING_1);
        assertThat(tla.getAlternativeMeanings()).contains(TEST_ALTERNATIVE_MEANING_2);
    }

    @Test
    void canCreateTLAWithExistingAbbreviationObject() {
        // given
        var abbr = new ShortName(TEST_TLA);
        var tlaBuilder = new TLABuilder(abbr)
                .withMeaning(TEST_MEANING);

        // when
        var tla = tlaBuilder.build();

        // then
        assertThat(tla)
                .isNotNull()
                .extracting("name", "meaning")
                .containsExactly(abbr, TEST_MEANING);
    }

    @Test
    void cannotCreateTLAWithoutMeaning() {
        // given
        var tlaBuilder = new TLABuilder(TEST_TLA);

        // when, then
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(tlaBuilder::build);
    }

    @Test
    void cannotCreateTLAWithStatusNull() {
        // given
        var tlaBuilder = new TLABuilder(TEST_TLA)
                .withMeaning(TEST_MEANING)
                .withStatus(null);

        // when, then
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(tlaBuilder::build);
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "",
            " ",
            "just a string"
    })
    void cannotCreateTLAWithInvalidLink(final String link) {
        // given
        var tlaBuilder = new TLABuilder(TEST_TLA)
                .withMeaning(TEST_MEANING)
                .withLink(link);

        // when, then
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(tlaBuilder::build);
    }

    @Test
    void canAcceptProposedTLA() {
        // given
        var tla = new TLABuilder(TEST_TLA)
                .withMeaning(TEST_MEANING)
                .build();

        // when
        tla.accept();

        //then
        assertThat(tla.getStatus()).isEqualTo(ACCEPTED);
    }

    @ParameterizedTest
    @EnumSource(value = TLAStatus.class, names = {"ACCEPTED", "DECLINED", "ARCHIVED"})
    void cannotAcceptTLAIfStateIsNotProposed(final TLAStatus status) {
        // given
        var tla = new TLABuilder(TEST_TLA)
                .withMeaning(TEST_MEANING)
                .withStatus(status)
                .build();

        // when, then
        assertThatExceptionOfType(InvalidTLAStateTransitionException.class).isThrownBy(tla::accept);
    }

    @Test
    void canDeclineProposedTLA() {
        // given
        var tla = new TLABuilder(TEST_TLA)
                .withMeaning(TEST_MEANING)
                .build();

        // when
        tla.decline();

        //then
        assertThat(tla.getStatus()).isEqualTo(DECLINED);
    }

    @ParameterizedTest
    @EnumSource(value = TLAStatus.class, names = {"ACCEPTED", "DECLINED", "ARCHIVED"})
    void cannotDeclineTLAIfStateIsNotProposed(final TLAStatus status) {
        // given
        var tla = new TLABuilder(TEST_TLA)
                .withMeaning(TEST_MEANING)
                .withStatus(status)
                .build();

        // when, then
        assertThatExceptionOfType(InvalidTLAStateTransitionException.class).isThrownBy(tla::decline);
    }

    @Test
    void canArchiveAcceptedTLA() {
        // given
        var tla = new TLABuilder(TEST_TLA)
                .withMeaning(TEST_MEANING)
                .withStatus(ACCEPTED)
                .build();

        // when
        tla.archive();

        // then
        assertThat(tla.getStatus()).isEqualTo(ARCHIVED);
    }

    @ParameterizedTest
    @EnumSource(value = TLAStatus.class, names = {"PROPOSED", "DECLINED", "ARCHIVED"})
    void cannotArchiveTLAIfItIsNotAccepted(final TLAStatus status) {
        // given
        var tla = new TLABuilder(TEST_TLA)
                .withMeaning(TEST_MEANING)
                .withStatus(status)
                .build();

        // when, then
        assertThatExceptionOfType(InvalidTLAStateTransitionException.class).isThrownBy(tla::archive);
    }

    @Test
    void verifyEqualsByNameOnly() {
        simple().forClass(ThreeLetterAbbreviation.class)
                .withNonnullFields("name")
                .withOnlyTheseFields("name")
                .verify();
    }

}
