package tech.jinhaoma.AnkiMaker.service;

import tech.jinhaoma.AnkiMaker.domain.VocabularyData;

import java.util.List;

/**
 * Created by mjrt on 1/30/2017.
 */
public interface VocabularyService extends CrudService<VocabularyData> {
    VocabularyData query(String word);
    List<VocabularyData> batchQuery(List<String> words);
    void purge(String word);
}
