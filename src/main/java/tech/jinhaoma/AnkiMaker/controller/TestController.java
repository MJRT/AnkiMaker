//package tech.jinhaoma.AnkiMaker.rest;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import tech.jinhaoma.AnkiMaker.domain.BaseRepository;
//import tech.jinhaoma.AnkiMaker.domain.Data;
//import tech.jinhaoma.AnkiMaker.task.AsyncTask;
//
//import java.lang.reflect.InvocationTargetException;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.concurrent.ExecutionException;
//
///**
// * Created by mjrt on 1/30/2017.
// */
//public class TestController<Data,Task extends AsyncTask> {
//
//
//    BaseRepository<Data> repository ;
//
//    @Autowired
//    public TestController(BaseRepository<Data> repository) {
//        this.repository = repository;
//    }
//
//    public Data query(String word) {
//
//        Data bingData = repository.findByWord(word);
//
//        if (bingData != null){
//            return bingData;
//        }
//s
//        Task bingTask = new Task();
//        ArrayList<String> s = new ArrayList<>();
//        s.add(word);
//        List<Data> r = null;
//        try {
//            r = bingTask.asyncTask(s);
//        } catch (NoSuchMethodException e) {
//            e.printStackTrace();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        } catch (InstantiationException e) {
//            e.printStackTrace();
//        } catch (InvocationTargetException e) {
//            e.printStackTrace();
//        }
//        repository.save(r);
//        bingData = r.get(0);
//
//        if (bingData ==null)
//            return null;
//        return bingData;
//    }
//
//}
