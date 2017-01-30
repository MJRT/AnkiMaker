package tech.jinhaoma.AnkiMaker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import tech.jinhaoma.AnkiMaker.domain.WordCard;
import tech.jinhaoma.AnkiMaker.service.WordCardService;

import java.util.List;


/**
 * Created by mjrt on 1/27/2017.
 */
@RestController
@RequestMapping(value = "/card")
public class WordCardController {

    private WordCardService service ;

    @Autowired
    public WordCardController(WordCardService service) {
        this.service = service;
    }

    @GetMapping(value = "" )
    public Page<WordCard> queryAll(Pageable pageable){
        return service.queryAll(pageable);
    }
    @GetMapping(value = "/query/{word}" )
    public Page<WordCard> query(@PathVariable  String word , Pageable pageable){
        return this.service.query(word,pageable);
    }

    @PutMapping(value = "/upload")
    @ResponseStatus(HttpStatus.CREATED)
    public void upload(@RequestBody List<WordCard> trucks) throws Exception {
        this.service.upload(trucks);
    }

    @PostMapping(value = "/updata")
    @ResponseStatus(HttpStatus.CREATED)
    public void updata(@RequestBody List<WordCard> trucks) throws Exception {
        this.service.updata(trucks);

    }

    @DeleteMapping(value = "/purge")
    public void purge() {
        this.service.purge();
    }

    @DeleteMapping(value = "/purge/{word}")
    public void purge(@PathVariable String word) {
        this.service.purge(word);
    }

    @GetMapping(value = "/test")
    public String test(){
        return "ok";
    }
}
