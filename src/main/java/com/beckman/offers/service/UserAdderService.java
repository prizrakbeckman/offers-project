package com.beckman.offers.service;

import com.beckman.offers.exception.UserNotFoundException;
import com.beckman.offers.model.Account;
import com.beckman.offers.model.User;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.concurrent.CompletableFuture;

@Service
public class UserAdderService {

    private final MongoTemplate mongoTemplate;

    public UserAdderService(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public CompletableFuture<User> insertUser(User user) {
        return CompletableFuture.supplyAsync(() -> this.mongoTemplate.insert(user));
    }


    public CompletableFuture<DeleteResult> removeUser(User user) {
        Query query = new Query()
                .addCriteria(Criteria.where("userId").is(user.getUserId()));
        return CompletableFuture.completedFuture(this.mongoTemplate.remove(query, User.class));
    }

    public CompletableFuture<UpdateResult> updateUser(Account account) throws Exception{
        Query query = new Query()
                .addCriteria(Criteria.where("userId").is(account.getDateCreation()));
        Update update = Update.update("account", account);
        if(Objects.isNull(this.mongoTemplate.find(query, User.class)))
            throw new UserNotFoundException("User not found exception");
        return CompletableFuture.completedFuture(this.mongoTemplate.updateMulti(query,
                update, User.class));
    }
}
