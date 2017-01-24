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
import java.util.concurrent.*;

/**
 * Created by mjrt on 1/24/2017.
 */
@Log4j2
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AsyncTask<Search,Data> {

    protected int sleepTime;

    public List<Data> asyncTask(List<String> words,Class<Search> searchClass) throws ExecutionException, InterruptedException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {

        ExecutorService executor = Executors.newCachedThreadPool();
        ArrayList<Search> so = new ArrayList<>();
        ArrayList<FutureTask<Data>> ft = new ArrayList<>();

        for (int i = 0; i < words.size(); i++) {
            so.add(searchClass.getConstructor(String.class).newInstance(words.get(i)));
        }

        for (int i = 0; i < words.size(); i++) {
            ft.add(new FutureTask<>((Callable<Data>) so.get(i)));
        }

        for (int i = 0; i < words.size(); i++) {
            executor.submit(ft.get(i));
        }

        ArrayList<Data> result = new ArrayList<>();

        int i = 0;
        try {
            for (; i < ft.size(); i++)
                result.add(i, ft.get(i).get());
        } catch (InterruptedException e) {
            e.printStackTrace();
            log.error(words.get(i));
        } catch (ExecutionException e) {
            e.printStackTrace();
            log.error(words.get(i));
        }
        executor.shutdown();

        return result;
    }

}