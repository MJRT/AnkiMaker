package tech.jinhaoma.AnkiMaker.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import tech.jinhaoma.AnkiMaker.domain.WordCard;

import java.util.List;

/**
 * Created by mjrt on 1/30/2017.
 */
public interface WordCardService extends CrudService<WordCard>{
    List<WordCard> query(String word);
    Page<WordCard> query(String word,Pageable pageable);
    Page<WordCard> queryAll(Pageable pageable);
    List<WordCard> batchQuery(List<String> words);

    void purge(String word );
}
