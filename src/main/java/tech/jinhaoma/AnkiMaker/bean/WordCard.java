package tech.jinhaoma.AnkiMaker.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by mjrt on 1/19/2017.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WordCard {

    private Long id;
    private String word;
    private String aps;
    private String meanEnglish;
    private String meanChinses;
    private String sentence;
    private String note;

}
