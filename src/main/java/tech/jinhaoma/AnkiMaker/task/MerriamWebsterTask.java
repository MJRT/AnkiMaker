package tech.jinhaoma.AnkiMaker.task;

import tech.jinhaoma.AnkiMaker.bean.MerriamWebsterData;
import tech.jinhaoma.AnkiMaker.search.MerriamWebsterSearchOnline;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

/**
 * Created by mjrt on 1/19/2017.
 */
public class MerriamWebsterTask {

    public List<MerriamWebsterData> asyncMerriamWebsterTask(List<String> words) throws ExecutionException, InterruptedException {

        ExecutorService executor = Executors.newCachedThreadPool();
        ArrayList<MerriamWebsterSearchOnline> so = new ArrayList<>();
        ArrayList<FutureTask<MerriamWebsterData>> ft = new ArrayList<>();

        for(int i = 0 ; i < words.size() ; i++){
            so.add(new MerriamWebsterSearchOnline(words.get(i)));
        }

        for(int i = 0 ; i < words.size() ; i++){
            ft.add( new FutureTask<MerriamWebsterData>(so.get(i)));
        }

        for(int i = 0 ; i < words.size() ; i++){
            executor.execute(ft.get(i));
        }

        ArrayList<MerriamWebsterData> result = new ArrayList<>();

        for(int i = 0 ; i < words.size() ; i++){
            result.add(i,ft.get(i).get());
        }
        executor.shutdown();

        return result;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        MerriamWebsterTask merriamWebsterTask = new MerriamWebsterTask();
        ArrayList<String> s = new ArrayList<>();
        s.add("eat");
        s.add("quick");
        s.add("people");
        List<MerriamWebsterData> r = merriamWebsterTask.asyncMerriamWebsterTask(s);

        for(MerriamWebsterData w : r){
            System.out.println(w.toString());
        }
    }
}
