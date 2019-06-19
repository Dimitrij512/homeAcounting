package com.home.dandrusiv.accounting.models;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class Category {
    @Id
    String id;
    String name;
    String budget;
    List<Item> itemList;
    List<SubCategory> subCategoryList;
}
