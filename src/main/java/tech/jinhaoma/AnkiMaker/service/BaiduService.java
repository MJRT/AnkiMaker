package tech.jinhaoma.AnkiMaker.service;

import org.springframework.stereotype.Service;
import tech.jinhaoma.AnkiMaker.domain.BaiduData;

/**
 * Created by mjrt on 1/30/2017.
 */
public interface BaiduService extends CrudService<BaiduData> {
    BaiduData query(String word);
    void purge(String word);
}
