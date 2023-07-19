package org.contextmapper.sample.tlas.application;

import org.contextmapper.sample.tlas.domain.tla.ThreeLetterAbbreviationRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
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

}
