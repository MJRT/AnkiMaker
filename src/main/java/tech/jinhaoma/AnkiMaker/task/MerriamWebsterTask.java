package tech.jinhaoma.AnkiMaker.task;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import tech.jinhaoma.AnkiMaker.domain.MerriamWebsterData;
import tech.jinhaoma.AnkiMaker.domain.MerriamWebsterDataRepository;
import tech.jinhaoma.AnkiMaker.search.MerriamWebsterSearchOnline;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by mjrt on 1/19/2017.
 */
@Log4j2

@NoArgsConstructor
public class MerriamWebsterTask extends AsyncTask<MerriamWebsterSearchOnline,MerriamWebsterData>{

    @Autowired
    MerriamWebsterDataRepository merriamWebsterDataRepository;

    public MerriamWebsterTask(int sleepTime) {
        super(sleepTime);
    }

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
        merriamWebsterTask.merriamWebsterDataRepository.save(r);
        for(MerriamWebsterData w : r){
            System.out.println(w.toString());
        }
    }
}
