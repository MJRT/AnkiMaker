package tech.jinhaoma.AnkiMaker.task;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import tech.jinhaoma.AnkiMaker.domain.BingData;
import tech.jinhaoma.AnkiMaker.domain.BingDataRepository;
import tech.jinhaoma.AnkiMaker.search.BingSearchOnline;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by mjrt on 1/23/2017.
 */
@Log4j2
@NoArgsConstructor
public class BingTask extends AsyncTask<BingSearchOnline,BingData>{

    public BingTask(int sleepTime) {
        super(sleepTime);
    }

    public List<BingData> asyncBingTask(List<String> words) throws NoSuchMethodException, InterruptedException, ExecutionException, IllegalAccessException, InstantiationException, InvocationTargetException, InvocationTargetException {
        List<BingData> bingDatas =  asyncTask(words,BingSearchOnline.class);
        return  bingDatas;
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
