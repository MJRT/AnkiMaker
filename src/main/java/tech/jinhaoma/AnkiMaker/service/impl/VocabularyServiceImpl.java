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

        VocabularyTask task = new VocabularyTask();
        ArrayList<String> s = new ArrayList<>();
        s.add(word);
        List<VocabularyData> r = null;
        try {
            r = task.asyncVocabularyTask(s);
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
        System.out.println("Vocabulary OK");
        return data;
    }

    @Override
    public List<VocabularyData> batchQuery(List<String> words) {

        List<String> Offline = new ArrayList<>();
        List<VocabularyData> r = new ArrayList<>();

        for(String word : words){
            VocabularyData data = repository.findByWord(word);
            if (data != null){
                r.add(data);
            } else {
                Offline.add(word);
            }
        }
        System.out.println(Offline.toString());
        VocabularyTask task = new VocabularyTask();

        try {
            r.addAll(task.asyncVocabularyTask(Offline));
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
        System.out.println("Vocabulary OK");
        return r;
    }

    @Override
    public void purge(String word) {
        VocabularyData data = repository.findByWord(word);
        if(data != null)
            repository.delete(data);
    }
}
