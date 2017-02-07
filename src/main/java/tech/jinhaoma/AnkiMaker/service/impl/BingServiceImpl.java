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

        BingTask task = new BingTask();
        ArrayList<String> s = new ArrayList<>();
        s.add(word);
        List<BingData> r = null;
        try {
            r = task.asyncBingTask(s);
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
        System.out.println("Bing OK");
        return data;
    }

    @Override
    public List<BingData> batchQuery(List<String> words) {

        List<String> Offline = new ArrayList<>();
        List<BingData> r = new ArrayList<>();

        for(String word : words){
            BingData data = repository.findByWord(word);
            if (data != null){
                r.add(data);
            } else {
                String tempWord = word.substring(0,1).toUpperCase()+word.substring(1,word.length());
                data = repository.findByWord(word.substring(0,1).toUpperCase()+word.substring(1,word.length()));
                if (data != null){
                    r.add(data);
                    word = tempWord;
                } else {
                    Offline.add(word);
                }
            }
        }
        System.out.println(Offline.toString());

        BingTask task = new BingTask();

        try {
            r.addAll(task.asyncBingTask(Offline));
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
        System.out.println("Bing OK");
        return r;
    }

    @Override
    public void purge(String word) {
        BingData data = repository.findByWord(word);
        if(data != null)
            repository.delete(data);
    }

}
