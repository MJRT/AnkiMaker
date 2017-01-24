package tech.jinhaoma.AnkiMaker.task;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import tech.jinhaoma.AnkiMaker.model.BaiduData;
import tech.jinhaoma.AnkiMaker.search.BaiduSearchOnline;

import java.lang.reflect.InvocationTargetException;
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
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaiduTask extends AsyncTask<BaiduSearchOnline,BaiduData>{

    private int sleepTime;

    public List<BaiduData> asyncBaiduTask(List<String> words) throws NoSuchMethodException, InterruptedException, ExecutionException, IllegalAccessException, InstantiationException, InvocationTargetException {
        return asyncTask(words,BaiduSearchOnline.class);
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        BaiduTask bt = new BaiduTask();
        ArrayList<String> s = new ArrayList<>();
        s.add("eat");
        s.add("quick");
        s.add("people");
        List<BaiduData> r = bt.asyncBaiduTask(s);
        System.out.println(bt.getSleepTime());
        for(BaiduData w : r){
            System.out.println(w.toString());
        }
    }
}
