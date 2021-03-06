package tech.jinhaoma.AnkiMaker.domain;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * Created by mjrt on 1/25/2017.
 */
@RepositoryRestResource(path = "/baidu")
public interface BaiduDataRepository extends MongoRepository<BaiduData, Long> {
    BaiduData findByWord(String word);
}
