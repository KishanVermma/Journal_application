package com.kishan.mongo.repository;

import com.kishan.mongo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class UserRepositoryImpl {
    @Autowired
    private MongoTemplate mongoTemplate;

    public List<User> getUserforSA(){
        Query query=new Query();
        Criteria criteria=new Criteria();
        //the following two also evaluates to and.
//        query.addCriteria(Criteria.where("username").is("prashant"));
//        query.addCriteria(Criteria.where("sentimentAnalysis").is(true));
//        query.addCriteria(criteria.orOperator(Criteria.where("username").is("prashant"),Criteria.where("sentimentAnalysis").is(true)));
        query.addCriteria(criteria.andOperator(Criteria.where("email")
                .regex("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$"),
                Criteria.where("sentimentAnalysis").is(true)));
        List<User> users=mongoTemplate.find(query, User.class);
        return users;
    }
}
