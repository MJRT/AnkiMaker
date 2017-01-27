package tech.jinhaoma.AnkiMaker.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import tech.jinhaoma.AnkiMaker.common.MongoGeneratedValue;

import javax.persistence.Id;

/**
 * Created by mjrt on 1/27/2017.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "sequence")
public class SequenceId {

    @Id
    @MongoGeneratedValue
    private String id;

    @Field("seq_id")
    private long seqId;

    @Field("coll_name")
    private String collName;

}
