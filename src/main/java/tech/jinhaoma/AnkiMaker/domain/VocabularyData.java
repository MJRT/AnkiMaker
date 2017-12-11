package tech.jinhaoma.AnkiMaker.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import tech.jinhaoma.AnkiMaker.utils.MongoGeneratedValue;

import java.util.List;

/**
 * Created by mjrt on 1/17/2017.
 */

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "VocabularyData")
public class VocabularyData {

    @Id
    @MongoGeneratedValue
    private long id;
    private String word;
    private String shortExplain ;
    private String longExplain ;
    private List<String> mean;
    private List<String> sentence;

    @Override
    public String toString() {

        StringBuffer buffer = new StringBuffer();


        for(int i = 0 ; i < mean.size() ; i++){
            buffer.append("mean" + i + "=" + mean.get(i) +"\n");
            buffer.append("sentence"+i+"=" + sentence.get(i) +"\n");
        }

        return "VocabularyData{" +  '\n'+
                "word=" + word + '\n' +
                "shortExplain=" + shortExplain + '\n' +
                "longExplain=" + longExplain + '\n' +
                buffer.toString() +
                '}';
    }
}
