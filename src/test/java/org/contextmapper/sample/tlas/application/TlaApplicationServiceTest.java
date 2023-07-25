package org.contextmapper.sample.tlas.application;

import org.contextmapper.sample.tlas.application.exception.TLAShortNameDoesNotExist;
import org.contextmapper.sample.tlas.application.exception.TLAShortNameNotValid;
import org.contextmapper.sample.tlas.domain.tla.Abbreviation;
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
        var shortName = new Abbreviation(TEST_TLA);
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
        verify(repository, times(1)).findByName(new Abbreviation("NOTEXISTING"));
    }

    @Test
    void throwExceptionIfTLANameIsNoValidShortName() {
        // when, then
        assertThatExceptionOfType(TLAShortNameNotValid.class)
                .isThrownBy(() -> testee.getTLAByName("invalid string for TLA name"));
    }

}
