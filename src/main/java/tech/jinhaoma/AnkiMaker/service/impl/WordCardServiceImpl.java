package tech.jinhaoma.AnkiMaker.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import tech.jinhaoma.AnkiMaker.domain.*;
import tech.jinhaoma.AnkiMaker.service.BingService;
import tech.jinhaoma.AnkiMaker.service.MerriamWebsterService;
import tech.jinhaoma.AnkiMaker.service.VocabularyService;
import tech.jinhaoma.AnkiMaker.service.WordCardService;

import java.util.List;

/**
 * Created by mjrt on 1/30/2017.
 */
@Service
public class WordCardServiceImpl extends CurdServiceImpl<WordCard,WordCardRepository> implements WordCardService{


    BingService bingService;
    MerriamWebsterService merriamWebsterService;
    VocabularyService vocabularyService;

    @Autowired
    public WordCardServiceImpl(WordCardRepository repository ,
                               @Autowired BingService bingServiceImpl,
                               @Autowired MerriamWebsterService merriamWebsterServiceImpl,
                               @Autowired VocabularyService vocabularyServiceImpl) {
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
            System.out.println(datas.toString());
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
