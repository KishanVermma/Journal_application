package com.kishan.mongo.service;

import com.kishan.mongo.model.Journal;
import com.kishan.mongo.model.User;
import com.kishan.mongo.repository.journalRepo;
import com.kishan.mongo.repository.userRepo;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class userService {

    //private static final Logger logger= LoggerFactory.getLogger(userService.class);
    @Autowired
    userRepo repo;
    private static final PasswordEncoder passen=new BCryptPasswordEncoder(12);
    public User add(User user) {
        try{
            user.setPassword(passen.encode(user.getPassword()));
            user.setRoles(List.of("ROLE_USER"));
            return repo.save(user);
        } catch (Exception e) {
            log.info("hahah error duplicate name {}:",user.getUsername(),e);
            log.debug("debug enabled");
            return new User();
        }
    }
    public User addAdmin(User user) {
        user.setPassword(passen.encode(user.getPassword()));
        user.setRoles(List.of("ROLE_USER","ROLE_ADMIN"));
        return repo.save(user);
    }
    public User addNew(User user) {

        return repo.save(user);
    }

    public List<User> getAll() {
        return repo.findAll();
    }

    public Optional<User> findById(ObjectId myid) {
        return repo.findById(myid);
    }

    public void deleteByid(ObjectId myid) {
        repo.deleteById(myid);
    }

    public User findByUsername(@NonNull String username) {
        return repo.findByUsername(username);

    }
}
