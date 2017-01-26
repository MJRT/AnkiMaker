package tech.jinhaoma.AnkiMaker.rest;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
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
        this.repository.save(trucks);
    }

    @RequestMapping(value = "/purge", method = RequestMethod.DELETE)
    public void purge() {
        this.repository.deleteAll();
    }

    @RequestMapping(value = "/test")
    public String findByWord(){

        BingTask bingTask = new BingTask();
        ArrayList<String> s = new ArrayList<>();
        s.add("easy");
        List<BingData> r = null;
        try {
            r = bingTask.asyncBingTask(s);
            RestTemplate restTemplate = new RestTemplate();
            for(BingData w : r){
                System.out.println(w.toString());
            }
            restTemplate.postForLocation("http://localhost:8888/bing/upload",r);

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



        return "ok" ;
    }
}
