package tech.jinhaoma.AnkiMaker.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.jinhaoma.AnkiMaker.domain.VocabularyData;
import tech.jinhaoma.AnkiMaker.domain.VocabularyDataRepository;
import tech.jinhaoma.AnkiMaker.domain.WordMap;
import tech.jinhaoma.AnkiMaker.domain.WordMapRepository;
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
    private WordMapRepository wordMapRepository;
    @Autowired
    public VocabularyServiceImpl(VocabularyDataRepository repository) {
        super(repository);
}

    @Override
    public VocabularyData query(String word) {

        WordMap map = wordMapRepository.findByKey(word);
        if (map != null){
            word = map.getValue();
        }
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
        data = r.get(0);
        if(data != null){
            repository.save(r);
        } else {
            data = new VocabularyData();
        }

        if(map == null && !word.equals(data.getWord())&& data.getWord()!=null){
            map = wordMapRepository.save(new WordMap(word,data.getWord()));
            word = map.getValue();
        }

//        System.out.println("Vocabulary OK");
        return data;
    }

    @Override
    public List<VocabularyData> batchQuery(List<String> words) {

        List<String> Offline = new ArrayList<>();
        List<VocabularyData> res = new ArrayList<>();
        List<VocabularyData> tmp = null;

        for(String word : words){
            WordMap map = wordMapRepository.findByKey(word);
            if(map != null){
                word = map.getValue();
            }
            VocabularyData data = repository.findByWord(word);
            if (data != null){
                res.add(data);
            } else {
                Offline.add(word);
            }
        }
        System.out.println("Vocabulary Offline "+ "("+ Offline.size() +")"+ Offline.toString());
        VocabularyTask task = new VocabularyTask();

        try {
            tmp = task.asyncVocabularyTask(Offline);
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


        if(tmp != null){
            for(VocabularyData data : tmp){
                if(data != null){
                    repository.save(data);
                }
                res.add(data);
            }
            for(int i = 0 ; i < Offline.size() ; i++){
                WordMap map = wordMapRepository.findByKey(Offline.get(i));
                if (map==null && tmp.get(i)!= null && !Offline.get(i).equals(tmp.get(i).getWord()) ){
                    wordMapRepository.save(new WordMap(Offline.get(i),tmp.get(i).getWord()));
                    Offline.set(i,tmp.get(i).getWord());
                }
            }
        }
//        System.out.println("Vocabulary OK");
        return res;
    }

    @Override
    public void purge(String word) {
        VocabularyData data = repository.findByWord(word);
        if(data != null)
            repository.delete(data);
    }
}
