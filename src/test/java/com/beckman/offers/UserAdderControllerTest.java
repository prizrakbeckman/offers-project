package com.beckman.offers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;


import com.beckman.offers.controller.UserAdderController;
import com.beckman.offers.exception.UserNotFoundException;
import com.beckman.offers.model.Account;
import com.beckman.offers.model.User;
import com.beckman.offers.service.UserAdderService;
import com.beckman.offers.service.UserGetterService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

@SpringBootTest
public class UserAdderControllerTest {


    private UserAdderController userAdderController;

    @InjectMocks
    private UserGetterService userGetterService;

    @InjectMocks
    private UserAdderService userAdderService;

    @InjectMocks
    private MongoTemplate mongoTemplate;


    @BeforeEach
    void setUp() {
        AtomicLong count = new AtomicLong(1);
        AtomicInteger age = new AtomicInteger(19);
        this.userAdderService.insertUser(aUser(count.get(), age.get()));
        count.incrementAndGet();
        age.incrementAndGet();
        pauseSeconds(3);
        this.userAdderService.insertUser(aUser(count.get(), age.get()));
        count.incrementAndGet();
        age.incrementAndGet();
        pauseSeconds(3);
        this.userAdderService.insertUser(aUser(count.get(), age.get()));
        count.incrementAndGet();
        age.incrementAndGet();
        pauseSeconds(3);
    }

    @Test
    void shouldReturnCREATEDWhenUsersUriCaulled() {
        String result = this.userAdderController.addAccountToUser(anAccount());
        pauseSeconds(3);
        assertEquals("CREATED", result);
    }


    @Test
    void shouldReturnErrorDWhenUsersUriCalledAndUserNotFound() {
        User u = aUser(1L,19);
        given(this.userGetterService.findSingleByUserName(u.getUserId()))
                .willThrow(new UserNotFoundException("User Not found"));
        assertThrows(UserNotFoundException.class, () ->
                this.userAdderController.addAccountToUser(anAccount()));
    }

    private void pauseSeconds(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private Account anAccount(){
        Account account = new Account();
        account.setAccountId("accoundId");
        account.setEmail("account@tt.com");

        return account;
    }

    private User aUser(long userCount, int age) {
        User user = new User();
        user.setUserId("user1" + userCount);
        user.setAccount(new Account());
        user.setAge(age);
        user.setId(userCount);
        return new User();
    }


}
