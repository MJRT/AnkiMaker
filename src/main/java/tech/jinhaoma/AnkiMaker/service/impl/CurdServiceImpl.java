package tech.jinhaoma.AnkiMaker.service.impl;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.util.Assert;
import tech.jinhaoma.AnkiMaker.service.CrudService;

import java.util.List;

/**
 * Created by mjrt on 1/28/2017.
 */
public abstract class CurdServiceImpl<Data,Repository extends MongoRepository> implements CrudService<Data> {

    protected Repository repository;

    public CurdServiceImpl(Repository repository) {
        Assert.notNull(this.getClass().getName().toString()+"is null");
        this.repository = repository;
    }

    public void upload(Data word) {
        repository.insert(word);
    }

    public void upload(List<Data> words) {
        repository.insert(words);
    }

    public void updata(List<Data> words) {
        repository.save(words);
    }

    public void purge() {
        repository.deleteAll();
    }


}
