package tech.jinhaoma.AnkiMaker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
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
    }
}
