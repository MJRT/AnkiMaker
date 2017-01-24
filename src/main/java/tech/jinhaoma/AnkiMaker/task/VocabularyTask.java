package tech.jinhaoma.AnkiMaker.task;

/**
 * Created by mjrt on 1/19/2017.
 */

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import tech.jinhaoma.AnkiMaker.model.VocabularyData;
import tech.jinhaoma.AnkiMaker.search.VocabularySearchOnline;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

@Log4j2
@AllArgsConstructor
@NoArgsConstructor
public class VocabularyTask extends AsyncTask<VocabularySearchOnline,VocabularyData>{

    private int sleepTime;

    public List<VocabularyData> asyncVocabularyTask(List<String> words) throws NoSuchMethodException, InterruptedException, ExecutionException, IllegalAccessException, InstantiationException, InvocationTargetException, InvocationTargetException, InvocationTargetException {
        return asyncTask(words,VocabularySearchOnline.class);
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
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
