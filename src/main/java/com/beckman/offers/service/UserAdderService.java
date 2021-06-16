package com.beckman.offers.service;

import com.beckman.offers.exception.UserExistingInDatabaseException;
import com.beckman.offers.exception.UserNotFoundException;
import com.beckman.offers.model.Account;
import com.beckman.offers.model.User;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;

@Service
public class UserAdderService {

    private static final String USER_ID = "userId";
    private static final String ACCOUNT = "account";

    private final MongoTemplate mongoTemplate;

    public UserAdderService(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public CompletableFuture<User> insertUser(User user) {
        return CompletableFuture.supplyAsync(() -> this.mongoTemplate.insert(user));
    }

    @Async
    public CompletableFuture<DeleteResult> removeUser(User user) {
        Query query = new Query()
                .addCriteria(Criteria.where(USER_ID).is(user.getUserId()));
        return CompletableFuture.completedFuture(this.mongoTemplate.remove(query, User.class));
    }

    @Async
    public CompletableFuture<UpdateResult> updateUser(Account account) throws UserNotFoundException{
        Query query = new Query()
                .addCriteria(Criteria.where(USER_ID).is(account.getUsername()));
        Update update = Update.update(ACCOUNT, account);
        List<User> results = this.mongoTemplate.find(query, User.class);
        if(CollectionUtils.isEmpty(results))
            throw new UserNotFoundException("User not found exception");
        return CompletableFuture.completedFuture(this.mongoTemplate.updateMulti(query,
                update, User.class));
    }

    @Async
    public CompletableFuture<User> addUser(User user) throws UserExistingInDatabaseException{
        Query query = new Query()
                .addCriteria(Criteria.where(USER_ID).is(user.getUserId()));
        if(Objects.nonNull(this.mongoTemplate.findOne(query,User.class)))
            throw new UserExistingInDatabaseException("User exists in database");
        return CompletableFuture.completedFuture(this.mongoTemplate.insert(user));
    }
}
