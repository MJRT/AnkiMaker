package tech.jinhaoma.AnkiMaker.task;

import lombok.extern.log4j.Log4j2;
import tech.jinhaoma.AnkiMaker.bean.BaiduData;
import tech.jinhaoma.AnkiMaker.bean.MerriamWebsterData;
import tech.jinhaoma.AnkiMaker.search.BaiduSearchOnline;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

/**
 * Created by mjrt on 1/19/2017.
 */
@Log4j2
public class BaiduTask {

    public List<BaiduData> asyncBaiduTask(List<String> words) throws ExecutionException, InterruptedException {

        ExecutorService executor = Executors.newCachedThreadPool();
        ArrayList<BaiduSearchOnline> so = new ArrayList<>();
        ArrayList<FutureTask<BaiduData>> ft = new ArrayList<>();

        for(int i = 0 ; i < words.size() ; i++){
            so.add(new BaiduSearchOnline(words.get(i)));
        }

        for(int i = 0 ; i < words.size() ; i++){
            ft.add( new FutureTask<BaiduData>(so.get(i)));
        }

        for(int i = 0 ; i < words.size() ; i++){
            executor.execute(ft.get(i));
        }

        ArrayList<BaiduData> result = new ArrayList<>();

        for(int i = 0 ; i < words.size() ; i++){
                result.add(i,ft.get(i).get());
        }
        executor.shutdown();

        return result;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        BaiduTask bt = new BaiduTask();
        ArrayList<String> s = new ArrayList<>();
        s.add("eat");
        s.add("quick");
        s.add("people");
        List<BaiduData> r = bt.asyncBaiduTask(s);

        for(BaiduData w : r){
            System.out.println(w.toString());
        }
    }
}
