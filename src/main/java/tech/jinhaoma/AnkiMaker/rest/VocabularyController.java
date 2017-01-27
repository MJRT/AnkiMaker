package tech.jinhaoma.AnkiMaker.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import tech.jinhaoma.AnkiMaker.domain.VocabularyData;

import tech.jinhaoma.AnkiMaker.domain.VocabularyDataRepository;
import tech.jinhaoma.AnkiMaker.task.VocabularyTask;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by mjrt on 1/27/2017.
 */
@RestController
@RequestMapping(value = "/vocab")
public class VocabularyController {

    private VocabularyDataRepository repository ;

    @Autowired
    public VocabularyController(VocabularyDataRepository bingDataRepository ) {
        this.repository = bingDataRepository;
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void upload(@RequestBody List<VocabularyData> trucks) throws Exception {
        this.repository.save(trucks);
    }

    @RequestMapping(value = "/purge", method = RequestMethod.DELETE)
    public void purge() {
        this.repository.deleteAll();
    }

    @RequestMapping(value = "/test")
    public String findByWord(){

        VocabularyTask bingTask = new VocabularyTask();
        ArrayList<String> s = new ArrayList<>();
        s.add("easy");
        List<VocabularyData> r = null;
        try {
            r = bingTask.asyncVocabularyTask(s);
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
