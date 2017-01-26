package tech.jinhaoma.AnkiMaker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import tech.jinhaoma.AnkiMaker.domain.BingData;
import tech.jinhaoma.AnkiMaker.domain.MerriamWebsterData;
import tech.jinhaoma.AnkiMaker.task.BingTask;
import tech.jinhaoma.AnkiMaker.task.MerriamWebsterTask;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;


/**
 * Created by mjrt on 1/21/2017.
 */

@SpringBootApplication
public class AnkiMakerServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(AnkiMakerServiceApplication.class, args);

//        try {
//            Thread.sleep(2000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

//        BingTask bingTask = new BingTask();
//        ArrayList<String> s = new ArrayList<>();
//        s.add("look");
//        List<BingData> r = null;
//        try {
//            r = bingTask.asyncBingTask(s);
//            RestTemplate restTemplate = new RestTemplate();
//            restTemplate.postForLocation("http://localhost:8888/bing/upload",r);
//
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
//
//        for(BingData w : r){
//            System.out.println(w.toString());
//        }

    }
}
