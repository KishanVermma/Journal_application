package com.kishan.mongo.controller;

import com.kishan.mongo.cache.AppCache;
import com.kishan.mongo.model.Journal;
import com.kishan.mongo.model.User;
import com.kishan.mongo.service.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private userService service;
    @Autowired
    private AppCache appCache;
    @GetMapping("/all-users")
    public ResponseEntity<?> getAll(){
        List<User> all=service.getAll();
        if(all!=null && !all.isEmpty()){
            return new ResponseEntity<>(all, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @PostMapping("/create-admin-user")
    public void createAdmin(@RequestBody User user){
        service.addAdmin(user);
    }
    @GetMapping("/clear-app-cache")
    public void clearCache(){
        appCache.init();
    }
}
