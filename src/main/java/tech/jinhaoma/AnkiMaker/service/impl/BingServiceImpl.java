package tech.jinhaoma.AnkiMaker.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.jinhaoma.AnkiMaker.domain.BingData;
import tech.jinhaoma.AnkiMaker.domain.BingDataRepository;
import tech.jinhaoma.AnkiMaker.service.BingService;
import tech.jinhaoma.AnkiMaker.task.BingTask;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by mjrt on 1/30/2017.
 */
@Service
public class BingServiceImpl extends CurdServiceImpl<BingData,BingDataRepository> implements BingService{


    @Autowired
    public BingServiceImpl(BingDataRepository repository) {
        super(repository);
    }

    @Override
    public BingData query(String word) {

        BingData data = repository.findByWord(word);

        if (data != null){
            return data;
        }

        BingTask bingTask = new BingTask();
        ArrayList<String> s = new ArrayList<>();
        s.add(word);
        List<BingData> r = null;
        try {
            r = bingTask.asyncBingTask(s);
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
        repository.save(r);
        data = r.get(0);

        if (data ==null)
            return null;
        return data;
    }

    @Override
    public void purge(String word) {
        BingData data = repository.findByWord(word);
        if(data != null)
            repository.delete(data);
    }

}
