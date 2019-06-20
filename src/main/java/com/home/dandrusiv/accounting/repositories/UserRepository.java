package com.home.dandrusiv.accounting.repositories;

import com.home.dandrusiv.accounting.models.User;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

public interface UserRepository {
    User create(User user);
    User update(User user);
    User getById(String id);
    User getByEmail(String email);
    List<User> findByFirstName(String firstName);
    List<User> findByLastName(String lastName);
    List<User> findByRole(String role);
    void delete(String userId);
}
