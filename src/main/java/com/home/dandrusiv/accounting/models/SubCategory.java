package com.home.dandrusiv.accounting.models;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Document
public class SubCategory {
    @Id
    String id;
    String name;
    List<Item> itemList;
    List<Category> categoryList;
}
