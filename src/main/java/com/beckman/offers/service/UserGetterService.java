package com.beckman.offers.service;


import com.beckman.offers.model.User;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class UserGetterService {

    private final MongoTemplate mongoTemplate;

    public UserGetterService(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Async
    public CompletableFuture<List<User>> findAll() {
        return CompletableFuture.completedFuture(this.mongoTemplate.findAll(User.class));
    }

    @Async
    public CompletableFuture<User> findSingleByUserName(String username) {
        Query query = new Query()
                .addCriteria(Criteria.where("username").is(username));
        return CompletableFuture.supplyAsync(() -> this.mongoTemplate.findOne(query, User.class));
    }

}
