package tech.jinhaoma.AnkiMaker.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import tech.jinhaoma.AnkiMaker.utils.MongoGeneratedValue;

/**
 * Created by mjrt on 1/18/2017.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "MerriamWebsterData")
public class MerriamWebsterData {
    @Id
    @MongoGeneratedValue
    private long id;
    private String word;
    private String splitWord;
}
