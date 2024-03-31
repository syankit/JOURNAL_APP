package com.jrn.journalapp.service;

import com.jrn.journalapp.entity.User;
import com.jrn.journalapp.entity.journal_entries;
import com.jrn.journalapp.repo.Journalentryrepo;
import com.jrn.journalapp.repo.Userrepo;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;


@Component
@Slf4j // avoid making instance for each call in logger
@Service
public class Userservice {
    @Autowired
    private Userrepo userrepo;//dependency injection service->repo
    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    //private static final Logger logger= LoggerFactory.getLogger(Journalentryservice.class);
    public boolean saveNewUser(User user) {
        try {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setRoles(Arrays.asList("USER"));
            userrepo.save(user);
            return true;
        }catch(Exception e){
//            logger.info("hiii");
//            logger.error("Error occur for {}:",user.getUsername(),e);
              log.error("Error occur for {}:",user.getUsername(),e);
            return false;
        }
    }
    public void saveAdmin(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Arrays.asList("USER", "ADMIN"));
        userrepo.save(user);
    }
    public void saveEntry(User user){
        userrepo.insert(user);
    }
    public void updateEntry(User user1,User user2){
        userrepo.delete(user1);
        userrepo.insert(user2);
    }
    public List<User> getall(){
        return userrepo.findAll();
    }
    public Optional<User> findById(ObjectId id) {
        return userrepo.findById(id);
    }
    public void deleteByid(ObjectId id){
        userrepo.deleteById(id);
    }
    public User findByusername(String username){
        return userrepo.findByusername(username);
    }


}
