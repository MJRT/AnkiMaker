package tech.jinhaoma.AnkiMaker.domain;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by mjrt on 5/28/2017.
 */

public interface EasyRepository extends MongoRepository<EasyWord, Long> {

}
