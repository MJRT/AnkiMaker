package tech.jinhaoma.AnkiMaker.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

/**
 * Created by mjrt on 1/25/2017.
 */
@RepositoryRestResource
public interface WordCardRepository extends MongoRepository<WordCard, Long> {

    List<WordCard> findByWord(String word);
    Page<WordCard> findByWord(String word,Pageable pageable);
}
