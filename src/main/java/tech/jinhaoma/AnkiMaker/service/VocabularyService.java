package tech.jinhaoma.AnkiMaker.service;

import tech.jinhaoma.AnkiMaker.domain.VocabularyData;

/**
 * Created by mjrt on 1/30/2017.
 */
public interface VocabularyService extends CrudService<VocabularyData> {
    VocabularyData query(String word);
    void purge(String word);
}
