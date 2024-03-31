package com.jrn.journalapp.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EmailServiceTests {


    @Autowired
    private EmailService emailService;

    //@Disabled
    @Test
    void testSendMail() {
        emailService.sendEmail("syanmarshall10@gmail.com", "Testing Java mail sender", "Hi, aap kaise hain ?");
    }
}