package tech.jinhaoma.AnkiMaker.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import tech.jinhaoma.AnkiMaker.domain.BaiduData;
import tech.jinhaoma.AnkiMaker.service.BaiduService;

import java.util.List;

/**
 * Created by mjrt on 1/25/2017.
 */
@RestController
@RequestMapping(value = "/baidu")
public class BaiduController {

    private BaiduService service ;

    @Autowired
    public BaiduController(BaiduService service) {
        this.service = service;
    }

    @GetMapping(value = "/query/{word}")
    public BaiduData query(@PathVariable  String word){
        return this.service.query(word);
    }

    @PostMapping(value = "/upload")
    @ResponseStatus(HttpStatus.CREATED)
    public void upload(@RequestBody List<BaiduData> trucks) throws Exception {
        this.service.upload(trucks);
    }

    @PostMapping(value = "/updata")
    @ResponseStatus(HttpStatus.CREATED)
    public void updata(@RequestBody List<BaiduData> trucks) throws Exception {
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
