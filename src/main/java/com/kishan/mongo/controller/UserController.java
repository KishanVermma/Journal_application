package com.kishan.mongo.controller;

import com.kishan.mongo.api.response.QuoteResponse;
import com.kishan.mongo.model.Journal;
import com.kishan.mongo.model.User;
import com.kishan.mongo.repository.userRepo;
import com.kishan.mongo.service.QuoteService;
import com.kishan.mongo.service.WeatherService;
import com.kishan.mongo.service.journalService;
import com.kishan.mongo.service.userService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private userService service;
    @Autowired
    userRepo repo;
    @Autowired
    private WeatherService weatherService;
    @Autowired
    private QuoteService quoteService;
    private static final PasswordEncoder enco=new BCryptPasswordEncoder(12);
    @GetMapping("/all")
    public List<User> getAll(){
        return service.getAll();
    }

    @PutMapping
    public ResponseEntity<?> updateUsr(@RequestBody User user){
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User usrIndb=service.findByUsername(username);
        if(usrIndb!=null){
            if (user.getPassword() != null && !user.getPassword().isBlank()) {
                usrIndb.setPassword(enco.encode(user.getPassword()));
            }
            usrIndb.setUsername(user.getUsername());
            service.addNew(usrIndb);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @DeleteMapping
    public ResponseEntity<?> delete(){
        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
        repo.deleteByUsername(authentication.getName());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }
    @GetMapping
    public ResponseEntity<?> greeting(){
        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
        return new ResponseEntity<>("Hi "+authentication.getName()+" weather feels like "+weatherService.getWeather("Mumbai").getCurrent().getFeelslikeC()+"quote is:"+quoteService.getQuote().getQuote(),HttpStatus.OK);

    }
    @PostMapping("thrr")
    public ResponseEntity<?> neww(){
        return new ResponseEntity<>(quoteService.getUser(),HttpStatus.OK);
    }

}
