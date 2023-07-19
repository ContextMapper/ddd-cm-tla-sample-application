package org.contextmapper.sample.tlas.infrastructure.jpa.internal_repos;

import org.contextmapper.sample.tlas.infrastructure.jpa.model.ThreeLetterAbbreviationJPAEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaThreeLetterAbbreviationRepository extends JpaRepository<ThreeLetterAbbreviationJPAEntity, String> {
}
