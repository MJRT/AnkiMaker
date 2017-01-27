package tech.jinhaoma.AnkiMaker.common;

import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;
import tech.jinhaoma.AnkiMaker.domain.SequenceId;

import javax.annotation.Resource;
import java.lang.reflect.Field;

/**
 * Created by mjrt on 1/27/2017.
 */
@Component
public class SaveMongoEventListener extends AbstractMongoEventListener<Object> {
    @Resource
    private MongoTemplate mongoTemplate;

    @Override
    public void onBeforeConvert(final Object source) {
        if(source != null) {
            ReflectionUtils.doWithFields(source.getClass(), new ReflectionUtils.FieldCallback() {
                public void doWith(Field field) throws IllegalArgumentException, IllegalAccessException {
                    ReflectionUtils.makeAccessible(field);
                    if (field.isAnnotationPresent(MongoGeneratedValue.class)) {
                        field.set(source, getNextId(source.getClass().getSimpleName()));
                    }
                }
            });
        }
    }

    private Long getNextId(String collectionName) {
        Query query = new Query(Criteria.where("collectionName").is(collectionName));
        Update update = new Update();
        update.inc("seqId", 1);
        FindAndModifyOptions options = new FindAndModifyOptions();
        options.upsert(true);
        options.returnNew(true);
        SequenceId seqId = mongoTemplate.findAndModify(query, update, options, SequenceId.class);
        return seqId.getSeqId()-1;
    }

}
