package tech.jinhaoma.AnkiMaker.task;

/**
 * Created by mjrt on 1/19/2017.
 */

import lombok.extern.log4j.Log4j2;
import tech.jinhaoma.AnkiMaker.bean.VocabularyData;
import tech.jinhaoma.AnkiMaker.search.VocabularySearchOnline;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

@Log4j2
public class VocabularyTask {

    public List<VocabularyData> asyncVocabularyTask(List<String> words) throws ExecutionException, InterruptedException {

        ExecutorService executor = Executors.newCachedThreadPool();
        ArrayList<VocabularySearchOnline> so = new ArrayList<>();
        ArrayList<FutureTask<VocabularyData>> ft = new ArrayList<>();

        for(int i = 0 ; i < words.size() ; i++){
            so.add(new VocabularySearchOnline(words.get(i)));
        }

        for(int i = 0 ; i < words.size() ; i++){
            ft.add( new FutureTask<VocabularyData>(so.get(i)));
        }

        for(int i = 0 ; i < words.size() ; i++){
            executor.execute(ft.get(i));
        }

        ArrayList<VocabularyData> result = new ArrayList<>();

        for(int i = 0 ; i < words.size() ; i++){
            result.add(i,ft.get(i).get());
        }
        executor.shutdown();

        return result;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        VocabularyTask bt = new VocabularyTask();
        ArrayList<String> s = new ArrayList<>();
        s.add("eat");
        s.add("quick");
        s.add("people");
        List<VocabularyData> r = bt.asyncVocabularyTask(s);

        for(VocabularyData w : r){
            System.out.println(w.toString());
        }
    }

}
