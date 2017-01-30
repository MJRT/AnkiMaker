package tech.jinhaoma.AnkiMaker.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tech.jinhaoma.AnkiMaker.common.MongoGeneratedValue;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by mjrt on 1/19/2017.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
//@Entity
//@Table(name = "WordCard")
public class WordCard {

    @Id
    @MongoGeneratedValue
    private Long id;

    private String word;
    private String splitWord;
    private String aps;
    private String meanEnglish;
    private String meanChinses;
    private String explain;
    private String sentence;
    private String note;

    public static List<WordCard> install(BingData bingData,
                                         VocabularyData vocabularyData,
                                         MerriamWebsterData merriamWebsterData) {

        if(bingData == null && vocabularyData == null && merriamWebsterData == null){
            return null;
        }

        ArrayList<WordCard> list = new ArrayList<>();

        String word = null;
        String splitWord = null;
        String aps = null ;
        String explain = null;
        String meanChinses = null ;
        String note = "";

        if(bingData != null){
            word = bingData.getWord();
            aps = bingData.getApsUs();
            meanChinses = bingData.getMeanChinese();
        }

        if(merriamWebsterData != null){
            word = merriamWebsterData.getWord();
            splitWord = merriamWebsterData.getSplitWord();
        }

        if(vocabularyData != null){
            word = vocabularyData.getWord();
            explain = vocabularyData.getShortExplain()+"<br>"+vocabularyData.getLongExplain();
            List<String> means = vocabularyData.getMean();
            for (int j = 0 ; j < means.size() ; j++){
                WordCard card = new WordCard();
                card.setWord(word);
                card.setAps(aps==null?"":aps);
                card.setMeanChinses(meanChinses==null?"":meanChinses);
                card.setSplitWord(splitWord==null?"":splitWord);
                card.setMeanEnglish(vocabularyData.getMean().get(j));
                card.setSentence(vocabularyData.getSentence().get(j));
                card.setExplain(explain);
                card.setNote(note);
                list.add(card);
            }
            return list;
        } else {
            WordCard card = new WordCard();
            card.setWord(word);
            card.setAps(aps==null?"":aps);
            card.setMeanChinses(meanChinses==null?"":meanChinses);
            card.setSplitWord(splitWord==null?"":splitWord);
            list.add(card);
            return list;
        }
    }

    public String toCard(){
        StringBuffer buffer = new StringBuffer();
        buffer.append(word==null?"":word);                 buffer.append('\t');
        buffer.append(splitWord==null?"":splitWord);       buffer.append('\t');
        buffer.append(aps==null?"":aps);                   buffer.append('\t');
        buffer.append(meanEnglish==null?"":meanEnglish);   buffer.append('\t');
        buffer.append(explain==null?"":explain);           buffer.append('\t');
        buffer.append(sentence==null?"":sentence);         buffer.append('\t');
        buffer.append(meanChinses==null?"":meanChinses);   buffer.append('\t');
        buffer.append(note==null?"":note);
        return buffer.toString();
    }
}
