package tech.jinhaoma.AnkiMaker.task;

/**
 * Created by mjrt on 1/19/2017.
 */

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import tech.jinhaoma.AnkiMaker.common.RandomUtils;
import tech.jinhaoma.AnkiMaker.domain.VocabularyData;
import tech.jinhaoma.AnkiMaker.api.VocabularyApi;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Log4j2
@NoArgsConstructor
public class VocabularyTask extends AsyncTask<VocabularyApi,VocabularyData>{

    public VocabularyTask(int sleepTime) {
        super(sleepTime);
    }

    public List<VocabularyData> asyncVocabularyTask(List<String> words) throws NoSuchMethodException, InterruptedException, ExecutionException, IllegalAccessException, InstantiationException, InvocationTargetException, InvocationTargetException, InvocationTargetException {
        return asyncTask(words,VocabularyApi.class);
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

    @Override
    protected long fixSleepTime(int idx) {
        return RandomUtils.nextInt(300,500);
    }
}
