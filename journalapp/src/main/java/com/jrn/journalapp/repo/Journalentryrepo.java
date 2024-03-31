package com.jrn.journalapp.repo;

import com.jrn.journalapp.entity.journal_entries;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface Journalentryrepo extends MongoRepository <journal_entries, ObjectId> {

}
//controller->service->repo