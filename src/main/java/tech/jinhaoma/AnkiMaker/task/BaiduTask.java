package tech.jinhaoma.AnkiMaker.task;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

import tech.jinhaoma.AnkiMaker.domain.BaiduData;

import tech.jinhaoma.AnkiMaker.api.BaiduApi;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by mjrt on 1/19/2017.
 */
@Log4j2
@NoArgsConstructor
public class BaiduTask extends AsyncTask<BaiduApi,BaiduData>{

    public BaiduTask(int sleepTime) {
        super(sleepTime);
    }

    public List<BaiduData> asyncBaiduTask(List<String> words) throws NoSuchMethodException, InterruptedException, ExecutionException, IllegalAccessException, InstantiationException, InvocationTargetException {
        List<BaiduData> baiduDatas = asyncTask(words,BaiduApi.class);

        return baiduDatas;
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
