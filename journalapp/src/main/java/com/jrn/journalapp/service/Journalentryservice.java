package com.jrn.journalapp.service;

import com.jrn.journalapp.entity.User;
import com.jrn.journalapp.entity.journal_entries;
import com.jrn.journalapp.repo.Journalentryrepo;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;



@Service
@Slf4j
public class Journalentryservice {
    @Autowired
    private Journalentryrepo journalentryrepo;//dependency injection service->repo
    @Autowired
    private Userservice userservice;

    @Transactional
    public void saveEntry(journal_entries journalentry, String username){
        try {
            User user = userservice.findByusername(username);
            User olduser = user;
            journalentry.setDate(LocalDateTime.now());
            journal_entries saved = journalentryrepo.save(journalentry);
            (user.getJournal_entriesList()).add(saved);
            userservice.updateEntry(olduser, user);
            //userservice.saveEntry(user);
        }catch(Exception e){
            System.out.println(e);
            throw  new RuntimeException("error",e);
        }

        //return user;
    }
    public void saveEntry(journal_entries journalentry){
        journalentryrepo.save(journalentry);
    }
    public List<journal_entries> getall(){
        return journalentryrepo.findAll();
    }
    public Optional<journal_entries> findById(ObjectId id) {
        return journalentryrepo.findById(id);
    }
    @Transactional
    public boolean deleteById(ObjectId id, String username) {
        boolean removed = false;
        try {
            User user = userservice.findByusername(username);
            User old=user;
            removed = user.getJournal_entriesList().removeIf(x -> x.getId().equals(id));
            if (removed) {
                userservice.updateEntry(old,user);
                journalentryrepo.deleteById(id);
            }
        } catch (Exception e) {
            log.error("Error ",e);
            throw new RuntimeException("An error occurred while deleting the entry.", e);
        }
        return removed;
    }
}
