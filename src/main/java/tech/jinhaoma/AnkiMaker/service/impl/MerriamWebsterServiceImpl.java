package tech.jinhaoma.AnkiMaker.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.jinhaoma.AnkiMaker.domain.MerriamWebsterData;
import tech.jinhaoma.AnkiMaker.domain.MerriamWebsterDataRepository;
import tech.jinhaoma.AnkiMaker.domain.WordMap;
import tech.jinhaoma.AnkiMaker.domain.WordMapRepository;
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
    private WordMapRepository wordMapRepository;
    @Autowired
    public MerriamWebsterServiceImpl(MerriamWebsterDataRepository repository) {
        super(repository);
    }

    @Override
    public MerriamWebsterData query(String word) {

        WordMap map = wordMapRepository.findByKey(word);
        if (map != null){
            word = map.getValue();
        }
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
        data = r.get(0);
        if(data != null){
            repository.save(r);
        } else {
            data = new MerriamWebsterData();
        }

        if(map == null && !word.equals(data.getWord()) && data.getWord()!=null){
            map = wordMapRepository.save(new WordMap(word,data.getWord()));
            word = map.getValue();
        }

        System.out.println("MerriamWebster OK");
        return data;
    }

    @Override
    public List<MerriamWebsterData> batchQuery(List<String> words) {

        List<String> Offline = new ArrayList<>();
        List<MerriamWebsterData> res = new ArrayList<>();
        List<MerriamWebsterData> tmp = null;

        for(String word : words){
            MerriamWebsterData data = repository.findByWord(word);
            if (data != null){
                res.add(data);
            } else {
                Offline.add(word);
            }
        }
        System.out.println("MerriamWebster Offline "+ "("+ Offline.size() +")"+Offline.toString());
        MerriamWebsterTask task = new MerriamWebsterTask();

        try {
            tmp = task.asyncMerriamWebsterTask(Offline);
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
            for(MerriamWebsterData data : tmp){
                if(data != null){
                    repository.save(data);
                }
                res.add(data);
            }

            for(int i = 0 ; i < Offline.size() ; i++){
                WordMap map = wordMapRepository.findByKey(Offline.get(i));
                if (map==null && tmp.get(i)!= null && !Offline.get(i).equals(tmp.get(i).getWord())){
                    Offline.set(i,wordMapRepository.save(new WordMap(Offline.get(i),tmp.get(i).getWord())).getValue());
                }
            }
        }
        System.out.println("MerriamWebster OK");
        return res;
    }

    @Override
    public void purge(String word) {
        MerriamWebsterData data = repository.findByWord(word);
        if(data != null)
            repository.delete(data);
    }
}
