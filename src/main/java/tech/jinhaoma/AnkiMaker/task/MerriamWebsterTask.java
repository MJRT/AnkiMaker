package tech.jinhaoma.AnkiMaker.task;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import tech.jinhaoma.AnkiMaker.common.RandomUtils;
import tech.jinhaoma.AnkiMaker.domain.MerriamWebsterData;
import tech.jinhaoma.AnkiMaker.api.MerriamWebsterApi;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by mjrt on 1/19/2017.
 */
@Log4j2

@NoArgsConstructor
public class MerriamWebsterTask extends AsyncTask<MerriamWebsterApi,MerriamWebsterData>{

    public MerriamWebsterTask(int sleepTime) {
        super(sleepTime);
    }

    public List<MerriamWebsterData> asyncMerriamWebsterTask(List<String> words) throws NoSuchMethodException, InterruptedException, ExecutionException, IllegalAccessException, InstantiationException, InvocationTargetException, InvocationTargetException {
        return asyncTask(words,MerriamWebsterApi.class);
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

    @Override
    protected long fixSleepTime(int idx) {
        return RandomUtils.nextInt(200,600);
    }
}
