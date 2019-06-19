package com.home.dandrusiv.accounting.models;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class AccountingContext {
    @Id
    String id;
    String name;
    Income income;
    Outlay outlay;
    List<User> userList;
}
