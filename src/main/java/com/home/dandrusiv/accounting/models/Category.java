package com.home.dandrusiv.accounting.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document
public class Category {
    @Id
    String id;
    String balanceId;
    String name;
    String budget;
}
