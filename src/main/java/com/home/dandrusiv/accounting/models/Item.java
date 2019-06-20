package com.home.dandrusiv.accounting.models;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Document
public class Item {
    @Id
    String id;
    String name;
    String value;
    Date date;
}
