package tn.esprit.notificationmodule.services;

import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import tn.esprit.notificationmodule.entities.DatabaseSequence;

/**
 * Service class for generating sequence numbers.
 */
@Service
public class SequenceGeneratorService {

    private final MongoOperations mongoOperations;

    /**
     * Constructs a SequenceGeneratorService with the specified MongoOperations.
     * @param mongoOperations The MongoOperations instance.
     */
    public SequenceGeneratorService(MongoOperations mongoOperations) {
        this.mongoOperations = mongoOperations;
    }

    /**
     * Generates a sequence number for the given sequence name.
     * @param seqName The name of the sequence.
     * @return The generated sequence number.
     */
    public long generateSequence(String seqName) {
        DatabaseSequence counter = mongoOperations.findAndModify(query(where("_id").is(seqName)),
                new Update().inc("seq", 1), options().returnNew(true).upsert(true),
                DatabaseSequence.class);
        return (counter != null) ? counter.getSeq() : 1;
    }
}