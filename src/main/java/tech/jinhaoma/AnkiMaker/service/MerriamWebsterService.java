package tech.jinhaoma.AnkiMaker.service;

import tech.jinhaoma.AnkiMaker.domain.MerriamWebsterData;

/**
 * Created by mjrt on 1/30/2017.
 */
public interface MerriamWebsterService extends CrudService<MerriamWebsterData>{
    MerriamWebsterData query(String word);
    void purge(String word);
}
