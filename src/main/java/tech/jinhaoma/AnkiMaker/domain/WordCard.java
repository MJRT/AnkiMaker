package tech.jinhaoma.AnkiMaker.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


/**
 * Created by mjrt on 1/19/2017.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
//@Entity
//@Table(name = "WordCard")
public class WordCard {

//    @Id
//    @GeneratedValue
    private Long id;

    private String word;
    private String splitWord;
    private String aps;
    private String meanEnglish;
    private String meanChinses;
    private String explain;
    private String sentence;
    private String note;

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
