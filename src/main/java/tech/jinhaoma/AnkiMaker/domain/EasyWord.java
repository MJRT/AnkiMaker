package tech.jinhaoma.AnkiMaker.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import tech.jinhaoma.AnkiMaker.utils.MongoGeneratedValue;

/**
 * Created by mjrt on 5/28/2017.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "EasyWord")
public class EasyWord {

    @Id
    @MongoGeneratedValue
    Long id;
    String word;

    public EasyWord(String word) {
        this.word = word;
    }
}
