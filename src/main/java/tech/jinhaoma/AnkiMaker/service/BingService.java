package tech.jinhaoma.AnkiMaker.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import tech.jinhaoma.AnkiMaker.domain.BingData;

import java.util.List;

/**
 * Created by mjrt on 1/30/2017.
 */
public interface BingService extends CrudService<BingData> {
    BingData query(String word);
    void purge(String word);
}
