package com.home.dandrusiv.accounting.repositories;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.home.dandrusiv.accounting.models.User;

@Repository
public interface UserRepository {
    User create(User user);
    User update(User user);
    User getById(String id);
    User getByEmail(String email);
    List<User> findByFirstName(String firstName);
    List<User> findByLastName(String lastName);
    List<User> findByRole(String role);
}
