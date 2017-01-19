package tech.jinhaoma.AnkiMaker.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jsoup.nodes.Element;

/**
 * Created by mjrt on 1/17/2017.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VocabularyData {

    private String word;
    private String shortExplain ;
    private String longExplain ;


}
