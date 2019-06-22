package com.home.dandrusiv.accounting.repositories;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import com.home.dandrusiv.accounting.models.User;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
@DirtiesContext
public class UserRepositoryImplTest {

    @Autowired
    private UserRepository repository;

    private User preparedUser;

    @Before
    public void setup() {
        preparedUser = prepareUser("test@email.com", "TestName", "TestLastName");
    }

    @After
    public void tearDown() {
        repository.deleteAll();
    }

    @Test
    public void testCreateUser() {
        User createdUser = repository.create(preparedUser);

        assertThat(preparedUser).isEqualToIgnoringGivenFields(createdUser, "id");
    }

    @Test
    public void testUpdateUser() {
        User createdUser = repository.create(preparedUser);
        createdUser.setEmail("other@email.com");

        User updatedUser = repository.update(createdUser);
        List<User> userList = repository.findAll();

        assertThat(userList.size()).isEqualTo(1);
        assertThat(createdUser).isEqualTo(updatedUser);
    }

    @Test
    public void testGetById() {
        User createdUser = repository.create(preparedUser);
        User userById = repository.getById(createdUser.getId());

        assertThat(createdUser).isEqualTo(userById);
    }

    @Test
    public void testGetByEmail() {
        User createdUser = repository.create(preparedUser);
        User userByEmail = repository.getByEmail(createdUser.getEmail());

        assertThat(createdUser).isEqualTo(userByEmail);
    }

    @Test
    public void testFindByFirstName() {
        User createdUser = repository.create(preparedUser);
        List<User> userList = repository.findByFirstName(createdUser.getFirstName());

        assertThat(userList).hasSize(1);
        assertThat(userList).contains(createdUser);
    }

    @Test
    public void testFindByLastName() {
        User createdUser = repository.create(preparedUser);
        List<User> userList = repository.findByLastName(createdUser.getLastName());

        assertThat(userList).hasSize(1);
        assertThat(userList).contains(createdUser);
    }

    @Test
    public void testDeleteUser() {
        User createdUser = repository.create(preparedUser);
        repository.delete(createdUser.getId());

        List<User> userList = repository.findAll();

        assertThat(userList).isEmpty();
    }

    private User prepareUser(String email, String name, String lastName) {
        User user = new User();
        user.setEmail(email);
        user.setFirstName(name);
        user.setLastName(lastName);
        user.setPassword("************");

        return user;
    }

}