package com.home.dandrusiv.accounting.models;

import lombok.Data;
import org.springframework.stereotype.Component;

import javax.persistence.Id;

@Data
@Component
public class User {
    @Id
    String id;
    String email;
    String password;
    String firstName;
    String lastName;
    Enum role;
}
