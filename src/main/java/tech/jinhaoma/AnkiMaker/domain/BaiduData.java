package tech.jinhaoma.AnkiMaker.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import tech.jinhaoma.AnkiMaker.common.MongoGeneratedValue;


import javax.persistence.*;

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

    @Column(name = "fromLanguage")
    private String from;
    @Column(name = "toLanguage")
    private String to;

    private String word;
    @Column(length = 4095)
    private String mean;

    private List<BaiduResult> trans_result;
}
