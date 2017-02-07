package tech.jinhaoma.AnkiMaker.domain;

import com.mongodb.Mongo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Created by mjrt on 1/25/2017.
 */
@RepositoryRestResource(path = "/bing")
public interface BingDataRepository extends MongoRepository<BingData, Long> {
    BingData findByWord(String word);
}
