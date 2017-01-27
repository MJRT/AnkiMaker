package tech.jinhaoma.AnkiMaker.rest;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import tech.jinhaoma.AnkiMaker.domain.BaiduData;
import tech.jinhaoma.AnkiMaker.domain.BingData;
import tech.jinhaoma.AnkiMaker.domain.BingDataRepository;
import tech.jinhaoma.AnkiMaker.task.BingTask;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by mjrt on 1/25/2017.
 */
@Log4j2
@RestController
@RequestMapping(value = "/bing")
public class BingController {

    private BingDataRepository repository ;

    @Autowired
    public BingController(BingDataRepository bingDataRepository ) {
        this.repository = bingDataRepository;
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void upload(@RequestBody List<BingData> trucks) throws Exception {
        this.repository.insert(trucks);
    }
    @RequestMapping(value = "/updata", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.CREATED)
    public void updata(@RequestBody List<BingData> trucks) throws Exception {
        this.repository.save(trucks);
    }

    @RequestMapping(value = "/purge", method = RequestMethod.DELETE)
    public void purge() {
        this.repository.deleteAll();
    }

    @RequestMapping(value = "/purge/{word}" , method = RequestMethod.DELETE)
    public void purge(@PathVariable String word) {
        BingData data = this.repository.findByWord(word);
        this.repository.delete(data);
    }

    @RequestMapping(value = "/test")
    public String test(){

        RestTemplate template = new RestTemplate();
        BingData data = repository.findByWord("four");
        data.setMeanChinese("test");
        data.setWord("test");
        System.out.println(data.toString());
        template.put("http://localhost:8888/bing/updata",data);
        return "ok";
    }

    @RequestMapping(value = "/query/{word}")
    public BingData query(@PathVariable  String word){

        BingData bingData = repository.findByWord(word);

        if (bingData != null){
            return bingData;
        }

        BingTask bingTask = new BingTask();
        ArrayList<String> s = new ArrayList<>();
        s.add(word);
        List<BingData> r = null;
        try {
            r = bingTask.asyncBingTask(s);
            repository.save(r);
            bingData = r.get(0);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        if (bingData ==null)
            return null;
        return bingData;
    }
}
