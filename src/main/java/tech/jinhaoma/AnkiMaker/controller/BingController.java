package tech.jinhaoma.AnkiMaker.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import tech.jinhaoma.AnkiMaker.domain.BingData;
import tech.jinhaoma.AnkiMaker.service.BingService;

import java.util.List;

/**
 * Created by mjrt on 1/25/2017.
 */
@Log4j2
@RestController
@RequestMapping(value = "/bing")
public class BingController {


    private BingService service ;

    @Autowired
    public BingController(BingService service) {
        this.service = service;
    }

    @GetMapping(value = "/query/{word}")
    public BingData query(@PathVariable  String word){
        return this.service.query(word);
    }

    @PostMapping(value = "/upload")
    @ResponseStatus(HttpStatus.CREATED)
    public void upload(@RequestBody List<BingData> trucks) throws Exception {
        this.service.upload(trucks);
    }

    @PutMapping(value = "/updata")
    @ResponseStatus(HttpStatus.CREATED)
    public void updata(@RequestBody List<BingData> trucks) throws Exception {
        this.service.updata(trucks);
    }

    @DeleteMapping(value = "/purge")
    public void purge() {
        this.service.purge();
    }

    @DeleteMapping(value = "/purge/{word}" )
    public void purge(@PathVariable String word) {
        this.service.purge(word);
    }

    @GetMapping(value = "/test")
    public String test(){
        return "ok";
    }


}
