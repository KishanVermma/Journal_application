package com.kishan.mongo.repository;

import com.kishan.mongo.model.ConfigJournalAppEntity;
import com.kishan.mongo.model.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface configJournalAppRepo extends MongoRepository<ConfigJournalAppEntity, ObjectId> {

}
