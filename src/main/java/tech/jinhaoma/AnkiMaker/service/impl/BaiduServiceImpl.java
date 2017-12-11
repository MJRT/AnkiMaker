package tech.jinhaoma.AnkiMaker.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.jinhaoma.AnkiMaker.domain.BaiduData;
import tech.jinhaoma.AnkiMaker.domain.BaiduDataRepository;
import tech.jinhaoma.AnkiMaker.service.BaiduService;
import tech.jinhaoma.AnkiMaker.task.BaiduTask;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by mjrt on 1/30/2017.
 */
@Service
public class BaiduServiceImpl extends CurdServiceImpl<BaiduData,BaiduDataRepository> implements BaiduService {

    @Autowired
    public BaiduServiceImpl(BaiduDataRepository repository) {
        super(repository);
    }

    @Override
    public BaiduData query(String word) {


        BaiduData data = repository.findByWord(word);

        if (data != null){
            return data;
        }

        BaiduTask task = new BaiduTask();
        ArrayList<String> s = new ArrayList<>();
        s.add(word);
        List<BaiduData> r = null;
        try {
            r = task.asyncBaiduTask(s);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        repository.save(r);
        data = r.get(0);

        if (data ==null)
            return null;
//        System.out.println("Baidu OK");
        return data;
    }

    @Override
    public void purge(String word) {
        BaiduData data = repository.findByWord(word);
        if(data != null)
            repository.delete(data);
    }
}
