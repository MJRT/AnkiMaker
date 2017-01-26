package tech.jinhaoma.AnkiMaker.rest;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import tech.jinhaoma.AnkiMaker.domain.MerriamWebsterData;
import tech.jinhaoma.AnkiMaker.domain.MerriamWebsterDataRepository;
import tech.jinhaoma.AnkiMaker.task.MerriamWebsterTask;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by mjrt on 1/25/2017.
 */
@RestController
@RequestMapping(value = "/MerriamWebster")
public class MerriamWebsterController {

    private MerriamWebsterDataRepository repository ;

    @Autowired
    public MerriamWebsterController(MerriamWebsterDataRepository MerriamWebsterDataRepository ) {
        this.repository = MerriamWebsterDataRepository;
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void upload(@RequestBody List<MerriamWebsterData> trucks) throws Exception {
        this.repository.save(trucks);
    }

    @RequestMapping(value = "/purge", method = RequestMethod.DELETE)
    public void purge() {
        this.repository.deleteAll();
    }

    @RequestMapping(value = "/test")
    public String findByWord(){

        MerriamWebsterData data = new MerriamWebsterData();
        List<MerriamWebsterData> r = new ArrayList<>();
        data.setWord("789");
        data.setSplitWord("456");
        r.add(data);
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.postForLocation("http://localhost:8888/MerriamWebster/upload",r);

        return "ok" ;
    }
}
