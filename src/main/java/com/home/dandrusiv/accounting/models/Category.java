package com.home.dandrusiv.accounting.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document
public class Category {
    @Id
    String id;
    String name;
    String budget;
    List<Item> itemList;
    List<SubCategory> subCategoryList;
}
