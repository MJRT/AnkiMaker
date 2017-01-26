package tech.jinhaoma.AnkiMaker.rest;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import tech.jinhaoma.AnkiMaker.domain.BaiduData;
import tech.jinhaoma.AnkiMaker.domain.BaiduDataRepository;
import tech.jinhaoma.AnkiMaker.task.BaiduTask;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by mjrt on 1/25/2017.
 */
@RestController
@RequestMapping(value = "/baidu")
public class BaiduController {

    private BaiduDataRepository repository ;

    @Autowired
    public BaiduController(BaiduDataRepository bingDataRepository ) {
        this.repository = bingDataRepository;
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void upload(@RequestBody List<BaiduData> trucks) throws Exception {
        this.repository.save(trucks);
    }

    @RequestMapping(value = "/purge", method = RequestMethod.DELETE)
    public void purge() {
        this.repository.deleteAll();
    }

    @RequestMapping(value = "/test")
    public String findByWord(){

        BaiduTask BaiduTask = new BaiduTask();
        ArrayList<String> s = new ArrayList<>();
        s.add("easy");
        List<BaiduData> r = null;
        try {
            r = BaiduTask.asyncBaiduTask(s);
            repository.save(r);
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
