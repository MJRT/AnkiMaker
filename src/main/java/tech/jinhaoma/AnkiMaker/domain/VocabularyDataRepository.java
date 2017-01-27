package tech.jinhaoma.AnkiMaker.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * Created by mjrt on 1/25/2017.
 */
@RepositoryRestResource(path = "/vocab")
public interface VocabularyDataRepository extends MongoRepository<VocabularyData, Long> {
}
