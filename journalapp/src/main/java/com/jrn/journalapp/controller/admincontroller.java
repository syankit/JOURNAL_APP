package com.jrn.journalapp.controller;

import com.jrn.journalapp.cache.AppCache;
import com.jrn.journalapp.entity.User;
import com.jrn.journalapp.service.Userservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Controller
@RequestMapping(path="/admin")
public class admincontroller {
    @Autowired
    private Userservice userservice;
    @Autowired
    private AppCache appCache;
    @GetMapping(path="/all-users")

    public ResponseEntity<?> getallUser(){
        List<User> all=userservice.getall();
        if (all != null && !all.isEmpty()) {
            return new ResponseEntity<>(all, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @PostMapping("/create-admin-user")
    public void createUser(@RequestBody User user) {
        userservice.saveAdmin(user);
    }
    @GetMapping("clear-app-cache")
    public void clearAppCache(){
        appCache.init();
    }
}
