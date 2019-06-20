package com.home.dandrusiv.accounting.models;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
public class Income {
    List<Category> categoryList;
}
