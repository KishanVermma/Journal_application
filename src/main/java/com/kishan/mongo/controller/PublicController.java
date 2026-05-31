package com.kishan.mongo.controller;

import com.kishan.mongo.model.User;
import com.kishan.mongo.service.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/public")
public class PublicController {
    @Autowired
    private userService service;
    @PostMapping("/create-user")
    public User createUser(@RequestBody User user){
        return service.add(user);
    }

}
