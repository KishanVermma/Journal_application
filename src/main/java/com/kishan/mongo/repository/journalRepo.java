package com.kishan.mongo.repository;

import com.kishan.mongo.model.Journal;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface journalRepo extends MongoRepository<Journal, ObjectId> {
}
