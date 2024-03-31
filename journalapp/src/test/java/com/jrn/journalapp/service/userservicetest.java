package com.jrn.journalapp.service;

import com.jrn.journalapp.entity.User;
import com.jrn.journalapp.repo.Userrepo;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class userservicetest {
    @Autowired
    private Userrepo userrepo;
    @Autowired
    private Userservice userservice;

    //@Disabled
    @ParameterizedTest
    @ArgumentsSource(UserArgumentProvider.class)
    public void testSaveNewUser(User user){
         assertNotNull(userservice.saveNewUser(user));
    }

    @Disabled
    @ParameterizedTest
    @CsvSource({
            "1,1,2",
            "2,10,12",
            "3,3,9"
    })
    public void test(int a, int b, int expected){
        assertEquals(expected, a + b);
    }
}
