package tech.jinhaoma.AnkiMaker.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import tech.jinhaoma.AnkiMaker.domain.MerriamWebsterData;
import tech.jinhaoma.AnkiMaker.service.MerriamWebsterService;

import java.util.List;

/**
 * Created by mjrt on 1/25/2017.
 */
@RestController
@RequestMapping(value = "/mw")
public class MerriamWebsterController {

    private MerriamWebsterService service ;

    @Autowired
    public MerriamWebsterController(MerriamWebsterService service) {
        this.service = service;
    }

    @GetMapping(value = "/query/{word}")
    public MerriamWebsterData query(@PathVariable  String word){
        return this.service.query(word);
    }

    @PostMapping(value = "/upload")
    @ResponseStatus(HttpStatus.CREATED)
    public void upload(@RequestBody List<MerriamWebsterData> trucks) throws Exception {
        this.service.upload(trucks);
    }

    @PutMapping(value = "/updata")
    @ResponseStatus(HttpStatus.CREATED)
    public void updata(@RequestBody List<MerriamWebsterData> trucks) throws Exception {
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
