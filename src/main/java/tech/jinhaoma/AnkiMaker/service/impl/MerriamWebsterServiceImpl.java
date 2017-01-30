package tech.jinhaoma.AnkiMaker.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.jinhaoma.AnkiMaker.domain.MerriamWebsterData;
import tech.jinhaoma.AnkiMaker.domain.MerriamWebsterDataRepository;
import tech.jinhaoma.AnkiMaker.service.MerriamWebsterService;
import tech.jinhaoma.AnkiMaker.task.MerriamWebsterTask;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by mjrt on 1/30/2017.
 */
@Service
public class MerriamWebsterServiceImpl extends CurdServiceImpl<MerriamWebsterData,MerriamWebsterDataRepository> implements MerriamWebsterService {
    @Autowired
    public MerriamWebsterServiceImpl(MerriamWebsterDataRepository repository) {
        super(repository);
    }

    @Override
    public MerriamWebsterData query(String word) {

        MerriamWebsterData data = repository.findByWord(word);

        if (data != null){

            return data;
        }

        MerriamWebsterTask bingTask = new MerriamWebsterTask();
        ArrayList<String> s = new ArrayList<>();
        s.add(word);
        List<MerriamWebsterData> r = null;
        try {
            r = bingTask.asyncMerriamWebsterTask(s);
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
        MerriamWebsterData data = repository.findByWord(word);
        if(data != null)
            repository.delete(data);
    }
}
