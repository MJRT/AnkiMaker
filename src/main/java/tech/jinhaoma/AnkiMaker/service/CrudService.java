package tech.jinhaoma.AnkiMaker.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by mjrt on 1/25/2017.
 */

public interface CrudService<Data> {

    public void upload(Data words);

    public void upload(List<Data> words);

    public void updata(List<Data> words);

    public void purge();

    public void purge(String word);

}
