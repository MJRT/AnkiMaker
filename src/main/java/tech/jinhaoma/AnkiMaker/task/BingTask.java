package tech.jinhaoma.AnkiMaker.task;

import tech.jinhaoma.AnkiMaker.bean.BingData;
import tech.jinhaoma.AnkiMaker.search.BingSearchOnline;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

/**
 * Created by mjrt on 1/23/2017.
 */
public class BingTask {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
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

    public List<BingData> asyncBingTask(List<String> words) throws ExecutionException, InterruptedException {

        ExecutorService executor = Executors.newCachedThreadPool();
        ArrayList<BingSearchOnline> so = new ArrayList<>();
        ArrayList<FutureTask<BingData>> ft = new ArrayList<>();

        for(int i = 0 ; i < words.size() ; i++){
            so.add(new BingSearchOnline(words.get(i)));
        }

        for(int i = 0 ; i < words.size() ; i++){
            ft.add( new FutureTask<BingData>(so.get(i)));
        }

        for(int i = 0 ; i < words.size() ; i++){
            executor.execute(ft.get(i));
        }

        ArrayList<BingData> result = new ArrayList<>();

        for(int i = 0 ; i < words.size() ; i++){
            result.add(i,ft.get(i).get());
        }
        executor.shutdown();

        return result;
    }
}
