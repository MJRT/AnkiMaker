package tech.jinhaoma.AnkiMaker.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import tech.jinhaoma.AnkiMaker.api.BingApi;
import tech.jinhaoma.AnkiMaker.domain.BingData;
import tech.jinhaoma.AnkiMaker.domain.BingDataRepository;
import tech.jinhaoma.AnkiMaker.domain.WordMap;
import tech.jinhaoma.AnkiMaker.domain.WordMapRepository;
import tech.jinhaoma.AnkiMaker.service.BingService;
import tech.jinhaoma.AnkiMaker.task.BingTask;

import java.io.IOException;
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
    WordMapRepository wordMapRepository;


    @Autowired
    public BingServiceImpl(BingDataRepository repository) {
        super(repository);
    }

    @Override
    public BingData query(String word) {

        WordMap map = wordMapRepository.findByKey(word);
        if (map != null){
            word = map.getValue();
        }

        BingData data = repository.findByWord(word);

        if (data != null){
            return data;
        }

        try {
            data = new BingApi().Search(word);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if(data != null){
            repository.save(data);
        } else {
            data = new BingData();
        }

        if(map == null && !word.equals(data.getWord())&& data.getWord()!=null){
            map = wordMapRepository.save(new WordMap(word,data.getWord()));
            word = map.getValue();
        }

        return data;
    }

    @Override
    public List<BingData> batchQuery(List<String> words) {

        List<String> Offline = new ArrayList<>();
        List<BingData> res = new ArrayList<>();
        List<BingData> tmp = null;

        for(String word : words){

            WordMap map = wordMapRepository.findByKey(word);
            if(map != null){
                word = map.getValue();
                System.out.println(map.getKey() +"----"+ map.getValue());
            }
            BingData data = repository.findByWord(word);
            if (data != null){
                res.add(data);
            } else {
                String tempWord = "";
                try{
                    tempWord = word.substring(0,1).toUpperCase()+word.substring(1,word.length());
                } catch (StringIndexOutOfBoundsException e){
                    System.out.println("word = " + word);
                }

                data = repository.findByWord(word.substring(0,1).toUpperCase()+word.substring(1,word.length()));
                if (data != null){
                    res.add(data);
                    word = tempWord;
                } else {
                    Offline.add(word);
                }
            }
        }
        System.out.println("Bing Offline "+ "("+ Offline.size() +")"+Offline.toString());

        BingTask task = new BingTask(1000);

        try {
            tmp = task.asyncBingTask(Offline);
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
            for(BingData data : tmp){
                if(data != null){
                    repository.save(data);
                }
                res.add(data);
            }
            for(int i = 0 ; i < Offline.size() ; i++){
                WordMap map = wordMapRepository.findByKey(Offline.get(i));
                if (map==null && tmp.get(i)!= null && !Offline.get(i).equals(tmp.get(i))){
                    Offline.set(i,wordMapRepository.save(new WordMap(Offline.get(i),tmp.get(i).getWord())).getValue());
                }
            }
        }
//        System.out.println("Bing OK");
        return res;
    }

    @Override
    public void purge(String word) {
        BingData data = repository.findByWord(word);
        if(data != null)
            repository.delete(data);
    }

}
