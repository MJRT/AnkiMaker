package tech.jinhaoma.AnkiMaker.domain;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


/**
 * Created by mjrt on 1/25/2017.
 */
@RepositoryRestResource(path = "mv")
public interface MerriamWebsterDataRepository extends MongoRepository<MerriamWebsterData, Long> {
    MerriamWebsterData findByWord(String word);
}
