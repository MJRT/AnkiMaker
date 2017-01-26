package tech.jinhaoma.AnkiMaker.domain;

import io.swagger.annotations.ApiOperation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

/**
 * Created by mjrt on 1/25/2017.
 */
@RepositoryRestResource
public interface MerriamWebsterDataRepository extends JpaRepository<MerriamWebsterData, Long> {

}
