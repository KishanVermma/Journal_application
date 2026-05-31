package com.kishan.mongo.service;

import com.kishan.mongo.model.Journal;
import com.kishan.mongo.model.User;
import com.kishan.mongo.repository.journalRepo;
import com.kishan.mongo.repository.userRepo;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class journalService {

    @Autowired
    journalRepo repo;
    @Autowired
    userRepo repo1;
    @Transactional
    public void add(Journal journal, String userName) {
        try{
            journal.setDate(LocalDateTime.now());
            User usrInDb = repo1.findByUsername(userName);
            Journal saved = repo.save(journal);
            usrInDb.getJournalEntry().add(saved);
            repo1.save(usrInDb);
        } catch (Exception e) {
            System.out.println(e);
            throw new RuntimeException("the error occurred while saving the entity",e);
        }

    }
    public void add(Journal journal) {
        repo.save(journal);
    }

    public List<Journal> getAll() {
        return repo.findAll();
    }

    public Optional<Journal> findById(ObjectId myid) {
        return repo.findById(myid);
    }
    @Transactional
    public boolean deleteByid(ObjectId myid, String username) {
        boolean removed=false;
        try{
            User usrInDb = repo1.findByUsername(username);
            removed = usrInDb.getJournalEntry().removeIf(x -> x.getId().equals(myid));
            if (removed) {
                repo1.save(usrInDb);
                repo.deleteById(myid);
            }
        } catch (Exception e) {
            log.error("ERROR",e);
            throw new RuntimeException("error occured while deleting an entry"+e);
        }
        return removed;

    }
}
