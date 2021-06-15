package com.beckman.offers.controller;

import com.beckman.offers.exception.UserNotFoundException;
import com.beckman.offers.model.User;
import com.beckman.offers.service.UserGetterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;


@RestController
@Validated
public class UserGetterController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserGetterController.class);
    private final UserGetterService userGetterService;

    public UserGetterController(UserGetterService userGetterService){
        this.userGetterService = userGetterService;
    }

    @GetMapping("/user/{username}")
    @ResponseStatus(HttpStatus.OK)
    public User getUser(@PathVariable String username) throws UserNotFoundException {
        LOGGER.debug("Query on user : "+ username);
        return Optional.ofNullable(this.userGetterService.findSingleByUserName(username)
                .join()).orElseThrow(()->new UserNotFoundException("No user found"));
    }

    @GetMapping("/users")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody CompletableFuture<ResponseEntity> getAllUsers() {
        LOGGER.debug("fetching all users");
        return this.userGetterService.findAll().<ResponseEntity>thenApply(ResponseEntity::ok)
                .exceptionally(manageUsersCall);
    }

    private static Function<Throwable, ResponseEntity<? extends List<User>>> manageUsersCall = throwable -> {
        LOGGER.error("Failed to read records: {}", throwable);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    };

}
