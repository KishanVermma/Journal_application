package com.kishan.mongo.service;

import com.kishan.mongo.repository.userRepo;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class userServiceTest {
    @Autowired
    userRepo repo;
    @Autowired
    userService service;
    @Test
    public void testFindByUsername(){
        assertNotNull(repo.findByUsername("manav"));
    }
    @Test
    public void testDeleteByUsername(){
        assertTrue(5>3);
    }
    @ParameterizedTest
    @CsvSource({
            "kishan",
            "manav"
    })
    public void testUsername(String name){
        assertNotNull(repo.findByUsername(name));
    }

    @Disabled
    @ParameterizedTest
    @CsvSource({
            "1,1,2",
            "2,10,12",
            "3,3,9"
    })
    public void test(int a,int b,int expected){
        assertEquals(expected,a+b);
    }
}
