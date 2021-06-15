package com.beckman.offers.controller;

import com.beckman.offers.exception.FailedAccountInsertionException;
import com.beckman.offers.model.Account;
import com.beckman.offers.model.User;
import com.beckman.offers.service.UserAdderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@Validated
public class UserAdderController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserAdderController.class);
    private static final String CREATED = "CREATED";

    private final UserAdderService userAdderService;

    public UserAdderController(UserAdderService userAdderService){
        this.userAdderService = userAdderService;
    }

    @PostMapping("/user/addAccount")
    @ResponseStatus(HttpStatus.CREATED)
    public String addAccountToUser(@RequestBody @Valid Account account) {
        LOGGER.debug("Account to be logged with id : "+ account.getAccountId());
        try{

            this.userAdderService.updateUser(account);
            return CREATED;
        }catch(Exception e){
            throw new FailedAccountInsertionException("Failed account creation exception");
        }

    }

    @PostMapping("/user/adduser")
    @ResponseStatus(HttpStatus.CREATED)
    public String addUser(@RequestBody @Valid User user){
        LOGGER.debug("Account to be logged with id : "+ user.getUserId());
        try{

            this.userAdderService.addUser(user);
            return CREATED;
        }catch(Exception e){
            throw new FailedAccountInsertionException("Failed account creation exception");
        }

    }

}
