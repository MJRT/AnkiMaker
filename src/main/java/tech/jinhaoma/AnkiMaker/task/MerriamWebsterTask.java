package tech.jinhaoma.AnkiMaker.task;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import tech.jinhaoma.AnkiMaker.model.MerriamWebsterData;
import tech.jinhaoma.AnkiMaker.search.MerriamWebsterSearchOnline;

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
@AllArgsConstructor
@NoArgsConstructor
public class MerriamWebsterTask extends AsyncTask<MerriamWebsterSearchOnline,MerriamWebsterData>{

    public List<MerriamWebsterData> asyncMerriamWebsterTask(List<String> words) throws NoSuchMethodException, InterruptedException, ExecutionException, IllegalAccessException, InstantiationException, InvocationTargetException, InvocationTargetException {
        return asyncTask(words,MerriamWebsterSearchOnline.class);
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        MerriamWebsterTask merriamWebsterTask = new MerriamWebsterTask();
        ArrayList<String> s = new ArrayList<>();
        s.add("eat");
        s.add("quick");
        s.add("people");
        s.add("yes");
        List<MerriamWebsterData> r = merriamWebsterTask.asyncMerriamWebsterTask(s);

        for(MerriamWebsterData w : r){
            System.out.println(w.toString());
        }
    }
}
