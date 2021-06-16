package com.beckman.offers;

import com.beckman.offers.exception.UserNotFoundException;
import com.beckman.offers.model.Account;
import com.beckman.offers.model.User;
import com.beckman.offers.service.UserAdderService;
import com.beckman.offers.service.UserGetterService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class UserGetterTest {

    public static final String USERNAME_MOCK = "nazywam";

    private UserGetterService userGetterService;
    private UserAdderService userAdderService;
    @InjectMocks
    private MongoTemplate mongoTemplate;

    @BeforeAll
    public void setUp(){
        this.userGetterService = new UserGetterService(this.mongoTemplate);
        this.userAdderService = new UserAdderService(this.mongoTemplate);
        AtomicLong count = new AtomicLong(1);
        AtomicInteger age = new AtomicInteger(19);
        this.userAdderService.insertUser(aUser(count.get(),age.get()));
        count.incrementAndGet();
        age.incrementAndGet();
        this.userAdderService.insertUser(aUser(count.get(),age.get()));
    }

    @Test
    void shouldReturnAUserWhenUsernameIsNaziwam(){
        assertNotNull(this.userGetterService.findSingleByUserName(USERNAME_MOCK));
    }

    @Test
    void shouldThrowUserNotFoundExceptionWhenUserNotFound(){
        assertThrows(UserNotFoundException.class, () -> {
            this.userGetterService.findSingleByUserName(USERNAME_MOCK);
        });
    }

    private User aUser(long userCount, int age){
        User user = new User();
        user.setId(userCount);
        user.setUserId(USERNAME_MOCK);
        user.setAccount( new Account());
        user.setAge(age);
        user.setId(userCount);
        return new User();
    }

}
