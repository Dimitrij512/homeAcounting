package com.home.dandrusiv.accounting.repositories;

import com.home.dandrusiv.accounting.models.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest
@DirtiesContext
public class UserRepositoryImplTest {

    @Autowired
    UserRepositoryImpl repository;

    @Test
    public void testCreateUser() {
        User preparedUser = prepareUser("test@email.com");
        User createdUser = repository.create(preparedUser);

        assertThat(preparedUser).isEqualToIgnoringGivenFields(createdUser, "id");
    }

    @Test
    public void testUdateUser() {
        User createdUser = repository.create(prepareUser("test@email.com"));
        createdUser.setEmail("other@email.com");

        User updatedUser = repository.update(createdUser);

        assertThat(createdUser).isEqualTo(updatedUser);
    }


    private User prepareUser(String email){
        User user = new User();
        user.setEmail(email);
        user.setFirstName("Test");
        user.setLastName("TestLastName");
        user.setPassword("************");

        return user;
    }

}