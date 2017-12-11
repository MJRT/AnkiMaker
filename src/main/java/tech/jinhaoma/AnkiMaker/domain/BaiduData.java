package tech.jinhaoma.AnkiMaker.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import tech.jinhaoma.AnkiMaker.utils.MongoGeneratedValue;


import java.util.List;

/**
 * Created by mjrt on 1/17/2017.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "BaiduData")
public class BaiduData {
    @Id
    @MongoGeneratedValue
    private long id;

    @Field(value = "fromLanguage")
    private String from;
    @Field(value = "toLanguage")
    private String to;

    private String word;
    private String mean;

    private List<BaiduResult> trans_result;
}
