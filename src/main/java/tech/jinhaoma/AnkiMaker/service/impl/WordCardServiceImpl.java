package tech.jinhaoma.AnkiMaker.service.impl;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import tech.jinhaoma.AnkiMaker.domain.*;
import tech.jinhaoma.AnkiMaker.service.BingService;
import tech.jinhaoma.AnkiMaker.service.MerriamWebsterService;
import tech.jinhaoma.AnkiMaker.service.VocabularyService;
import tech.jinhaoma.AnkiMaker.service.WordCardService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mjrt on 1/30/2017.
 */
@Service
@Log4j2
public class WordCardServiceImpl extends CurdServiceImpl<WordCard,WordCardRepository> implements WordCardService{


    @Autowired
    BingService bingService;
    @Autowired
    MerriamWebsterService merriamWebsterService;
    @Autowired
    VocabularyService vocabularyService;

    @Autowired
    public WordCardServiceImpl(WordCardRepository repository ,
                               BingService bingServiceImpl,
                               MerriamWebsterService merriamWebsterServiceImpl,
                               VocabularyService vocabularyServiceImpl) {
        super(repository);

        this.bingService = bingService;
        this.merriamWebsterService = merriamWebsterService;
        this.vocabularyService = vocabularyService;
    }

    @Override
    public Page<WordCard> queryAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public List<WordCard> query(String word) {

        List<WordCard> datas = repository.findByWord(word);

        if (datas != null && datas.size() != 0){
            return datas;
        }

        BingData bingData = bingService.query(word);
        VocabularyData vocabularyData = vocabularyService.query(word);
        MerriamWebsterData merriamWebsterData = merriamWebsterService.query(word);

        List<WordCard> cards = WordCard.install(bingData,vocabularyData,merriamWebsterData);
        repository.save(cards);

        return cards;
    }

    @Override
    public List<WordCard> batchQuery(List<String> words) {

        List<String> Offline = new ArrayList<>();
        List<WordCard> cards = new ArrayList<>();

        for(String word : words){
            List<WordCard> datas = repository.findByWord(word);

            if (datas != null && datas.size() != 0){
                cards.addAll(datas);
            } else {
                Offline.add(word);
            }
        }

        List<BingData> bingDatas = bingService.batchQuery(Offline);
        List<VocabularyData> vocabularyDatas = vocabularyService.batchQuery(Offline);
        List<MerriamWebsterData> merriamWebsterDatas = merriamWebsterService.batchQuery(Offline);

        for(int i = 0 ; i < Offline.size() ; i++){
            cards.addAll(WordCard.install(bingDatas.get(i),vocabularyDatas.get(i),merriamWebsterDatas.get(i)));
        }

        repository.save(cards);
        return cards;
    }

    @Override
    public Page<WordCard> query(String word, Pageable pageable){
        Page<WordCard> cards = repository.findByWord(word,pageable);

        if (cards.getTotalElements() == 0){
            query(word);
            return repository.findByWord(word,pageable);
        } else {
            return cards;
        }
    }

    @Override
    public void purge(String word) {
        List<WordCard> datas = repository.findByWord(word);
        if(datas != null && datas.size() != 0)
            repository.delete(datas);
    }
}
