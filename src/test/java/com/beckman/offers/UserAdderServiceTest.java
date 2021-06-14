package com.beckman.offers;

import com.beckman.offers.exception.UserUnderEigtheenException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import com.beckman.offers.model.Account;
import com.beckman.offers.model.User;
import com.beckman.offers.service.UserAdderService;
import org.springframework.data.mongodb.core.MongoTemplate;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class UserAdderServiceTest {

    @Mock
    private UserAdderService userAdderService;

    @Mock
    private MongoTemplate mongoTemplate;

    @BeforeAll
    void setUp(){
      System.out.print("initialiazing mock");
    }

    @Test
    public void shouldInsertAccountWhenUserOver18(){
        assertNotNull(this.userAdderService.insertUser(aUser(1L,18)));
    }

    @Test
    public void shouldThrowExceptiontWhenUserlessThanEighteen(){

        assertThrows(UserUnderEigtheenException.class, () ->{
            this.userAdderService.insertUser(aUser(2L,17));
        });
    }


    private User aUser(long userCount, int age){
        User user = new User();
        user.setUserId("user1"+userCount);
        user.setAccount( new Account());
        user.setAge(age);
        user.setId(userCount);
        return new User();
    }

}
