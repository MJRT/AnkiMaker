package tech.jinhaoma.AnkiMaker.service;

import tech.jinhaoma.AnkiMaker.domain.MerriamWebsterData;

import java.util.List;

/**
 * Created by mjrt on 1/30/2017.
 */
public interface MerriamWebsterService extends CrudService<MerriamWebsterData>{
    MerriamWebsterData query(String word);
    List<MerriamWebsterData> batchQuery(List<String> words);
    void purge(String word);
}
