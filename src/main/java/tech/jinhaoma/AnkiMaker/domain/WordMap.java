package tech.jinhaoma.AnkiMaker.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import tech.jinhaoma.AnkiMaker.common.MongoGeneratedValue;

import javax.persistence.Id;

/**
 * Created by mjrt on 2/8/2017.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "WordMap")
public class WordMap {

    @Id
    @MongoGeneratedValue
    private long id;

    private String key;
    private String value;

    public WordMap(String key, String value) {
        this.key = key;
        this.value = value;
    }
}
