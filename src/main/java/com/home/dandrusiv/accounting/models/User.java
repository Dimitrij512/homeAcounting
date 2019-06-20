package com.home.dandrusiv.accounting.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class User {
    @Id
    String id;
    String email;
    String password;
    String firstName;
    String lastName;
    Enum role;
}
