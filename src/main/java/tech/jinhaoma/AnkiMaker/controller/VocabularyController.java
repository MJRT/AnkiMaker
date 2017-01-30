package tech.jinhaoma.AnkiMaker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import tech.jinhaoma.AnkiMaker.domain.VocabularyData;

import tech.jinhaoma.AnkiMaker.service.VocabularyService;

import java.util.List;

/**
 * Created by mjrt on 1/27/2017.
 */
@RestController
@RequestMapping(value = "/vocab")
public class VocabularyController {

    private VocabularyService service ;

    @Autowired
    public VocabularyController(VocabularyService service) {
        this.service = service;
    }

    @GetMapping(value = "/{word}")
    public VocabularyData query(@PathVariable  String word){
        return this.service.query(word);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void upload(@RequestBody List<VocabularyData> trucks) throws Exception {
        this.service.upload(trucks);
    }

    @PutMapping
    public void updata(@RequestBody List<VocabularyData> trucks) throws Exception {
        this.service.updata(trucks);
    }

    @DeleteMapping
    public void purge() {
        this.service.purge();
    }

    @DeleteMapping(value = "/{word}" )
    public void purge(@PathVariable String word) {
        this.service.purge(word);
    }
}
