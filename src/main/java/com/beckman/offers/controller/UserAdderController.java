package com.beckman.offers.controller;

import javax.validation.Valid;

import com.beckman.offers.exception.UserUnderEigtheenException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.beckman.offers.exception.FailedAccountInsertionException;
import com.beckman.offers.model.Account;
import com.beckman.offers.service.UserAdderService;

import java.util.Objects;

@RestController
@Validated
public class UserAdderController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserAdderController.class);
    private static String CREATED = "CREATED";

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

}
