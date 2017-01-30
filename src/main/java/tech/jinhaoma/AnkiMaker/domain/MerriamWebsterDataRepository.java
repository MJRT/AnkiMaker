package tech.jinhaoma.AnkiMaker.domain;

import com.mongodb.Mongo;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

/**
 * Created by mjrt on 1/25/2017.
 */
@RepositoryRestResource(path = "mv")
public interface MerriamWebsterDataRepository extends MongoRepository<MerriamWebsterData, Long> {
    MerriamWebsterData findByWord(String word);
}
