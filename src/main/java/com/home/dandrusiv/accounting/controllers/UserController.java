package com.home.dandrusiv.accounting.controllers;

import com.home.dandrusiv.accounting.models.User;
import com.home.dandrusiv.accounting.repositories.UserRepository;
import com.home.dandrusiv.accounting.repositories.UserRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("user")
public class UserController {

    private final UserRepository repository;

    public UserController(UserRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/name/{firstName}")
    List<User> findByFirstName(@PathVariable String firstName){
        return repository.findByFirstName(firstName);
    }

}
