package com.jrn.journalapp.controller;

import com.jrn.journalapp.entity.User;
import com.jrn.journalapp.service.Userservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@Controller
@RequestMapping("/public")
public class PublicController {

    @Autowired
    private Userservice userservice;
    @GetMapping("/health-check")
    public String healthCheck() {
        return "Ok";
    }

    @PostMapping("/create-user")
    public void createUser(@RequestBody User user) {
        userservice.saveNewUser(user);
    }
}
