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

    private String word;
    private String aps;
    private String meanOfEnglish;
    private String meanOfChinses;
    private String sentence;
    private String note;
}
