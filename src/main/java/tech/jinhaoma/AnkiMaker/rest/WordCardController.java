package tech.jinhaoma.AnkiMaker.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import tech.jinhaoma.AnkiMaker.domain.WordCard;
import tech.jinhaoma.AnkiMaker.domain.WordCardRepository;
import java.util.List;


/**
 * Created by mjrt on 1/27/2017.
 */
@RestController
@RequestMapping(value = "/card")
public class WordCardController {

    private WordCardRepository repository ;

    @Autowired
    public WordCardController(WordCardRepository bingDataRepository ) {
        this.repository = bingDataRepository;
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void upload(@RequestBody List<WordCard> trucks) throws Exception {
        this.repository.save(trucks);
    }

    @RequestMapping(value = "/purge", method = RequestMethod.DELETE)
    public void purge() {
        this.repository.deleteAll();
    }

    @RequestMapping(value = "/test")
    public String findByWord(){
       return "ok";
    }
}
