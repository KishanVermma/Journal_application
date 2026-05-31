package com.kishan.mongo.controller;

import com.kishan.mongo.model.Journal;
import com.kishan.mongo.model.User;
import com.kishan.mongo.service.journalService;
import com.kishan.mongo.service.userService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/journal")
public class journalController {
    @Autowired
    private journalService service;
    @Autowired
    private userService serviceu;
    @PostMapping
    public  ResponseEntity<Journal> add(@RequestBody Journal journal){
        try {
            Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
            String userName = authentication.getName();
            service.add(journal,userName);
            return new ResponseEntity<>(journal,HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }
    @GetMapping
    public ResponseEntity<?> getAllJournalEnteriesByUser(){
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        User user = serviceu.findByUsername(userName);
        List<Journal> journalEntry = user.getJournalEntry();
        if(journalEntry!=null && !journalEntry.isEmpty()){
            return new ResponseEntity<>(journalEntry,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @GetMapping("/id/{myid}")
    public ResponseEntity<Journal> findByid(@PathVariable ObjectId myid){
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        User user=serviceu.findByUsername(userName);
        List<Journal> collect=user.getJournalEntry().stream().filter(x->x.getId().equals(myid)).toList();
        if(!collect.isEmpty()){
            Optional<Journal> journalEntry = service.findById(myid);
            if(journalEntry.isPresent()){
                return new ResponseEntity<>(journalEntry.get(), HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @DeleteMapping("/id/{myid}")
    public ResponseEntity<?> deleteById(@PathVariable ObjectId myid){
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        boolean removed = service.deleteByid(myid, userName);
        if(removed){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }
    @PutMapping("/id/{myid}")
    public ResponseEntity<Journal> update(@PathVariable ObjectId myid,@RequestBody Journal neww){
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        User user=serviceu.findByUsername(userName);
        List<Journal> collect=user.getJournalEntry().stream().filter(x->x.getId().equals(myid)).toList();
        if(!collect.isEmpty()){
            Journal old=service.findById(myid).orElse(null);
            if(old!=null) {
                old.setTitle(old.getTitle() != null && !old.getTitle().equals("") ? neww.getTitle() : old.getTitle());
                old.setContent(old.getContent() != null && !old.getContent().equals("") ? neww.getContent() : neww.getContent());
                service.add(old);
                return new ResponseEntity<>(old, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
