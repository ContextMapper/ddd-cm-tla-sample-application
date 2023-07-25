package org.contextmapper.sample.tlas.infrastructure.jpa;

import org.contextmapper.sample.tlas.domain.tla.Abbreviation;
import org.contextmapper.sample.tlas.domain.tla.TLAStatus;
import org.contextmapper.sample.tlas.infrastructure.jpa.internal_repos.JpaThreeLetterAbbreviationRepository;
import org.contextmapper.sample.tlas.infrastructure.jpa.model.ThreeLetterAbbreviationJPAEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.contextmapper.sample.tlas.domain.tla.TLAStatus.ACCEPTED;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ThreeLetterAbbreviationRepositoryImplTest {

    private static final String TEST_TLA = "QAS";
    private static final String TEST_MEANING = "Quality Attribute Scenario";
    private static final String TEST_ALTERNATIVE_MEANING_1 = "Quite a show";
    private static final String TEST_ALTERNATIVE_MEANING_2 = "Quality Assurance Specialist";
    private static final TLAStatus TEST_STATUS = ACCEPTED;
    private static final String TEST_LINK = "https://www.acronymfinder.com/QAS.html";

    @Mock
    private JpaThreeLetterAbbreviationRepository jpaInternalRepo;

    @InjectMocks
    private ThreeLetterAbbreviationRepositoryImpl testee;

    @Test
    void canFindAll() {
        // given
        when(jpaInternalRepo.findAll()).thenReturn(List.of(
                createTestTLAEntity()
        ));

        // when
        var tlas = testee.findAll();

        // then
        verify(jpaInternalRepo, times(1)).findAll();
        assertThat(tlas)
                .isNotNull()
                .hasSize(1);
        assertThat(tlas.get(0))
                .extracting("name.name", "meaning", "status", "link")
                .containsExactly(TEST_TLA, TEST_MEANING, ACCEPTED, TEST_LINK);
        assertThat(tlas.get(0).getAlternativeMeanings())
                .hasSize(2)
                .contains(TEST_ALTERNATIVE_MEANING_1, TEST_ALTERNATIVE_MEANING_2);
    }

    @Test
    void canFindByName() {
        // given
        var shortName = new Abbreviation(TEST_TLA);
        when(jpaInternalRepo.findById(TEST_TLA)).thenReturn(
                Optional.of(createTestTLAEntity())
        );

        // when
        var tla = testee.findByName(shortName);

        // then
        verify(jpaInternalRepo, times(1)).findById(TEST_TLA);
        assertThat(tla)
                .isNotNull()
                .isNotEmpty();
        assertThat(tla.get())
                .extracting("name.name", "meaning", "status", "link")
                .containsExactly(TEST_TLA, TEST_MEANING, ACCEPTED, TEST_LINK);
    }

    @Test
    void canReturnEmptyOptionalIfNameNotFound() {
        // given
        when(jpaInternalRepo.findById(anyString())).thenReturn(Optional.empty());

        // when
        var tla = testee.findByName(new Abbreviation(TEST_TLA));

        // then
        assertThat(tla)
                .isNotNull()
                .isEmpty();
    }

    private ThreeLetterAbbreviationJPAEntity createTestTLAEntity() {
        ThreeLetterAbbreviationJPAEntity entity = new ThreeLetterAbbreviationJPAEntity();
        entity.setName(TEST_TLA);
        entity.setMeaning(TEST_MEANING);
        entity.setAlternativeMeanings(List.of(TEST_ALTERNATIVE_MEANING_1, TEST_ALTERNATIVE_MEANING_2));
        entity.setUrl(TEST_LINK);
        entity.setStatus(TEST_STATUS.name());
        return entity;
    }

}
