package tech.jinhaoma.AnkiMaker.task;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import tech.jinhaoma.AnkiMaker.model.BingData;
import tech.jinhaoma.AnkiMaker.search.BingSearchOnline;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

/**
 * Created by mjrt on 1/23/2017.
 */
@Log4j2
@AllArgsConstructor
@NoArgsConstructor
public class BingTask extends AsyncTask<BingSearchOnline,BingData>{

    public List<BingData> asyncBingTask(List<String> words) throws NoSuchMethodException, InterruptedException, ExecutionException, IllegalAccessException, InstantiationException, InvocationTargetException, InvocationTargetException {
        return asyncTask(words,BingSearchOnline.class);
    }
    public static void main(String[] args) throws ExecutionException, InterruptedException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        BingTask bingTask = new BingTask();
        ArrayList<String> s = new ArrayList<>();
        s.add("eat");
        s.add("quick");
        s.add("people");
        List<BingData> r = bingTask.asyncBingTask(s);

        for(BingData w : r){
            System.out.println(w.toString());
        }
    }


}
