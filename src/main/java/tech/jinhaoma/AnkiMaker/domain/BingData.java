package tech.jinhaoma.AnkiMaker.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import tech.jinhaoma.AnkiMaker.utils.MongoGeneratedValue;


/**
 * Created by mjrt on 1/23/2017.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "BingData")
public class BingData {

    @Id
    @MongoGeneratedValue
    private long id;

    private String word;
    private String apsUs ;
    private String meanChinese;
}
