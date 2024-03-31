package com.jrn.journalapp.repo;

import com.jrn.journalapp.entity.User;
import com.jrn.journalapp.entity.journal_entries;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface Userrepo extends MongoRepository <User, ObjectId> {
 User findByusername(String username);
 void deleteByUsername(String username);
}
//controller->service->repo