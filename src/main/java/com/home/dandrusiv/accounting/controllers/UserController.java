package com.home.dandrusiv.accounting.controllers;

import com.home.dandrusiv.accounting.models.User;
import com.home.dandrusiv.accounting.repositories.UserRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    UserRepositoryImpl repository;

    @GetMapping("/name/{firstName}")
    List<User> findByFirstName(@PathVariable String firstName){
        return repository.findByFirstName(firstName);
    }


}
