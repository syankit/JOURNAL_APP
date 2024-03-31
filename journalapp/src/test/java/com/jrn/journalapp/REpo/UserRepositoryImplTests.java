package com.jrn.journalapp.REpo;

import com.jrn.journalapp.repo.UserRepoImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserRepositoryImplTests {

    @Autowired
    private UserRepoImpl userRepository;

    @Disabled("tested")
    @Test
    void testSaveNewUser() {
        Assertions.assertNotNull(userRepository.getUserForSA());
    }
}
