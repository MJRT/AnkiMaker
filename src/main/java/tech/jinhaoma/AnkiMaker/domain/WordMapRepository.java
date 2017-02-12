package tech.jinhaoma.AnkiMaker.domain;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

/**
 * Created by mjrt on 2/8/2017.
 */
@RepositoryRestResource
public interface WordMapRepository extends MongoRepository<WordMap,Long>{
    WordMap findByKey(String key);
    List<WordMap> findByKey (List<String> keys);
}
