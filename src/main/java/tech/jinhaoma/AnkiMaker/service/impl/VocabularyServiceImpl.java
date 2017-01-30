package tech.jinhaoma.AnkiMaker.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.jinhaoma.AnkiMaker.domain.VocabularyData;
import tech.jinhaoma.AnkiMaker.domain.VocabularyDataRepository;
import tech.jinhaoma.AnkiMaker.service.VocabularyService;
import tech.jinhaoma.AnkiMaker.task.VocabularyTask;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by mjrt on 1/30/2017.
 */
@Service
public class VocabularyServiceImpl extends CurdServiceImpl<VocabularyData,VocabularyDataRepository> implements VocabularyService{
    @Autowired
    public VocabularyServiceImpl(VocabularyDataRepository repository) {
        super(repository);
}

    @Override
    public VocabularyData query(String word) {

        VocabularyData data = repository.findByWord(word);

        if (data != null){
            return data;
        }

        VocabularyTask bingTask = new VocabularyTask();
        ArrayList<String> s = new ArrayList<>();
        s.add(word);
        List<VocabularyData> r = null;
        try {
            r = bingTask.asyncVocabularyTask(s);
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
        VocabularyData data = repository.findByWord(word);
        if(data != null)
            repository.delete(data);
    }
}
