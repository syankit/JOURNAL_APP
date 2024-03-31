package com.jrn.journalapp.controller;

import com.jrn.journalapp.api.WeatherResponse;
import com.jrn.journalapp.entity.User;
import com.jrn.journalapp.entity.journal_entries;
import com.jrn.journalapp.repo.Userrepo;
import com.jrn.journalapp.service.Journalentryservice;
import com.jrn.journalapp.service.Userservice;
import com.jrn.journalapp.service.Weatherservice;
import org.bson.types.ObjectId;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@Controller
@RequestMapping(path="/user") // class pe mapping url
public class Usercontroller {
    @Autowired
    private Userservice userservice;//dependency injection connection to service
    @Autowired
    private Userrepo userrepo;
    @Autowired
    private Weatherservice weatherservice;

//      @GetMapping
//      public List<User> getAll(){
//     return userservice.getall();
//      }
  @PostMapping
    public void createEntry(@RequestBody User user){
      userservice.saveNewUser(user);
  }
    @PutMapping
    public ResponseEntity<?> updateUser(@RequestBody User user) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        User userInDb = userservice.findByusername(userName);
        userInDb.setUsername(user.getUsername());
        userInDb.setPassword(user.getPassword());
        userservice.saveNewUser(userInDb);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

//  @PutMapping
//    public ResponseEntity<?> updateUser(@RequestBody User user){
//      Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//      String usern= authentication.getName();
//      User userInDb = userservice.findByusername(usern);
//
////      return user.getUsername();
//      User oldUser=userInDb;
//      if(userInDb!=null){
//          userInDb.setUsername(user.getUsername());
//          userInDb.setPassword(user.getPassword());
//          //userservice.saveEntry(userInDb);
//          userservice.updateEntry(oldUser,userInDb);
//      }
////      //return userInDb;
//     return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//  }
    @DeleteMapping
    public ResponseEntity<?> deleteUserById() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        userrepo.deleteByUsername(authentication.getName());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @GetMapping
    public ResponseEntity<?> greeting() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        WeatherResponse weatherResponse = weatherservice.getWeather("Mumbai");
        String greeting = "";
        if (weatherResponse != null) {
            greeting = ", Weather feels like " + weatherResponse.getCurrent().getFeelslike();
        }
        return new ResponseEntity<>("Hi " + authentication.getName() + greeting, HttpStatus.OK);
    }
}
