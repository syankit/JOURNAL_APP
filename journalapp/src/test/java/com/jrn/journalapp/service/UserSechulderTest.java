package com.jrn.journalapp.service;


import com.jrn.journalapp.scheduler.UserScheduler;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
    public class UserSechulderTest {

        @Autowired
        private UserScheduler userScheduler;


        @Test
        public void testFetchUsersAndSendSaMail(){
            userScheduler.fetchUsersAndSendSaMail();
        }
    }
