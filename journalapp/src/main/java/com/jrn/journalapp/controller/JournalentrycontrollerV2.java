package com.jrn.journalapp.controller;

import com.jrn.journalapp.entity.User;
import com.jrn.journalapp.entity.journal_entries;
import com.jrn.journalapp.service.Journalentryservice;
import com.jrn.journalapp.service.Userservice;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path="/journal") // class pe mapping url
public class JournalentrycontrollerV2 {
    @Autowired
    private Journalentryservice journalentryservice;//dependency injection connection to service
    @Autowired
    private Userservice userservice;
    @GetMapping
    public ResponseEntity<?> getAllJournalEntriesOfUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = userservice.findByusername(username);
        List<journal_entries> all = user.getJournal_entriesList();
        if (all != null && !all.isEmpty()) {
            return new ResponseEntity<>(all, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<journal_entries> createEntry(@RequestBody journal_entries myEntry) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String username = authentication.getName();
            journalentryservice.saveEntry(myEntry, username);
            return new ResponseEntity<>(myEntry, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(path="id/{myid}")
    public ResponseEntity<?> getJid(@PathVariable ObjectId myid){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user=userservice.findByusername(username);
        //checking whether this user is prsent in our jounayentries or not
        List<journal_entries> collect = user.getJournal_entriesList().stream().filter(x -> x.getId().equals(myid)).collect(Collectors.toList());
        if (!collect.isEmpty()) {
            Optional<journal_entries> journalEntry = journalentryservice.findById(myid);
            if (journalEntry.isPresent()) {
                return new ResponseEntity<>(journalEntry.get(), HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @DeleteMapping(path="id/{myid}")
    public ResponseEntity<?> deleteJid(@PathVariable ObjectId myid){//wildcard pattern->koi aur calss ka obj bhi return kr sakte
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        boolean removed = journalentryservice.deleteById(myid, username);
        if (removed) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(path="id/{myid}")
    public ResponseEntity<?> updateJid(@PathVariable ObjectId myid,@RequestBody journal_entries newEntry){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        User user = userservice.findByusername(userName);
        List<journal_entries> collect = user.getJournal_entriesList().stream().filter(x -> x.getId().equals(myid)).collect(Collectors.toList());
        if (!collect.isEmpty()) {
            Optional<journal_entries> journalEntry = journalentryservice.findById(myid);
            if (journalEntry.isPresent()) {
                journal_entries old = journalEntry.get();
                old.setTitle(newEntry.getTitle() != null && !newEntry.getTitle().equals("") ? newEntry.getTitle() : old.getTitle());
                old.setContent(newEntry.getContent() != null && !newEntry.getContent().equals("") ? newEntry.getContent() : old.getContent());
                journalentryservice.saveEntry(old);
                return new ResponseEntity<>(old, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
