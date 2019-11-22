package com.home.dandrusiv.accounting.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@Document
public class Item {
    @Id
    String id;
    String categoryId;
    String name;
    String value;
    Date date;
}
