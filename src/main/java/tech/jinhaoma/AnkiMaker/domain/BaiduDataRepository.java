package tech.jinhaoma.AnkiMaker.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by mjrt on 1/25/2017.
 */
@RepositoryRestResource(path = "/baidu")
public interface BaiduDataRepository extends MongoRepository<BaiduData, Long> {

    /* /Search */

    @RestResource(path = "/word",rel = "by-word")
    Page<BaiduData> findByWord(@Param("query") String word,Pageable pageable);
}
